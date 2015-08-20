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

import com.ait.tooling.server.core.support.spring.ServerContextInstance;
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
        return Objects.requireNonNull(getBeanSafely("HazelcastInstanceProvider", HazelcastInstanceProvider.class), "HazelcastInstanceProvider is null, initialization error.");
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
    public <T> IList<T> getIList(String name)
    {
        name = Objects.requireNonNull(name);

        final IList<T> valu = getBeanSafely(name, IList.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getList(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> IQueue<E> getIQueue(String name)
    {
        name = Objects.requireNonNull(name);

        final IQueue<E> valu = getBeanSafely(name, IQueue.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getQueue(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> ITopic<E> getITopic(String name)
    {
        name = Objects.requireNonNull(name);

        final ITopic<E> valu = getBeanSafely(name, ITopic.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getTopic(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> IMap<K, V> getIMap(String name)
    {
        name = Objects.requireNonNull(name);

        final IMap<K, V> valu = getBeanSafely(name, IMap.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getMap(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> ISet<E> getISet(String name)
    {
        name = Objects.requireNonNull(name);

        final ISet<E> valu = getBeanSafely(name, ISet.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getSet(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> MultiMap<K, V> getIMultiMap(String name)
    {
        name = Objects.requireNonNull(name);

        final MultiMap<K, V> valu = getBeanSafely(name, MultiMap.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getMultiMap(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> ReplicatedMap<K, V> getIReplicatedMap(String name)
    {
        name = Objects.requireNonNull(name);

        final ReplicatedMap<K, V> valu = getBeanSafely(name, ReplicatedMap.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getReplicatedMap(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> Ringbuffer<E> getRingbuffer(String name)
    {
        name = Objects.requireNonNull(name);

        final Ringbuffer<E> valu = getBeanSafely(name, Ringbuffer.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getRingbuffer(name);
    }

    @Override
    public IAtomicLong getIAtomicLong(String name)
    {
        name = Objects.requireNonNull(name);

        final IAtomicLong valu = getBeanSafely(name, IAtomicLong.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getAtomicLong(name);
    }

    @Override
    public ICountDownLatch getICountDownLatch(String name)
    {
        name = Objects.requireNonNull(name);

        final ICountDownLatch valu = getBeanSafely(name, ICountDownLatch.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getCountDownLatch(name);
    }

    @Override
    public IdGenerator getIdGenerator(String name)
    {
        name = Objects.requireNonNull(name);

        final IdGenerator valu = getBeanSafely(name, IdGenerator.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getIdGenerator(name);
    }

    @Override
    public IExecutorService getIExecutorService(String name)
    {
        name = Objects.requireNonNull(name);

        final IExecutorService valu = getBeanSafely(name, IExecutorService.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getExecutorService(name);
    }

    @Override
    public ISemaphore getISemaphore(String name)
    {
        name = Objects.requireNonNull(name);

        final ISemaphore valu = getBeanSafely(name, ISemaphore.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getSemaphore(name);
    }

    @Override
    public ILock getILock(String name)
    {
        name = Objects.requireNonNull(name);

        final ILock valu = getBeanSafely(name, ILock.class);

        if (null != valu)
        {
            return valu;
        }
        return hz().getLock(name);
    }
}
