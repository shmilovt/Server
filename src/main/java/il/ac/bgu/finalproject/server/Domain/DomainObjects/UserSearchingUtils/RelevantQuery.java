package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelevantQuery extends CategoryQuery {
    public RelevantQuery() {
    }

    ;
    public static final String dateFormat = "yyyy/MM/dd HH:mm:ss";


    private Boolean timeBetweenDates(String givenDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date now = new Date();
        try {
            Date gdate = formatter.parse(givenDate);
            long gap = now.getTime() - gdate.getTime();
            //   https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/getTime
            if (gap < 2592000000.0)
                return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    @Override
    public boolean mainQuery(ResultRecord apartment) {
        if ((apartment.getLat() != -1) && (apartment.getLat() != -1)) {
            if (timeBetweenDates(apartment.getDateOfPublish())) {
                if (apartment.getContacts().length > 0)
                    return true;
            }
        }
        return false;
    }
}
