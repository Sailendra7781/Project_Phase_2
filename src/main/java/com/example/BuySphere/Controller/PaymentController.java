package com.example.BuySphere.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.BuySphere.Classes.CommonClasses.*;
import static com.example.BuySphere.SQLStatements.SQLCart.showCartDetails;

@RestController
public class PaymentController {
    @RequestMapping("/paymentDetails")
    public JSONObject paymentDetails(HttpSession session, HttpServletRequest request){
        JSONObject response = new JSONObject();
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        String userID = (String)session.getAttribute("userID");
        Map<String,String> userDetails = com.example.BuySphere.Classes.CommonClasses.getCustomerInfo(userID);
        String customerName = userDetails.get("Name");
        String customerEmail = userDetails.get("Email");
        String customerPhoneNumber = userDetails.get("PhoneNumber");
        Map<String,String> cartDetails = showCartDetails(userID);
        String ProductDetailsId = cartDetails.get("ProductDetailsId");
        String amount = cartDetails.get("Total");
        String txnid = com.example.BuySphere.Classes.Utilities.GenarateRandomNumberforTewelve();
        String surl = "http://localhost:8080/payment_success";
        String furl = "http://localhost:8080/payment_failure";
        long lastId = InsertIntoPaymentDetails(userID,customerName,customerEmail,customerPhoneNumber,ProductDetailsId,surl,furl,"",0,txnid);
        UpdateToPaymentDetails(Long.toString(lastId),"1","CNB01","","Card","");
        UpdateNewProductQuantity(userID,ipAddress);
        UpdateCartDetails(userID,ipAddress);
        long userLastId = InsertIntoUserProductDetails(userID);
        if(userLastId > 0){
            response.put("ResponseCode",1);
        }
        else{
            response.put("ResponseCode",2);
        }
        return response;
    }

}
