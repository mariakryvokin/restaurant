package app.config;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constants {
    private static BasicDataSource ds = new BasicDataSource();
    public static BasicDataSource getConnectedBasicDataSource(){
        return ds;
    }
    static {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");

            prop.load(input);
        } catch (IOException ex) {
            System.out.println("Exception initializing app");
            ex.printStackTrace();
            System.exit(1);
        }

        String DB_USER = prop.getProperty("db.user");
        String DB_PASSWORD = prop.getProperty("db.password");
        String DB_DATABASE = prop.getProperty("db.database");
        String DB_HOST = prop.getProperty("db.host");
        String DB_PORT = prop.getProperty("db.port");

        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ds.setUrl("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_DATABASE);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);

    }
}
