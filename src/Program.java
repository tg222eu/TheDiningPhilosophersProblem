import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Totte on 2015-12-08.
 */
public class Program {
        public static int numberOfPhilosophers = 5;

        public static void main(String args[]) throws InterruptedException {
            ExecutorService executorService = null;

            Philosopher[] philosophers = null;
            try {
                philosophers = new Philosopher[numberOfPhilosophers];

                ChopStick[] chopSticks = new ChopStick[numberOfPhilosophers];
                for (int i = 0; i < numberOfPhilosophers; i++) {
                    chopSticks[i] = new ChopStick(i);
                }

                executorService = Executors.newFixedThreadPool(numberOfPhilosophers);

                for (int i = 0; i < numberOfPhilosophers; i++) {
                    if(i+1 != numberOfPhilosophers) {
                        philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[i + 1]);
                    }else{
                        philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[0]);
                    }
                }
                for(int i=0; i<numberOfPhilosophers; i++){
                    executorService.execute(philosophers[i]);
                }
                Thread.sleep(1000);

            } finally {

                executorService.shutdown();
                while (!executorService.isTerminated()) {
                    Thread.sleep(1000);
                }
            }
        }
}
