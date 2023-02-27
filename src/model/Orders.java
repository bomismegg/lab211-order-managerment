package model;

import java.util.ArrayList;
import services.CustomersService;
import utils.*;
import services.ProductService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Tri
 */
public class Orders implements Comparable<Orders> {

    private static final String ID_FORMAT = "DXXX";
    private static final String ID_PATTERN = "D\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 6;

    private String orderID;
    private String customerID;
//    private String productID;
//    private int orderQuantity;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if (Validation.checkCustomerID(customerID)) {
            this.customerID = customerID;
        } else {

        }
    }

    public List<OrderLine> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderLine> productList) {
        if (productList != null) {
            this.productList.addAll(productList);
        }
    }

//    public String getProductID() {
//        return productID;
//    }
//
//    public void setProductID(String productID) {
//        if (Validation.checkProductID(productID)) {
//            this.productID = productID;
//        } else {
////            throw new OException("Error");
//        }
//    }
//
//    public int getOrderQuantity() {
//        return orderQuantity;
//    }
//
//    public void setOrderQuantity(int orderQuantity) {
//        if (Validation.checkOrderQuantity(orderQuantity)) {
//            this.orderQuantity = orderQuantity;
//        } else {
//
//        }
//    }
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
            if (CustomersService.getInstance().getCustomerById(customerID) != null) {
                if (Validation.checkCustomerID(customerID)) {
                    setCustomerID(customerID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } while (true);

        // productID
        System.out.println("Products List:");
        ProductService.getInstance().PrintAll();
        String productID = null;
        do {

//            do {
                productID = Util.inputString("Input product's id", true);
                if (!productID.isBlank() && ProductService.getInstance().getProductById(productID) != null) {
                    if (Validation.checkProductID(productID)) {
//                        setProductID(productID);
                        int quantity = Util.inputInteger("Input order quantity", 0, Integer.MAX_VALUE);
                        this.productList.add(new OrderLine(productID, quantity));
//                        break;
                    } else {
                        System.out.println("Error");
                    }
                } else {
                    System.out.println("Product not found.");
                }
//            } while (true);

            // orderQuantity
//            do {
//                int quantity = Util.inputInteger("Input order quantity", 0, Integer.MAX_VALUE);
//                if (Validation.checkOrderQuantity(quantity)) {
//                    setOrderQuantity(quantity);
//                    break;
//                } else {
//                    System.out.println("Error.");
//                }
//            } while (true);
        } while (!productID.isBlank() || this.productList.isEmpty());

        // orderDate
//        do {
//            Date orderDate = Util.inputDate("Input order date", false);
//            if (Validation.validateDate(orderDate)) {
//                setOrderDate(orderDate);
//                break;
//            } else {
//                System.out.println("Error.");
//            }
//        } while (true);

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
//        do {
//            System.out.println("\nOld customer ID: " + this.customerID);
//            String cID = Util.inputString("Enter the new customer ID", true);
//            if (!cID.isEmpty()) {
//                if (Validation.checkCustomerID(cID)) {
//                    setCustomerID(cID);
//                    break;
//                } else {
//                    System.out.println("Error");
//                }
//            } else {
//                break;
//            }
//        } while (true);
//
//        // productID
//        do {
//            System.out.println("\nOld product ID: " + this.productID);
//            String pID = Util.inputString("Enter the new product ID", true);
//            if (!pID.isEmpty()) {
//                if (Validation.checkProductID(pID)) {
//                    setProductID(pID);
//                    break;
//                } else {
//                    System.out.println("Error");
//                }
//            } else {
//                break;
//            }
//        } while (true);

        // orderQuantity
//        do {
//            System.out.println("\nOld order quantity: " + this.orderQuantity);
//            String quantity = Util.inputString("Enter the new product ID", true);
//            if (!quantity.isEmpty()) {
//                if (Validation.checkOrderQuantity(Integer.parseInt(quantity))) {
//                    setOrderQuantity(Integer.parseInt(quantity));
//                    break;
//                } else {
//                    System.out.println("Error");
//                }
//            } else {
//                break;
//            }
//        } while (true);

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
            sb.append(System.lineSeparator());
            sb.append(orderID);
            sb.append(Util.SEP).append(customerID);
            sb.append(Util.SEP).append(orderLine.getProductID());
            sb.append(Util.SEP).append(orderLine.getOrderQuantity());
            sb.append(Util.SEP).append(Util.toString(orderDate));
            sb.append(Util.SEP).append(status);
        }
        return sb.toString().substring(1);
    }

    public void parseOrders(String entityString) throws Exception {
        // can check so luong attribute  (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Orders.ENTITY_ATTRIBUTE_COUNT) {
                setOrderID(attributes[0]);
                setCustomerID(attributes[1]);
//                setProductID(attributes[2]);
//                setOrderQuantity(Integer.parseInt(attributes[3]));
                productList.add(new OrderLine(attributes[2], Integer.parseInt(attributes[3])));
                setOrderDate(Util.toDate(attributes[4]));
                setStatus(Boolean.parseBoolean(attributes[5]));
            }
        }
    }

    @Override
    public int compareTo(Orders o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
