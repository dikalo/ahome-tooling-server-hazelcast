/*
 * Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.
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

import com.ait.tooling.server.core.support.CoreGroovySupport
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
import com.hazelcast.ringbuffer.Ringbuffer

@CompileStatic
public class HazelcastSupport extends CoreGroovySupport implements IHazelcastContext, Serializable
{
    private static final HazelcastSupport INSTANCE = new HazelcastSupport()

    private static final long serialVersionUID = 7194686823825056219L

    @Memoized
    public static final HazelcastSupport getHazelcastSupport()
    {
        INSTANCE
    }

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

    @Memoized
    public <T> IList<T> getIList(String name)
    {
        getHazelcastContext().getIList(Objects.requireNonNull(name))
    }

    @Memoized
    public <E> IQueue<E> getIQueue(String name)
    {
        getHazelcastContext().getIQueue(Objects.requireNonNull(name))
    }

    @Memoized
    public <E> ITopic<E> getITopic(String name)
    {
        getHazelcastContext().getITopic(Objects.requireNonNull(name))
    }

    @Memoized
    public <K, V> IMap<K, V> getIMap(String name)
    {
        getHazelcastContext().getIMap(Objects.requireNonNull(name))
    }

    @Memoized
    public <E> ISet<E> getISet(String name)
    {
        getHazelcastContext().getISet(Objects.requireNonNull(name))
    }

    @Memoized
    public <K, V> MultiMap<K, V> getIMultiMap(String name)
    {
        getHazelcastContext().getIMultiMap(Objects.requireNonNull(name))
    }

    @Memoized
    public <K, V> ReplicatedMap<K, V> getIReplicatedMap(String name)
    {
        getHazelcastContext().getIReplicatedMap(Objects.requireNonNull(name))
    }

    @Memoized
    public <E> Ringbuffer<E> getRingbuffer(String name)
    {
        getHazelcastContext().getRingbuffer(Objects.requireNonNull(name))
    }

    @Memoized
    public IAtomicLong getIAtomicLong(String name)
    {
        getHazelcastContext().getIAtomicLong(Objects.requireNonNull(name))
    }

    @Memoized
    public ICountDownLatch getICountDownLatch(String name)
    {
        getHazelcastContext().getICountDownLatch(Objects.requireNonNull(name))
    }

    @Memoized
    public IdGenerator getIdGenerator(String name)
    {
        getHazelcastContext().getIdGenerator(Objects.requireNonNull(name))
    }

    @Memoized
    public IExecutorService getIExecutorService(String name)
    {
        getHazelcastContext().getIExecutorService(Objects.requireNonNull(name))
    }

    @Memoized
    public ISemaphore getISemaphore(String name)
    {
        getHazelcastContext().getISemaphore(Objects.requireNonNull(name))
    }

    @Memoized
    public ILock getILock(String name)
    {
        getHazelcastContext().getILock(Objects.requireNonNull(name))
    }
}
