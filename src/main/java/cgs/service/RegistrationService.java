package cgs.service;

import cgs.repository.DatabaseHandler;

public class RegistrationService {
    private static final RegistrationService registrationService = new RegistrationService();

    private RegistrationService() {
    }

    public static RegistrationService getInstance() {
        return registrationService;
    }

    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public String registration(String login, String password) {
        if (login.equals(""))
            return "Введи логин";
        if (login.length() < 5)
            return "Логин короче 5 символов";
        if (password.equals(""))
            return "Введи пароль";
        if (password.length() < 5)
            return "Пароль короче 5 символов";

        return databaseHandler.registration(login, password);
    }
}
