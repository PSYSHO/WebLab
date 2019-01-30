package PO63pr.Simakin.wdad.data.storage;


import PO63pr.Simakin.wdad.data.managers.PreferencesManager;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.*;

public class DataSourceFactory {
    private static PreferencesManager pm = PreferencesManager.getInstance();
    public static javax.sql.DataSource createDataSource(){
        try {
            Class.forName(pm.getProperty(CLASSNAME));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(pm.getProperty(HOST_NAME));
        ds.setPortNumber(Integer.parseInt(pm.getProperty(PORT)));
        ds.setDatabaseName(pm.getProperty(DBNAME));
        ds.setUser((pm.getProperty(USER)));
        ds.setPassword((pm.getProperty(PASS)));

        try {
            ds.setServerTimezone("UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static javax.sql.DataSource createDataSource(String className, String
            driverType, String host, int port, String dbName, String user, String password){
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(host);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        ds.setUser(user);
        ds.setPassword(password);
        return ds;
    }
}
