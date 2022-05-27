
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.Singleton.Singleton;
import com.dao.DbConnection;
import com.dao.UserDao;
import com.model.User;
import java.util.Random;
public class Automate {
    static boolean performTransaction(User src,User dest,double amount,String date) {
        Connection conn = DbConnection.getConnection();
        try {
            conn.setAutoCommit(false);
            // if(src.getBalance()<amount){
            //     return false;
            // }
            // src.setBalance(src.getBalance()-amount);
            // dest.setBalance(dest.getBalance()+amount);
            // if(src==null||dest==null){
            //     System.out.println("Null Value Exception...");
            // }
            // UserDao ud = new UserDao();
            // boolean s1 = ud.saveUser(src);
            // if(!s1){
            //     conn.rollback();
            //     conn.setAutoCommit(true);
            // }
            // boolean s2 =ud.saveUser(dest);
            // if(!s2){
            //     conn.rollback();
            //     conn.setAutoCommit(true);
            // }
            String sql = "INSERT INTO public.\"Transaction\"(src,dest,amount,date,time) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,src.getAccno());
            ps.setString(2,dest.getAccno());
            ps.setDouble(3, amount);
            // ps.setObject(4, LocalDate.now());
            ps.setObject(4, LocalDate.parse(date));
            ps.setObject(5, LocalTime.now());
            // ps.setObject(5, LocalTime.now());
            ps.execute();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                System.out.println(e1);
                return false;
            }
            e.printStackTrace();
            System.out.println("Not able to perform transaction...");
        }
        return false;
    }
    static boolean isLeapYear(int year){
        return ((year%4==0)&&(year%100!=0))||(year%400==0);
    }
    static String generateRandomDate(String[] years){
        Random r = new Random();
        int yr = r.nextInt(0,4);
        int month = r.nextInt(1,13);
        int day;
        if(month==2){
            if(isLeapYear(Integer.parseInt(years[yr]))){
                day=r.nextInt(1,30);
            }else{
                day=r.nextInt(1,29);
            }
        }
        else{
            day=r.nextInt(1,31);
        }
        LocalDate d = LocalDate.of(Integer.parseInt(years[yr]), month, day);
        return d.toString();
    }
    public static void main(String[] args) {
        UserDao uDao = Singleton.getUserDao();
        List<User> users = uDao.getAllUsers();
        String[] years = {"2019","2020","2021","2022"};
        int size = users.size();
        Random r = new Random();
        for(int i=0;i<8000;i++){
            int src = r.nextInt(0,size);
            int dest = r.nextInt(0,size);
            double amount = r.nextDouble(300,10000);
            String date = generateRandomDate(years);
            if(src!=dest){
                boolean success = performTransaction(users.get(src), users.get(dest), amount, date);
                if(!success){
                    System.out.println("Transaction Failed...");
                }else{
                    System.out.println("Transaction Successfull...");
                }
            }
        }
    }
}
