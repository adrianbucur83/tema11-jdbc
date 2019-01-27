package booking;

import org.junit.*;

import java.sql.*;

public class InstertTest extends BookingDBTest {

    private BookingDBTest db;
    private SQLAccomodationDAO accomodationsDAO;

    @BeforeClass
    public static void initTests() throws BookingDbException, SQLException {

        BookingDBTest.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingDbException, SQLException {
        BookingDBTest.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new BookingDBTest();
        accomodationsDAO = new SQLAccomodationDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void insertDataIntoTables() throws BookingDbException, SQLException, ClassNotFoundException {


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/booking_test", "postgres", "123");

     /*   String testAcc = new StringBuilder()
                .append("select * from accomodation ").toString();
        PreparedStatement testAccStmt = con.prepareStatement(testAcc);
        ResultSet rsAcc = testAccStmt.executeQuery();
        while (rsAcc.next()){
            System.out.println(rsAcc.getInt("id"));
        }
*/
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("b & b added on last test");
        accomodation1.setBedType("kingsize");
        accomodation1.setMaxGuests(12);
        accomodation1.setDescription("my lovely cottage");

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setType("hotel for last test");
        accomodation2.setBedType("hostel 6 beds");
        accomodation2.setMaxGuests(100);
        accomodation2.setDescription("party place");

        accomodationsDAO.add(accomodation1);
        accomodationsDAO.add(accomodation2);

        String sql = new StringBuilder()
                .append("insert into room_fair(value, season) values(10,'offseason'); ")
                .append("insert into room_fair(value, season) values(20,'peak'); ")
                .append("insert into accomodation_fair_relation(id_accomodation, id_room_fair) values(1,1); ")
                .append("insert into accomodation_fair_relation(id_accomodation, id_room_fair) values(2,2); ").toString();

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.executeUpdate();

        String retrieveDataQuery = new StringBuilder()
                .append("select acc.type, fair.value from accomodation acc ")
                .append("inner join accomodation_fair_relation relation on acc.id = relation.id_accomodation ")
                .append("inner join room_fair fair on fair.id = relation.id_room_fair ").toString();

        PreparedStatement stmt2 = con.prepareStatement(retrieveDataQuery);
        ResultSet resultSet = stmt2.executeQuery();

        while (resultSet.next()) {
            System.out.println("Roomy type: " + resultSet.getString("type") + "  " + "Price: " + resultSet.getString("value"));
            System.out.println();
        }

        con.close();


    }

}
