package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Base64;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;

public class SQLIndex {
    public static Map<String,String> searchDetails(String productCategory, String searchText){
        JSONObject jsonObject = new JSONObject();
        String query = "";
        String ProductName = "";
        String ProductDescription = "";
        Blob FileDetails = null;
        String searchView = "";
        String CategoryName = "";
        try{
            if(searchText.equals("")){
                query = "SELECT t.ProductName,t.ProductDescription, t.FileDetails, t1.CategoryName\n" +
                        "FROM productdetails t\n" +
                        "LEFT JOIN master_product_category t1 ON t1.ID = t.productCategoryID\n" +
                        "WHERE t.productCategoryID = ?" ;
                try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                    PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                    stm.setString(1,productCategory);
                    ResultSet rs = selectQuery(query,stm);
                    while(rs.next()){
                        ProductDescription = rs.getString("ProductDescription");
                        ProductName = rs.getString("ProductName");
                        CategoryName = rs.getString("CategoryName");
                        FileDetails = rs.getBlob("FileDetails");
                        byte[] bdata = FileDetails.getBytes(1, (int) FileDetails.length());
                        String base64Image = Base64.getEncoder().encodeToString(bdata);
                        searchView += "<div class=\"col\">\n" +
                                "       <a href=\"\" class=\"card h-100 text-decoration-none\">\n"+
                                "           <img src=\"data:image/png;base64,"+base64Image+"\" class=\"card-img-top\" alt=\"Electronics\">\n" +
                                "           <div class=\"card-body\">\n" +
                                "               <h4 class=\"card-title\">"+CategoryName+"</h5>\n" +
                                "               <h5 class=\"card-title\">"+ProductName+"</h5>\n" +
                                "               <p class=\"card-text\">"+ProductDescription+"</p>\n" +
                                "           </div>\n" +
                                "       </a>\n"+
                                "</div>\n" ;
                    }
                }
            }
            else{
                query = "SELECT t.ProductName,t.ProductDescription, t.FileDetails, t1.CategoryName\n" +
                        "FROM productdetails t\n" +
                        "LEFT JOIN master_product_category t1 ON t1.ID = t.productCategoryID\n" +
                        "WHERE t.productCategoryID = ? \n" +
                        "AND t.ProductName LIKE ?;";
                try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                    PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                    stm.setString(1,productCategory);
                    stm.setString(2,"%"+searchText+"%");
                    ResultSet rs = selectQuery(query,stm);
                    while(rs.next()){
                        ProductDescription = rs.getString("ProductDescription");
                        ProductName = rs.getString("ProductName");
                        CategoryName = rs.getString("CategoryName");
                        FileDetails = rs.getBlob("FileDetails");
                        byte[] bdata = FileDetails.getBytes(1, (int) FileDetails.length());
                        String base64Image = Base64.getEncoder().encodeToString(bdata);
                        searchView += "<div class=\"col\">\n" +
                                "       <a href=\"\" class=\"card h-100 text-decoration-none\">\n"+
                                "           <img src=\"data:image/png;base64,"+base64Image+"\" class=\"card-img-top\" alt=\"Electronics\">\n" +
                                "           <div class=\"card-body\">\n" +
                                "               <h4 class=\"card-title\">"+CategoryName+"</h5>\n" +
                                "               <h5 class=\"card-title\">"+ProductName+"</h5>\n" +
                                "               <p class=\"card-text\">"+ProductDescription+"</p>\n" +
                                "           </div>\n" +
                                "       </a>\n"+
                                "</div>\n" ;
                    }
                }
            }
            if(searchView.equals("")){
                jsonObject.put("ResponseCode",2);
                jsonObject.put("Result","No products matching your search query");
            }
            else{
                jsonObject.put("ResponseCode",1);
                jsonObject.put("Result",searchView);
            }
        }
        catch(Exception e){

        }

        return jsonObject;
    }

    public static Map<String,String> showProducts(){
        JSONObject jsonObject = new JSONObject();
        String query = "";
        String ProductName = "";
        String ProductDescription = "";
        Blob FileDetails = null;
        String showProducts = "";
        String CategoryName = "";
        try{
            query = "SELECT t.ProductName,t.ProductDescription, t.FileDetails,t1.CategoryName \n" +
                    "FROM productdetails t\n" +
                    "LEFT JOIN master_product_category t1 ON t1.ID = t.productCategoryID;";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                ResultSet rs = selectQuery(query,stm);
                while(rs.next()){
                    ProductDescription = rs.getString("ProductDescription");
                    ProductName = rs.getString("ProductName");
                    CategoryName = rs.getString("CategoryName");
                    FileDetails = rs.getBlob("FileDetails");
                    byte[] bdata = FileDetails.getBytes(1, (int) FileDetails.length());
                    String base64Image = Base64.getEncoder().encodeToString(bdata);
                    showProducts += "<div class=\"col\">\n" +
                            "       <a href=\"\" class=\"card h-100 text-decoration-none\">\n"+
                            "           <img src=\"data:image/png;base64,"+base64Image+"\" class=\"card-img-top\" alt=\"Electronics\">\n" +
                            "           <div class=\"card-body\">\n" +
                            "               <h4 class=\"card-title\">"+CategoryName+"</h5>\n" +
                            "               <h5 class=\"card-title\">"+ProductName+"</h5>\n" +
                            "               <p class=\"card-text\">"+ProductDescription+"</p>\n" +
                            "           </div>\n" +
                            "       </a>\n"+
                            "</div>\n" ;
                }
            }
            if(showProducts.equals("")){
                jsonObject.put("ResponseCode",2);
                jsonObject.put("Result","No products matching your search query");
            }
            else{
                jsonObject.put("ResponseCode",1);
                jsonObject.put("Result",showProducts);
            }
        }
        catch(Exception e){

        }
        return jsonObject;
    }
}
