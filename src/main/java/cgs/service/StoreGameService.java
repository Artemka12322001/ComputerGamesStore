package cgs.service;

import cgs.entity.Game;
import cgs.entity.User;
import cgs.repository.DatabaseHandler;

public class StoreGameService {
    private static final StoreGameService libraryGameService = new StoreGameService();

    private StoreGameService() {
    }

    public static StoreGameService getInstance() {
        return libraryGameService;
    }

    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private Game game;
    private User user;

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void buyGame() {
        String[] userGameList = user.getGameList().split("");
        for (String g : userGameList) {
            if (g.equals(game.getId().toString())) {
                return;
            }
        }
        user.setGameList(user.getGameList() + game.getId());
        databaseHandler.updateGameList(user.getGameListId(), user.getGameList());
    }
}
