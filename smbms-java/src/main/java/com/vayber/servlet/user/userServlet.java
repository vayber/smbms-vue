package com.vayber.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.vayber.pojo.User;
import com.vayber.services.user.userServicesImpl;
import com.vayber.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/management.do")
public class userServlet extends BaseServlet {


    //查询用户列表
    public void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRolesss");


        int queryUserRole = 0;

        //获取用户列表
        userServicesImpl userServices = new userServicesImpl();
        List<User> userList = null;


        if (queryUserName == null){ //给查询赋值
            queryUserName = "";
        }
        //int queryUserRole = 0   下拉框不选择默认是0 如果选了就走下面if 赋选择的值
        if (temp != null && !temp.equals("")){   //给下拉框赋值
            queryUserRole = Integer.parseInt(temp);
        }


        //获取用户列表展示
        userList = userServices.getUserList(queryUserName, queryUserRole);



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(userList);
        writer.print(s);
        writer.flush();
        writer.close();



    }

    //查询用户数量
    public void getUserCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRolesss");


        int queryUserRole = 0;

        //获取用户列表
        userServicesImpl userServices = new userServicesImpl();



        if (queryUserName == null){ //给查询赋值
            queryUserName = "";
        }
        //int queryUserRole = 0   下拉框不选择默认是0 如果选了就走下面if 赋选择的值
        if (temp != null && !temp.equals("")){   //给下拉框赋值
            queryUserRole = Integer.parseInt(temp);
        }


        //获取用户列表展示

        int userCount = userServices.getUserCount(queryUserName, queryUserRole);


        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(userCount);
        writer.print(s);
        writer.flush();
        writer.close();

    }

    //增加用户
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String userAge = req.getParameter("userAge");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String queryUserRole = req.getParameter("queryUserRole");

        String createdBy = req.getParameter("createdBy");


        java.util.Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String creationDate = simpleDateFormat.format(date);

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setAge(Integer.parseInt(userAge));
        user.setGender(Integer.parseInt(gender));
        user.setBirthday(Date.valueOf(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(queryUserRole));
        user.setCreatedBy(Long.parseLong(createdBy));
        user.setCreationDate(creationDate);


        userServicesImpl userServices = new userServicesImpl();

        String result = "";
        boolean b = true;

        try {
            b =  userServices.addUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (b){
            result = "add success";
        }else {
            result = "add failure";
        }



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(result);
        writer.print(s);
        writer.flush();
        writer.close();



    }

    //删除用户
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");

        userServicesImpl userServices = new userServicesImpl();
        boolean b = userServices.deleteUser(Integer.parseInt(uid));

        String result = "";


        if (b){
            result = "true";
        }else {
            result = "false";
        }



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(result);
        writer.print(s);
        writer.flush();
        writer.close();


    }

    //查询修改用户  已废弃
    public void modifyQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        userServicesImpl userServices = new userServicesImpl();
        User user = userServices.queryUser(Integer.parseInt(uid));



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(user);
        writer.print(s);
        writer.flush();
        writer.close();

    }
    //修改用户
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        String modifyBy = req.getParameter("modifyBy");

        java.util.Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDate = simpleDateFormat.format(date);


        User user = new User();
        user.setId(Long.parseLong(id));
        user.setUserName(userName);
        user.setGender(Integer.parseInt(gender));
        user.setAge(Integer.parseInt(age));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setModifyBy(Long.parseLong(modifyBy));
        user.setModifyDate(modifyDate);


        userServicesImpl userServices = new userServicesImpl();
        boolean b = true;
        String result = "";

        try {
             b = userServices.modifyUser(user);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        if (b){
            result = "alter success!";
        }else {

            result = "alter failure!";
        }


        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(result);
        writer.print(s);
        writer.flush();
        writer.close();

    }

    //查询用户  已废弃
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        userServicesImpl userServices = new userServicesImpl();
        User user = userServices.queryUser(Integer.parseInt(uid));

        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(user);
        writer.print(s);
        writer.flush();
        writer.close();

    }
}
