package PO63pr.Simakin.wdad.learn.rmi;

import PO63pr.Simakin.wdad.data.managers.DataManager;
import PO63pr.Simakin.wdad.data.managers.PreferencesManager;
import PO63pr.Simakin.wdad.learn.xml.Officiant;
import PO63pr.Simakin.wdad.learn.xml.Restaurant;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.*;

public class Client {
    static final String SECURITY_FILE_PATH = "C:\\Users\\PSYSHO\\Desktop\\РВПРС\\starting-monkey-to-human-path\\src\\PO63pr" +
            "\\Simakin\\wdad\\learn\\rmi\\policy";
    public static void main(String[] args)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = sdf.parse("05.11.2007");
        PreferencesManager pm = PreferencesManager.getInstance();
        Restaurant restaurant = new Restaurant();
        System.setProperty("java.security.policy", pm.getProperty(POLICYPATH));
        System.setSecurityManager(new SecurityManager());
        Officiant Ivan = new Officiant();
        Ivan.setSecondname("Petrov");Ivan.setFirstname("Ivar");
        Officiant Jack = new Officiant();
        Jack.setSecondname("Inqvar");Jack.setFirstname("Ivanov");
        try{
        Registry registry = LocateRegistry.getRegistry(pm.getProperty(REGISTRYADDRESS),
                Integer.parseInt(pm.getProperty(REGISTRYPORT)));
        DataManager dataManager = (DataManager) registry.lookup(
                pm.getBindedObjectName("PO63pr.Simakin.wdad.data.managers.DataManager"));
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
