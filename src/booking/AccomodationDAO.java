package booking;

import java.sql.SQLException;
import java.util.List;

public interface AccomodationDAO {


    // R
    List<Accomodation> getAll() throws Exception, BookingDbException;

    // D
    void delete(Accomodation accomodation) throws BookingDbException, SQLException;

    // C
    void add(Accomodation accomodation) throws BookingDbException, SQLException;

    // U
    void update(Accomodation accomodation) throws BookingDbException, SQLException;


}
