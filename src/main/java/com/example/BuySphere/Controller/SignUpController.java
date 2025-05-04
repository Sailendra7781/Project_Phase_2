package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class SignUpController {

    @RequestMapping("/formSubmitController")
    public JSONObject formSubmit(@RequestParam(name = "name",defaultValue = "",required = false)String name,
                                 @RequestParam(name = "emailID",defaultValue = "",required = false)String emailID,
                                 @RequestParam(name = "phoneNumber",defaultValue = "",required = false)String phoneNumber,
                                 @RequestParam(name = "Gender",defaultValue = "",required = false)String gender,
                                 @RequestParam(name = "country",defaultValue = "",required = false)String country,
                                 @RequestParam(name = "address",defaultValue = "",required = false)String address,
                                 @RequestParam(name = "username",defaultValue = "",required = false)String username,
                                 @RequestParam(name = "password",defaultValue = "",required = false)String password,
                                 @RequestParam(name = "confirmpassword",defaultValue = "",required = false)String confirmPassword,
                                 HttpServletRequest request){
        //System.out.println(name+" "+emailID+" "+phoneNumber+" "+gender+" "+country+" "+address+" "+username+" "+password+" "+confirmPassword);
        JSONObject obj = new JSONObject();
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLSignUp.formSubmit(name,emailID,phoneNumber,gender,country,address,username,password,ipAddress);
        obj.putAll(object);
        return obj;
    }
}
