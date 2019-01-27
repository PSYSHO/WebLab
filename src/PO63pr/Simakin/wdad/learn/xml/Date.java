
package PO63pr.Simakin.wdad.learn.xml;

import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.lang.Integer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "order"
})
@XmlRootElement(name = "date")
public class Date {

    @XmlAttribute(name = "day", required = true)
    //@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected int day;
    @XmlAttribute(name = "month", required = true)
    //@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected int month;
    @XmlAttribute(name = "year", required = true)
    //@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected int year;
    @XmlElement
    protected List<Order> order;
    public static Date newInstance(int day, int month, int year, List<Order> order)
    {
        Date newDate = new Date();
        newDate.day = day;
        newDate.month = month;
        newDate.year = year;
        newDate.order = order;
        return newDate;
    }

    /**
     * Gets the value of the day property.
     *
     * @return
     *     possible object is
     *     {@link int }
     *
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     *
     * @param value
     *     allowed object is
     *     {@link int }
     *
     */
    public void setDay(Integer value) {
        this.day = value;
    }

    /**
     * Gets the value of the month property.
     *
     * @return
     *     possible object is
     *     {@link int }
     *
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     *
     * @param value
     *     allowed object is
     *     {@link int }
     *
     */
    public void setMonth(Integer value) {
        this.month = value;
    }

    /**
     * Gets the value of the year property.
     *
     * @return
     *     possible object is
     *     {@link int }
     *
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     *
     * @param value
     *     allowed object is
     *     {@link int }
     *
     */
    public void setYear(Integer value) {
        this.year = value;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    /**
     * Gets the value of the order property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the order property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrder().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Order }
     *
     *
     */

    public List<Order> getOrder() {
        return this.order;
    }
    public boolean equalsDate(Date date){
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }
    public boolean equals(Object obj)
    {
        if(obj instanceof Date)
        {
            Date o = (Date)obj;
            return equalsDate(o) && this.order.equals(o.order);
        }
        return super.equals(obj);
    }
    public int hashCode()
    {
        return this.day^this.month^this.year^this.order.hashCode();
    }
    public List<Order> getOficciantDayOrder(String officiantSecondName)
    {
        List<Order> officiantsOrder = new ArrayList<>();
        for(Order order : this.order)
            if(order.getOfficiant().secondname.equals(officiantSecondName))
                officiantsOrder.add(order);
        return officiantsOrder;
    }
    public void updateOfficiantName(Officiant oldName, Officiant newName)
    {
        for(Order o : getOrdersByOfficiant(oldName))
            o.setOfficiant(newName);
    }
    public List<Order> getOrdersByOfficiant(Officiant officiant)
    {
        ArrayList<Order> ords = new ArrayList<>();
        for(Order o : order)
            if(o.officiant.equals(officiant))
                ords.add(o);
        return ords;
    }

    public boolean equalsByDate(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return this.day == cal.get(Calendar.DAY_OF_MONTH) && this.month == cal.get(Calendar.MONTH)+1 && this.year == cal.get(Calendar.YEAR);
    }

    public boolean hasSomeOrdersByOfficiant(Officiant officiant) {
            return getOrdersByOfficiant(officiant).size() != 0;
    }

    public java.util.Date getDate() {
        java.util.Date d = java.sql.Date.valueOf(LocalDate.of(year, month, day));
        return d;
    }
    /*public java.util.Date getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(getDay()).append("-");
        sb.append(getMonth()).append("-");
        sb.append(getYear());
        java.util.Date date = null;
        try {
            date = simpleDateFormat.parse(sb.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }*/

}
