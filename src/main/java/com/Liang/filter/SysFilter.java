package com.Liang.filter;

import com.Liang.pojo.User;
import com.Liang.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
  public void init(FilterConfig filterConfig) throws ServletException {}

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
    if (user == null) { // 拦截未登录用户
      response.sendRedirect("/SMBMS/error.jsp");
    } else {
      chain.doFilter(req, resp);
    }
  }

  public void destroy() {}
}
