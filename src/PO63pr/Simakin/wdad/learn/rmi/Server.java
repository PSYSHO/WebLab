package PO63pr.Simakin.wdad.learn.rmi;

import PO63pr.Simakin.wdad.data.managers.PreferencesManager;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.*;

public class Server {
    public static void main(String[] args)throws Exception {

        PreferencesManager pm = PreferencesManager.getInstance();
        System.setSecurityManager(new SecurityManager());
        try {
            XMLDataManagerImpl obj = new XMLDataManagerImpl("C:\\Users\\PSYSHO\\Desktop\\starting-monkey-to-human-path\\src\\PO63pr\\Simakin\\wdad\\learn\\xml\\Test.xml");
            XMLDataManager stub = (XMLDataManager) UnicastRemoteObject.exportObject((Remote) obj, 0);
            Registry registry;

            if (pm.getProperty(CREATEREGISTRY).equals("yes")) {
                registry = LocateRegistry.createRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            } else {
                registry = LocateRegistry.getRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            }
            registry.rebind("XMLDataManager", (Remote) obj);
            System.out.println("Server is online (probably)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}