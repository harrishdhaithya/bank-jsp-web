package com.dao;
import java.util.*;
import java.sql.*;

import com.model.User;

public class UserDao {
    public List<User> getAllUsers(){
        List<User> li = new ArrayList<>();
        try{
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"User\"");
            while(rs.next()){
                String accno = rs.getString("accno");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double balance = rs.getDouble("balance");
                String passwordHash = rs.getString("passwordhash");
                User u = new User(accno, fname, lname, phone, email, passwordHash, balance);
                li.add(u);
            }
            return li;
        }catch(Exception e){
            System.out.println("Not able to fetch from the Database...");
            System.out.println(e);
        }
        return null;
    }
    public User getUserByAccno(String accno){
        User u = null;
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"User\" where accno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String accn = rs.getString("accno");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double balance = rs.getDouble("balance");
                String passwordHash = rs.getString("passwordhash");
                u = new User(accn, fname, lname, phone, email, passwordHash, balance);
            }
        }catch(Exception e){
            System.out.println("Not able to Fetch User from the database...");
        }
        return u;
    }
    public User getUserByEmail(String email){
        User u = null;
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"User\" where email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String accn = rs.getString("accno");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String phone = rs.getString("phone");
                String email1 = rs.getString("email");
                double balance = rs.getDouble("balance");
                String passwordHash = rs.getString("passwordhash");
                u = new User(accn, fname, lname, phone, email1, passwordHash, balance);
            }
        }catch(Exception e){
            System.out.println("Not able to Fetch User from the database...");
        }
        return u;
    }
    public boolean saveUser(User u){
        try{
            String accno = u.getAccno();
            String fname = u.getFname();
            String lname = u.getLname();
            String phone = u.getPhone();
            String email = u.getEmail();
            double balance = u.getBalance();
            String passwordHash = u.getPasswordHash();
            Connection conn = DbConnection.getConnection();
            User check = getUserByAccno(accno);
            if(check==null){
               
                String sql = "INSERT INTO public.\"User\""+
                "(accno, fname, lname, phone, email, balance, passwordhash)"+
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, accno);
                ps.setString(2,fname);
                ps.setString(3,lname);
                ps.setString(4, phone);
                ps.setString(5,email);
                ps.setDouble(6, balance);
                ps.setString(7,passwordHash);
                ps.execute();
                return true;
            }
            else
            {
                String sql = "UPDATE public.\"User\" SET fname=?, lname=?, phone=?, email=?, balance=?, passwordhash=? WHERE accno=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                // ps.setString(1,accno);
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,phone);
                ps.setString(4,email);
                ps.setDouble(5, balance);
                ps.setString(6,passwordHash);
                ps.setString(7, accno);
                // System.out.println(ps.toString());
                ps.execute();
                return true;
            }
        }catch(Exception e){
            System.out.println("Unable to Execute Query...");
            System.out.println(e);
        }
        return false;
    }
}
