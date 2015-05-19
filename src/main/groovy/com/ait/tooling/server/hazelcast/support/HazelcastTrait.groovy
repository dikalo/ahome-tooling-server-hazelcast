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

import org.springframework.cache.Cache

import com.ait.tooling.server.hazelcast.support.spring.HazelcastContextInstance
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastContext
import com.ait.tooling.server.hazelcast.support.spring.IHazelcastInstanceProvider
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.cache.HazelcastCacheManager

@CompileStatic
public trait HazelcastTrait
{
    @Memoized
    public IHazelcastContext getHazelcastContext()
    {
        HazelcastContextInstance.get()
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
    public HazelcastCacheManager getHazelcastCacheManager()
    {
        getHazelcastInstanceProvider().getHazelcastCacheManager()
    }

    @Memoized
    public HazelcastInstance hz()
    {
        getHazelcastInstance()
    }

    @Memoized
    public HazelcastCacheManager hzcache()
    {
        getHazelcastInstanceProvider().getHazelcastCacheManager()
    }
    
    @Memoized
    public Cache getCache(String name)
    {
        getHazelcastInstanceProvider().getCache(name)
    }

    @Memoized
    public List<String> getCacheNames()
    {
        getHazelcastInstanceProvider().getCacheNames()
    }
}
