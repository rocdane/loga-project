module startup.loga.loga {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires spring.context;

    opens startup.loga.loga to javafx.fxml;
    exports startup.loga.loga;
}