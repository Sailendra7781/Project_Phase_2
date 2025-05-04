package com.example.BuySphere.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.*;
import static com.example.BuySphere.Classes.Utilities.getCurrentDateTime;

public class CommonClasses {
    public static String getCategoryDetails(){
        String query = "";
        String categoryId = "";
        String categoryName = "";
        String categoryDetails = "";
        try
        {
            query = "select ID,CategoryName from master_product_category";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
                ResultSet rs = selectQuery(query,stm);
                while(rs.next()){
                    categoryId = rs.getString("ID");
                    categoryName = rs.getString("CategoryName");
                    categoryDetails += "<option value='"+categoryId+"'>"+categoryName+"</option>\n";
                }
            }
        }
        catch(Exception e){

        }
        return categoryDetails;
    }
    public static Map<String,String> getCustomerInfo(String userId){
        String query = "";
        String userName = "";
        String userEmail = "";
        String userPhoneNumber  ="";
        Map<String,String> userDetails = new HashMap<String,String>();
        try{
            query = "select Name, EmailID, MobileNumber from registration where ID= ? ";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);)
            {
                stm.setString(1,userId);
                ResultSet rs = selectQuery(query,stm);
                if(rs.next())
                {
                    userName = rs.getString("Name");
                    userEmail = rs.getString("EmailID");
                    userPhoneNumber = rs.getString("MobileNumber");
                }
            }
            userDetails.put("Name",userName);
            userDetails.put("Email",userEmail);
            userDetails.put("PhoneNumber",userPhoneNumber);
        }
        catch(Exception e){

        }
        return userDetails;
    }

    public static long InsertIntoPaymentDetails(String userID,String customerName,String customerEmail,String customerPhoneNumber,String ProductDetailsId,String surl,String furl,String hashFormation,int status,String txnid){
        String iQuery = "";
        long lastId = 0l;
        try{
            iQuery = "Insert into paymentdetails(UserId, CustomerName, CustomerEmail, CustomerPhoneNumber, ProductDetailsId, surl, furl, RequestedDateTime, RequestedData, Status, MtxId) Values(?,?,?,?,?,?,?,?,?,?,?)";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
            PreparedStatement stm = con.prepareStatement(iQuery,Statement.RETURN_GENERATED_KEYS)){
                stm.setString(1,userID);
                stm.setString(2,customerName);
                stm.setString(3,customerEmail);
                stm.setString(4,customerPhoneNumber);
                stm.setString(5,ProductDetailsId);
                stm.setString(6,surl);
                stm.setString(7,furl);
                stm.setString(8,getCurrentDateTime());
                stm.setString(9,hashFormation);
                stm.setInt(10,status);
                stm.setString(11,txnid);
                lastId = insertQuery(iQuery,stm);
            }
        }
        catch(Exception e){

        }
        return lastId;
    }

    public static void UpdateToPaymentDetails(String lastId,String status,String bankcode,String mihpayid,String mode,String responseData){
        String uQuery = "";
        boolean updated;
        try{
            uQuery = "update paymentdetails set ResponseDateTime = ?, Status = ?, BankCode = ?, PayuTransactionId = ?, Mode = ?, ResponseData = ? where ID = ?";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(uQuery,Statement.RETURN_GENERATED_KEYS)){
                stm.setString(1,getCurrentDateTime());
                stm.setString(2,"1");
                stm.setString(3,bankcode);
                stm.setString(4,mihpayid);
                stm.setString(5,mode);
                stm.setString(6,responseData);
                stm.setString(7,lastId);
                updated = updateQuery(uQuery,stm);
            }
        }
        catch(Exception e){

        }
    }

    public static void UpdateCartDetails(String userId,String ipAddress){
        String uQuery = "";
        boolean updated;
        try {
            uQuery = "update temp_productdetails set IsPurchased = ?,UpdatedDate = ?,UpdatedIP = ? where UserId = ?;";
            try (Connection con = MYSQLAccess.dataSourcePool.getConnection();
                 PreparedStatement stm = con.prepareStatement(uQuery, Statement.RETURN_GENERATED_KEYS)) {
                stm.setString(1, "1");
                stm.setString(2, getCurrentDateTime());
                stm.setString(3, ipAddress);
                stm.setString(4, userId);
                updated = updateQuery(uQuery, stm);
            }
        } catch (Exception e) {

        }
    }

    public static long InsertIntoUserProductDetails(String userId){
        String iQuery = "";
        String sQuery = "";
        String ProductDetailsId = "";
        String UserProductQuantity = "";
        long lastId = 0l;
        try{
            sQuery = "select ProductDetailsId, Quantity from temp_productdetails where UserId = ?";
            try(Connection scon = MYSQLAccess.dataSourcePool.getConnection();
            PreparedStatement selectStm = scon.prepareStatement(sQuery,Statement.RETURN_GENERATED_KEYS)){
                selectStm.setString(1,userId);
                ResultSet rs = selectQuery(sQuery,selectStm);
                while(rs.next()){
                    ProductDetailsId = rs.getString("ProductDetailsId");
                    UserProductQuantity = rs.getString("Quantity");
                    iQuery = "Insert into userproductdetails(UserId, ProductDetailsId, CreatedDateTime, UserQuantity) values(?,?,?,?)";
                    try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                        PreparedStatement stm = con.prepareStatement(iQuery,Statement.RETURN_GENERATED_KEYS)){
                        stm.setString(1,userId);
                        stm.setString(2,ProductDetailsId);
                        stm.setString(3,getCurrentDateTime());
                        stm.setString(4,UserProductQuantity);
                        lastId = insertQuery(iQuery,stm);
                    }
                }
            }
        }
        catch(Exception e){

        }
        return lastId;
    }

    public static void UpdateNewProductQuantity(String userId,String ipAddress){
        String ProductQuantity = "";
        String UserProductQuantity = "";
        String ProductID = "";
        int updatedQuantity = 0;
        String sQuery = "";
        String uQuery = "";
        boolean updated;

        try{
            sQuery = "select t.ID,t.ProductQuantity,t1.Quantity from temp_productdetails t1 left join productdetails t on t1.ProductDetailsId = t.ID where t1.UserId = ? and IsPurchased = 0";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(sQuery,Statement.RETURN_GENERATED_KEYS)){
                stm.setString(1,userId);
                ResultSet rs = selectQuery(sQuery,stm);
                while(rs.next()){
                    ProductID = rs.getString("ID");
                    ProductQuantity = rs.getString("ProductQuantity");
                    UserProductQuantity = rs.getString("Quantity");
                    updatedQuantity = Integer.parseInt(ProductQuantity) - Integer.parseInt(UserProductQuantity);
                    uQuery = "update productdetails set ProductQuantity = ?, UpdatedDate = ?, UpdatedIP = ? where ID = ?";
                    try(Connection updateCon = MYSQLAccess.dataSourcePool.getConnection();
                        PreparedStatement updateStm = updateCon.prepareStatement(uQuery,Statement.RETURN_GENERATED_KEYS)){
                        updateStm.setString(1,Integer.toString(updatedQuantity));
                        updateStm.setString(2,getCurrentDateTime());
                        updateStm.setString(3,ipAddress);
                        updateStm.setString(4,ProductID);
                        updated = updateQuery(uQuery,updateStm);
                    }
                }
            }
        }
        catch(Exception e){

        }
    }

    public static HashMap<String,String> getLastIdAndUserId(String txnid){
        String countQuery = "";
        String ID = "";
        String userId = "";
        HashMap<String,String> values = new HashMap<>();
        try{
            countQuery = "Select ID,UserId from paymentdetails where MtxId = ?";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(countQuery,Statement.RETURN_GENERATED_KEYS)){
                stm.setString(1,txnid);
                ResultSet rs = selectQuery(countQuery,stm);
                if(rs.next()){
                    ID = rs.getString("ID");
                    userId = rs.getString("UserId");
                }
                values.put("UserId",userId);
                values.put("LastId",ID);
            }
        }
        catch(Exception e){

        }
        return values;
    }
}
