package com.joker.collector.base;

public interface Device {

	void processReadingFrame(byte[] readingFrame);

	byte[] getWritingFrame();

}
