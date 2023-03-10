package cgs.service;

import cgs.repository.DatabaseHandler;

import java.sql.SQLException;

public class AuthorizationService {
    private static final AuthorizationService authorizationService = new AuthorizationService();

    private AuthorizationService() {
    }

    public static AuthorizationService getInstance() {
        return authorizationService;
    }
    private final StoreService storeService = StoreService.getInstance();
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public String authorization(String login, String password) {
        try {
            if (login.equals(""))
                return "Введи логин";
            if (password.equals(""))
                return "Введи пароль";

            String response = databaseHandler.authorization(login, password);
            if (response.equals("login not found")) {
                return "Пользователь не найден";
            }
            if (response.equals("incorrect password")) {
                return "Неверный пароль";
            }
            storeService.findUser(Long.parseLong(response));
            return "success";
        } catch (SQLException e) {
            return "Неизвестная ошибка";
        }
    }
}
