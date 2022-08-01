package com.vayber.servlet.Role;


import com.alibaba.fastjson.JSONObject;
import com.vayber.pojo.Role;
import com.vayber.services.Role.roleServicesImpl;
import com.vayber.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/roleManagement.do")
public class roleServlet extends BaseServlet {
   public void queryList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       //获取角色列表
       roleServicesImpl roleService = new roleServicesImpl();
       List<Role> roleList = roleService.getRoleList();
       List<Role> roleNameList = new ArrayList<Role>();

       for (Role role: roleList) {
           Role r = new Role();
           r.setId(role.getId());
           r.setRoleName(role.getRoleName());
           roleNameList.add(r);
       }

       PrintWriter writer = resp.getWriter();
       String s = JSONObject.toJSONString(roleNameList);
       writer.print(s);
       writer.flush();
       writer.close();

   }
}
