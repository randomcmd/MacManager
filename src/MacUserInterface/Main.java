package MacUserInterface;

import Debug.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Screen;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads GUI
     */

    static Stage primaryStage;
    private double xOffset = 0;
    private double yOffset = 0;

    public void start(Stage localPrimaryStage) throws Exception {
        primaryStage = localPrimaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Mac Manager");
        primaryStage.setScene(new Scene(root, 964, 500));
        primaryStage.setResizable(false);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.show();
    }

}

