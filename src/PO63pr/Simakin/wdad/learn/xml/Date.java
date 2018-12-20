
package PO63pr.Simakin.wdad.learn.xml;

import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
        if (order == null) {
            order = new ArrayList<Order>();
        }
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
