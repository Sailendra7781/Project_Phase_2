package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class productController {
    @RequestMapping("/loadProductDetails")
    public static JSONObject getProductDetails(HttpSession session) {
        JSONObject obj = new JSONObject();
        String UserTypeID = (String)session.getAttribute("UserTypeID");
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLProducts.getProductDetails(UserTypeID);
        obj.putAll(object);
        return obj;
    }
    @RequestMapping("/addToCartController")
    public static JSONObject addToCart(@RequestParam(name = "productID",defaultValue = "",required = false)String ProductId,
                                       @RequestParam(name = "ProductQuantity",defaultValue = "",required = false)String ProductQuantity,
                                       HttpSession session, HttpServletRequest request)
    {
        JSONObject obj = new JSONObject();
        String userID = (String)session.getAttribute("userID");
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLProducts.addToCartDetails(ProductId,userID,ProductQuantity,ipAddress);
        obj.putAll(object);
        return obj;
    }
}