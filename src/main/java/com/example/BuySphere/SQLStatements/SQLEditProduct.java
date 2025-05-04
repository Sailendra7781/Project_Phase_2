package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Base64;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.MYSQLAccess.updateQuery;
import static com.example.BuySphere.Classes.Utilities.getCurrentDateTime;

public class SQLEditProduct {
    public static Map<String,String> getProductData(String ProductId){
        String squery = "";
        String ProductName = "";
        String ProductPrice = "";
        String ProductDescription = "";
        String ProductQuantity = "";
        Blob FileDetails = null;
        String base64Image = "";
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try{
            squery = "Select ProductName,ProductPrice,ProductDescription,FileDetails,ProductQuantity from productdetails where ID = ?";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(squery, Statement.RETURN_GENERATED_KEYS)){
                stm.setString(1,ProductId);
                ResultSet rs = selectQuery(squery,stm);
                if(rs.next()){
                    ProductName = rs.getString("ProductName");
                    ProductPrice = rs.getString("ProductPrice");
                    ProductDescription = rs.getString("ProductDescription");
                    ProductQuantity = rs.getString("ProductQuantity");
                    FileDetails = rs.getBlob("FileDetails");
                    byte[] bdata = FileDetails.getBytes(1, (int) FileDetails.length());
                    base64Image = Base64.getEncoder().encodeToString(bdata);
                }
                result = "<div class=\"row m-2 p-2\">\n"+
                        "   <div class=\"col-md-6\">\n" +
                        "      <img style=\"width:100%;height:100%\" src=\"data:image/png;base64,"+base64Image+"\" class=\"img-fluid\" alt=\"Product Image\">\n" +
                        "   </div>"+
                        "   <div class=\"col-md-6\">\n" +
                        "      <h2>"+ProductName+"</h2>\n" +
                        "      <p class=\"lead\">"+ProductDescription+"</p>\n" +
                        "      <p><b>Price: "+ProductPrice+"</b></p>\n" +
                        "      <p>Present Product Quantity: "+ProductQuantity+"</p>\n" +
                        "       <div class=\"mb-3\">\n" +
                        "           <label for=\"productQuantity\" class=\"form-label\">Update Product Quantity</label>\n" +
                        "           <input type=\"number\" class=\"form-control\" id=\"productQuantity\" placeholder=\"Enter Product Quantity to update\">\n" +
                        "       </div>\n" +
                        "       <button type=\"button\" class=\"btn btn-primary w-100\" onclick=\"updateQuantity("+ProductId+")\">Update Quantity</button>\n" +
                        "   </div>\n"+
                        "</div>";

                jsonObject.put("ResponseCode", "1");
                jsonObject.put("productDetails", result);
            }
        }
        catch(Exception e){

        }
        return jsonObject;
    }

    public static Map<String,String> updateQuantity(String ProductId, String newProductQuantity, String ipAddress, String userID){
        String query = "";
        String squery = "";
        String productQuantity = "";
        boolean updated;
        JSONObject jsonObject = new JSONObject();
        try{
            squery = "Select ProductQuantity from productdetails where ID = ?";
            try(Connection newcon = MYSQLAccess.dataSourcePool.getConnection();
            PreparedStatement newstm = newcon.prepareStatement(squery,Statement.RETURN_GENERATED_KEYS)){
                newstm.setString(1,ProductId);
                ResultSet rs = selectQuery(squery,newstm);
                if(rs.next()){
                    productQuantity = rs.getString("ProductQuantity");
                }
            }
            try{
                int currentQuantity = Integer.parseInt(productQuantity);
                int newQuantity = Integer.parseInt(newProductQuantity);
                int updatedValue = currentQuantity + newQuantity;
                String updatedQuantity = Integer.toString(updatedValue);
                query = "Update productdetails set ProductQuantity = ?, UpdatedDate = ?, UpdatedIP = ?, UpdatedBy = ? where ID = ?";
                try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                    PreparedStatement stm = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
                    stm.setString(1,updatedQuantity);
                    stm.setString(2,getCurrentDateTime());
                    stm.setString(3,ipAddress);
                    stm.setString(4,userID);
                    stm.setString(5,ProductId);
                    updated = updateQuery(query,stm);
                    if(updated==true){
                        jsonObject.put("ResponseCode",1);
                        jsonObject.put("ResponseMessage","Quantity Updated Successfully");
                    }
                    else{
                        jsonObject.put("ResponseCode",2);
                        jsonObject.put("ResponseMessage","Retry Again.");
                    }
                }
            }
            catch(Exception e){

            }

        }
        catch(Exception e){

        }
        return jsonObject;
    }

}
