package cgs.entity;

public class User {
    private Long id;
    private String login;
    private String password;
    private Long gameListId;
    private String gameList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGameListId() {
        return gameListId;
    }

    public void setGameListId(Long gameListId) {
        this.gameListId = gameListId;
    }

    public String getGameList() {
        return gameList;
    }

    public void setGameList(String gameList) {
        this.gameList = gameList;
    }
}
