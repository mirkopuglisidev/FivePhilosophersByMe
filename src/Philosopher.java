import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private int philID;
    private int mealCounter;
    private ChopStick firstToTake, secondToTake;
    /*
    meglio specificare temporalmente quali chopstick verranno presi,
    rispetto ad indicare chopstick destro da sinistro in modo da non confondersi con le stampe...
    in quanto l'ultimo filosofo si troverà il chopstick alla sua SX come secondo da prendere
    e quello alla sua DX come primo in modo da scongiurare il deadlock.
     */

    Philosopher(int i, ChopStick c1, ChopStick c2, int numberOfPhils){
        mealCounter = 0;
        firstToTake = c1;
        secondToTake = c2;
        philID = i;

        if(philID == numberOfPhils){
            // swap left with right chopsticks
            ChopStick tmp;
            tmp = secondToTake;
            secondToTake = firstToTake;
            firstToTake = tmp;
        }
    }

    void think() {
        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm thinking...");
        try {
            Thread.sleep( ThreadLocalRandom.current().nextLong(10, 50));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    void eat(){

        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm going to eat");

        System.out.println("I'm philosopher "+ philID +" I'll take the first Chopstick (chopstick "+ firstToTake.getChopID() +")");
        firstToTake.takeChopstick(philID);
        try {
          Thread.sleep(500); // deadlock molto facile così
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I'm philosopher "+ philID +" I'll take the second Chopstick (chopstick "+ secondToTake.getChopID() +")");
        secondToTake.takeChopstick(philID);

        System.err.println("I'm philosopher "+ philID  +", I am STARTING TO EAT");
        // simulo mangiata
        try {
            Thread.sleep( ThreadLocalRandom.current().nextLong(50, 100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mealCounter++;

        firstToTake.putChopsticks(philID);
        secondToTake.putChopsticks(philID);
    }

    void sleep(){
        System.out.println("I'm philosopher "+ philID +" I'll go sleep after " + mealCounter + " meals");
        try {
            sleep(1000* (int)Math.random());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        System.out.println("[Philosopher " + this.philID + "] first chopstick to take => "  + firstToTake.getChopID());
        System.out.println("[Philosopher " + this.philID + "], second chopstick to take => "  + secondToTake.getChopID());

       while (true)
       {
           think();
           eat();
           sleep();
       }
    }
}
