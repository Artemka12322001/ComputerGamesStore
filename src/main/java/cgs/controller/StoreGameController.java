package cgs.controller;

import cgs.service.StoreGameService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StoreGameController extends Controller {
    StoreGameService service = StoreGameService.getInstance();

    @FXML
    private Button buyButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button storeButton;

    @FXML
    private Label nameLabel;


    @FXML
    void initialize() {
        storeButton.setOnAction(actionEvent -> openOtherWindow("store", storeButton));
        buyButton.setOnMouseClicked(mouseEvent -> {
            service.buyGame();
            openOtherWindow("store", storeButton);
        });
        nameLabel.setText(service.getGame().getName());
        descriptionLabel.setText(service.getGame().getDescription());
    }
}
