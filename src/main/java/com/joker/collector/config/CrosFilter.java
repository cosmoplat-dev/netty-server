package com.joker.collector.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: CrosFilter
 * @Description: 处理 OPTIONS 请求
 * @author XS guo
 * @date 2017年7月24日 上午10:25:16
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class CrosFilter implements Filter {

  @Override
  public void destroy() {

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "Content-Type, x-requested-with, X-Custom-Header, Authorization");
    chain.doFilter(req, res);

  }

  @Override
  public void init(FilterConfig config) throws ServletException {

  }

}
