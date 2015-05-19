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

import java.io.IOException;
import java.util.Objects;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

public class HazelcastInstanceProvider implements IHazelcastInstanceProvider
{
    private static final long           serialVersionUID = 9139899257457715041L;

    private final HazelcastInstance     m_instance;

    private final HazelcastCacheManager m_hz_cache;

    public HazelcastInstanceProvider(final HazelcastInstance instance, final HazelcastCacheManager hz_cache)
    {
        m_instance = Objects.requireNonNull(instance);

        m_hz_cache = Objects.requireNonNull(hz_cache);
    }

    @Override
    public HazelcastInstance getHazelcastInstance()
    {
        return m_instance;
    }

    @Override
    public HazelcastCacheManager getHazelcastCacheMananer()
    {
        return m_hz_cache;
    }

    @Override
    public void close() throws IOException
    {
        Hazelcast.shutdownAll();
    }
}
