package com.seereals.beidou.controller;

import jdk.nashorn.internal.scripts.JS;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
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
        System.out.println(userName);
        System.out.println(password);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userName.equals("zhr") && password.equals("123")) {
            map.put("status", 1);
            map.put("msg", "成功");
        } else {
            map.put("status", 0);
            map.put("msg", "失败");
        }
        List<JSONObject> list = new ArrayList<>();
        Map Map1 = new HashMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("pwd", password);
        list.add(jsonObject);
        map.put("data", jsonObject);
        System.out.println(map.toString());
        return map;

    }

}
