package PO63pr.Simakin.wdad.learn.rmi;

import PO63pr.Simakin.wdad.data.managers.DataManager;
import PO63pr.Simakin.wdad.data.managers.PreferencesManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.*;

public class Server {
    static final String FILEPATH = "C:\\Users\\PSYSHO\\Desktop\\РВПРС\\starting-monkey-to-human-path\\src\\PO63pr\\Simakin\\wdad\\learn\\xml\\Test.xml";
    static final String SECURITY_FILE_PATH = "C:\\Users\\PSYSHO\\Desktop\\РВПРС\\starting-monkey-to-human-path\\src\\PO63pr\\Simakin\\wdad\\learn\\rmi\\policy";
    static final String CLASS_NAME = "PO63pr.Simakin.wdad.data.managers.DataManager";
    public static void main(String[] args) {

        PreferencesManager pm = PreferencesManager.getInstance();
        System.setProperty("java.security.policy", pm.getProperty(POLICYPATH));
        System.setSecurityManager(new SecurityManager());
        try {
            XMLDataManagerImpl obj = new XMLDataManagerImpl(FILEPATH);
            DataManager stub = (DataManager) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry;

            if(pm.getProperty(CREATEREGISTRY).equals("yes")){
                registry = LocateRegistry.createRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            } else {
                registry = LocateRegistry.getRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            }
            registry.bind("DataManager", obj);
            if(pm.getBindedObjectName(CLASS_NAME) == null){
                pm.addBindedObject("DataManager", CLASS_NAME);
            }
            System.out.println("Server is online (probably)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}