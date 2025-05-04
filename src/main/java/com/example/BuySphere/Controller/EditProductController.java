package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
@RestController
public class EditProductController {
    @RequestMapping("/showProductController")
    public JSONObject getProductData(@RequestParam(name = "productID",defaultValue = "",required = false)String ProductId){
        JSONObject obj = new JSONObject();
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLEditProduct.getProductData(ProductId);
        obj.putAll(object);
        return obj;
    }
    @RequestMapping("/updateQuantityController")
    public JSONObject updateQuantity(@RequestParam(name = "productID",defaultValue = "",required = false)String ProductId,
                                     @RequestParam(name = "newProductQuantity",defaultValue = "",required = false)String newProductQuantity,
                                     HttpServletRequest request,HttpSession session){
        JSONObject obj = new JSONObject();
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        String userID = (String)session.getAttribute("userID");
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLEditProduct.updateQuantity(ProductId,newProductQuantity,ipAddress,userID);
        obj.putAll(object);
        return obj;
    }
}
