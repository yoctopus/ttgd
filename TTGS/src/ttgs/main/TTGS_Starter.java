/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Simple Pre loader Using the ProgressBar Control
 *
 * @author Vincent
 */
public class TTGS_Starter extends Preloader {

    private ProgressBar bar;
    private Label status;
    private Stage stage;

    private Scene createPreloaderScene() {
        setBar(new ProgressBar());
        setStatus(new Label());
        BorderPane p = new BorderPane();
        p.setCenter(getStatus());
        return new Scene(p, 300, 150);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.setStage(stage);
        stage.setScene(createPreloaderScene());
        stage.show();
        start_TTGS();

    }

    public void start_TTGS() {
        RunTTGS.start(new String[1]);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            getStage().hide();
        }
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        getBar().setProgress(pn.getProgress());
    }

    /**
     * @return the bar
     */
    public ProgressBar getBar() {
        return bar;
    }

    /**
     * @param bar the bar to set
     */
    public void setBar(ProgressBar bar) {
        this.bar = bar;
    }

    /**
     * @return the status
     */
    public Label getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Label status) {
        this.status = status;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
