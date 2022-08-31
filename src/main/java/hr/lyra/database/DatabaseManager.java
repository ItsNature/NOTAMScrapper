package hr.lyra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager() {
        this.connection = this.openConnection();

//        try {
//            this.createSchema();
//            this.createDatabase();
//        } catch (SQLException e) {
//            System.out.println("Failed to create database table");
//            throw new RuntimeException(e);
//        }
    }

    private Connection openConnection() {
        var url = "jdbc:postgresql://" +
            System.getenv("PG_HOSTNAME") + ":" +
            System.getenv("PG_PORT") + "/" +
            System.getenv("PG_DB_NAME");

        var props = new Properties();
        props.setProperty("user", System.getenv("PG_USERNAME"));
        props.setProperty("password", System.getenv("PG_PASSWORD"));
        props.setProperty("ssl", "true");

        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("Postgres connection failed");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        if(this.connection == null) {
            this.connection = this.openConnection();
        }

        return this.connection;
    }

//    private void createSchema() throws SQLException {
//        this.getConnection().prepareStatement(SqlQueries.SCHEMA_CREATE).executeUpdate();
//    }
//
//    private void createDatabase() throws SQLException {
//        this.getConnection().prepareStatement(SqlQueries.TABLE_CREATE).executeUpdate();
//    }
}
