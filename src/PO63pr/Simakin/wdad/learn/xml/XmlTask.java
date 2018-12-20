package PO63pr.Simakin.wdad.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlTask {
    List<Order> dump = new ArrayList<>(1);
    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    private Restaurant restaurant;

    XmlTask() throws Exception
    {
        restaurant = (Restaurant)loadObjectFromXML("Test.xml", Restaurant.class);
    }
    static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        System.setProperty("javax.xml.accessExternalDTD", "all");
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Object)unmarshaller.unmarshal(sr);
    }

    public static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    public double earningsTotal(String officiantSecondName, Calendar calendar)
    {
        double total = 0.0;
        Date comparingDate = Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR),dump);
        Date trin = restaurant.getDate(comparingDate);
        //System.out.println(trin.toString());
        for(Order ord : restaurant.getDate(comparingDate).getOficciantDayOrder(officiantSecondName))
            total += ord.totalcost;
        return total == 0.0 ? -1 : total;
    }
    public void removeDay(Calendar calendar)
    {
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        restaurant.date.remove(restaurant.getDate(Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), dump)));
    }
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName)
    {
        for(Date date : restaurant.date)
            for(Order order : date.order)
                if(order.officiant.firstname.equals(oldFirstName) && order.officiant.secondname.equals(oldSecondName))
                {
                    order.officiant.firstname = newFirstName;
                    order.officiant.secondname = newSecondName;
                }
    }
}
