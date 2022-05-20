package com.dao;

import java.sql.*;
import com.model.AdminSecret;

public class AdminSecretDao {
    public boolean saveSecret(AdminSecret a){
        try{
            String empno = a.getEmpid();
            String secret = a.getSecret();
            Connection conn = DbConnection.getConnection();
            String sql = "INSERT INTO public.\"UserSecret\"(empid,secret) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empno);
            ps.setString(2, secret);
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Not able to set into the database...");
            System.out.println(e);
        }
        return false;
    }
    public String getSecret(String accno){
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"AdminSecret\" where empid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accno);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return rs.getString("secret");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}