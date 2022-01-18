package com.Liang.servlet.user;

import com.Liang.pojo.User;
import com.Liang.service.user.UserService;
import com.Liang.service.user.UserServiceImpl;
import com.Liang.util.Constants;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("savepwd")&&!StringUtils.isNullOrEmpty(method)){
            this.updatePwd(req, resp);
        }else if(method.equals("pwdmodify")&&!StringUtils.isNullOrEmpty(method)){
            this.pwdModify(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        //从sessi获取用户id
        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String rnewpassword = req.getParameter("rnewpassword");

        if (user != null &&!StringUtils.isNullOrEmpty(rnewpassword)) {
            UserService userService = new UserServiceImpl();
            boolean b = userService.updatePwd(((User) user).getId(), rnewpassword);
            if (b) {
                req.setAttribute("message", "修改密码成功，请退出重新登录");
                //移除当前session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "修改密码失败");
            }
        }else{
            req.setAttribute("message", "新密码有问题");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证旧密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //使用Map存结果集
        Map<String, String> resultMap = new HashMap<String, String>();
        if(user == null){//session过期了
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//输入旧密码为空
            resultMap.put("result", "error");
        }else{
            String userPassword = ((User) user).getUserPassword();
            if(oldpassword.equals(userPassword)){
                resultMap.put("result", "true");
            }else{
                resultMap.put("result", "false");
            }
        }

        //将Map转化为json
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
