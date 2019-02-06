package Manage;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util{
  public static Timestamp convertStringToTimestamp(String str_date) throws ParseException {
    Timestamp timeStampDate = Timestamp.valueOf(str_date);
     
     return timeStampDate;
  }
}