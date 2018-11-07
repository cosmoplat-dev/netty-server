package com.joker.collector.task;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.joker.collector.attribute.EqmContext;
import com.joker.collector.init.EqmContextMap;

@Component
public class HeartProcessor {

	EqmContext dtuContext;

	byte[] heartFrame = { 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

	// 可做定时器做心跳维持机制
	@Scheduled(cron = "0 */3 * * * ?")
	public void heartBeat() {
		ConcurrentHashMap<String, EqmContext> channels = EqmContextMap.getInstance();
		if (channels == null) {
			return;
		}
		for (String deviceNo : channels.keySet()) {
			dtuContext = channels.get(deviceNo);
			dtuContext.getDeviceCHC().writeAndFlush(heartFrame);
		}
	}

}
