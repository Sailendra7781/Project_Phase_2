package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;

public class SQLCart {
    public static Map<String,String> showCartDetails(String userID){
        String query = "";
        String ProductQuantity = "";
        String ProductPrice = "";
        String ProductName = "";
        String ProductDetailsId = "";
        int productTotal = 0;
        int totalValue = 0;
        String cartView = "";
        int count = 0;
        JSONObject jsonObject = new JSONObject();
        try
        {
            query = "select t.ProductName,ProductPrice,t1.Quantity, t1.ProductDetailsId from temp_productdetails t1 left join productdetails t on t1.ProductDetailsId = t.ID where t1.UserId = ? and isPurchased = 0";
            try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
            {

                stm.setString(1,userID);
                ResultSet rs = selectQuery(query,stm);
                cartView = "<h2>Your Cart</h2>\n" +
                        "    <table class=\"table\">\n" +
                        "        <thead>\n" +
                        "        <tr>\n" +
                        "            <th>Product</th>\n" +
                        "            <th>Price</th>\n" +
                        "            <th>Quantity</th>\n" +
                        "            <th>Total</th>\n" +
                        "        </tr>\n" +
                        "        </thead>\n" +
                        "        <tbody>\n";
                while(rs.next()){
                    count++;
                    ProductQuantity = rs.getString("Quantity");
                    ProductPrice = rs.getString("ProductPrice");
                    ProductName = rs.getString("ProductName");
                    if(ProductDetailsId==""){
                        ProductDetailsId = rs.getString("ProductDetailsId");
                    }
                    else{
                        ProductDetailsId += ","+rs.getString("ProductDetailsId");
                    }
                    productTotal = Integer.parseInt(ProductQuantity)*Integer.parseInt(ProductPrice);
                    totalValue += productTotal;
                    cartView += "<tr>\n" +
                            "            <td>"+ProductName+"</td>\n" +
                            "            <td>"+ProductPrice+"</td>\n" +
                            "            <td>"+ProductQuantity+"</td>\n" +
                            "            <td>"+productTotal+"</td>\n" +
                            "        </tr>\n";
                }
                cartView += "</tbody>\n" +
                        "    </table>\n" +
                        "    <div class=\"d-flex justify-content-between\">\n" +
                        "        <strong>Total: "+totalValue+"</strong>\n" +
                        "\n" +
                        "        <button type=\"button\" class=\"btn btn-success\" onclick=\"ProceedToPayment()\" >Proceed to Pay</button>\n" +
                        "\n" +
                        "    </div>";

            }
            if(count>0){
                jsonObject.put("ResponseCode",1);
                jsonObject.put("Result",cartView);
                jsonObject.put("Total",Integer.toString(totalValue));
                jsonObject.put("ProductDetailsId",ProductDetailsId);
            }
            else{
                jsonObject.put("ResponseCode",1);
                jsonObject.put("Result","No Products in Cart");
            }


        }
        catch(Exception e)
        {

        }

        return jsonObject;
    }
}
