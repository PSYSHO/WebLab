package PO63pr.Simakin.wdad.data.managers;
import  PO63pr.Simakin.wdad.learn.xml.Order;
import  PO63pr.Simakin.wdad.learn.xml.Officiant;

import java.util.Date;
import java.util.List;

public interface DataManager {
    double earningsTotal(Officiant officiant, Date date);
    void removeDay(java.util.Date date);
    void changeOfficiantName(Officiant OldOfficiant,Officiant newOfficiant);
    List<Order> getOrder(java.util.Date date);
    java.util.Date lastOfficiantWorkDay(Officiant officiant);
}
