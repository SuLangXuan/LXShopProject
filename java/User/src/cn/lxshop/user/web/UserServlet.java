package cn.lxshop.user.web;

import cn.lxshop.user.dao.UserDao;
import cn.lxshop.user.dao.UserDaoImpl;
import cn.lxshop.user.model.LXUser;
import cn.lxshop.user.model.MsgModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {

    /**
     * 注册用户
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("注册用户");

        Map<String, String[]> parameterMap = request.getParameterMap();

        LXUser user = new LXUser();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //去注册
        UserDao dao = new UserDaoImpl();
        MsgModel registerM = dao.register(user);
        System.out.println(registerM+"=============================");
        if (registerM.getMsgcode() == 1){
            //注册成功
            response.getWriter().write(registerM.getMsg());
        }else if (registerM.getMsgcode()==0){
            //注册失败
            response.getWriter().write(registerM.getMsg());
        }


    }


}
