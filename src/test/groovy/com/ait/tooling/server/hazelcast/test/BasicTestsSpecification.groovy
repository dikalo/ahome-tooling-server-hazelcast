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

package com.ait.tooling.server.hazelcast.test

import com.ait.tooling.server.core.support.CoreGroovyTrait
import com.ait.tooling.server.core.support.spring.IServerContext
import com.ait.tooling.server.core.support.spring.testing.spock.ServerCoreSpecification
import com.ait.tooling.server.core.support.spring.testing.IServerCoreTesting.TestingOps

class BasicTestsSpecification extends ServerCoreSpecification implements CoreGroovyTrait
{
    def setupSpec()
    {        
        TestingOps.setupServerCoreDefault(["classpath:/com/ait/tooling/server/hazelcast/test/ApplicationContext.xml", "classpath:/com/ait/tooling/server/core/config/CoreApplicationContext.xml"])
    }

    def cleanupSpec()
    {
        TestingOps.closeServerCoreDefault()
    }

    def "test server context property provider"()
    {
        expect: getPropertyByName("core.server.events.keep.alive") == "30"
    }

    def "test server context crypto provider"()
    {
        setup:
        def text = getCryptoProvider().encrypt("ok")

        expect:
        getCryptoProvider().decrypt(text) != null
        getCryptoProvider().decrypt(text) == "ok"
        getCryptoProvider().decrypt(text) != text
    }
    
    def "test JSONObject"()
    {
        setup:
        def valu = json([name: "Dean"])

        expect:
        valu['name'] == "Dean"
    }
    
    def "test Keys"()
    {
        setup:
        String pass = getCryptoProvider().getRandomPass()
        String salt = getCryptoProvider().getRandomSalt()
        println pass
        println salt
        
        expect:
        getCryptoProvider().isPassValid(pass) == true
    }
}
