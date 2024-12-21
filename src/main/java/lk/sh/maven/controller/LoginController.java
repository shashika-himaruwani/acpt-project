package lk.sh.maven.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.sh.maven.User;

public class LoginController {


    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    public void login(javafx.event.ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String userpassword = txtPassword.getText();

        User user1 = new User(username, userpassword);

        saveData(username, userpassword);
    }

    public void saveData(String username, String userpassword){
        System.out.println("Saving Data...");
        System.out.println("Username: " + username);
        System.out.println("Password: " + userpassword);

    }
}
