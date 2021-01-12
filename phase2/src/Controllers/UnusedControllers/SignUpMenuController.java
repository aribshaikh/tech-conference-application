package Controllers.UnusedControllers;


import Controllers.AccountHandler;

public class SignUpMenuController {
    private final AccountHandler accountHandler;


    public SignUpMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
    }
    public boolean signUp(String username, String password){
        return accountHandler.signup(username, password, "attendee");
    }



}
