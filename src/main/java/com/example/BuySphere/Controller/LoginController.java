package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {
    @RequestMapping("/loginSubmitController")
    public JSONObject loginSubmit(@RequestParam(name = "username",defaultValue = "",required = false)String Username,
                                  @RequestParam(name = "password",defaultValue = "",required = false)String Password,
                                  HttpSession session){
        JSONObject obj = new JSONObject();
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLLogin.login(Username,Password,session);
        obj.putAll(object);
        return obj;
    }
}