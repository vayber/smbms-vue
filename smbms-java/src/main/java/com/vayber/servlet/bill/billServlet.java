package com.vayber.servlet.bill;

import com.alibaba.fastjson.JSONObject;
import com.vayber.pojo.Bill;
import com.vayber.services.Bill.billServicesImpl;
import com.vayber.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/billManagement.do")
public class billServlet extends BaseServlet {

    public void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从前端获取数据
        String queryProductName = req.getParameter("queryProductName"); //查询商品
        String queryProvider = req.getParameter("queryProvider");       //查询供应商
        String queryIsPayment = req.getParameter("queryIsPayment");     //查询是否付款



        int queryProviderId = 0;
        int queryIsPaymentNum = 0;

        //获取订单列表
        billServicesImpl billServices = new billServicesImpl();
        List<Bill> billList = null;



        if (queryProductName == null){  //给查询赋值
            queryProductName = "";
        }
        //     下拉框不选择默认是null 如果选了就走下面if 赋选择的值
        if (queryProvider != null && !queryProvider.equals("")){
            queryProviderId = Integer.parseInt(queryProvider);
        }
        if (queryIsPayment != null && !queryIsPayment.equals("")){
            queryIsPaymentNum = Integer.parseInt(queryIsPayment);
        }


        //获取账单列表展示
         billList = billServices.getBillList(queryProductName, queryProviderId, queryIsPaymentNum);



        PrintWriter out = resp.getWriter();     //获取输出字符流

        String s = JSONObject.toJSONString(billList);
        out.print(s);
        out.flush();
        out.close();

    }


    public void getBillCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //从前端获取数据
        String queryProductName = req.getParameter("queryProductName"); //查询商品
        String queryProvider = req.getParameter("queryProvider");       //查询供应商
        String queryIsPayment = req.getParameter("queryIsPayment");     //查询是否付款


        int queryProviderId = 0;
        int queryIsPaymentNum = 0;

        if (queryProductName == null){  //给查询赋值
            queryProductName = "";
        }
        //   int queryProviderId = 0  下拉框不选择默认是0 如果选了就走下面if 赋选择的值
        if (queryProvider != null && !queryProvider.equals("")){
            queryProviderId = Integer.parseInt(queryProvider);
        }
        if (queryIsPayment != null && !queryIsPayment.equals("")){
            queryIsPaymentNum = Integer.parseInt(queryIsPayment);
        }

        PrintWriter out = resp.getWriter();     //获取输出字符流

        billServicesImpl billServices = new billServicesImpl();
        int billCount = billServices.getBillCount(queryProductName, queryProviderId, queryIsPaymentNum);

        String s = JSONObject.toJSONString(billCount);
        out.print(s);
        out.flush();
        out.close();
    }


    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String createdBy = req.getParameter("createdBy");
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creationDate = simpleDateFormat.format(date);

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setCreationDate(creationDate);
        bill.setCreatedBy(Long.parseLong(createdBy));

        billServicesImpl billServices = new billServicesImpl();
            billServices.addBill(bill);


        PrintWriter out = resp.getWriter();     //获取输出字符流

        String s = JSONObject.toJSONString("add success!");
        out.print(s);
        out.flush();
        out.close();
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String billId = req.getParameter("billid");


        billServicesImpl billServices = new billServicesImpl();
        boolean b = billServices.deleteBill(Integer.parseInt(billId));

        PrintWriter out = resp.getWriter();

        String result = "";

        if (b){
           result = "delete success!";
        }else {

            result = "delete failure!";
        }

        String s = JSONObject.toJSONString(result);
        out.print(s);
        out.flush();
        out.close();

    }
    //已废弃
    public void modifyQuery(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        String id = req.getParameter("billid");

        billServicesImpl billServices = new billServicesImpl();
        Bill bill = billServices.queryBill(Integer.parseInt(id));

        PrintWriter out = resp.getWriter();     //获取输出字符流

        String s = JSONObject.toJSONString(bill);
        out.print(s);
        out.flush();
        out.close();
    }

    public void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String id = req.getParameter("id");
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");
        String modifyBy = req.getParameter("modifyBy");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDate = simpleDateFormat.format(date);

        Bill bill = new Bill();
        bill.setId(Long.parseLong(id));
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setModifyBy(Long.parseLong(modifyBy));
        bill.setModifyDate(modifyDate);


        billServicesImpl billServices = new billServicesImpl();
         billServices.modifyBill(bill);

        PrintWriter out = resp.getWriter();     //获取输出字符流

        String s = JSONObject.toJSONString("alter success!");
        out.print(s);
        out.flush();
        out.close();

    }

    //已废弃
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("billid");

        billServicesImpl billServices = new billServicesImpl();
        Bill bill = billServices.queryBill(Integer.parseInt(id));
        PrintWriter out = resp.getWriter();     //获取输出字符流

        String s = JSONObject.toJSONString(bill);
        out.print(s);
        out.flush();
        out.close();
    }
}
