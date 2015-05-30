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

package com.ait.tooling.server.hazelcast.support

import groovy.transform.CompileStatic
import groovy.transform.Memoized

import com.ait.tooling.server.hazelcast.support.spring.HazelcastContextInstance
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastContext
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastInstanceProvider
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IList
import com.hazelcast.core.IMap
import com.hazelcast.core.IQueue
import com.hazelcast.core.ITopic

@CompileStatic
public trait HazelcastTrait
{
    @Memoized
    public IHazelcastContext getHazelcastContext()
    {
        HazelcastContextInstance.getHazelcastContextInstance()
    }

    @Memoized
    public IHazelcastInstanceProvider getHazelcastInstanceProvider()
    {
        getHazelcastContext().getHazelcastInstanceProvider()
    }

    @Memoized
    public HazelcastInstance getHazelcastInstance()
    {
        getHazelcastInstanceProvider().getHazelcastInstance()
    }

    @Memoized
    public HazelcastInstance hz()
    {
        getHazelcastInstance()
    }

    public <T> IList<T> getList(String name)
    {
        hz().getList(Objects.requireNonNull(name))
    }

    public <E> IQueue<E> getQueue(String name)
    {
        hz().getQueue(Objects.requireNonNull(name))
    }

    public <E> ITopic<E> getTopic(String name)
    {
        hz().getTopic(Objects.requireNonNull(name))
    }

    public <K, V> IMap<K, V> getMap(String name)
    {
        hz().getMap(Objects.requireNonNull(name))
    }
}
