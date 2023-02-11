package cgs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import cgs.service.AuthorizationService;

public class AuthorizationController extends Controller {
    AuthorizationService service = AuthorizationService.getInstance();

    @FXML
    private Button authorizationButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button registrationButton;

    @FXML
    void initialize() {
        registrationButton.setOnAction(actionEvent -> openOtherWindow("registration", registrationButton));
        authorizationButton.setOnMouseClicked(mouseEvent -> {
            errorLabel.setText(service.authorization(loginTextField.getText(), passwordPasswordField.getText()));
            if (errorLabel.getText().equals("success")) {
                openOtherWindow("store", authorizationButton);
            }
        });
    }
}
