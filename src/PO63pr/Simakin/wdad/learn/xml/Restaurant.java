
package PO63pr.Simakin.wdad.learn.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "date"
})
@XmlRootElement(name = "restaurant")
public class Restaurant implements Serializable {
    @XmlElement
    protected List<Date> date;

    /**
     * Gets the value of the date property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Date }
     * 
     * 
     */

    public Date getDate(Date d)
    {
        return d;
    }
    public List<Date> getDate() {
        if (date == null) {
            date = new ArrayList<>();
        }
        return this.date;
    }
    public void changeOfficciantName(Officiant oldName, Officiant newName)
    {
        for(Date d : date)
            d.updateOfficiantName(oldName, newName);
    }

    public List<Order> getOrders(java.util.Date date) {
        List<Order> result = new ArrayList<>();
        for(Date d : this.date)
        {
            if(d.equalsByDate(date))
            {
                result.addAll(d.order);
                return result;
            }
        }
        return null;
    }

    public List<java.util.Date> getDatesByOfficiantUtilDate(Officiant officiant) {
        List<java.util.Date> dateList = new ArrayList<>();
        for (Date d : getDatesByOfficiant(officiant))
            dateList.add(d.getDate());
        return dateList;
    }

    private List<Date> getDatesByOfficiant(Officiant officiant) {
        List<Date> dateList = new ArrayList<>();
        for (Date d : date)
            if(d.hasSomeOrdersByOfficiant(officiant))
                dateList.add(d);
        return dateList;
    }
}
