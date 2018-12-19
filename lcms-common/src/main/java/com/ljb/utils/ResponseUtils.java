package com.ljb.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/10<br>
 * 描述: <br>
 */
public class ResponseUtils {

    public static void ajaxResponse(HttpServletResponse response, Object respongseValue) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = response.getWriter();
            writer.write(JSON.toJSONString(respongseValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void responseFile(HttpServletResponse response, String fileName, byte[] data) {
        try {
            String extension=fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String MIME_TYPE=ConfigUtils.mimeConfig().getString(extension, "application/zip");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, CharEncoding.UTF_8) + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType(MIME_TYPE+";charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
