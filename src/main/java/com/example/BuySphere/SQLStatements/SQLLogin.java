package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.Utilities.emDecrypt;

public class SQLLogin {
    public static Map<String,String> login(String username, String password, HttpSession session){
        String query = "";
        String userID = "";
        int count = 0;
        JSONObject jsonObject = new JSONObject();
        String pwdQuery = "";
        String dbPassword = "";
        String UserType = "";
        try{query = "Select count(ID) as count from registration where Username = ?; ";
            try (Connection newcon = MYSQLAccess.dataSourcePool.getConnection();
                 PreparedStatement newstm = newcon.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                newstm.setString(1, username);
                ResultSet rs = selectQuery(query, newstm);
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
            if(count==0){
                jsonObject.put("ResponseCode",2);
                jsonObject.put("ResponseMessage","Username doesn't exist");
            }
            else{
                try{
                    pwdQuery = "select Password,ID,userType from registration where Username = ?;";
                    try (Connection pwdcon = MYSQLAccess.dataSourcePool.getConnection();
                         PreparedStatement pwdstm = pwdcon.prepareStatement(pwdQuery, Statement.RETURN_GENERATED_KEYS)) {
                        pwdstm.setString(1, username);
                        ResultSet result = selectQuery(pwdQuery, pwdstm);
                        if(result.next()){
                            dbPassword = emDecrypt(result.getString("Password")).trim();
                            userID  = result.getString("ID");
                            UserType = result.getString("userType");
                        }
                    }
                    if(dbPassword.equals(password)){
                        session.setAttribute("userID",userID);
                        session.setAttribute("UserTypeID",UserType);
                        jsonObject.put("ResponseCode",1);
                    }
                    else{
                        jsonObject.put("ResponseCode",2);
                        jsonObject.put("ResponseMessage","Password doesn't match");
                    }
                }
                catch(Exception e){

                }

            }
        }
        catch(Exception e){

        }
        return jsonObject;

    }
}
