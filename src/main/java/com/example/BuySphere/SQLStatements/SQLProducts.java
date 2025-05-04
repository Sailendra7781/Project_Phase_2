package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Base64;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.MYSQLAccess.updateQuery;
import static com.example.BuySphere.Classes.Utilities.getCurrentDateTime;

public class SQLProducts{
    public static Map<String,String> getProductDetails(String UserTypeID)
    {
        String query = "";
        String productDetails = "";
        String productID = "";
        String ProductPrice = "";
        String ProductQuantity = "";
        String ProductDescription = "";
        String ProductName = "";
        Blob FileDetails = null;
        String ProductCategoryName = "";
        JSONObject obj = new JSONObject();
        int count = 0;
        try {
            query = "select t.ID,ProductPrice,ProductQuantity,ProductDescription,\n" +
                    "t.ProductName,FileDetails,t1.CategoryName as ProductCategoryName from productdetails t\n" +
                    "left join master_product_category t1 on t1.ID = t.ProductCategoryID";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
            {
                ResultSet rs = selectQuery(query,pst);
                while (rs.next())
                {
                    count++;
                    productID = rs.getString("ID");

                    ProductQuantity = rs.getString("ProductQuantity");
                    ProductPrice = rs.getString("ProductPrice");
                    ProductDescription = rs.getString("ProductDescription");
                    ProductName = rs.getString("ProductName");
                    FileDetails = rs.getBlob("FileDetails");
                    byte[] bdata = FileDetails.getBytes(1, (int) FileDetails.length());
                    String base64Image = Base64.getEncoder().encodeToString(bdata);
                    ProductCategoryName = rs.getString("ProductCategoryName");
                    if(UserTypeID.equals("1"))
                    {
                        productDetails +=
                                        "<div class=\"row m-2 p-2\">\n"+
                                        "   <div class=\"col-md-6\">\n" +
                                        "      <img style=\"width:100%;height:100%\" src=\"data:image/png;base64,"+base64Image+"\" class=\"img-fluid\" alt=\"Product Image\">\n" +
                                        "   </div>"+
                                        "   <div class=\"col-md-6\">\n" +
                                        "      <h3>"+ProductCategoryName+"</h3>\n" +
                                        "      <h2>"+ProductName+"</h2>\n" +
                                        "      <p class=\"lead\">"+ProductDescription+"</p>\n" +
                                        "      <p><b>Price: "+ProductPrice+"</b></p>\n" +
                                        "      <p>Quantity: "+ProductQuantity+"</p>\n" +
                                        "      <a><button class=\"btn btn-secondary\"  onclick=\"saveProductId("+productID+")\">Edit Product</button></a>\n" +
                                        "   </div>\n"+
                                        "</div>";
                    }
                    else
                    {
                        productDetails +=
                                "<div class=\"row m-2 p-2\">\n"+
                                        "<div class=\"col-md-6\">\n" +
                                        "      <img style=\"width:100%;height:100%\" src=\"data:image/png;base64,"+base64Image+"\" class=\"img-fluid\" alt=\"Product Image\">\n" +
                                        "    </div>"+
                                        "    <div class=\"col-md-6\">\n" +
                                        "      <h3>"+ProductCategoryName+"</h3>\n" +
                                        "      <h2>"+ProductName+"</h2>\n" +
                                        "      <p class=\"lead\">"+ProductDescription+"</p>\n" +
                                        "      <p><b>Price: "+ProductPrice+"</b></p>\n" +
                                        "      <p>Quantity: "+ProductQuantity+"</p>\n" +
                                        "      <div class=\"row\">\n"+
                                        "           <div class=\"col-md-6\">\n"+
                                        "               <input type=\"number\" class=\"form-control\" id=\"productQuantity"+productID+"\" placeholder=\"Enter Quantity\">\n" +
                                        "           </div>\n"+
                                        "           <div class=\"col-md-6\">\n"+
                                        "               <a><button type=\"button\" class=\"btn btn-primary\" onclick=\"addToCart("+productID+")\">Add to Cart</button></a>\n" +
                                        "           </div>\n"+
                                        "       </div>\n"+
                                        "    </div>\n"+
                                        "    </div>";
                    }
                }
                if(count > 0){
                    obj.put("ResponseCode", "1");
                    obj.put("productDetails", productDetails);
                }
                else{
                    obj.put("ResponseCode", "2");
                    obj.put("productDetails", "No products to display");
                }
            }
        }
        catch (Exception e)
        {

        }
        return obj;
    }

    public static Map<String,String> addToCartDetails(String ProductID,String userID,String ProductQuantity,String ipAddress)
    {
        String squery = "";
        String countQuery = "";
        String DBProductQuantity = "";
        String iQuery = "";
        String DBCartQuantity = "";
        String uQuery = "";
        String updatedQuantity = "";
        int DBQuantityValue = 0;
        int QuantityValue = 0;
        int updatedQuantityValue = 0;
        int count = 0;
        long lastId = 0;
        int tempProductDetailsId = 0;
        JSONObject jsonObject = new JSONObject();
        try
        {
            squery = "Select ProductQuantity from productdetails where ID = ?";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(squery, Statement.RETURN_GENERATED_KEYS))
            {
                stm.setString(1,ProductID);
                ResultSet rs = selectQuery(squery,stm);
                if(rs.next())
                {
                    DBProductQuantity = rs.getString("ProductQuantity");
                }
                DBQuantityValue = Integer.parseInt(DBProductQuantity);
                QuantityValue = Integer.parseInt(ProductQuantity);
            }
            if(QuantityValue > DBQuantityValue)
            {
                jsonObject.put("ResponseCode",2);
                jsonObject.put("ResponseMessage","Please Enter Value below Available Quantity");
            }
            else
            {
                countQuery = "select count(ID) as count,ID,Quantity from temp_productdetails where ProductDetailsId = ? and UserId = ? and isPurchased = '0' group by ID";
                try(Connection countCon = MYSQLAccess.dataSourcePool.getConnection();
                    PreparedStatement countStm = countCon.prepareStatement(countQuery,Statement.RETURN_GENERATED_KEYS))
                {
                    countStm.setString(1, ProductID);
                    countStm.setString(2, userID);
                    ResultSet rs = selectQuery(countQuery, countStm);
                    if (rs.next())
                    {
                        count = rs.getInt("count");
                        tempProductDetailsId = rs.getInt("ID");
                        DBCartQuantity = rs.getString("Quantity");
                    }
                }
                if(count>0)
                {
                    updatedQuantityValue = Integer.parseInt(DBCartQuantity)+QuantityValue;
                    if(updatedQuantityValue > DBQuantityValue)
                    {
                        jsonObject.put("ResponseCode",2);
                        jsonObject.put("ResponseMessage","Please Enter Value below Available Quantity");
                    }
                    else
                    {
                        updatedQuantity = Integer.toString(updatedQuantityValue);
                        uQuery = "Update temp_productdetails set Quantity = ?, UpdatedDate = ?, UpdatedIP = ? where ID = ?";
                        try(Connection updateCon = MYSQLAccess.dataSourcePool.getConnection();
                            PreparedStatement updateStm = updateCon.prepareStatement(uQuery, Statement.RETURN_GENERATED_KEYS))
                        {
                            updateStm.setString(1, updatedQuantity);
                            updateStm.setString(2, getCurrentDateTime());
                            updateStm.setString(3, ipAddress);
                            updateStm.setInt(4, tempProductDetailsId);
                            boolean updated = updateQuery(uQuery, updateStm);
                            if (updated == true)
                            {
                                jsonObject.put("ResponseCode", 1);
                                jsonObject.put("ResponseMessage", "Updated to Cart Successfully");
                            } else
                            {
                                jsonObject.put("ResponseCode", 2);
                                jsonObject.put("ResponseMessage", "Retry Again.");
                            }
                        }
                    }
                }
                else
                {
                    iQuery = "Insert into temp_productdetails(UserId,ProductDetailsId,Quantity,Createddate,CreatedIP,IsPurchased) values(?,?,?,?,?,?)";
                    try(Connection insertCon = MYSQLAccess.dataSourcePool.getConnection();
                        PreparedStatement insertStm = insertCon.prepareStatement(iQuery, Statement.RETURN_GENERATED_KEYS))
                    {
                        insertStm.setString(1, userID);
                        insertStm.setString(2, ProductID);
                        insertStm.setString(3, ProductQuantity);
                        insertStm.setString(4, getCurrentDateTime());
                        insertStm.setString(5, ipAddress);
                        insertStm.setString(6, "0");
                        lastId = MYSQLAccess.insertQuery(iQuery, insertStm);
                        if (lastId > 0){
                            jsonObject.put("ResponseCode", 1);
                            jsonObject.put("ResponseMessage", "Added to Cart Successfully");
                        }else{
                            jsonObject.put("ResponseCode", 2);
                            jsonObject.put("ResponseMessage", "Retry Again.");
                        }
                    }

                }
            }

        }
        catch(Exception e)
        {

        }
        return jsonObject;
    }
}