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

    public Text averageTimeText = new Text();

    public int hungryCounter = 0;
    public int ateCounter = 0;
    public int thinkCounter = 0;
    public int globalTime = 0;

    public int id;
    public boolean hungry;
    public int countSeconds;
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

        updateAverageTimeText();

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
        text.setText("Stop");
    }

    public void stop(){
        havingADinnerParty = false;
    }

    public void think(){
        System.out.println("Philosopher_" + id + " is thinking.");
        text.setText(id + " THINKING");
        try {
            int time = (int) ( Math.random()* 30 * 1000);
            Thread.sleep(time);
            thinkCounter += time/1000;
            globalTime += time/1000;
            updateAverageTimeText();
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
            int time = (int) ( Math.random() * 30* 1000);
            Thread.sleep(time);
            ateCounter += time/1000;
            globalTime += time/1000;
            updateAverageTimeText();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            if(leftStick.chopStickAvailable && rightStick.chopStickAvailable)
        if (leftStick.pickUp()) {
            if (rightStick.pickUp()) {
                eat();
            } else {
                leftStick.putDown();
                Thread.sleep(10);
                countSeconds += 10;
                if(countSeconds == 1000){
                    hungryCounter++;
                    updateAverageTimeText();
                    countSeconds = 0;
                }
            }
        }
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

    public Text averageMoodTime(){
        return averageTimeText;
    }

    public void updateAverageTimeText(){
        int averageAte = 0;
        int averageThought = 0;
        int averageHungry = 0;

        if(ateCounter!=0){
            averageAte = globalTime / ateCounter;
        }else{
            averageAte = 0;
        }
        if(thinkCounter != 0){
            averageThought = globalTime / thinkCounter;
        }else{
            averageThought = 0;
        }
        if(hungryCounter != 0){
            averageHungry = globalTime / hungryCounter;
        }else{
            averageHungry = 0;
        }

        if(globalTime != 0) {
            averageTimeText.setText("Philosopher" + id + " average time(ate: " + averageAte + ". Thought: " + averageThought + " Hungered: " +
                    averageHungry + ")");
        }else{
            averageTimeText.setText("Philosopher" + id + " average time(ate: " + 0 + ". Thought: " + 0 + " Hungered: " +
                    0 + ")");
        }
    }

    public int AteNumberOfTimes(){
        return ateCounter;
    }
}
