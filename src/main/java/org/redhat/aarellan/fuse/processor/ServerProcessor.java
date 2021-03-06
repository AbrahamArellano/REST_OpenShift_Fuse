package org.redhat.aarellan.fuse.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ServerProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.getIn().getHeader("CamelNettyRemoteAddress"));
	}

}
