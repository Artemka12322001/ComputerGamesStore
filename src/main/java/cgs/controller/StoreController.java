package cgs.controller;

import cgs.entity.Game;
import cgs.service.StoreService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StoreController extends Controller {
    StoreService service = StoreService.getInstance();

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Game> gameTableView;

    @FXML
    private Button libraryButton;

    @FXML
    private TableColumn<Game, String> nameTableColumn;

    @FXML
    private TableColumn<Game, String> priceTableColumn;

    @FXML
    private TableColumn<Game, String> descriptionTableColumn;

    @FXML
    void initialize() {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        exitButton.setOnAction(actionEvent -> openOtherWindow("authorization", exitButton));
        libraryButton.setOnAction(actionEvent -> {
            service.injectUser();
            openOtherWindow("library", libraryButton);
        });
        gameTableView.setItems(service.getGameList());

        gameTableView.setOnMouseClicked(mouseEvent -> {
            Game game = gameTableView.getSelectionModel().getSelectedItem();
            if(game != null){
                service.injectGame(game);
                openOtherWindow("store_game", libraryButton);
            }
        });
    }
}
