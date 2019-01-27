package PO63pr.Simakin.wdad.learn.rmi;

import PO63pr.Simakin.wdad.data.managers.PreferencesManager;
import PO63pr.Simakin.wdad.learn.xml.Date;
import PO63pr.Simakin.wdad.learn.xml.Officiant;
import PO63pr.Simakin.wdad.learn.xml.Order;
import PO63pr.Simakin.wdad.learn.xml.Restaurant;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.REGISTRYADDRESS;
import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.REGISTRYPORT;

public class Client {
    public static void main(String[] args)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = sdf.parse("05.11.2007");
        PreferencesManager pm = PreferencesManager.getInstance();
        Restaurant restaurant = new Restaurant();
        System.setSecurityManager(new SecurityManager());
        Officiant Ivan = new Officiant();
        Officiant Jack = new Officiant();
        try{
        Registry registry = LocateRegistry.getRegistry(pm.getProperty(REGISTRYADDRESS),
                Integer.parseInt(pm.getProperty(REGISTRYPORT)));
        XMLDataManager dataManager = (XMLDataManager) registry.lookup(
                pm.getBindedObjectName("C:\\Users\\PSYSHO\\Desktop\\starting-monkey-to-human-path\\PO63pr\\Simakin\\wdad\\learn\\rmi\\XMLDataManager"));
        System.out.println(dataManager.lastOfficiantWorkDay(Ivan));
        System.out.println(restaurant.getOrders(date));
        System.out.println(dataManager.earningsTotal(Ivan,date));
        restaurant.changeOfficciantName(Ivan,Jack);
        dataManager.removeDay(date);
        System.out.println(dataManager.getOrder(date));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
