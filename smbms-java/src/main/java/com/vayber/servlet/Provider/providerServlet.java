package com.vayber.servlet.Provider;

import com.alibaba.fastjson.JSONObject;
import com.vayber.pojo.Provider;
import com.vayber.services.Provider.providerServicesImpl;
import com.vayber.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/providerManagement.do")
public class providerServlet extends BaseServlet {

    //获取供应商名称列表
    public void getNameList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        providerServicesImpl providerServices = new providerServicesImpl();
        List<Provider> providerList = providerServices.getProviderList();

        List<Provider> providerNameList = new ArrayList<Provider>();

        for ( Provider p : providerList) {
            Provider provider = new Provider();
            provider.setId(p.getId());
            provider.setProName(p.getProName());
            providerNameList.add(provider);
        }


        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(providerNameList);
        writer.print(s);
        writer.flush();
        writer.close();

    }

    //获取供应商列表
    public void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从前端获取数据
        String queryProCode = req.getParameter("queryProCode"); //查询供应商编码
        String queryProName = req.getParameter("queryProName"); //查询供应商名称


        //给查询赋值
        if (queryProCode == null){
            queryProCode = "";
        }
        if (queryProName == null){
            queryProName = "";
        }


        providerServicesImpl providerServices = new providerServicesImpl();

        //获取供应商列表
        List<Provider> providerList = providerServices.getProviderList(queryProCode,queryProName);



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(providerList);
        writer.print(s);
        writer.flush();
        writer.close();

    }

    //获取供应商数量
    public void getProviderCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从前端获取数据
        String queryProCode = req.getParameter("queryProCode"); //查询供应商编码
        String queryProName = req.getParameter("queryProName"); //查询供应商名称


        //给查询赋值
        if (queryProCode == null){
            queryProCode = "";
        }
        if (queryProName == null){
            queryProName = "";
        }


        providerServicesImpl providerServices = new providerServicesImpl();


        //获取供应商总数
        int totalCount = providerServices.getProviderCount(queryProCode, queryProName);



        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(totalCount);
        writer.print(s);
        writer.flush();
        writer.close();

    }


    //添加供应商
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proDesc = req.getParameter("proDesc");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String createdBy = req.getParameter("createdBy");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creationDate = simpleDateFormat.format(date);

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setCreatedBy(Long.parseLong(createdBy));
        provider.setCreationDate(creationDate);

        providerServicesImpl providerServices = new providerServicesImpl();

        String result = "";
        boolean b = true;

        try {
           b =  providerServices.addProvider(provider);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

    //删除供应商
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String proid = req.getParameter("proid");



        providerServicesImpl providerServices = new providerServicesImpl();
        boolean b = false;
        try {
            b = providerServices.deleteProvider(Integer.parseInt(proid));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String result = "";

        if (b){
            result = "delete success";
        }else {
           result = "delete failure";
        }


        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(result);
        writer.print(s);
        writer.flush();
        writer.close();
    }

    //修改查询  已废弃
    public void modifyQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("proid");
        providerServicesImpl providerServices = new providerServicesImpl();
        Provider provider = null;
        try {
             provider = providerServices.getQueryProvider(Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(provider);
        writer.print(s);
        writer.flush();
        writer.close();
    }
    //修改供应商
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proDesc = req.getParameter("proDesc");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String modifyBy = req.getParameter("modifyBy");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDate = simpleDateFormat.format(date);

        Provider provider = new Provider();
        provider.setId(Long.parseLong(id));
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setModifyBy(Long.parseLong(modifyBy));
        provider.setModifyDate(modifyDate);

        providerServicesImpl providerServices = new providerServicesImpl();
        boolean b = true;
        String result = "";

        try {
            b = providerServices.modifyProvider(provider);
        } catch (SQLException throwables) {
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

    //查询供应商 已废弃
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("proid");

        providerServicesImpl providerServices = new providerServicesImpl();
        Provider provider = null;
        try {
            provider = providerServices.getQueryProvider(Integer.parseInt(id));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(provider);
        writer.print(s);
        writer.flush();
        writer.close();
    }
}
