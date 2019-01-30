package PO63pr.Simakin.wdad.learn.xml;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class TestXmlTask {
    public static void main(String[] args) throws Exception
    {
        XmlTask task = new XmlTask();
        task.changeOfficciantName("Ingvar", "sidorov", "Vesimir", "Oldskin");
        Calendar calend = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse("05.11.2007");
        calend.setTime(date);
        double total = task.earningsTotal("oldskin", calend);
        task.removeDay(calend);
        XmlTask.saveObjectToXML("new.xml", Restaurant.class, task.getRestaurant());
    }
}
