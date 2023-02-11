package cgs.service;

import cgs.entity.Game;
import cgs.entity.User;
import cgs.repository.DatabaseHandler;

public class LibraryGameService {
    private static final LibraryGameService libraryGameService = new LibraryGameService();

    private LibraryGameService() {
    }

    public static LibraryGameService getInstance() {
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

    public void deleteGame() {
        String[] userGameList = user.getGameList().split("");
        for (int i = 0; i < userGameList.length; i++) {
            if (userGameList[i].equals(game.getId().toString())) {
                userGameList[i] = "delete_this";
            }
        }
        StringBuilder str = new StringBuilder();
        for (String s : userGameList) {
            if (!s.equals("delete_this")) {
                str.append(s);
            }
        }
        user.setGameList(str.toString());
        databaseHandler.updateGameList(user.getGameListId(), user.getGameList());
    }

    public void setUser(User user) {
        this.user = user;
    }
}
