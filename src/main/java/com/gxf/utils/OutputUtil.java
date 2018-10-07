package com.gxf.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午2:43
 **/
public class OutputUtil {


    public static void outputHmtlContent(HttpServletRequest req, HttpServletResponse resp, String title, String content) throws ServletException, IOException {
        System.out.println("doPost");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder htmltContentBuilder = new StringBuilder("<html><body><h1>");
        htmltContentBuilder.append(title).append("</h1><p>").append(content).append("</p></body></html>");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(htmltContentBuilder.toString());
        printWriter.flush();
    }
}
