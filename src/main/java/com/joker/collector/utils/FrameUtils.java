package com.joker.collector.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.ArrayUtils;

public abstract class FrameUtils extends ArrayUtils {

	public static String getDeviceNo(byte[] frame) {
		if (frame == null || frame.length < 10) {
			return null;
		}
		StringBuilder deviceNo = new StringBuilder();
		// TODO 根据协议去解析表号等信息
		return deviceNo.toString().toUpperCase();
	}

	public static double floatIeeeConvert(int[] data, int index) {
		int s = 1;
		if ((data[index] & 0x80) == 0x80) {
			s = -1;
		}
		int e = ((data[index] & 0x7f) << 1) + ((data[index + 1] & 0x80) >> 7);
		double m = 0;
		if (e == 0) {
			e = 1 - e;
			m = ((data[index + 1] & 0x7f) * 256 * 256 + data[index + 2] * 256 + data[index + 3]) * 1.0 / (2 << 22);
		} else {
			e = e - 127;
			m = (((data[index + 1] & 0x7f) + 0x80) * 256 * 256 + data[index + 2] * 256 + data[index + 3]) * 1.0
					/ (2 << 22);
		}
		double n = s * Math.pow(2, e) * m;
		BigDecimal b = new BigDecimal(n);
		n = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return n;
	}

	public static String toString(byte[] frame) {
		if (frame == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (int i = 0; i < frame.length; i++) {
			if (i != 0) {
				builder.append(' ');
			}
			String hex = Integer.toHexString(frame[i] & 0xFF);
			if (hex.length() == 1) {
				builder.append('0');
			}
			builder.append(hex);
		}
		builder.append(']');
		return builder.toString();
	}

	public static String toHexString(byte[] frame) {
		if (frame == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		// TODO 获取编号
		return builder.toString();
	}

	public static String getCollectorNo(byte[] msg) {
		if (msg == null || msg.length < 6) {
			return null;
		}
		StringBuilder collector = new StringBuilder();
		// TODO 获取编号
		return collector.toString().toUpperCase();
	}

}
