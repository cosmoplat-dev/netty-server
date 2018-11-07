package com.joker.collector.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.collector.utils.FrameUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * @author Joker 解码插件
 */
public class SocketDecoder extends ByteToMessageDecoder {

	private static final Logger logger = LoggerFactory.getLogger(SocketDecoder.class);

	/**
	 * 编码插件，接受到的数据在此处进行过滤，做拆包粘包处理等操作
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] orignalBytes = new byte[in.readableBytes()];
		in.getBytes(in.readerIndex(), orignalBytes);
		logger.warn("Decoder收到数据：" + FrameUtils.toString(orignalBytes));
		if (in.readableBytes() <= 0) {
			return;
		}
		// TODO 做粘包处理

		byte[] bytes = new byte[in.readableBytes()];

		in.readBytes(bytes, 0, bytes.length);
		out.add(bytes);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("unexpected exception", cause);
		// ctx.close();
	}
}
