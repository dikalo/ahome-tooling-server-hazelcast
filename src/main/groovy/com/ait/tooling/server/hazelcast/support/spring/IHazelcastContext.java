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

import com.ait.tooling.server.core.support.spring.IServerContext;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.ICountDownLatch;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IList;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ISemaphore;
import com.hazelcast.core.ISet;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.IdGenerator;
import com.hazelcast.core.MultiMap;
import com.hazelcast.core.ReplicatedMap;
import com.hazelcast.ringbuffer.Ringbuffer;

public interface IHazelcastContext extends IServerContext
{
    public IHazelcastInstanceProvider getHazelcastInstanceProvider();

    public HazelcastInstance getHazelcastInstance();

    public HazelcastInstance hz();

    public <E> ISet<E> getISet(String name);

    public <T> IList<T> getIList(String name);

    public <E> IQueue<E> getIQueue(String name);

    public <E> ITopic<E> getITopic(String name);

    public <K, V> IMap<K, V> getIMap(String name);

    public <K, V> MultiMap<K, V> getIMultiMap(String name);

    public <K, V> ReplicatedMap<K, V> getIReplicatedMap(String name);

    public <E> Ringbuffer<E> getRingbuffer(String name);

    public IAtomicLong getIAtomicLong(String name);

    public ICountDownLatch getICountDownLatch(String name);

    public IdGenerator getIdGenerator(String name);

    public IExecutorService getIExecutorService(String name);

    public ISemaphore getISemaphore(String name);

    public ILock getILock(String name);
}
