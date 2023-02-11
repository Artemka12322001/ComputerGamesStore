module cgs {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;

    opens cgs.entity to javafx.base;
    opens cgs.controller to javafx.fxml;
    exports cgs.controller;
    opens cgs to javafx.fxml;
    exports cgs;
}