package com.example.BuySphere.SQLStatements;

import com.example.BuySphere.Classes.MYSQLAccess;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import static com.example.BuySphere.Classes.MYSQLAccess.selectQuery;
import static com.example.BuySphere.Classes.MYSQLAccess.updateQuery;
import static com.example.BuySphere.Classes.Utilities.*;

public class SQLChangePassword {
    public static Map<String,String> changePassword(String currentPassword,String newPassword, String ipAddress,String userID){
        String query = "";
        String sQuery = "";
        String DBpassword = "";
        JSONObject jsonObject = new JSONObject();
        try{
            sQuery = "select Password from registration where ID = ?";
            try(Connection newcon = MYSQLAccess.dataSourcePool.getConnection();
            PreparedStatement newstm = newcon.prepareStatement(sQuery, Statement.RETURN_GENERATED_KEYS);){
                newstm.setString(1,userID);
                ResultSet rs = selectQuery(sQuery,newstm);
                if(rs.next()){
                    DBpassword = emDecrypt(rs.getString("Password")).trim();
                }
                if(DBpassword.equals(currentPassword)){
                    try{
                        query = "update registration set Password = ?,updatedDate = ?,updatedIP=? where ID = ?";
                        try(Connection con = MYSQLAccess.dataSourcePool.getConnection();
                            PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                            stm.setString(1,emEncrypt(newPassword));
                            stm.setString(2,getCurrentDateTime());
                            stm.setString(3,ipAddress);
                            stm.setString(4,userID);
                            boolean updated = updateQuery(query,stm);
                            if(updated==true){
                                jsonObject.put("ResponseCode",1);
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
                else{
                    jsonObject.put("ResponseCode",2);
                    jsonObject.put("ResponseMessage","Invalid Current Password");
                }
            }
            catch (Exception e){

            }

        }
        catch (Exception e){

        }

        return jsonObject;
    }
}
