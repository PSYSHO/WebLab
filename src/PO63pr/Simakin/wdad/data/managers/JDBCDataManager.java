package PO63pr.Simakin.wdad.data.managers;

import PO63pr.Simakin.wdad.learn.xml.Officiant;
import PO63pr.Simakin.wdad.learn.xml.Order;
import PO63pr.Simakin.wdad.learn.xml.XmlTask;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JDBCDataManager implements DataManager{
    private XmlTask task;
    @Override
    public double earningsTotal(Officiant officiant, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return task.earningsTotal(officiant.getSecondname(), cal);
    }

    @Override
    public void removeDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        task.removeDay(cal);
    }

    @Override
    public void changeOfficiantName(Officiant OldOfficiant, Officiant newOfficiant) {
        task.changeOfficciantName(OldOfficiant, newOfficiant);
    }

    @Override
    public List<Order> getOrder(Date date) {
            return  task.getOrders(date);
    }

    @Override
    public Date lastOfficiantWorkDay(Officiant officiant) {
        return task.lastOfficiantWorkDate(officiant);
    }
}
