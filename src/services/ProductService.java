package services;

import dataservice.DataManagement;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;

/**
 *
 * @author Minh Tri
 */
public class ProductService extends DataManagement<Products> {

    public static final ProductService instance = new ProductService();

    public static ProductService getInstance() {
        return instance;
    }
    
    public Products getProductById(String proID) {
        if (proID != null && !proID.isBlank()) {
            for (Products pro : entityList) {
                if (proID.equalsIgnoreCase(pro.getProductID())) {
                    return pro;
                }
            }
        }
        return null;
    }
    
    public void PrintAll(){ 
        printOutTable(entityList);
    }
    
    private void printOutTable(List<Products> list) {
        Formatter fmt = new Formatter();
        fmt.format("%12s %25s %20s %25s %8s\n", "ProductID", "Name", "Unit", "Origin", "Price");
        for (Products product : list) {
            fmt.format("%12s %25s %20s %25s %8s\n",
                    product.getProductID(),
                    product.getProductName(),
                    product.getUnit(),
                    product.getOrigin(),
                    product.getPrice());
        }
        System.out.println(fmt);
    }
     
    @Override
    protected Products parseEntity(String stringEntity) {
        try {
            Products p = new Products();
            p.parseProduct(stringEntity);
            return p;
        } catch (Exception e) {
            Logger.getLogger(CustomersService.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
