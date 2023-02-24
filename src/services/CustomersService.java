package services;

import dataservice.DataManagement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customers;

import utils.Util;

/**
 *
 * @author Minh Tri
 */
public class CustomersService extends DataManagement<Customers> {

    private static CustomersService instance = new CustomersService();

    public static CustomersService getInstance() {
        return instance;
    }

    public Customers addNew() {
        String customerId = Customers.inputId();
        Customers customer = getCustomerById(customerId);
        if (customer == null) {
            customer = new Customers();
            customer.setCustomerID(customerId);
            customer.input();
            if (entityList.add(customer)) {
                insertData(customer);
            }
        } else {
            System.out.println("This customers [" + customerId + "] already exists.");
        }
        return customer;
    }

    public void printAll() {
        printOutTable(entityList);
    }

    public void updateCustomer() {
        String customerId = Customers.inputId();
        Customers customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Not found");
        } else {
            customer.update();
            saveData();
        }
    }

    public Customers getCustomerById(String cusID) {
        if (cusID != null && !cusID.isBlank()) {
            for (Customers cus : entityList) {
                if (cusID.equalsIgnoreCase(cus.getCustomerID())) {
                    return cus;
                }
            }
        }
        return null;
    }

    public void search() {
        String FindID = Util.inputString("Enter the customerID you want to find", true);
        Customers cus = getCustomerById(FindID);
        if (cus != null) {
            // format
            Formatter fmt = new Formatter();
            fmt.format("%6s %20s %12s %15s\n", "Id", "Name", "Address", "Phone");
            fmt.format("%6s %20s %12s %15s",
                    cus.getCustomerID(),
                    cus.getCustomerName(),
                    cus.getCustomerAddress(),
                    cus.getCustomerPhone());
            System.out.println(fmt);
        } else {
            System.out.println("Not found");
        }
    }

    private void printOutTable(List<Customers> list) {
        Formatter fmt = new Formatter();
        fmt.format("%6s %20s %12s %15s\n", "Id", "Name", "Address", "Phone");
        for (Customers customer : list) {
            fmt.format("%6s %20s %12s %15s\n",
                    customer.getCustomerID(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerPhone());
        }
        System.out.println(fmt);
    }

    @Override
    protected Customers parseEntity(String stringEntity) {
        try {
            Customers obj = new Customers();
            obj.parseCustomer(stringEntity);
            return obj;

        } catch (Exception ex) {
            Logger.getLogger(CustomersService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
