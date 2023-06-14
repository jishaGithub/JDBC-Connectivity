package com.solvd.laba.db.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileDAO {
    private static BasicDataSource dataSource;

    public static void loadPropertyConfigFile() {
        Properties property = new Properties();
        try (InputStream dbPropertiesStream = ConfigFileDAO.class.getClassLoader().getResourceAsStream("db.properties")) {
            property.load(dbPropertiesStream);
            dataSource = new BasicDataSource();
            dataSource.setUrl(property.getProperty("db.url"));
            dataSource.setUsername(property.getProperty("db.username"));
            dataSource.setPassword(property.getProperty("db.password"));
            dataSource.setInitialSize(5);
            dataSource.setMaxTotal(10);
            dataSource.setMaxIdle(10);
        } catch (IOException e) {
            System.out.println("Error loading the property file");
        }
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
