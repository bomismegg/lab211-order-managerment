package model;

import services.CustomersService;
import utils.*;

/**
 *
 * @author Minh Tri
 */
public class Customers implements Comparable<Customers> {

    private static final String ID_FORMAT = "CXXX";
    private static final String ID_PATTERN = "C\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 4;

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID");
        sb.append(Util.SEP).append("Name");
        sb.append(Util.SEP).append("Quantity");
        sb.append(Util.SEP).append("Address");
        sb.append(Util.SEP).append("Phone");
        return sb.toString();
    }

    public Customers() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        // check
        if (Validation.checkCustomerID(customerID)) {
            this.customerID = customerID;
        } else {

        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        // check
        if (Validation.checkCustomerName(customerName)) {
            this.customerName = customerName;
        } else {

        }
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        // check
        if (Validation.checkCustomerAddress(customerAddress)) {
            this.customerAddress = customerAddress;
        } else {

        }
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        // check
        if (Validation.checkCustomerPhone(customerPhone)) {
            this.customerPhone = customerPhone;
        } else {

        }
    }

    public static String inputId() {
        String id = null;
        do {
            id = Util.inputString("Input id with patern (" + ID_FORMAT + ")", false);
            if (!Validation.checkCustomerID(id)) {
                System.out.println("Error");
            } else {
                break;
            }
        } while (true);
        return id;
    }

    //input()
    public void input() {

        // customerID
        do {
            String customerID = Util.inputString("Input customer ID", false);
            if (CustomersService.getInstance().getCustomerById(customerID) == null) {
                if (Validation.checkCustomerID(customerID)) {
                    setCustomerID(customerID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Existed ID.");
            }
        } while (true);

        // customerName
        do {
            String customerName = Util.inputString("Input customer name", false);
            if (Validation.checkCustomerName(customerName)) {
                setCustomerName(customerName);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);

        // customerAddress
        do {
            String address = Util.inputString("Input customer address", false);
            if (Validation.checkCustomerAddress(address)) {
                setCustomerAddress(address);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);

        // customerPhone
        do {
            String phone = Util.inputString("Input customer phone", false);
            if (Validation.checkCustomerPhone(phone)) {
                setCustomerPhone(phone);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
    }

    public void update() {

        System.out.println("Press ENTER to skip fields that don't need to be changed");

        // customerID
        do {
            System.out.println("\nOld customer ID: " + this.customerID);
            String id = Util.inputString("Enter the new customer ID", true);
            if (!id.isEmpty()) {
                if (Validation.checkCustomerID(id)) {
                    setCustomerID(id);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // customerName
        do {
            System.out.println("\nOld customer name: " + this.customerName);
            String name = Util.inputString("Enter the new customer name", true);
            if (!name.isEmpty()) {
                if (Validation.checkCustomerName(name)) {
                    setCustomerName(name);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // customerAddress
        do {
            System.out.println("\nOld customer address: " + this.customerAddress);
            String address = Util.inputString("Enter the new customer address", true);
            if (!address.isEmpty()) {
                if (Validation.checkCustomerAddress(address)) {
                    setCustomerAddress(address);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // customerPhone
        do {
            System.out.println("\nOld customer phone: " + this.customerPhone);
            String phone = Util.inputString("Enter the new customer phone", true);
            if (!phone.isEmpty()) {
                if (Validation.checkCustomerPhone(phone)) {
                    setCustomerPhone(phone);
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
        sb.append(customerID);
        sb.append(Util.SEP).append(customerName);
        sb.append(Util.SEP).append(customerAddress);
        sb.append(Util.SEP).append(customerPhone);
        return sb.toString();
    }

    public void parseCustomer(String entityString) throws Exception {
        // can check so luong attribute (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Customers.ENTITY_ATTRIBUTE_COUNT) {
                setCustomerID(attributes[0]);
                setCustomerName(attributes[1]);
                setCustomerAddress(attributes[2]);
                setCustomerPhone(attributes[3]);
            }
        }
    }

    @Override
    public int compareTo(Customers o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
