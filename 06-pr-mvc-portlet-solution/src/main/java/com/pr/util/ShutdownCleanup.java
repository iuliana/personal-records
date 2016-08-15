package com.pr.util;

/**
 * Created by iuliana.cosmina on 4/26/15.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class ShutdownCleanup implements javax.servlet.ServletContextListener {
    protected static final Logger logger = LoggerFactory.getLogger(ShutdownCleanup.class);

    // On application shutdown
    public void contextDestroyed(ServletContextEvent event) {
        deregisterJdbcDrivers();
        logger.info("Context destroyed.");
    }

    private void deregisterJdbcDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            // We search for driver that was loaded by this web application
            if (driver.getClass().getClassLoader() == this.getClass().getClassLoader()) {
                try {
                    DriverManager.deregisterDriver(driver);
                    logger.info("Deregistered '{}' JDBC driver.", driver);
                } catch (SQLException e) {
                    logger.error("Failed to deregister '{}' JDBC driver.", driver, e);
                }
            }
        }
    }

    public void contextInitialized(ServletContextEvent event) {
        // Nothing to do here
        logger.info("Context initialized.");
    }

}