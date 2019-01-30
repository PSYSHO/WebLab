package PO63pr.Simakin.wdad;

import PO63pr.Simakin.wdad.data.managers.DataManager;
import PO63pr.Simakin.wdad.data.managers.JDBCDataManager;
import PO63pr.Simakin.wdad.learn.xml.Officiant;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JDBCTest {
    public static void main(String[] args) throws RemoteException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = sdf.parse("05.11.2007");
        DataManager dm = new JDBCDataManager();
        Officiant Ivan = new Officiant();
        Ivan.setSecondname("Petrov");
        dm.earningsTotal(Ivan,date);

    }

}
