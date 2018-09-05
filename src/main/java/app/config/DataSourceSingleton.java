package app.config;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public enum DataSourceSingleton {
    DATA_SOURCE;
    ResourceBundle resource = ResourceBundle.getBundle("database");
    private String DB_DATABASE = resource.getString("db.database");
    private String DB_USER = resource.getString("db.user");
    private String DB_PASSWORD = resource.getString("db.password");
    private String DB_HOST = resource.getString("db.host");
    private String DB_PORT = resource.getString("db.port");
    private  final DataSource dataSource;
    DataSourceSingleton() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_DATABASE);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);
        ds.setMaxIdle(5);
        ds.setMaxOpenPreparedStatements(50);
        this.dataSource = ds;
    }
    public DataSource getDataSource(){
        return dataSource;
    }

}
