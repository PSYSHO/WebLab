package PO63pr.Simakin.wdad.learn.rmi;
import PO63pr.Simakin.wdad.learn.xml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XMLDataManagerImpl implements PO63pr.Simakin.wdad.data.managers.DataManager {
    private String filepath;
    private Restaurant restaurant;
    public XMLDataManagerImpl(){
        super();
    }
    public XMLDataManagerImpl(String filepath) {
        this.filepath = filepath;
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Unmarshal();
    }
    private XmlTask task;
    public void init() throws Exception
    {
        task = new XmlTask("test.xml");
    }


    @Override
    public double earningsTotal(Officiant officiant, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return task.earningsTotal(officiant.getSecondname(), cal);
    }

    @Override
    public void removeDay(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        task.removeDay(cal);

    }

    @Override
    public void changeOfficiantName(Officiant OldOfficiant, Officiant newOfficiant) {
        task.changeOfficciantName(OldOfficiant, newOfficiant);
    }

    @Override
    public List<Order> getOrder(java.util.Date date) {
        return  task.getOrders(date);
    }


    @Override
    public Date lastOfficiantWorkDay(Officiant officiant) {
        return task.lastOfficiantWorkDate(officiant);
    }
    private void Marshal(Restaurant restaurant){
        try {
            JAXBContext context = JAXBContext.newInstance(Restaurant.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream(filepath);
            marshaller.marshal(restaurant, os);
            os.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    private void Unmarshal(){
        try {
            JAXBContext context = JAXBContext.newInstance(Restaurant.class);
            InputStream is = new FileInputStream(filepath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            restaurant = (Restaurant) unmarshaller.unmarshal(is);
            is.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
}
