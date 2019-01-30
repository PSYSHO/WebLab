package PO63pr.Simakin.wdad.data.managers;
import  PO63pr.Simakin.wdad.learn.xml.Order;
import  PO63pr.Simakin.wdad.learn.xml.Officiant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface DataManager extends Remote {
    double earningsTotal(Officiant officiant, Date date) throws RemoteException;;
    void removeDay(java.util.Date date) throws RemoteException;;
    void changeOfficiantName(Officiant OldOfficiant,Officiant newOfficiant) throws RemoteException;;
    List<Order> getOrder(java.util.Date date) throws RemoteException;;
    java.util.Date lastOfficiantWorkDay(Officiant officiant) throws RemoteException;;
}
