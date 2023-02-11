package cgs.repository;

import cgs.entity.Game;
import cgs.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler {
    private static final DatabaseHandler databaseHandler = new DatabaseHandler();

    public static DatabaseHandler getInstance() {
        return databaseHandler;
    }

    private DatabaseHandler() {
        String connectionString = "jdbc:postgresql://localhost:5432/cgs";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionString, "postgres", "890123890123");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final Connection connection;

    public String authorization(String login, String password) throws SQLException {
        String request = "select * from users where login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return "login not found";
        }
        String request2 = "select * from users where login = ? and password = ?";
        preparedStatement = connection.prepareStatement(request2);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next() ? String.valueOf(resultSet.getLong("id")) : "incorrect password";
    }

    public String registration(String login, String password) {
        try {
            String request = "insert into users (id, login, password, game_list_id) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, generateNewId("users", "id"));
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setLong(4, insertGameList());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return "error";
        }
        return "success";
    }

    private Long insertGameList() throws SQLException {
        long id = generateNewId("game_list", "game_list_id");
        String request = "insert into game_list (game_list_id, list) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, "");
        preparedStatement.executeUpdate();
        return id;
    }

    public ObservableList<Game> getGameList() {
        ObservableList<Game> games = FXCollections.observableArrayList();
        try {
            String request = "select * from game";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = new Game();
                game.setId(resultSet.getLong("id"));
                game.setName(resultSet.getString("name"));
                game.setPrice(resultSet.getDouble("price"));
                game.setDescription(resultSet.getString("description"));

                games.add(game);
            }
            return games;
        } catch (SQLException e) {
            return games;
        }
    }

    public User findUser(Long id) {
        User user = new User();
        try {
            String request = "select * from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setGameListId(resultSet.getLong("game_list_id"));

            request = "select * from game_list where game_list_id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, user.getGameListId());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user.setGameList(resultSet.getString("list"));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Game findGame(Long id) {
        Game game = new Game();
        try {
            String request = "select * from game where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            game.setId(resultSet.getLong("id"));
            game.setName(resultSet.getString("name"));
            game.setPrice(resultSet.getDouble("price"));
            game.setDescription(resultSet.getString("description"));

            return game;
        } catch (SQLException e) {
            return game;
        }
    }

    public Game findGame(String name) {
        Game game = new Game();
        try {
            String request = "select * from game where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            game.setId(resultSet.getLong("id"));
            game.setName(resultSet.getString("name"));
            game.setPrice(resultSet.getDouble("price"));
            game.setDescription(resultSet.getString("description"));

            return game;
        } catch (SQLException e) {
            return game;
        }
    }

    public void updateGameList(Long id, String list) {
        try {
            String request = "update game_list set list = ? where game_list_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, list);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {
        }
    }

    private long generateNewId(String table, String id) throws SQLException {
        String request = "select " + id + " from " + table;
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        ResultSet resultSet = preparedStatement.executeQuery();
        long newId = 0;
        try {
            while (resultSet.next()) {
                long thisId = Long.parseLong(resultSet.getString(id));
                if (newId < thisId) {
                    newId = thisId;
                }
            }
        } catch (Exception ignored) {
        }
        return ++newId;
    }
}
