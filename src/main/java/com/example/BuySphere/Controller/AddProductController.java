package com.example.BuySphere.Controller;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.Utilities.getCurrentDateTime;

@RestController
public class AddProductController {
    @RequestMapping("/uploadFile")
    public JSONObject fileUpload(@RequestParam("AttachedFileValue") MultipartFile file,
                                 @RequestParam(name = "ProductName",defaultValue = "",required = false)String ProductName,
                                 @RequestParam(name = "ProductPrice",defaultValue = "",required = false)String ProductPrice,
                                 @RequestParam(name = "ProductQuantity",defaultValue = "",required = false)String ProductQuantity,
                                 @RequestParam(name = "ProductDescription",defaultValue = "",required = false)String ProductDescription,
                                 @RequestParam(name = "ProductCategory",defaultValue = "",required = false)String ProductCategory,
                                 HttpServletRequest request){
        InputStream fileValue=null;
        String base64EncodedData =null;
        int count = 0;
        String query = "";
        String countQuery = "";
        String ipAddress = com.example.BuySphere.Classes.Utilities.getClientIpAddress(request);
        long lastID = 0;
        JSONObject jsonObject = new JSONObject();
        try {
            countQuery = "select count(ID) as count from productdetails where ProductName = ?";
            try (Connection newcon = MYSQLAccess.dataSourcePool.getConnection();
                 PreparedStatement newstm = newcon.prepareStatement(countQuery, Statement.RETURN_GENERATED_KEYS)) {
                newstm.setString(1, ProductName);
                ResultSet rs = selectQuery(countQuery, newstm);
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
            if (count > 0) {
                jsonObject.put("ResponseCode", 2);
                jsonObject.put("ResponseMessage", "Product already exists");
            } else {
                try {
                    fileValue = file.getInputStream();
                    byte[] data = IOUtils.toByteArray(fileValue);
                    base64EncodedData = Base64.getEncoder().encodeToString(data);
                    byte[] fileBytes = Base64.getDecoder().decode(base64EncodedData);
                    query = "Insert into productdetails(ProductName,ProductPrice,ProductQuantity,ProductDescription,CreatedDate,CreatedIP,FileDetails,ProductCategoryID) values(?,?,?,?,?,?,?,?);";
                    try (Connection con = MYSQLAccess.dataSourcePool.getConnection();
                         PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                        stm.setString(1, ProductName);
                        stm.setString(2, ProductPrice);
                        stm.setString(3, ProductQuantity);
                        stm.setString(4, ProductDescription);
                        stm.setString(5, getCurrentDateTime());
                        stm.setString(6, ipAddress);
                        stm.setBytes(7, fileBytes);
                        stm.setString(8,ProductCategory);
                        lastID = MYSQLAccess.insertQuery(query, stm);
                        if (lastID > 0) {
                            jsonObject.put("ResponseCode", 1);
                            jsonObject.put("ResponseMessage", "Product Added Successfully");
                        } else {
                            jsonObject.put("ResponseCode", 2);
                            jsonObject.put("ResponseMessage", "Unable to add product.Please try again.");
                        }
                    }

                } catch (Exception e) {

                }
            }
        }
        catch(Exception e){

        }

        return jsonObject;
    }
}
