package com.dao;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.model.Transaction;
import com.model.User;

public class TransactionDao {
    public List<Transaction> getAllTransaction(){
        List<Transaction> li = new ArrayList<>();
        try{
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM public.\"Transaction\"";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String src = rs.getString("src");
                String dest = rs.getString("dest");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                String time = rs.getString("time");
                Transaction t = new Transaction(id,src, dest, amount,date,time);
                li.add(t);
            }
        }catch(Exception e){
            System.out.println("Not able to write the transaction...");
            System.out.println(e);
        }
        return li;        
    }
    public List<Transaction> getTransactionsByDate(String date){
        List<Transaction> li = new ArrayList<>();
        try{
            Connection conn = DbConnection.getConnection();
            String sql = "SELECT * FROM public.\"Transaction\" WHERE date=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,LocalDate.parse(date));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String src = rs.getString("src");
                String dest = rs.getString("dest");
                double amount = rs.getDouble("amount");
                String time = rs.getString("time");
                Transaction t = new Transaction(id,src,dest,amount,date,time);
                li.add(t);
	        }
        }catch(Exception e){
            System.out.println("Not able to fetch transaction...");
            System.out.println(e);
       }
       return li;
   }
   public List<Transaction> getTransactionsByAccno(String accno){
       List<Transaction> li = new ArrayList<>();
       try{
        Connection conn = DbConnection.getConnection();
        String sql = "SELECT * FROM public.\"Transaction\" WHERE src=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,accno);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String src = rs.getString("src");
            String dest = rs.getString("dest");
            double amount = rs.getDouble("amount");
            String date = rs.getString("date");
            String time = rs.getString("time");
            Transaction t = new Transaction(id,src,dest,amount,date,time);
            li.add(t);
        }
       }catch(Exception e){
            System.out.println("Not able to fetch user...");
            System.out.println(e);
       }
       return li;
   }
    public boolean performTransaction(User src,User dest,double amount) throws SQLException {
        Connection conn = DbConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            if(src.getBalance()<amount){
                return false;
            }
            src.setBalance(src.getBalance()-amount);
            dest.setBalance(dest.getBalance()+amount);
            if(src==null||dest==null){
                System.out.println("Null Value Exception...");
            }
            UserDao ud = new UserDao();
            boolean s1 = ud.saveUser(src);
            if(!s1){
                throw new Exception("Not able to save the user");
            }
            boolean s2 =ud.saveUser(dest);
            if(!s2){
                throw new Exception("Not able to save the user");
            }
            String sql = "INSERT INTO public.\"Transaction\"(src,dest,amount,date,time) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,src.getAccno());
            ps.setString(2,dest.getAccno());
            ps.setDouble(3, amount);
            ps.setObject(4, LocalDate.now());
            ps.setObject(5, LocalTime.now());
            ps.execute();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            e.printStackTrace();
            System.out.println("Not able to perform transaction...");
        }
        return false;
    }
}
