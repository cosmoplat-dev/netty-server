/**
 * Project Name:socket-server File Name:ServerPeriod.java Package Name:com.xhb.sockserv.config
 * Date:Mar 3, 201712:26:11 PM Copyright (c) 2017, lorisun@live.com All Rights Reserved.
 * 
 */

package com.joker.collector.config;

/**
 * @ClassName:ServerPeriod
 * @Function: TODO ADD FUNCTION.
 * @Reason: TODO ADD REASON.
 * @Date: Mar 3, 2017 12:26:11 PM
 * @author Joker
 * @version
 * @since JDK 1.8
 * @see
 */
public enum ServerPeriod {
  /**
   * 常用延时时间 30秒
   */
  DELAY("delay", 30),
  /**
   * 常用周期时间 30秒
   */
  PERIOD("period", 30),
  /**
   * 心跳延时时间
   */
  HEART_BEAT_DELAY("heartBeatDelay", 0),
  /**
   * 心跳周期
   */
  HEART_BEAT_PERIOD("heartBeatPeriod", 3),
  /**
   * 历史数据读取周期 900
   */
  READ_HISTORY_PERIOD("readHistoryPeriod", 900000L),
  /**
   * 超时时间 60
   */
  TIMEOUT("timeout", 300);

  private String name;
  private long value;

  private ServerPeriod(String name, long value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? "" : name;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value + ":" + this.name;
  }
}
