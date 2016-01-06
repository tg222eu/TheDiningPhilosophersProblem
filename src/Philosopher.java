/**
 * Created by Totte on 2015-12-08.
 */
public class Philosopher implements Runnable{

    public ChopStick leftStick;
    public ChopStick rightStick;

    public int id;
    public boolean hungry;
    public int ateCounter;
    public boolean havingADinnerParty;

    public Philosopher(int n, ChopStick l, ChopStick r){
        havingADinnerParty = true;
        hungry = false;
        id = n;
        this.leftStick = l;
        this.rightStick = r;
        ateCounter = 0;
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
        try {
            Thread.sleep((int) ( Math.random()* 30 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hungry();
    }

    public void eat(){
        System.out.println("Philosopher_" + id + " is eating.");
        try {
            Thread.sleep((int) ( Math.random() * 30* 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ateCounter++;
        hungry = false;
        leftStick.putDown();
        rightStick.putDown();
    }

    public void hungry(){
        hungry = true;
        System.out.println("Philosopher_" + id + " is hungry");
    }

    public void tryToEat(){
        if (leftStick.pickUp()) {
            if (rightStick.pickUp()) {
                eat();
            } else {
                leftStick.putDown();
            }
        }
        try {
            Thread.sleep((int) ( Math.random()* 30 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int AteNumberOfTimes(){
        return ateCounter;
    }
}
