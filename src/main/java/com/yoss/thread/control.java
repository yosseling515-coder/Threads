package com.yoss.thread;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class control implements Initializable {



    @FXML
    private TextField txtClics;
    @FXML
    private Button btnContar;
    @FXML
    private ProgressBar pgbProgreso;
    @FXML
    private Button btnEsperar;

    private int contadorClics = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtClics.setText(String.valueOf(contadorClics));
        pgbProgreso.setProgress(0.0);
    }

    @FXML
    private void btnContarClick(ActionEvent event) {
        contadorClics++;
        txtClics.setText(String.valueOf(contadorClics));
    }

    @FXML
    private void btnEsperarClick(ActionEvent event) {
        btnEsperar.setDisable(true);
        pgbProgreso.setProgress(0.0);

        Thread hilo = new Thread(() -> {
            int tiempoTotalMs = 10000; // 10 segundos
            int intervaloMs = 100;
            int pasos = tiempoTotalMs / intervaloMs;

            for (int i = 1; i <= pasos; i++) {
                try {
                    Thread.sleep(intervaloMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                double progreso = (double) i / pasos;

                Platform.runLater(() -> {
                    pgbProgreso.setProgress(progreso);
                });
            }

            Platform.runLater(() -> {
                btnEsperar.setDisable(false);
            });
        });

        hilo.start();
    }
}
    

