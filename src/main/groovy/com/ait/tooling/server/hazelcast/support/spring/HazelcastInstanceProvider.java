/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.tooling.server.hazelcast.support.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.ait.tooling.common.api.java.util.StringOps;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastInstanceProvider implements BeanFactoryAware, IHazelcastInstanceProvider
{
    private final HashMap<String, HazelcastInstance> m_instances = new HashMap<String, HazelcastInstance>();

    public HazelcastInstanceProvider()
    {
    }

    public HazelcastInstanceProvider(final String name, final HazelcastInstance instance)
    {
        m_instances.put(StringOps.requireTrimOrNull(name), Objects.requireNonNull(instance));
    }

    public HazelcastInstanceProvider(final HazelcastInstance instance)
    {
        m_instances.put(StringOps.requireTrimOrNull(getDefaultInstanceName()), Objects.requireNonNull(instance));
    }

    @Override
    public Collection<String> getInstanceNames()
    {
        return m_instances.keySet();
    }

    @Override
    public HazelcastInstance getHazelcastInstance(final String name)
    {
        return m_instances.get(StringOps.requireTrimOrNull(name));
    }

    @Override
    public HazelcastInstance getHazelcastInstance()
    {
        return m_instances.get(StringOps.requireTrimOrNull(getDefaultInstanceName()));
    }

    @Override
    public String getDefaultInstanceName()
    {
        return HazelcastContextInstance.get().getDefaultInstanceName();
    }

    @Override
    public void setBeanFactory(final BeanFactory factory) throws BeansException
    {
        if (factory instanceof DefaultListableBeanFactory)
        {
            for (String name : ((DefaultListableBeanFactory) factory).getBeansOfType(HazelcastInstance.class).keySet())
            {
                if (null == m_instances.get(name))
                {
                    final HazelcastInstance instance = factory.getBean(name, HazelcastInstance.class);

                    m_instances.put(name, instance);
                }
            }
        }
        if (null == m_instances.get("default"))
        {
            m_instances.put("default", Hazelcast.newHazelcastInstance());
        }
    }
}
