package com.example.BuySphere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }

    @RequestMapping("/addproduct")
    public String AddProduct(){
        return "AddProduct";
    }

    @RequestMapping("/changepassword")
    public String changePassword(){
        return "changePassword";
    }

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @RequestMapping("/editproduct")
    public String editProduct(){
        return "editProduct";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }

    @RequestMapping("/register")
    public String register(){
        return "signup";
    }

    @RequestMapping("/products")
    public String products(){
        return "product";
    }

    @RequestMapping("/payment")
    public String payment(){
        return "paymentGateway";
    }

    @RequestMapping("/payment_success")
    public String paymentSuccess(){
        return "payment/success";
    }

    @RequestMapping("/payment_failure")
    public String paymentFailure(){
        return "payment/failure";
    }
}
