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
}
