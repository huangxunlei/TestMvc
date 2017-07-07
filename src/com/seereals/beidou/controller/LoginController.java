package com.seereals.beidou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getJson(HttpServletRequest req, HttpServletResponse rep) throws Exception {
        String userName = req.getParameter("userName");
        String password = req.getParameter("pwd");


        Map<String, Object> map = new HashMap<String, Object>();
        if (userName.equals("zhr") && password.equals("123")) {
            map.put("results", "login success");
        } else {
            map.put("results", "login fail");
        }
        List<Map<String, String>> list = new ArrayList<>();
        Map Map1 = new HashMap();
        list.add(Map1);
        Map1.put("key", "黄训磊");
        map.put("data", list);
        return map;

    }

}
