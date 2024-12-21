module lk.sh.maven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens lk.sh.maven to javafx.fxml;
    exports lk.sh.maven;
    exports lk.sh.maven.controller;
    opens lk.sh.maven.controller to javafx.fxml;
    exports lk.sh.maven.tm;
    opens lk.sh.maven.tm to javafx.fxml;
}