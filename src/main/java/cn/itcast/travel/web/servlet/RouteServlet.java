package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    RouteService routeService=new RouteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr=request.getParameter("currentPage");
        String pageSizeStr=request.getParameter("pageSize");
        String cidStr=request.getParameter("cid");
        //接受rname路线名称
        String rname=request.getParameter("rname");
        if(rname!=null){
            rname=new String(rname.getBytes("iso-8859-1"),"utf-8");
        }


        int cid=0; //类编id
        //2.处理参数
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage=0;//当前页码 如果不传递  默认为第一页
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }

        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=5;
        }
        //3.调用service查询PageBean对象
            PageBean pageBean=routeService.pageQuery(cid, currentPage,  pageSize,rname);

        //4.将pageBean对象序列化为json 返回网页

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),pageBean);

    }
    /**
     * 通过rid，查询Route线路的详细信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");

        //调用service查询route对象
        Route route=routeService.findOne(rid);

        //转为json写回客户端
        writeValue(route,response);

    }



}
