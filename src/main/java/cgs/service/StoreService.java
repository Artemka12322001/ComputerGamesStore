package cgs.service;

import cgs.entity.Game;
import cgs.entity.User;
import cgs.repository.DatabaseHandler;
import javafx.collections.ObservableList;

public class StoreService {
    private static final StoreService storeService = new StoreService();

    private StoreService() {
    }

    public static StoreService getInstance() {
        return storeService;
    }

    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final LibraryService libraryService = LibraryService.getInstance();
    private final StoreGameService storeGameService = StoreGameService.getInstance();
    private User user;

    public ObservableList<Game> getGameList() {
        return databaseHandler.getGameList();
    }

    public void findUser(Long id) {
        this.user = databaseHandler.findUser(id);
    }

    public void injectUser() {
        libraryService.findUser(user.getId());
    }

    public void injectGame(Game game) {
        storeGameService.setGame(game);
        storeGameService.setUser(user);
    }
}
