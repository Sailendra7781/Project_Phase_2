package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.Utilities.emEncrypt;
import static com.example.BuySphere.Classes.Utilities.getCurrentDateTime;

public class SQLSignUp {
    public static Map<String,String> formSubmit(String name, String emailID, String phoneNumber, String gender, String country, String address, String username, String password,String ipAddress){
        String query = "";
        String countQuery = "";
        long lastID = 0L;
        JSONObject jsonObject = new JSONObject();
        int count = 0;
        try {
            countQuery = "select count(ID) as count from registration where Username = ?";
            try (Connection newcon = MYSQLAccess.dataSourcePool.getConnection();
                 PreparedStatement newstm = newcon.prepareStatement(countQuery, Statement.RETURN_GENERATED_KEYS)) {
                newstm.setString(1, username);
                ResultSet rs = selectQuery(countQuery, newstm);
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
            if (count > 0) {
                jsonObject.put("ResponseCode", 2);
                jsonObject.put("ResponseMessage", "Username already exists");
            }
            else {
                query = "INSERT INTO registration (Name,EmailID,MobileNumber,Gender,Country,Address,Password,CreatedDate,CreatedIP,Username,userType) values (?,?,?,?,?,?,?,?,?,?,?)";
                try (Connection con = MYSQLAccess.dataSourcePool.getConnection();
                     PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    stm.setString(1, name);
                    stm.setString(2, emailID);
                    stm.setString(3, phoneNumber);
                    stm.setString(4, gender);
                    stm.setString(5, country);
                    stm.setString(6, address);
                    stm.setString(7, emEncrypt(password));
                    stm.setString(8, getCurrentDateTime());
                    stm.setString(9, ipAddress);
                    stm.setString(10, username);
                    stm.setString(11, "2");
                    lastID = MYSQLAccess.insertQuery(query, stm);
                    if (lastID > 0) {
                        jsonObject.put("ResponseCode", 1);
                        jsonObject.put("ResponseMessage", "Form Submitted Successfully");
                    } else {
                        jsonObject.put("ResponseCode", 2);
                        jsonObject.put("ResponseMessage", "Unable to submit the form.");
                    }
                }
            }


        }
        catch (Exception e) {

        }
        return jsonObject;
    }
}
