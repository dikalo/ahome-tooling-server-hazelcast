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

package com.ait.tooling.server.hazelcast.support.spring;

import java.util.Objects;

import org.springframework.context.ApplicationContext;

import com.ait.tooling.server.core.support.spring.ServerContextInstance;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ITopic;

public class HazelcastContextInstance extends ServerContextInstance implements IHazelcastContext
{
    private static final long                     serialVersionUID = 8064409806893442509L;

    private static final HazelcastContextInstance INSTANCE         = new HazelcastContextInstance();

    public static final HazelcastContextInstance getHazelcastContextInstance()
    {
        return INSTANCE;
    }

    protected HazelcastContextInstance()
    {
    }

    @Override
    public IHazelcastInstanceProvider getHazelcastInstanceProvider()
    {
        return Objects.requireNonNull(getBean("HazelcastInstanceProvider", HazelcastInstanceProvider.class), "HazelcastInstanceProvider is null, initialization error.");
    }

    @Override
    public HazelcastInstance getHazelcastInstance()
    {
        return getHazelcastInstanceProvider().getHazelcastInstance();
    }

    @Override
    public HazelcastInstance hz()
    {
        return getHazelcastInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> IList<T> getList(String name)
    {
        name = Objects.requireNonNull(name);

        final IList<T> valu = getBeanSafe(name, IList.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getList(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> IQueue<E> getQueue(String name)
    {
        name = Objects.requireNonNull(name);

        final IQueue<E> valu = getBeanSafe(name, IQueue.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getQueue(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> ITopic<E> getTopic(String name)
    {
        name = Objects.requireNonNull(name);

        final ITopic<E> valu = getBeanSafe(name, ITopic.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getTopic(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> IMap<K, V> getMap(String name)
    {
        name = Objects.requireNonNull(name);

        final IMap<K, V> valu = getBeanSafe(name, IMap.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getMap(name);
    }

    public <T> T getBeanSafe(final String name, final Class<T> type)
    {
        Objects.requireNonNull(name);

        Objects.requireNonNull(type);

        T valu = null;

        final ApplicationContext ctxt = getApplicationContext();

        if (ctxt.containsBean(name))
        {
            try
            {
                valu = ctxt.getBean(name, type);
            }
            catch (Exception e)
            {
                ;
            }
            if (null == valu)
            {
                try
                {
                    final Object object = ctxt.getBean(name);

                    if (null != object)
                    {
                        if (type.isAssignableFrom(object.getClass()))
                        {
                            return type.cast(object);
                        }
                    }
                }
                catch (Exception e)
                {
                    ;
                }
            }
        }
        return valu;
    }
}
