package cgs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import cgs.service.RegistrationService;

public class RegistrationController extends Controller {
    RegistrationService service = RegistrationService.getInstance();

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
        registrationButton.setOnMouseClicked(mouseEvent -> {
            String response = service.registration(loginTextField.getText(), passwordPasswordField.getText());

            errorLabel.setText(response);
            if (response.equals("success")) {
                openOtherWindow("authorization", registrationButton);
            }
        });
    }
}
