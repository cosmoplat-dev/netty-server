package com.joker.collector.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDevice implements Device {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected byte[] readingFrames = null;
	protected byte[] writingFrames = null;

	@Override
	public void processReadingFrame(byte[] readingFrame) {
		// 先判断设备号是否一致
		if (!isPacketBelongToDevice(readingFrame)) {
			return;
		}
		// TODO 此处对帧进行解析
		try {
			handleResult();
		} catch (Exception ex) {
			logger.error("unknow Exception", ex);
		}
	}

	/**
	 * Do the receive packet belong to current Meter ?
	 * 
	 * @param msg
	 *            receive packet
	 * @return Yes retrun true,No return false
	 */
	private boolean isPacketBelongToDevice(byte[] msg) {
		try {
			// 去判断设备编号是否正确和匹配
			return true;
		} catch (Exception ex) {
			logger.error("Exception in isPacketBelongToDevice：", ex);
		}
		return false;
	}

	// 获取下发帧
	@Override
	public byte[] getWritingFrame() {
		return writingFrames;
	}

	public abstract void buildWritingFrames();

	public abstract boolean analyzeFrame(byte[] frame);

	public abstract void handleResult();

}
