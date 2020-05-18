/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.redhat.aarellan.fuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.boot.SpringBootXmlCamelContextConfigurer;
import org.redhat.aarellan.fuse.processor.ServerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application extends SpringBootXmlCamelContextConfigurer {
	
	@Autowired
	ServerProcessor serverProcessor;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    class RestApi extends RouteBuilder {
    	
    	OrderService service = new OrderService();

        @Override
        public void configure() {
            restConfiguration()
            	.host("0.0.0.0")
            	.port(8080)
                .contextPath("/camel-rest")
                .component("netty4-http")
                .bindingMode(RestBindingMode.json);

            rest("/data").description("Simple REST service")
                .get("{id}").description("My Request")
                    .route().routeId("simple-api")
                    .bean(service, "generateOrder")
                    .process(serverProcessor)
                    .log("HAProxy address: ${in.headers.CamelNettyRemoteAddress} - id: ${header.id} - localAddr: ${header.CamelNettyLocalAddress} - ${header.Connection}")
                    .endRest();
        }
    }
}
