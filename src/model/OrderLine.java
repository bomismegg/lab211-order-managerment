/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class OrderLine {

    private String productID;
    private int orderQuantity;

    public String getProductID() {
        return productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public OrderLine(String productID, int orderQuantity) {
        this.productID = productID;
        this.orderQuantity = orderQuantity;
    }
}
