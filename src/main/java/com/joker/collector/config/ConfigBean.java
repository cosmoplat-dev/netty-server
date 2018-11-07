package com.joker.collector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "config")
@PropertySource("classpath:config.properties")
@Component
public class ConfigBean {

  // 端口号
  public static Integer serverPort;
  // boss线程
  public static Integer bossCount;
  // worker线程
  public static Integer workerCount;
  // 是否active
  public static boolean keepalive;
  public static Integer backlog;
  // 心跳周期，实际为发帧间隔，单位为 秒
  public static Integer heartbeat;
  // 定时器延时启动时间，单位为 秒
  public static Integer delay;
  // 采集周期 单位为 分钟，与数据库中周期单位对应
  public static Integer readPeriod;

  public static Integer getServerPort() {
    return serverPort;
  }

  public static void setServerPort(Integer serverPort) {
    ConfigBean.serverPort = serverPort;
  }

  public static Integer getBossCount() {
    return bossCount;
  }

  public static void setBossCount(Integer bossCount) {
    ConfigBean.bossCount = bossCount;
  }

  public static Integer getWorkerCount() {
    return workerCount;
  }

  public static void setWorkerCount(Integer workerCount) {
    ConfigBean.workerCount = workerCount;
  }

  public static boolean isKeepalive() {
    return keepalive;
  }

  public static void setKeepalive(boolean keepalive) {
    ConfigBean.keepalive = keepalive;
  }

  public static Integer getBacklog() {
    return backlog;
  }

  public static void setBacklog(Integer backlog) {
    ConfigBean.backlog = backlog;
  }

  public static Integer getHeartbeat() {
    return heartbeat;
  }

  public static void setHeartbeat(Integer heartbeat) {
    ConfigBean.heartbeat = heartbeat;
  }

  public static Integer getDelay() {
    return delay;
  }

  public static void setDelay(Integer delay) {
    ConfigBean.delay = delay;
  }

  public static Integer getReadPeriod() {
    return readPeriod;
  }

  public static void setReadPeriod(Integer readPeriod) {
    ConfigBean.readPeriod = readPeriod;
  }

}
