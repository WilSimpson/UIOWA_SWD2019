public class Order
{
    private static int TOTAL_ORDERS_CREATED = 0;
    private static int TOTAL_ORDERS_DELIVERED = 0;

    private Address address;
    private String customerName;
    private String item;
    private String category;

    private ShippingCenter shippingCenter;
    private Section section;
    private DeliveryTruck deliveryTruck;

    public boolean delivered = false;

    public Order(Address address, String customerName, String item, String category)
    {
        this.address = address;
        this.customerName = customerName;
        this.item = item;
        this.category = category;

        TOTAL_ORDERS_CREATED++;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String name)
    {
        this.customerName = name;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Section getSection()
    {
        return section;
    }

    public void setSection(Section section)
    {
        this.section = section;
    }

    public ShippingCenter getShippingCenter()
    {
        return shippingCenter;
    }

    public void setShippingCenter(ShippingCenter shippingCenter)
    {
        this.shippingCenter = shippingCenter;
    }

    public DeliveryTruck getDeliveryTruck()
    {
        return deliveryTruck;
    }

    public void setDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        this.deliveryTruck = deliveryTruck;
    }

    public static int getTotalOrdersCreated()
    {
        return TOTAL_ORDERS_CREATED;
    }

    public static int getTotalOrdersDelivered()
    {
        return TOTAL_ORDERS_DELIVERED;
    }

    public void setDelivered()
    {
        delivered = true;
        TOTAL_ORDERS_DELIVERED++;
    }

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
