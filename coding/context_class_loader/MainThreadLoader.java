package context_class_loader;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class MainThreadLoader {
    public static void main(String[] args) throws SQLException {
        System.out.println(Thread.currentThread().getContextClassLoader());
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    }
}

class TestDriver{
    protected void testRegister(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Class.forName()
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
