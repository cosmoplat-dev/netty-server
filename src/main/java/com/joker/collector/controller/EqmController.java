/**
 * Project Name:MeterSocketservApp File Name:DtuController.java Package
 * Name:com.xhb.sockserv.collector Date:Mar 9, 20179:12:26 AM Copyright (c) 2017, lorisun@live.com
 * All Rights Reserved.
 * 
 */
package com.joker.collector.controller;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.collector.attribute.EqmContext;
import com.joker.collector.base.Device;
import com.joker.collector.init.EqmContextMap;
import com.joker.collector.task.DataProcessor;
import com.joker.collector.utils.FrameUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * @ClassName:DtuController
 * @Function: DTU信息处理
 * @Date: Mar 9, 2017 9:12:26 AM
 * @author Joker
 * @version
 * @since JDK 1.8
 * @see
 */
public class EqmController {

	private static final Logger logger = LoggerFactory.getLogger(EqmController.class);

	DataProcessor dataProcessor;

	// 通道上下文属性key
	private AttributeKey<EqmContext> attreqmContext;

	// 通道上下文属性
	private EqmContext eqmContext;

	public EqmController(AttributeKey<EqmContext> attreqmContext) {
		this.attreqmContext = attreqmContext;
	}

	/**
	 * heartbeat: 处理dtu的心跳报文
	 * 
	 * @author Joker
	 * @param ctx
	 * @param msg
	 * @since JDK 1.8
	 */
	public void heartbeat(ChannelHandlerContext ctx, byte[] msg) {
		String deviceNo = FrameUtils.getDeviceNo(msg);
		if (StringUtils.isEmpty(deviceNo)) {
			return;
		}
		updateDeviceStatus(deviceNo, ctx);
	}

	/**
	 * readDevice: 处理上传的数据报文
	 * 
	 * @author Joker
	 * @param ctx
	 * @param msg
	 * @since JDK 1.8
	 */
	public void readDevice(ChannelHandlerContext ctx, byte[] msg) {
		String deviceNo = FrameUtils.getDeviceNo(msg);
		// 更新设备状态
		updateDeviceStatus(deviceNo, ctx);
		EqmContext eqmContext = ctx.channel().attr(attreqmContext).get();
		if (eqmContext == null) {
			return;
		}

		Device device = eqmContext.getCurrentDevice();
		ctx.channel().attr(attreqmContext).set(eqmContext);
		if (device == null) {
			return;
		}
		device.processReadingFrame(msg);
	}

	/**
	 * saveRegisterInfo: 处理采集器（dtu）注册信息
	 * 
	 * @author Joker
	 * @param ctx
	 * @param msg
	 * @since JDK 1.8
	 */
	public void saveRegisterInfo(ChannelHandlerContext ctx, byte[] msg) {
		// 从数据帧获取设备No
		String deviceNo = FrameUtils.getDeviceNo(msg);
		if (StringUtils.isEmpty(deviceNo)) {
			return;
		}
		eqmContext = new EqmContext();
		eqmContext.setDeviceNo(deviceNo);
		eqmContext.setDeviceCHC(ctx);

		// TODO 开启定时器

		// 封装管道上下文属性
		ctx.channel().attr(attreqmContext).set(eqmContext);
		logger.warn(ctx.channel().attr(attreqmContext).get().getDeviceNo() + " : 编号设备注册成功!");
		// 将采集器信息 保存到缓存中
		EqmContextMap.getInstance().put(deviceNo, eqmContext);
		// 更新状态
		updateDeviceStatus(deviceNo, ctx);

		// 启动定时任务
		dataProcessor = new DataProcessor(ctx, attreqmContext);
		ctx.executor().scheduleAtFixedRate(dataProcessor, 1, 15, TimeUnit.MINUTES);

		// 读取设备状态
		ctx.writeAndFlush(new byte[] { 0x55, (byte) 0xAA, 0x00, 0x1C, 0x00, 0x00, 0x1C });

	}

	/**
	 * updateDeviceStatus: 更新设备在线状态
	 * 
	 * @author Joker
	 * @param deviceNo
	 * @param ctx
	 * @since JDK 1.8
	 */
	private void updateDeviceStatus(String collectorNo, ChannelHandlerContext ctx) {
		// TODO 更新设备状态
	}

}
