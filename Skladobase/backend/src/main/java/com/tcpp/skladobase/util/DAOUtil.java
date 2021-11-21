package com.tcpp.skladobase.util;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.MessagesForException;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DAOUtil {

    private static final String DRIVER_PATH_PROPERTY = "org.postgresql.Driver";

    private static final Logger log = Logger.getLogger(DAOUtil.class);

    private DAOUtil() {
    }

    public static Connection getDataSource(String URL, String USERNAME, String PASSWORD)
            throws DAOConfigException {
        try {
            Class.forName(DRIVER_PATH_PROPERTY);

            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e){
            log.error(MessagesForException.DAO_CONFIG_EXCEPTION + e.getMessage());
            throw new DAOConfigException(MessagesForException.DAO_CONFIG_EXCEPTION,e);
        }
    }

}
