package com.joker.collector.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.collector.utils.ChannelHandlerContextUtils;
import com.joker.collector.utils.FrameUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * @ClassName: SocketResponseEncoder
 * @Function: 消息编码
 * @date: May 22, 2017 10:49:11 AM
 * 
 * @author Joker
 * @version
 * @since JDK 1.8
 */
//@Sharable
public class SocketEncoder extends ByteArrayEncoder
{
	 private static final Logger logger = LoggerFactory.getLogger(SocketEncoder.class.getName());

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception
	{
		logger.warn("服务器发送数据：" + FrameUtils.toString(msg));
		ChannelHandlerContextUtils.writeAndFlush(ctx, msg);
	}
}
