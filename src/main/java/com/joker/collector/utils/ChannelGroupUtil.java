/**
 * Project Name:multi-server File Name:ChannelGroupUtil.java Package Name:com.sun.collector.utils
 * Date:2018年2月8日下午12:48:14 Copyright (c) 2018, lorisun@live.com All Rights Reserved.
 * 
 */

package com.joker.collector.utils;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * @ClassName:ChannelGroupUtil
 * @Function: 饿汉式单例ChannelGroupUtil
 * @Reason: 公共类
 * @Date: 2018年2月8日 下午12:48:14
 * @author Joker
 * @version
 * @since JDK 1.8
 * @see
 */
public class ChannelGroupUtil {
  /**
   * 创建ChannelGroup实例用于存储Channel信息
   */
  private static ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

  private ChannelGroupUtil() {}

  public static ChannelGroup getInstance() {
    return group;
  }

}
