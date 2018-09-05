package app.config;


import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    static ResourceBundle resource = ResourceBundle.getBundle("database");
    static String DB_USER = resource.getString("db.user");
    static String DB_PASSWORD = resource.getString("db.password");
    static String DB_DATABASE = resource.getString("db.database");
    static String DB_HOST = resource.getString("db.host");
    static String DB_PORT = resource.getString("db.port");
    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();/*Constants.getConnectedBasicDataSource();*/
                    ds.setUrl("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_DATABASE);
                    ds.setUsername(DB_USER);
                    ds.setPassword(DB_PASSWORD);
                    ds.setMaxIdle(5);
                    ds.setMaxOpenPreparedStatements(50);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

}
