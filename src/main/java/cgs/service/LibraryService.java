package cgs.service;

import cgs.entity.Game;
import cgs.entity.User;
import cgs.repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryService {
    private static final LibraryService libraryService = new LibraryService();

    private LibraryService() {
    }

    public static LibraryService getInstance() {
        return libraryService;
    }
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final LibraryGameService libraryGameService = LibraryGameService.getInstance();
    private User user;

    public ObservableList<Game> getGameList(){
        ObservableList<Game> games = FXCollections.observableArrayList();
        String[] gamesList = user.getGameList().split("");
        if (!gamesList[0].equals("")) {
            for (String g : gamesList) {
                games.add(databaseHandler.findGame(Long.parseLong(g)));
            }
        }
        return games;
    }

    public void findUser(Long id) {
        this.user = databaseHandler.findUser(id);
    }

    public void injectGame(Game game) {
        libraryGameService.setGame(databaseHandler.findGame(game.getName()));
        libraryGameService.setUser(user);
    }
}
