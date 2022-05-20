package com.dao;
import java.util.*;
import java.sql.*;
import com.model.Admin;

public class AdminDao {
    public List<Admin> getAllAdmins(){
        List<Admin> li = new ArrayList<>();
        try{
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM public.\"Admin\"";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String empid = rs.getString("empid");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String passwordhash = rs.getString("passwordhash");
                Admin a = new Admin(empid, name, phone, email, passwordhash);
                li.add(a);
            }
        }catch(Exception e){
            System.out.println("Not able to fetch from the database...");
            System.out.println(e);
        }
        return li;
    }
    public Admin getAdminByEmpId(String empid){
        Admin a = null;
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"Admin\" where empid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,empid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String empid1 = rs.getString("empid");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String passwordhash = rs.getString("passwordhash");
                a = new Admin(empid1, name, phone, email, passwordhash);
            }
        }catch(Exception e){
            System.out.println("Not able to Fetch from the Database...");
            System.out.println(e);
        }
        return a;
    }
    public Admin getAdminByEmail(String email){
        Admin a = null;
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"Admin\" where email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String empid = rs.getString("empid");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email1 = rs.getString("email");
                String passwordhash = rs.getString("passwordhash");
                a = new Admin(empid, name, phone, email1, passwordhash);
            }
        }catch(Exception e){
            System.out.println("Not able to Fetch from the Database...");
            System.out.println(e);
        }
        return a;
    }
    public boolean saveAdmin(Admin a){
        try{
            String empid = a.getEmpid();
            String name = a.getName();
            String phone = a.getPhone();
            String email = a.getEmail();
            String passwordhash = a.getPasswordHash();
            Connection conn = DbConnection.getConnection();
            Admin check = getAdminByEmpId(empid);
            if(check==null){
                String sql = "INSERT INTO public.\"Admin\"(empid, name, phone, email,passwordhash) VALUES(?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,empid);
                ps.setString(2,name);
                ps.setString(3,phone);
                ps.setString(4,email);
                ps.setString(5,passwordhash);
                ps.execute();
                return true;
            }else{
                String sql = "UPDATE public.\"Admin\" SET empid=?, name=?, phone=?, email=?, passwordhash=? WHERE empid=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,empid);
                ps.setString(2,name);
                ps.setString(3,phone);
                ps.setString(4,email);
                ps.setString(5,passwordhash);
                ps.setString(6,empid);
                ps.execute();
                return true;
            }
        }catch(Exception e){
            System.out.println("Not able to save the user into database...");
            System.out.println(e);
        }
        return false;
    }
}
