package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法的分发
        //1.获取请求路劲
        String uri=req.getRequestURI();
        System.out.println("请求uri:"+uri);//   /travel/user/add
        //2.获取方法名称
        String methodName=uri.substring(uri.lastIndexOf("/")+1);
        System.out.println("方法名称:"+methodName);
        //3.获取方法的对象
        System.out.println(this);
        try {
            //忽略访问权限修饰符，获取方法
           Method method= this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
           //4.执行方法
            //暴力反射
//            method.setAccessible(true);
           method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);

    }

    public String writeValueAsString(Object obj) throws JsonProcessingException{
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
