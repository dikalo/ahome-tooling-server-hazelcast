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

import java.util.List;
import java.util.Objects;

import com.ait.tooling.server.core.pubsub.AbstractSubscribeDescriptor;
import com.ait.tooling.server.core.pubsub.IMessageReceivedHandler;
import com.ait.tooling.server.core.pubsub.IMessageReceivedHandlerRegistration;
import com.ait.tooling.server.core.pubsub.PubSubChannelType;

@SuppressWarnings("serial")
public abstract class AbstractHazelcastSubscribeDescriptor extends AbstractSubscribeDescriptor
{
    private int m_size = 0;

    private int m_subs = 0;

    protected AbstractHazelcastSubscribeDescriptor(final String name, final PubSubChannelType type)
    {
        super(name, type);
    }

    protected void addSubscriberCount(final int incr)
    {
        m_subs = Math.max(0, m_subs + incr);

        ping();
    }

    protected int getTotalSubscriberCount()
    {
        return m_subs + m_size;
    }

    protected void ping()
    {
    }

    @Override
    public IMessageReceivedHandlerRegistration addMessageReceivedHandler(final IMessageReceivedHandler handler)
    {
        Objects.requireNonNull(handler);

        addSubscriberCount(0 + 1);

        return new IMessageReceivedHandlerRegistration()
        {
            private static final long                         serialVersionUID = -4801721135204751486L;

            private final IMessageReceivedHandlerRegistration m_proxy          = getSubscribeDescriptorSupport().addMessageReceivedHandler(handler);

            @Override
            public void removeHandler()
            {
                m_proxy.removeHandler();

                addSubscriberCount(0 - 1);
            }
        };
    }

    @Override
    public void setSubscribeListener(final IMessageReceivedHandler listener)
    {
        super.setSubscribeListener(Objects.requireNonNull(listener));

        m_size = 1;

        ping();
    }

    public void setSubscribeListeners(final List<IMessageReceivedHandler> listeners)
    {
        super.setSubscribeListeners(listeners);

        m_size = listeners.size();

        ping();
    }
}
