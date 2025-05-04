package com.example.BuySphere.Controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {
    @RequestMapping("/searchDetails")
    public static JSONObject searchDetails(@RequestParam(name = "productCategory",defaultValue = "",required = false)String productCategory,
                                           @RequestParam(name = "searchText",defaultValue = "",required = false)String searchText){
        JSONObject jsonObject = new JSONObject();
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLIndex.searchDetails(productCategory,searchText);
        jsonObject.putAll(object);
        return jsonObject;
    }

    @RequestMapping("/showProducts")
    public static JSONObject showProducts(){
        JSONObject jsonObject = new JSONObject();
        Map<String,String> object = com.example.BuySphere.SQLStatements.SQLIndex.showProducts();
        jsonObject.putAll(object);
        return jsonObject;
    }
}
