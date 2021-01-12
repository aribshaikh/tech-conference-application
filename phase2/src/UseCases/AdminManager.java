package UseCases;

import Entities.Admin;

import java.util.ArrayList;
import java.util.List;


/**
 * this class manages (stores and updates) the Admin
 * It also supports the following Admin functionalities
 *         - create the Admin account
 *         - check password for the Admin
 * @author Khoa Pham, Vladimir Caterov
 * @see Admin
 */
public class AdminManager {

    private final Admin admin = new Admin("admin4", "admin4");

    /**
     * This method checks if the username and password are correct
     * @param username: the inputted username (param_type: String)
     * @param password: the inputted password to be checked (param_type: String)
     * @return boolean whether username and password are correct
     */
    public boolean checkPassword(String username, String password) {
        return username.equals(admin.getUserId()) && password.equals(admin.getPassword());
    }

    /**
     * This method checks if the username provided is associated with an Admin Entity
     * @param username: the inputted username (param_type: String)
     * @return Boolean : returns true if the provided username is associated with an Admin Entity, false otherwise
     */
    public boolean isAdmin(String username){
        return username.equals(admin.getUserId());
    }


}