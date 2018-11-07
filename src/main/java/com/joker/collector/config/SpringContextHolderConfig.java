package com.joker.collector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nhb.utils.nhb_utils.context.SpringContextHolder;

@Configuration
public class SpringContextHolderConfig {
  @Bean
  public SpringContextHolder getSpringContextHolder() {
    SpringContextHolder springContextHolder = new SpringContextHolder();
    return springContextHolder;
  }
}
