package dataservice;

import dataservice.IDatabaseService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Tri
 */
public class FileDataService implements IDatabaseService{

    private String filePath;
    private String attributesHeader;

    public FileDataService(String filePath, String attributesHeader) throws Exception {
        if (filePath == null || filePath.isBlank()) {
            throw new Exception("File is null or empty.");
        }
        this.filePath = filePath;
        this.attributesHeader = attributesHeader;
        prepareDataFile(this.filePath);
    }

    @Override
    public List<String> loadData() {
        System.out.println("Load data from file " + this.filePath + " ...");
        List<String> entityStringList = new ArrayList();
        try ( Scanner sc = new Scanner(new File(this.filePath))) {
            if (sc.hasNextLine()) {
                // skip header
                String header = sc.nextLine();
            }
            String entityString;
            while (sc.hasNextLine()) {
                entityString = sc.nextLine();
                if (!entityString.isBlank()) {
                    entityStringList.add(entityString);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entityStringList;
    }

    @Override
    public boolean saveData(List<String> entityStringList) {
        System.out.println("Save data to file " + this.filePath + " ...");
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8, false))) {
            // add header
            writer.append(this.attributesHeader);
            writer.append("\n");
            for (String entityString : entityStringList) {
                writer.append(entityString);
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insert(String entityString) {
        System.out.println("Insert data to file " + this.filePath + " ...");
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.append(entityString);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(String entityString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void prepareDataFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                file.createNewFile();
                saveData(List.of());
            } catch (IOException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
            }
        }
    }
}
