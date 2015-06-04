/*
 * Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ait.tooling.server.hazelcast.pubsub;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ait.tooling.json.JSONObject;
import com.ait.tooling.server.core.pubsub.JSONMessage;
import com.ait.tooling.server.core.pubsub.PubSubChannelType;
import com.hazelcast.core.IQueue;

public class HazelcastQueueSubscribeDescriptor extends AbstractHazelcastSubscribeDescriptor
{
    private static final long        serialVersionUID = -6238196945440514095L;

    private final IQueue<JSONObject> m_queue;

    private final ExecutorService    m_exec           = Executors.newSingleThreadExecutor();

    public HazelcastQueueSubscribeDescriptor(final String name, final IQueue<JSONObject> queue)
    {
        super(Objects.requireNonNull(name), PubSubChannelType.QUEUE);

        setActive(false);

        m_queue = Objects.requireNonNull(queue);
    }

    @Override
    protected synchronized void ping()
    {
        if (getTotalSubscriberCount() > 0)
        {
            if (false == isActive())
            {
                setActive(true);

                final HazelcastQueueSubscribeDescriptor self = this;

                m_exec.execute(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (isActive())
                        {
                            try
                            {
                                JSONObject json = m_queue.poll(30, TimeUnit.SECONDS);

                                if (null != json)
                                {
                                    getSubscribeDescriptorSupport().dispatch(new JSONMessage(json), self);
                                }
                            }
                            catch (Exception e)
                            {
                                logger().error("Something bad happened", e);
                            }
                        }
                    }
                });
                m_exec.shutdown();
            }
        }
    }
}
