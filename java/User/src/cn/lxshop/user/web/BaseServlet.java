package cn.lxshop.user.web;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;


public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.setUTF8(req,resp);

        String requestURI = req.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,req,resp);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 统一给 请求和响应 设置UTF-8 编码
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    public void setUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }

    /**
     * 获取request 的全部信息
     *
     * @param request
     * @param response
     */
    public void getReuestAllMsg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("当前类被访问了  :" + BaseServlet.class);
        this.getRequestLineMsg(request, response);
        this.getRequestHeaderMsg(request, response);
        this.getRequestParameterMsg(request, response);
    }

    /**
     * 获取 request 请求行的全部信息
     *
     * @param request
     * @param response
     */
    public void getRequestLineMsg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("=============  获取请求行（请求方式  url   协议版本）的信息  ==============");
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("虚拟目录：" + request.getContextPath());
        System.out.println("资源路径:" + request.getServletPath());
        System.out.println("URI:" + request.getRequestURI());
        System.out.println("URL:" + request.getRequestURL());
        System.out.println("协议版本:" + request.getProtocol());
        System.out.println("客户端的IP地址:" + request.getRemoteAddr());
    }

    /**
     * 获取 request 请求头中全部的键值对
     *
     * @param request
     * @param response
     */
    public void getRequestHeaderMsg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==============  获取请求头的全部键值对  ===================");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + " = " + value);
        }
    }

    /**
     * 获取 request 中的全部参数信息
     *
     * @param request
     * @param response
     * @param useAND   当一个请求参数有多个值时  true 把数据显示成&格式  hobby=sport&hobby=study
     *                 false 把数据显示成数组格式
     */
    public void getRequestParameterMsg(HttpServletRequest request, HttpServletResponse response, Boolean useAND) {
        System.out.println("===============   获取请求参数  ===============");
//        （post请求下，从请求体获取请求参数）
        //        http://url?username=xuan1234&userpassword=123&hobby=sport&hobby=study
        Map<String, String[]> parameterMap = request.getParameterMap();
        //获取全部的请求参数 以及请求值
        Set<String> parameterNames = parameterMap.keySet();
        if (useAND) {
            this.useANDType(parameterNames, parameterMap);
        } else {
            this.useArrType(parameterNames, parameterMap);
        }


    }

    //使用数组格式
    public void useArrType(Set<String> parameterNames, Map<String, String[]> parameterMap) {
        for (String parameterName : parameterNames) {
            String[] values = parameterMap.get(parameterName);
            for (int i = 0; i < values.length; i++) {
                if (values.length > 1) {
                    if (i == 0) {
                        System.out.print(parameterName + " = {" + values[i] + ",");
                    } else if (i == values.length - 1) {
                        System.out.println(values[i] + "}");
                    } else {
                        System.out.print(values[i] + ",");
                    }
                } else {
                    System.out.println(parameterName + " = " + values[i]);
                }
            }
        }
    }

    //使用&格式
    public void useANDType(Set<String> parameterNames, Map<String, String[]> parameterMap) {
        for (String parameterName : parameterNames) {
            String[] values = parameterMap.get(parameterName);
            for (int i = 0; i < values.length; i++) {
                if (values.length > 1) {
                    if (i == values.length - 1) {
                        System.out.println(parameterName + "=" + values[i]);
                    } else {
                        System.out.print(parameterName + "=" + values[i] + " & ");
                    }
                } else {
                    System.out.println(parameterName + " = " + values[i]);
                }
            }
        }
    }

    /**
     * 获取 request 中的全部参数信息
     *
     * @param request
     * @param response
     */
    public void getRequestParameterMsg(HttpServletRequest request, HttpServletResponse response) {
        this.getRequestParameterMsg(request, response, false);
    }
}
