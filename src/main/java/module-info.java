module com.yoss.thread {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.yoss.thread to javafx.fxml;
    exports com.yoss.thread;
}
