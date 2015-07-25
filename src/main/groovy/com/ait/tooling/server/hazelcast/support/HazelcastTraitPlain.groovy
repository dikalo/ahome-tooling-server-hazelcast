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

import com.ait.tooling.server.hazelcast.support.spring.HazelcastContextInstance
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastContext
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastInstanceProvider
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IAtomicLong
import com.hazelcast.core.ICountDownLatch
import com.hazelcast.core.IExecutorService
import com.hazelcast.core.IList
import com.hazelcast.core.ILock
import com.hazelcast.core.IMap
import com.hazelcast.core.IQueue
import com.hazelcast.core.ISemaphore
import com.hazelcast.core.ISet
import com.hazelcast.core.ITopic
import com.hazelcast.core.IdGenerator
import com.hazelcast.core.MultiMap
import com.hazelcast.core.ReplicatedMap

@CompileStatic
public trait HazelcastTraitPlain
{
    public IHazelcastContext getHazelcastContext()
    {
        HazelcastContextInstance.getHazelcastContextInstance()
    }

    public IHazelcastInstanceProvider getHazelcastInstanceProvider()
    {
        getHazelcastContext().getHazelcastInstanceProvider()
    }

    public HazelcastInstance getHazelcastInstance()
    {
        getHazelcastInstanceProvider().getHazelcastInstance()
    }

    public HazelcastInstance hz()
    {
        getHazelcastInstance()
    }

    public <T> IList<T> getIList(String name)
    {
        getHazelcastContext().getIList(Objects.requireNonNull(name))
    }

    public <E> IQueue<E> getIQueue(String name)
    {
        getHazelcastContext().getIQueue(Objects.requireNonNull(name))
    }

    public <E> ITopic<E> getITopic(String name)
    {
        getHazelcastContext().getITopic(Objects.requireNonNull(name))
    }

    public <K, V> IMap<K, V> getIMap(String name)
    {
        getHazelcastContext().getIMap(Objects.requireNonNull(name))
    }

    public <E> ISet<E> getISet(String name)
    {
        getHazelcastContext().getISet(Objects.requireNonNull(name))
    }

    public <K, V> MultiMap<K, V> getIMultiMap(String name)
    {
        getHazelcastContext().getIMultiMap(Objects.requireNonNull(name))
    }

    public <K, V> ReplicatedMap<K, V> getIReplicatedMap(String name)
    {
        getHazelcastContext().getIReplicatedMap(Objects.requireNonNull(name))
    }

    public IAtomicLong getIAtomicLong(String name)
    {
        getHazelcastContext().getIAtomicLong(Objects.requireNonNull(name))
    }

    public ICountDownLatch getICountDownLatch(String name)
    {
        getHazelcastContext().getICountDownLatch(Objects.requireNonNull(name))
    }

    public IdGenerator getIdGenerator(String name)
    {
        getHazelcastContext().getIdGenerator(Objects.requireNonNull(name))
    }

    public IExecutorService getIExecutorService(String name)
    {
        getHazelcastContext().getIExecutorService(Objects.requireNonNull(name))
    }

    public ISemaphore getISemaphore(String name)
    {
        getHazelcastContext().getISemaphore(Objects.requireNonNull(name))
    }

    public ILock getILock(String name)
    {
        getHazelcastContext().getILock(Objects.requireNonNull(name))
    }
}
