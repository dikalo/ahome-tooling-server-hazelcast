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

import java.io.IOException;
import java.util.Objects;

import com.ait.tooling.json.JSONObject;
import com.ait.tooling.server.core.pubsub.JSONMessage;
import com.ait.tooling.server.core.pubsub.PubSubChannelType;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class HazelcastTopicSubscribeDescriptor extends AbstractHazelcastSubscribeDescriptor
{
    private static final long        serialVersionUID = -5778616503194903239L;

    private final ITopic<JSONObject> m_topic;

    private final String             m_added;

    public HazelcastTopicSubscribeDescriptor(final String name, final ITopic<JSONObject> topic)
    {
        super(Objects.requireNonNull(name), PubSubChannelType.TOPIC);

        final HazelcastTopicSubscribeDescriptor self = this;

        m_topic = Objects.requireNonNull(topic);

        m_added = m_topic.addMessageListener(new MessageListener<JSONObject>()
        {
            @Override
            public void onMessage(final Message<JSONObject> message)
            {
                if (null != message)
                {
                    final JSONObject json = message.getMessageObject();

                    if (null != json)
                    {
                        try
                        {
                            getSubscribeDescriptorSupport().dispatch(new JSONMessage(json), self);
                        }
                        catch (Exception e)
                        {
                            logger().error("Something bad happened", e);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void close() throws IOException
    {
        m_topic.removeMessageListener(m_added);

        super.close();
    }
}
