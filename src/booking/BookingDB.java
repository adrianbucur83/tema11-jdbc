package booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingDB {

    public Connection connect() throws BookingDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("booking")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("123").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new BookingDbException("Could not load from DB.");
        }
    }


}
