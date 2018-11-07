package com.joker.collector.attribute;

import com.joker.collector.base.Device;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @ClassName: EqmContext
 * @Function: Eqm属性上下文
 * @date: May 22, 2017 11:29:15 AM
 * @author Joker
 * @version
 * @since JDK 1.8
 */
public class EqmContext {

	// 设备编号
	private String deviceNo;

	private ChannelHandlerContext deviceCHC;

	private ChannelHandlerContext cmdCHC;

	private Device cmdQueue;

	private Device currentDevice;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public ChannelHandlerContext getDeviceCHC() {
		return deviceCHC;
	}

	public void setDeviceCHC(ChannelHandlerContext deviceCHC) {
		this.deviceCHC = deviceCHC;
	}

	public ChannelHandlerContext getCmdCHC() {
		return cmdCHC;
	}

	public void setCmdCHC(ChannelHandlerContext cmdCHC) {
		this.cmdCHC = cmdCHC;
	}

	public Device getCmdQueue() {
		return cmdQueue;
	}

	public void setCmdQueue(Device cmdQueue) {
		this.cmdQueue = cmdQueue;
	}

	public Device getCurrentDevice() {
		return currentDevice;
	}

	public void setCurrentDevice(Device currentDevice) {
		this.currentDevice = currentDevice;
	}

}
