package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        rep.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, rep);
    }

    @Override
    public void destroy() {

    }
}
