package com.solvd.laba.db.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {
    private static final String CONFIG_FILE = "MyBatisConfig.xml";
    private static final Logger logger = LogManager.getLogger(MyBatisConfig.class);
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream input = Resources.getResourceAsStream(CONFIG_FILE)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }
}


