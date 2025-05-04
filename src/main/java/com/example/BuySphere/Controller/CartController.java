package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class CartController {
    @RequestMapping("/loadCartDetails")
    public static JSONObject showCartDetails(HttpSession session){
        JSONObject obj = new JSONObject();
        String userID = (String)session.getAttribute("userID");
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLCart.showCartDetails(userID);
        obj.putAll(object);
        return obj;
    }
}
