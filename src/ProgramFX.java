/**
 * Created by Totte on 2015-12-08.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ProgramFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int circlePositionX = 300;
        int circlePositionY = 300;
        int circleRadius = 120;

        Circle circleTable = new Circle();
        circleTable.setCenterX(circlePositionX);
        circleTable.setCenterY(circlePositionY);
        circleTable.setRadius(circleRadius);

        Circle circleChopStick0 = createChopStick(circlePositionX, circlePositionY, circleRadius-30, 0);
        Circle circleChopStick1 = createChopStick(circlePositionX, circlePositionY, circleRadius-30, 72);
        Circle circleChopStick2 = createChopStick(circlePositionX, circlePositionY, circleRadius-30, 144);
        Circle circleChopStick3 = createChopStick(circlePositionX, circlePositionY, circleRadius-30, 216);
        Circle circleChopStick4 = createChopStick(circlePositionX, circlePositionY, circleRadius-30, 288);

        Circle seat0 = createSeat(circlePositionX, circlePositionY, circleRadius + 30, 0 + 36);
        Circle seat1 = createSeat(circlePositionX, circlePositionY, circleRadius + 30, 72 + 36);
        Circle seat2 = createSeat(circlePositionX, circlePositionY, circleRadius + 30, 144 + 36);
        Circle seat3 = createSeat(circlePositionX, circlePositionY, circleRadius + 30, 216 + 36);
        Circle seat4 = createSeat(circlePositionX, circlePositionY, circleRadius+30, 288+36);

        BorderPane root = new BorderPane();
        Pane canvas = new Pane();

        canvas.getChildren().addAll(circleTable, circleChopStick0, circleChopStick1, circleChopStick2, circleChopStick3,
                circleChopStick4, seat0, seat1, seat2, seat3, seat4);
        //canvas.getChildren().remove(line1);

        root.setCenter(canvas);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

    public Circle createChopStick(int circlePositionX, int circlePositionY, int circleRadius, int degree){
        Circle temp = new Circle();
        temp.setStroke(Color.YELLOW);
        temp.setCenterX(circlePositionX + circleRadius * Math.cos(degree * (Math.PI / 180)));
        temp.setCenterY(circlePositionY + circleRadius * Math.sin(degree * (Math.PI / 180)));
        temp.setRadius(15.0f);
        return temp;
    }

    public Circle createSeat(int circlePositionX, int circlePositionY, int circleRadius, int degree){
        Circle temp = new Circle();
        temp.setCenterX(circlePositionX + circleRadius * Math.cos(degree * (Math.PI / 180)));
        temp.setCenterY(circlePositionY + circleRadius * Math.sin(degree * (Math.PI / 180)));
        temp.setRadius(30.0f);
        return temp;
    }
}
