import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Totte on 2015-12-08.
 */
public class ChopStick {

    public volatile boolean chopStickAvailable;
    private Lock lock = new ReentrantLock();
    public Circle circle;

    public double x;
    public double y;
    public Text idText;

    public int id;

    public ChopStick(int i, int circlePositionX, int circlePositionY, int circleRadius, int degree){
        id = i;
        chopStickAvailable = true;
        Circle temp = new Circle();
        temp.setStroke(Color.YELLOW);
        temp.setFill(Color.YELLOW);
        x = circlePositionX + (circleRadius-30) * Math.cos(degree * (Math.PI / 180));
        y = circlePositionY + (circleRadius-30) * Math.sin(degree * (Math.PI / 180));

        Text tempText = new Text(Integer.toString(id));
        tempText.setLayoutX(x);
        tempText.setLayoutY(y);
        idText = tempText;

        temp.setCenterX(x);
        temp.setCenterY(y);
        temp.setRadius(15.0f);
        circle = temp;
    }

    public boolean pickUp() throws InterruptedException{
        if(lock.tryLock()){
            chopStickAvailable = false;
            System.out.println(this.toString() + " is picked up");
            return true;
        }
        return false;
    }

    public void putDown(){
        System.out.println(this.toString() + " is put down");
        chopStickAvailable = true;
        lock.unlock();
    }

    public String toString(){
        return "ChopStick_" + id;
    }

    public Text getText(){
        return idText;
    }

    public Circle getCircle(){
        return circle;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

}
