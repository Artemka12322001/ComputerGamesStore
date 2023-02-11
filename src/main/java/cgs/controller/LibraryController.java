package cgs.controller;

import cgs.entity.Game;
import cgs.service.LibraryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibraryController extends Controller {
    LibraryService service = LibraryService.getInstance();
    @FXML
    private Button exitButton;

    @FXML
    private TableView<Game> gameTableView;

    @FXML
    private TableColumn<Game, String> nameTableColumn;

    @FXML
    private Button storeButton;

    @FXML
    void initialize() {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        exitButton.setOnAction(actionEvent -> openOtherWindow("authorization", exitButton));
        storeButton.setOnAction(actionEvent -> openOtherWindow("store", storeButton));

        gameTableView.setItems(service.getGameList());

        gameTableView.setOnMouseClicked(mouseEvent -> {
            Game game = gameTableView.getSelectionModel().getSelectedItem();
            if(game != null){
                service.injectGame(game);
                openOtherWindow("library_game", exitButton);
            }
        });
    }
}
