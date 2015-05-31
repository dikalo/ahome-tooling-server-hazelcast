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

import com.ait.tooling.common.api.types.Activatible;
import com.ait.tooling.json.JSONObject;
import com.ait.tooling.server.core.pubsub.IPublishDescriptor;
import com.ait.tooling.server.core.pubsub.PubSubChannelType;
import com.hazelcast.core.ITopic;

public class HazelcastTopicPublishDescriptor extends Activatible implements IPublishDescriptor
{
    private static final long        serialVersionUID = -6238196945440514095L;

    private final String             m_name;

    private final ITopic<JSONObject> m_topic;

    public HazelcastTopicPublishDescriptor(final String name, final ITopic<JSONObject> topic)
    {
        m_name = Objects.requireNonNull(name);

        m_topic = Objects.requireNonNull(topic);
    }

    @Override
    public PubSubChannelType getChannelType()
    {
        return PubSubChannelType.TOPIC;
    }

    @Override
    public String getName()
    {
        return m_name;
    }

    @Override
    public JSONObject publish(final JSONObject message) throws Exception
    {
        m_topic.publish(Objects.requireNonNull(message));

        return message;
    }

    @Override
    public void close() throws IOException
    {
        setActive(false);
    }
}
