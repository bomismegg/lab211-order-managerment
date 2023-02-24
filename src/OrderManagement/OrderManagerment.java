package OrderManagement;

import dataservice.FileDataService;
import services.OrderService;
import services.UserService;
import services.ProductService;
import services.CustomersService;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import model.Products;
import model.Customers;

/**
 *
 * @author Minh Tri
 */
public class OrderManagerment {

    private static String productDataFilePath = "./src/data/product.txt";
    private static String orderDataFilePath = "./src/data/order.txt";
    private static String customerDataFilePath = "./src/data/customer.txt";

    private OrderService orderManagement;
    private ProductService productManagement;
    private CustomersService customerManagement;

    private void init() {
        try {
            // order
            this.orderManagement = orderManagement.getInstace();
            this.orderManagement.setDatabaseService(new FileDataService(OrderManagerment.orderDataFilePath, Orders.getAttributesHeader()));
            this.orderManagement.loadData();
            // product
            this.productManagement = productManagement.getInstance();
            this.productManagement.setDatabaseService(new FileDataService(OrderManagerment.productDataFilePath, Products.getAttributesHeader()));
            this.productManagement.loadData();
            // customer
            this.customerManagement = customerManagement.getInstance();
            this.customerManagement.setDatabaseService(new FileDataService(OrderManagerment.customerDataFilePath, Customers.getAttributesHeader()));
            this.customerManagement.loadData();
        } catch (Exception ex) {
            Logger.getLogger(OrderManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void process() {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case CUSTOMER_CREATE_NEW_CUSTOMER:
                    customerManagement.addNew();
                    break;
                case CUSTOMER_UPDATE:
                    customerManagement.updateCustomer();
                    break;
                case CUSTOMER_SEARCH:
                    customerManagement.search();
                    break;
                case CUSTOMER_SAVE_TO_FILE:
                    customerManagement.saveData();
                    break;
                case CUSTOMER_PRINT_ALL:
                    customerManagement.printAll();
                    break;
                    
                case PRODUCT_PRINT_ALL:
                    productManagement.PrintAll();
                    break;

                case ORDER_CREATE_NEW_ORDER:
                    orderManagement.addNew();
                    break;
                case ORDER_UPDATE:
                    orderManagement.updateOrder();
                    break;
                case ORDER_DELETE:
                    orderManagement.deleteOrder();
                    break;
                case ORDER_LIST_ALL_ORDER_ASC:
                    orderManagement.printAllAsc();
                    break;
                case ORDER_LIST_ALL_PENDING_ORDER:
                    orderManagement.filterPendingOrder();
                    break;
                case ORDER_SAVE_TO_FILE:
                    orderManagement.saveData();
                    break;

                case EXIT:
                    System.out.println("Exited!");
                    break;
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() {
        System.out.println("Order management");
        if (UserService.getInstance().login()) {
            UserService.getInstance().getCurrentUser().output();
            init();
            process();
        } else {
            System.out.println("Login failed!");
        }
    }

    public static void main(String[] args) {
        new OrderManagerment().start();
    }
}
