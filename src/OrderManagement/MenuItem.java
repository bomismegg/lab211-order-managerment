package OrderManagement;

/**
 *
 * @author Minh Tri
 */
public enum MenuItem {
    MAIN_OPTIONS("Book store",UserRole.USER),
    BACK("Back", UserRole.USER),
    EXIT("Exit", UserRole.USER),
    
    // Product
    PRODUCT("Product", UserRole.USER),
    PRODUCT_PRINT_ALL("Print all Product", UserRole.USER),
    
    // Order
    ORDER("Order", UserRole.USER),
    ORDER_CREATE_NEW_ORDER("Create new Order", UserRole.ADMIN),
    ORDER_UPDATE("Update the Order", UserRole.ADMIN),
    ORDER_DELETE("Delete the Order", UserRole.ADMIN),
    ORDER_LIST_ALL_PENDING_ORDER("List all Product that status are pending", UserRole.USER),
    ORDER_LIST_ALL_ORDER_ASC("List all Orders in ascending order of Customer name", UserRole.USER),
    ORDER_SAVE_TO_FILE("Save Orders to file(named orders.txt)", UserRole.ADMIN),
    ORDER_PRINT_ALL("Print from file", UserRole.USER),
    
    // Customer
    CUSTOMER("Customer", UserRole.USER),
    CUSTOMER_CREATE_NEW_CUSTOMER("Create new Customer", UserRole.ADMIN),
    CUSTOMER_UPDATE("Update the new Customer", UserRole.ADMIN),
    CUSTOMER_SEARCH("Search customer base on ID", UserRole.USER),
    CUSTOMER_SAVE_TO_FILE("Save to file(named customer.txt)", UserRole.ADMIN),
    CUSTOMER_PRINT_ALL("Print from file", UserRole.USER);

    private final UserRole role;
    private final String label;

    public UserRole getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, UserRole role) {
        this.role = role;
        this.label = label;
    }

}
