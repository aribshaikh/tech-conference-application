package Entities;


/**
 * this class stores all info relating to an Admin
 * and provides getters to extract those info
 * Admin is a subclass of User
 * @author Khoa Pham
 * @see User
 * A few notes to consider:
 *    * disallows changes in username, password for now
 */
public class Admin extends User {

    /**
     * a constructor to create an Admin object
     * There will only be one Admin
     * this will be created automatically without interference of clients
     */
    public Admin(String username, String password) { super(username, password); }

}
