package com.solvd.laba.db.util;

import com.solvd.laba.db.dao.BranchDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileDAO {
    private static final Logger logger = LogManager.getLogger(ConfigFileDAO.class);
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
            dataSource.setMaxTotal(15);
            dataSource.setMaxIdle(10);
            dataSource.setMaxWaitMillis(200);
        } catch (IOException e) {
            logger.error("Error loading the property file");
        }
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
