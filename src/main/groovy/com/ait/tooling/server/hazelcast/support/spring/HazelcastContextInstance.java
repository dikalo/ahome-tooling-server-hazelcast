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

import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.json.JSONObject;
import com.ait.tooling.server.core.jmx.management.ICoreServerManager;
import com.ait.tooling.server.core.pubsub.IPubSubDescriptorProvider;
import com.ait.tooling.server.core.security.AuthorizationResult;
import com.ait.tooling.server.core.security.IAuthorizationProvider;
import com.ait.tooling.server.core.support.spring.IBuildDescriptorProvider;
import com.ait.tooling.server.core.support.spring.IExecutorServiceDescriptorProvider;
import com.ait.tooling.server.core.support.spring.IPropertiesResolver;
import com.ait.tooling.server.core.support.spring.IServerContext;
import com.ait.tooling.server.core.support.spring.ServerContextInstance;

public final class HazelcastContextInstance implements IHazelcastContext
{
    private static final long                     serialVersionUID = 8064409806893442509L;

    private static final HazelcastContextInstance INSTANCE         = new HazelcastContextInstance();

    public static final HazelcastContextInstance get()
    {
        return INSTANCE;
    }

    private HazelcastContextInstance()
    {
    }

    @Override
    public IHazelcastInstanceProvider getHazelcastInstanceProvider()
    {
        return getBean("HazelcastInstanceProvider", HazelcastInstanceProvider.class);
    }

    @Override
    public IServerContext getServerContext()
    {
        return ServerContextInstance.get();
    }

    @Override
    public WebApplicationContext getApplicationContext()
    {
        return getServerContext().getApplicationContext();
    }

    @Override
    public Environment getEnvironment()
    {
        return getServerContext().getEnvironment();
    }

    public <T> T getBean(final String name, final Class<T> type)
    {
        return getApplicationContext().getBean(StringOps.requireTrimOrNull(name), Objects.requireNonNull(type));
    }

    @Override
    public String getPropertyByName(final String name)
    {
        return getServerContext().getPropertyByName(StringOps.requireTrimOrNull(name));
    }

    @Override
    public String getPropertyByName(final String name, final String otherwise)
    {
        return getServerContext().getPropertyByName(StringOps.requireTrimOrNull(name), otherwise);
    }

    @Override
    public IAuthorizationProvider getAuthorizationProvider()
    {
        return getServerContext().getAuthorizationProvider();
    }

    @Override
    public Iterable<String> getPrincipalsKeys()
    {
        return getServerContext().getPrincipalsKeys();
    }

    @Override
    public ICoreServerManager getCoreServerManager()
    {
        return getServerContext().getCoreServerManager();
    }

    @Override
    public IExecutorServiceDescriptorProvider getExecutorServiceDescriptorProvider()
    {
        return getServerContext().getExecutorServiceDescriptorProvider();
    }

    @Override
    public IBuildDescriptorProvider getBuildDescriptorProvider()
    {
        return getServerContext().getBuildDescriptorProvider();
    }

    @Override
    public IPropertiesResolver getPropertiesResolver()
    {
        return getServerContext().getPropertiesResolver();
    }

    @Override
    public IPubSubDescriptorProvider getPubSubDescriptorProvider()
    {
        return getServerContext().getPubSubDescriptorProvider();
    }

    @Override
    public AuthorizationResult isAuthorized(Object target, JSONObject principals)
    {
        return getServerContext().isAuthorized(target, principals);
    }
}
