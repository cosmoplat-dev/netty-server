/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.joker.collector.server;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;

/**
 * @ClassName: EqmServer
 * @Function: 服务类
 * @date: May 22, 2017 11:12:20 AM
 * @author Joker
 * @version
 * @since JDK 1.8
 */
@Component
public class EqmServer {
	private static final Logger logger = LoggerFactory.getLogger(EqmServer.class.getName());

	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap serverBootstrap;

	@Autowired
	@Qualifier("serverPort")
	private int serverPort;

	private Channel serverChannel;

	public void start() throws Exception {
		logger.warn(StringUtils.repeat("*", 10) + "DTU采集软件已经启动" + StringUtils.repeat("*", 10));
		serverChannel = serverBootstrap.bind(serverPort).sync().channel().closeFuture().sync().channel();
	}

	@PreDestroy
	public void stop() throws Exception {
		serverChannel.close();
		serverChannel.parent().close();
	}

	public ServerBootstrap getServerBootstrap() {
		return serverBootstrap;
	}

	public void setServerBootstrap(ServerBootstrap serverBootstrap) {
		this.serverBootstrap = serverBootstrap;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

}
