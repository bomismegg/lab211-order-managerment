package model;

import OrderManagement.UserRole;
import utils.Util;


/**
 *
 * @author hasu
 */
public class User {

    private static final int ATTRIBUTE_COUNT = 3;

    private String id;
    private String pass;
    private UserRole role;

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        if (id != null && !id.isBlank()) {
            this.id = id;
        }
    }

    public String getPass() {
        return pass;
    }

    public final void setPass(String pass) {
        if (pass != null && !pass.isBlank()) {
            this.pass = pass;
        }
    }

    public UserRole getRole() {
        return role;
    }

    public final void setRole(UserRole role) {
        this.role = role;
    }

    public User() {
    }

    public User(String id, String pass) {
        setId(id);
        setPass(pass);
    }

    public User(String id, String pass, UserRole role) {
        setId(id);
        setPass(pass);
        setRole(role);
    }

    

    public boolean checkRole(UserRole role) {
        return this.role.intValue() <= role.intValue();
    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEP));
        }
        return 0;
    }

    public int setAttribute(String attributes[]) {
        int idx = 0;
        if (attributes != null && attributes.length >= User.ATTRIBUTE_COUNT) {
            this.id = attributes[idx++].trim();
            this.pass = attributes[idx++].trim();
            try {
                this.role = UserRole.valueOf(Integer.parseInt(attributes[idx++].trim()));
            } catch (NumberFormatException ex) {
                this.role = UserRole.INVALID;
            }
        }
        return idx;
    }

    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t{User: ");
        sb.append(id);
        sb.append(", Role: ");
        sb.append(role);
        sb.append("}");
        System.out.println(sb.toString());
    }
}
