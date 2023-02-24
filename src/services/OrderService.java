/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package services;

import dataservice.DataManagement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import utils.Util;

/**
 *
 * @author Minh Tri
 */
public class OrderService extends DataManagement<Orders> {

    private static OrderService instance = new OrderService();

    public static OrderService getInstace() {
        return instance;
    }

    public Orders addNew() {
        String orderID = Orders.inputId();
        Orders order = getOrderById(orderID);
        if (order == null) {
            order = new Orders();
            order.setOrderID(orderID);
            order.input();
            if (entityList.add(order)) {
                insertData(order);
            }
        } else {
            System.out.println("This order [" + orderID + "] already exists.");
        }
        return order;
    }

    public Orders getOrderById(String orderID) {
        if (orderID != null && !orderID.isBlank()) {
            for (Orders ord : entityList) {
                if (orderID.equalsIgnoreCase(ord.getOrderID())) {
                    return ord;
                }
            }
        }
        return null;
    }

    public void updateOrder() {
        String orderID = Orders.inputId();
        Orders order = getOrderById(orderID);
        if (order == null) {
            System.out.println("Not found");
        } else {
            order.update();
            saveData();
        }
    }

    public void deleteOrder() {
        String orderID = Orders.inputId();
        Orders order = getOrderById(orderID);
        if (order == null) {
            System.out.println("Not found");
        } else {
            entityList.remove(order);
            System.out.println("Removed " + orderID);
            saveData();
        }
    }

    public void printAllAsc() {
        Collections.sort(entityList, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return o1.getCustomerID().compareTo(o2.getCustomerID());
            }
        });
        printOutTable(entityList);
    }

    public void filterPendingOrder() {
        List<Orders> resultList = this.entityList
                .stream()
                .filter(ord -> (ord.getStatus()) == false)
                .toList();
        printOutTable(resultList);
    }

    private void printOutTable(List<Orders> list) {
        Formatter fmt = new Formatter();
        fmt.format("%9s %11s %11s %9s %13s %9s\n", "OrderID", "CustomerID",
                "ProductID", "Quantity", "OrderDate", "Status");
        for (Orders ord : list) {
            fmt.format("%9s %11s %11s %9s %13s %9s\n",
                    ord.getOrderID(),
                    ord.getCustomerID(),
                    ord.getProductID(),
                    ord.getOrderQuantity(),
                    Util.toString(ord.getOrderDate()),
                    ord.getStatus());
        }
        System.out.println(fmt);
    }

    @Override
    protected Orders parseEntity(String stringEntity) {
        try {
            Orders obj = new Orders();
            obj.parseOrders(stringEntity);
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
