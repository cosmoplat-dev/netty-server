package com.joker.collector.handler;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.joker.collector.attribute.EqmContext;
import com.joker.collector.controller.EqmController;
import com.joker.collector.utils.FrameUtils;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @ClassName: DtuServerHandler
 * @Function: Handler处理类
 * @date: May 23, 2017 3:36:12 PM
 * @author Joker
 * @version
 * @since JDK 1.8
 */
public class EqmServerHandler extends SimpleChannelInboundHandler<byte[]> {
	private static final Logger logger = LoggerFactory.getLogger(EqmServerHandler.class);
	/**
	 * 通道上下文信息
	 */
	private AttributeKey<EqmContext> attrDEqmContext;

	/**
	 * 设备业务控制
	 */
	@Autowired
	private EqmController dtuController;

	/**
	 * 客户端连接后触发
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.warn("channelActive被触发，已经有设备连接上采集软件");
		// 生成 唯一dtu 上下文 key
		attrDEqmContext = AttributeKey.valueOf(String.valueOf(UUID.randomUUID()));
		// dtu 业务处理
		dtuController = new EqmController(attrDEqmContext);

	};

	/**
	 * channelInactive: 客户端断开连接后触发
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		if (StringUtil.isNullOrEmpty(ctx.channel().attr(attrDEqmContext).get().getDeviceNo())) {
			logger.warn("设备注册失败！");
		} else {
			logger.warn(ctx.channel().attr(attrDEqmContext).get().getDeviceNo() + " : 编号设备主动断开连接!");
		}
	}

	/**
	 * channelRead0: Decoder发送数据后触发
	 * 
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext,
	 *      java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
		logger.warn("channelRead0服务器接收到数据：" + FrameUtils.toString(msg));
		// TODO 做数据判断，进行呢业务处理，做协议分割等
		dtuController.saveRegisterInfo(ctx, msg);
		dtuController.heartbeat(ctx, msg);
		dtuController.readDevice(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info(ctx.channel().attr(attrDEqmContext).get().getDeviceNo() + "：未知异常！", cause.getMessage());
	}
}
