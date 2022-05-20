package com.dao;

import com.model.UserSecret;
import java.sql.*;
public class UserSecretDao {
    public boolean saveSecret(UserSecret us){
        try{
            String accno = us.getAccno();
            String secret = us.getSecret();
            Connection conn = DbConnection.getConnection();
            String sql = "INSERT INTO public.\"UserSecret\"(accno,secret) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accno);
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
            String sql = "SELECT * FROM public.\"UserSecret\" where accno=?";
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

