package com.gxf.controllers;

import com.gxf.annotation.GxfController;
import com.gxf.annotation.GxfPath;
import com.gxf.utils.OutputUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午2:40
 **/
@GxfController(path = "/hello")
public class HelloController {

    @GxfPath(path = "/print")
    public void outputHello(HttpServletRequest req, HttpServletResponse resp){
        try {
            OutputUtil.outputHmtlContent(req, resp, "mvc framework", "Test");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GxfPath(path = "/sing")
    public void sing(HttpServletRequest req, HttpServletResponse resp){
        try {
            OutputUtil.outputHmtlContent(req, resp, "mvc framework", "Sing");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GxfPath(path = "/laugh")
    public void laugh(HttpServletRequest req, HttpServletResponse resp){
        try {
            OutputUtil.outputHmtlContent(req, resp, "mvc framework", "Laugh");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
