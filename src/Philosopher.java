import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


/**
 * Created by Totte on 2015-12-08.
 */
public class Philosopher implements Runnable{

    public ChopStick leftStick;
    public ChopStick rightStick;

    public Circle circle;
    public Text text;
    public Line leftLine;
    public Line rightLine;

    public int id;
    public boolean hungry;
    public int ateCounter;
    public boolean havingADinnerParty;
    public double x;
    public double y;

    public Philosopher(int n, ChopStick l, ChopStick r, int circlePositionX, int circlePositionY, int circleRadius, int degree){
        havingADinnerParty = true;
        hungry = false;
        id = n;
        this.leftStick = l;
        this.rightStick = r;
        ateCounter = 0;

        Line leftTemp = new Line();
        Line rightTemp = new Line();
        leftTemp.setStroke(Color.RED);
        rightTemp.setStroke(Color.RED);

        leftLine = leftTemp;
        rightLine = rightTemp;

        Text temp2 = new Text(id + " THINKING");
        x = circlePositionX + (circleRadius+30) * Math.cos(degree * (Math.PI / 180));
        y = circlePositionY + (circleRadius+30) * Math.sin(degree * (Math.PI / 180));

        temp2.setLayoutX(x);
        temp2.setLayoutY(y);
        temp2.setFill(Color.BLUE);

        Circle temp = new Circle();
        temp.setCenterX(circlePositionX + (circleRadius+30) * Math.cos(degree * (Math.PI / 180)));
        temp.setCenterY(circlePositionY + (circleRadius + 30) * Math.sin(degree * (Math.PI / 180)));
        temp.setRadius(30.0f);

        text = temp2;
        circle = temp;
        //this.think();

    }

    public void run(){
        while(havingADinnerParty) {
            if (hungry) {
                tryToEat();
            } else {
                think();
            }
        }
    }

    public void stop(){
        havingADinnerParty = false;
    }

    public void think(){
        System.out.println("Philosopher_" + id + " is thinking.");
        text.setText(id + " THINKING");
        try {
            Thread.sleep((int) ( Math.random()* 30 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hungry();
    }

    public void eat() {
        System.out.println("Philosopher_" + id + " is eating.");
        text.setText(id + " EATING");
        leftLine.setStartX(leftStick.x);
        leftLine.setStartY(leftStick.y);
        leftLine.setEndX(x);
        leftLine.setEndY(y);

        rightLine.setStartX(rightStick.x);
        rightLine.setStartY(rightStick.y);
        rightLine.setEndX(x);
        rightLine.setEndY(y);

        try {
            Thread.sleep((int) ( Math.random() * 30* 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ateCounter++;
        hungry = false;

        leftLine.setStartX(0);
        leftLine.setStartY(0);
        leftLine.setEndX(0);
        leftLine.setEndY(0);

        rightLine.setStartX(0);
        rightLine.setStartY(0);
        rightLine.setEndX(0);
        rightLine.setEndY(0);

        leftStick.putDown();
        rightStick.putDown();
    }

    public void hungry(){
        hungry = true;
        System.out.println("Philosopher_" + id + " is hungry");
        text.setText(id + " HUNGRY");
    }

    public void tryToEat(){
        try {
        if (leftStick.pickUp()) {
            if (rightStick.pickUp()) {
                eat();
            } else {
                leftStick.putDown();
            }
        }
            Thread.sleep((int) ( Math.random()* 30 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Circle getCircle(){
        return circle;
    }

    public Text getText(){
        return text;
    }

    public Line getLeftLine(){
        return leftLine;
    }

    public Line getRightLine(){
        return rightLine;
    }

    public int AteNumberOfTimes(){
        return ateCounter;
    }
}
