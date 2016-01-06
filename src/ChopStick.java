/**
 * Created by Totte on 2015-12-08.
 */
public class ChopStick {

    public boolean chopStickAvailable;

    public int id;

    public ChopStick(int i){
        id = i;
        chopStickAvailable = true;
    }

    public boolean pickUp(){
        if(chopStickAvailable){
            chopStickAvailable = false;
            System.out.println(this.toString() + " is picked up");
            return true;
        }
        return false;
    }

    public void putDown(){
        System.out.println(this.toString() + " is put down");
        chopStickAvailable = true;
    }

    public String toString(){
        return "ChopStick_" + id;
    }

}
