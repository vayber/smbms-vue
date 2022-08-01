package com.vayber.servlet.operate;

import com.alibaba.fastjson.JSONObject;
import com.vayber.pojo.User;
import com.vayber.services.user.userServices;
import com.vayber.services.user.userServicesImpl;
import com.vayber.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/operate.do")
public class operateServlet extends BaseServlet {

    //登录
    public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库进行匹配 调用业务层
        userServices userServices = new userServicesImpl();

        User user = userServices.Login(userCode, userPassword);    //如果查不到返回值为null

        if (user != null){      //查到了
            PrintWriter out = resp.getWriter();     //获取输出字符流
            String s = JSONObject.toJSONString(user);
            out.print(s);
            out.flush();
            out.close();
        }
    }


    //验证旧密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userId = req.getParameter("userId");

        String oldpassword = req.getParameter("oldpassword");       //从ajax里面传的参数

        //和数据库进行匹配 调用业务层
        userServices userServices = new userServicesImpl();
        User user = userServices.queryUser(Integer.parseInt(userId));
        String userPassword = user.getUserPassword();

        PrintWriter out = resp.getWriter();     //获取输出字符流
        String result = "";

            if (oldpassword.equals(userPassword)){
                result = "true";
            }else {
                result = "false";
            }
        String s = JSONObject.toJSONString(result);
        out.print(s);
        out.flush();
        out.close();




    }

    //修改密码
    public void savePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里面拿id
        String userId = req.getParameter("userId");

        String newPassword = req.getParameter("newPassword");


        userServices userServices = new userServicesImpl();
        boolean b = userServices.updateUserPassword(Integer.parseInt(userId), newPassword);

        PrintWriter writer = resp.getWriter();
        String result = "";


        if (b){
            result = "true";
        }else
        {
            result = "false";
        }
        String s = JSONObject.toJSONString(result);
        writer.print(s);
        writer.flush();
        writer.close();

    }

}
