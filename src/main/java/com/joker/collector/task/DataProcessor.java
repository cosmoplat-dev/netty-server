package com.joker.collector.task;

import com.joker.collector.attribute.EqmContext;
import com.joker.collector.base.Device;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * @ClassName: DataProcessor
 * @Function: DTU定时采集任务
 * @date: May 27, 2017 10:31:05 AM
 * 
 * @author Joker
 * @version
 * @since JDK 1.8
 */
public class DataProcessor implements Runnable {

  private ChannelHandlerContext ctx;

  private AttributeKey<EqmContext> attrDtuContext;

  public DataProcessor(ChannelHandlerContext ctx, AttributeKey<EqmContext> attrDtuContext) {
    this.ctx = ctx;
    this.attrDtuContext = attrDtuContext;
  }

  public DataProcessor(ChannelHandlerContext ctx) {
    this.ctx = ctx;
  }

  EqmContext dtuContext;

  @Override
  public void run() {

    // 获取当前dtu的信息
    dtuContext = ctx.channel().attr(attrDtuContext).get();
    // 当前dtu context信息
    ChannelHandlerContext dtuCHC = dtuContext.getDeviceCHC();
    if (dtuCHC == null || !dtuCHC.channel().isActive()) {
      return;
    }
    writeDevice(dtuContext);

  }

  /**
   * write one frame every time
   * 
   * @param dtuContext
   */
  private void writeDevice(EqmContext dtuContext) {
    Device device = dtuContext.getCurrentDevice();
    if (null == device) {
      return;
    }
    byte[] frame = device.getWritingFrame();
    if (frame == null) {
      return;
    }
    // 下发指令
    ctx.writeAndFlush(frame);
  }
}
