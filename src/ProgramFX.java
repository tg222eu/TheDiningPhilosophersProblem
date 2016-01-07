/**
 * Created by Totte on 2015-12-08.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProgramFX extends Application {

    ArrayList<ChopStick> listOfChop = new ArrayList<ChopStick>();
    ChopStick[] chopArr = new ChopStick[5];
    Philosopher[] philArr = new Philosopher[5];
    public ExecutorService executorService = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int circlePositionX = 300;
        int circlePositionY = 300;
        int circleRadius = 120;
        Pane canvas = new Pane();
        VBox vbox = new VBox();

        Button startButton = new Button("Start");

        Circle circleTable = new Circle();
        circleTable.setCenterX(circlePositionX);
        circleTable.setCenterY(circlePositionY);
        circleTable.setRadius(circleRadius);

        canvas.getChildren().addAll(circleTable);

        for(int i=0; i<5; i++){
            chopArr[i] = new ChopStick(i, circlePositionX, circlePositionY, circleRadius, i*72);
            System.out.println(chopArr[i].getX() + " " + chopArr[i].getY());
            canvas.getChildren().addAll(chopArr[i].getCircle(), chopArr[i].getText());
        }

        executorService = Executors.newFixedThreadPool(5);

        startButton.setOnAction(Event -> {
            for(int i=0; i<5; i++){
                philArr[i] = new Philosopher(i, chopArr[i], chopArr[(i+1)%5], circlePositionX, circlePositionY,
                        circleRadius, i*72+36);
                canvas.getChildren().addAll(philArr[i].getCircle(), philArr[i].getText(), philArr[i].getLeftLine(),
                        philArr[i].getRightLine());
                vbox.getChildren().addAll(philArr[i].averageMoodTime());
                executorService.execute(philArr[i]);
            }
        });

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        Button stopButton = new Button("Stop");

        stopButton.setOnAction(Event -> {
            for (Philosopher p : philArr) {
                p.havingADinnerParty = false;
            }
        });

        hbox.getChildren().addAll(startButton, stopButton);

        BorderPane root = new BorderPane();

        root.setRight(vbox);
        root.setTop(hbox);
        root.setCenter(canvas);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }
}
