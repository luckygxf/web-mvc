package com.gxf.filters;

import com.gxf.annotation.GxfController;
import com.gxf.annotation.GxfPath;
import com.gxf.utils.OutputUtil;
import com.gxf.utils.ScanningFile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午2:47
 **/
public class DispatcherFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("do init DispatcherFilter");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        dispach(request, response);
    }

    public void destroy() {

    }

    /**
     * 根据url执行对应的方法
     * */
    private void dispach(ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletRequestPath = httpServletRequest.getServletPath();
        List<Class> controllerList = ScanningFile.scanPackage("com.gxf.controllers");
        System.out.println("servletRequestPath: " + servletRequestPath);
        boolean findMatchUrl = false;
        for(Class controller : controllerList){
            boolean isAnnotationedByGxfController = controller.isAnnotationPresent(GxfController.class);
            if(!isAnnotationedByGxfController){
                //没有被GxfController注解
                continue;
            }
            GxfController gxfController = (GxfController) controller.getAnnotation(GxfController.class);
            String controllerPath = gxfController.path();
            System.out.println("controllerPath: " + controllerPath);

            //解析contronller方法
            Method[] controllerMethods = controller.getDeclaredMethods();
            for(Method method : controllerMethods){
                System.out.println("method: " + method);
                boolean isAnnotionedByGxfPath = method.isAnnotationPresent(GxfPath.class);
                if(!isAnnotionedByGxfPath)
                    continue;
                GxfPath gxfPath = method.getAnnotation(GxfPath.class);
                String methodPath = gxfPath.path();
                System.out.println("methodPath: " + methodPath);
                String clazzAndMethodPath = controllerPath + methodPath;
                if(servletRequestPath.equals(clazzAndMethodPath)){
                    //路径匹配到合适的方法，执行方法
                    findMatchUrl = true;
                    try {
                        method.invoke(controller.newInstance(), request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } //for
        } //for

        //没有匹配到合适的url
        if(!findMatchUrl){
            try {
                OutputUtil.outputHmtlContent((HttpServletRequest)request, (HttpServletResponse) response,
                        "Url Not Match", "404");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
