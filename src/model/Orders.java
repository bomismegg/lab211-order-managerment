package model;

import java.util.ArrayList;
import services.CustomersService;
import utils.*;
import services.ProductService;
import java.util.Date;
import java.util.List;
import services.OrderService;

/**
 *
 * @author Minh Tri
 */
public class Orders {

    private static final String ID_FORMAT = "DXXX";
    private static final String ID_PATTERN = "D\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 6;

    private String orderID;
    private Customers customer;
    private List<OrderLine> productList;
    private Date orderDate;
    private boolean status;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("orderID");
        sb.append(Util.SEP).append("customerID");
        sb.append(Util.SEP).append("productID");
        sb.append(Util.SEP).append("orderQuantity");
        sb.append(Util.SEP).append("orderDate");
        sb.append(Util.SEP).append("status");
        return sb.toString();
    }

    public Orders() {
        this.productList = new ArrayList();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        if (Validation.checkOrderID(orderID)) {
            this.orderID = orderID;
        } else {
            System.err.println("Error");
        }
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public List<OrderLine> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderLine> productList) {
        if (productList != null) {
            this.productList.addAll(productList);
        }
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        if (/*Validate*/true) {
            this.orderDate = orderDate;
        }
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (/*Validate*/true) {
            this.status = status;
        }
    }

    public static String inputId() {
        String id = null;
        do {
            id = Util.inputString("Input id with patern (" + ID_FORMAT + ")", false);
            if (!Validation.checkOrderID(id)) {
                System.out.println("Error");
            } else {
                break;
            }
        } while (true);
        return id;
    }

    public void input() {

        // customerID
        System.out.println("Customers List:");
        CustomersService.getInstance().printAll();
        do {
            String customerID = Util.inputString("Input customer's id", false);
            Customers customer = CustomersService.getInstance().getCustomerById(customerID);
            if (customer != null) {
                if (Validation.checkCustomerID(customer.getCustomerID())) {
                    setCustomer(customer);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } while (true);

        // productList
        System.out.println("Products List:");
        ProductService.getInstance().PrintAll();
        String productID = null;
        do {
            productID = Util.inputString("Input product's id", true);
            if (productID.isBlank()) {
                break;
            }
            if (ProductService.getInstance().getProductById(productID) != null) {
                if (Validation.checkProductID(productID)) {
                    int quantity = Util.inputInteger("Input order quantity", 0, Integer.MAX_VALUE);
                    this.productList.add(new OrderLine(productID, quantity));
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Product not found.");
            }
        } while (!productID.isBlank() || this.productList.isEmpty());

        // orderDate
        do {
            Date orderDate = Util.inputDate("Input order date", false);
            if (Validation.validateDate(orderDate)) {
                setOrderDate(orderDate);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
        // status
        do {
            boolean status = Util.inputBoolean("Input status (T/F)");
            if (Validation.checkStatus(status)) {
                setStatus(status);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
    }

    public void update() {

        System.out.println("Press ENTER to skip fields that don't need to be changed");

        // orderID
        do {
            System.out.println("\nOld order ID: " + this.orderID);
            String oID = Util.inputString("Enter the new order ID", true);
            if (!oID.isEmpty()) {
                if (Validation.checkOrderID(oID)) {
                    setOrderID(oID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // customerID
        System.out.println("Customers List:");
        CustomersService.getInstance().printAll();
        System.out.println("Old Customer Name: " + this.customer.getCustomerName());
        String customerID;
        do {
            customerID = Util.inputString("Input new customer's id", true);
            if (!customerID.isEmpty()) {
                Customers customer = CustomersService.getInstance().getCustomerById(customerID);
                if (customer != null) {
                    if (Validation.checkCustomerID(customer.getCustomerID())) {
                        setCustomer(customer);
                        break;
                    } else {
                        System.out.println("Error");
                    }
                } else {
                    System.out.println("Customer not found.");
                }

            } else {
                break;
            }
        } while (true);

        // productList
        System.out.println("Products List:");
        ProductService.getInstance().PrintAll();
        System.out.println("Old product list: ");
        System.out.println(OrderService.getInstace().getAllOrderLine(productList));
        for (OrderLine ord : productList) {
            do {
                System.out.println("Product: " + ord);
                String isModify = Util.inputString("Modify or Remove", false);
                if (isModify.trim().toUpperCase().startsWith("R")) {
//                    productList.remove(ord);
                    System.out.println("Removed");
                    break;
                }
                String ID = Util.inputString("Enter the new product ID", true);
                if (ID.isEmpty()) {
                    break;
                } else {
                    if (!Validation.checkProductID(ID)) {
                        System.out.println("Error");
                        continue;
                    } else {
                        ord.setProductID(ID);
                    }
                }
                String quantityString = Util.inputString("Enter the new quantity", true);
                if (quantityString.isEmpty()) {
                    break;
                } else {
                    int quantity = Integer.parseInt(quantityString);
                    if (!Validation.checkOrderQuantity(quantity)) {
                        System.out.println("Error");
                    } else {
                        ord.setOrderQuantity(quantity);
                        break;
                    }
                }
            } while (true);
        }

        // orderDate
        do {
            System.out.println("\nOld order date: " + Util.toString(this.orderDate));
            Date date = Util.inputDate("Enter the new date", true);
            if (!Util.toString(date).isEmpty()) {
                if (Validation.validateDate(date)) {
                    setOrderDate(date);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // status
        do {
            System.out.println("\nOld order status: " + this.status);
            String status = Util.inputString("Enter the new status (T/F)", true);
            if (!status.isEmpty()) {
                if (status.trim().toUpperCase().startsWith("T") || status.trim().toUpperCase().startsWith("F")) {
                    setStatus(status.trim().toUpperCase().startsWith("T"));
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderLine orderLine : productList) {
            sb.append(orderID);
            sb.append(Util.SEP).append(customer.getCustomerID());
            sb.append(Util.SEP).append(orderLine.getProductID());
            sb.append(Util.SEP).append(orderLine.getOrderQuantity());
            sb.append(Util.SEP).append(Util.toString(orderDate));
            sb.append(Util.SEP).append(status);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void parseOrders(String entityString) throws Exception {
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Orders.ENTITY_ATTRIBUTE_COUNT) {
                setOrderID(attributes[0]);
                setCustomer(CustomersService.getInstance().getCustomerById(attributes[1]));
                productList.add(new OrderLine(attributes[2], Integer.parseInt(attributes[3])));
                setOrderDate(Util.toDate(attributes[4]));
                setStatus(Boolean.parseBoolean(attributes[5]));
            }
        }
    }
}