package OrderManagement;

import services.UserService;
import utils.Util;

/**
 *
 * @author Minh Tri
 */
public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.CUSTOMER,
        MenuItem.PRODUCT,
        MenuItem.ORDER
    };

    private final MenuItem[] customerOptions = {
        MenuItem.BACK,
        MenuItem.CUSTOMER_CREATE_NEW_CUSTOMER,
        MenuItem.CUSTOMER_SEARCH,
        MenuItem.CUSTOMER_UPDATE,
        MenuItem.CUSTOMER_PRINT_ALL,
        MenuItem.CUSTOMER_SAVE_TO_FILE
    };

    private final MenuItem[] productOptions = {
        MenuItem.BACK,
        MenuItem.PRODUCT_PRINT_ALL
    };

    private final MenuItem[] orderOptions = {
        MenuItem.BACK,
        MenuItem.ORDER_CREATE_NEW_ORDER,
        MenuItem.ORDER_UPDATE,
        MenuItem.ORDER_DELETE,
        MenuItem.ORDER_LIST_ALL_PENDING_ORDER,
        MenuItem.ORDER_LIST_ALL_ORDER_ASC
    };

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                if (!isRepeatAction()) {
                    subOption = getChoice(primaryOption);
                }
                if (needAdmin() && !isAdmin()) {
                    System.out.println("Must be admin to use this function");
                    return MenuItem.BACK;
                }
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Order Management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);

        return optionList[choice];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        for (int i = 1; i < optionList.length; i++) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case CUSTOMER ->
                    customerOptions;
                case PRODUCT ->
                    productOptions;
                case ORDER ->
                    orderOptions;
                default ->
                    primaryOptions;
            };
        }

        return optionList;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case CUSTOMER_CREATE_NEW_CUSTOMER:
            case CUSTOMER_UPDATE:
            case CUSTOMER_SEARCH:
            case ORDER_CREATE_NEW_ORDER:
            case ORDER_DELETE:
            case ORDER_UPDATE:
                String confirm = Util.inputString("Repeat action (Y/N)", false);
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }

    private boolean needAdmin() {
        switch (subOption) {
            case CUSTOMER_CREATE_NEW_CUSTOMER:
            case CUSTOMER_UPDATE:
            case ORDER_CREATE_NEW_ORDER:
            case ORDER_DELETE:
            case ORDER_UPDATE:
                return true;
        }
        return false;
    }

    private boolean isAdmin() {
        return UserService.getInstance().getCurrentUser().checkRole(UserRole.ADMIN);
    }
}
