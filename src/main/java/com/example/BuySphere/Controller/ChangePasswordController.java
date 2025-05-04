package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class ChangePasswordController {
    @RequestMapping("/changePasswordController")
    public JSONObject changePasswordSubmit(
            @RequestParam(name="CurrentPassword",defaultValue = "",required = false)String currentPassword,
            @RequestParam(name="NewPassword",defaultValue = "",required = false)String newPassword,
            HttpServletRequest request, HttpSession session
            ){
        JSONObject jsonObject = new JSONObject();
        String userID = (String)session.getAttribute("userID");
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLChangePassword.changePassword(currentPassword,newPassword,ipAddress,userID);
        jsonObject.putAll(object);
        return jsonObject;
    }
}
