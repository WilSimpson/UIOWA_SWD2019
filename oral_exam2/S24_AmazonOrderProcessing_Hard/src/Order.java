/**
 * Represents and order placed on Amazon
 *
 * @author Wil Simpson
 */
public class Order
{
    /**
     * Total number of orders created
     */
    private static int TOTAL_ORDERS_CREATED = 0;

    /**
     * Total number of orders
     */
    private static int TOTAL_ORDERS_DELIVERED = 0;

    /**
     * Delivery address
     */
    private Address address;

    /**
     * Customers name
     */
    private String customerName;

    /**
     * Items name
     */
    private String item;

    /**
     * Items category
     */
    private String category;

    /**
     * Shipping center order was processed through
     */
    private ShippingCenter shippingCenter;

    /**
     * Section order was processed through
     */
    private Section section;

    /**
     * Truck that delivered
     */
    private DeliveryTruck deliveryTruck;

    /**
     * Whether the order has been delivered
     */
    private boolean delivered = false;

    /**
     * Creates a new order and increments the number of total orders created
     *
     * @param address delivery address
     * @param customerName customers name
     * @param item item name
     * @param category item category
     */
    public Order(Address address, String customerName, String item, String category)
    {
        this.address = address;
        this.customerName = customerName;
        this.item = item;
        this.category = category;

        TOTAL_ORDERS_CREATED++;
    }

    /**
     * Gets the address
     *
     * @return delivery address
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Sets the address
     *
     * @param address new address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }

    /**
     * Gets the customer name
     *
     * @return customer name
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Sets the customer name
     *
     * @return new
     */
    public void setCustomerName(String name)
    {
        this.customerName = name;
    }

    /**
     * Gets the item name
     *
     * @return item name
     */
    public String getItem()
    {
        return item;
    }

    /**
     * Sets the item name
     *
     * @return new item name
     */
    public void setItem(String item)
    {
        this.item = item;
    }

    /**
     * Gets the item category
     *
     * @return item category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Sets the item category
     *
     * @return new item category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Gets the shipping center the order was processed through
     *
     * @return shipping center the order was processed through
     */
    public Section getSection()
    {
        return section;
    }

    /**
     * Sets the section the order was processed through
     *
     * @return new section the order was processed through
     */
    public void setSection(Section section)
    {
        this.section = section;
    }

    /**
     * Gets the shipping center the order was processed through
     *
     * @return the shipping center the order was processed through
     */
    public ShippingCenter getShippingCenter()
    {
        return shippingCenter;
    }

    /**
     * Sets the shipping center the order was processed through
     *
     * @return new shipping center the order was processed through
     */
    public void setShippingCenter(ShippingCenter shippingCenter)
    {
        this.shippingCenter = shippingCenter;
    }

    /**
     * Gets the truck that delivered the order
     *
     * @return truck that delivered the order
     */
    public DeliveryTruck getDeliveryTruck()
    {
        return deliveryTruck;
    }

    /**
     * Sets the truck that delivered the order
     *
     * @return new truck that delivered the order
     */
    public void setDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        this.deliveryTruck = deliveryTruck;
    }

    /**
     * Gets the total number of orders created
     *
     * @return total number of orders created
     */
    public static int getTotalOrdersCreated()
    {
        return TOTAL_ORDERS_CREATED;
    }

    /**
     * Gets the total number of delivered orders
     *
     * @return total orders delivered
     */
    public static int getTotalOrdersDelivered()
    {
        return TOTAL_ORDERS_DELIVERED;
    }

    /**
     * Sets the order as delivered and increments the total number of orders
     */
    public void setDelivered()
    {
        delivered = true;
        TOTAL_ORDERS_DELIVERED++;
    }

    /**
     * Generates a human readable string containing the address, item, category, shipping center, section and
     * delivery truck attached to the order
     *
     * @return string representation
     */
    public String toString()
    {
        return address
                +" || Name: "+customerName
                +" || Item: "+item
                +" || Category: "+category
                +" || SC: "+((shippingCenter == null) ? "" : shippingCenter.getNumber())
                +" || SCS: "+((section == null) ? "" : section.getNumber())
                +" || DT: "+((deliveryTruck == null) ? "" : deliveryTruck.getTruckNumber());

    }



}
