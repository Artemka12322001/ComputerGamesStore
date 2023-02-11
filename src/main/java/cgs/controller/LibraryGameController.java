package cgs.controller;

import cgs.service.LibraryGameService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LibraryGameController extends Controller {
    LibraryGameService service = LibraryGameService.getInstance();

    @FXML
    private Button deleteButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button libraryButton;

    @FXML
    private Label nameLabel;


    @FXML
    void initialize() {
        libraryButton.setOnAction(actionEvent -> openOtherWindow("library", libraryButton));
        deleteButton.setOnMouseClicked(mouseEvent -> {
            service.deleteGame();
            openOtherWindow("library", libraryButton);
        });
        nameLabel.setText(service.getGame().getName());
        descriptionLabel.setText(service.getGame().getDescription());
    }
}
