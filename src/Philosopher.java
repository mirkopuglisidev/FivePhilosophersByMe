import java.sql.Time;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    int philID;
    int mealCounter;
    ChopStick left, right;

    Philosopher(int i, ChopStick c1, ChopStick c2, int numberOfPhils){
        mealCounter = 0;
        left = c1;
        right = c2;
        philID = i;

        if(philID == numberOfPhils){
            // swap left with right chopsticks
            ChopStick tmp;
            tmp = right;
            right = left;
            left = tmp;
        }
    }

    void think() {
        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm going to think");
        try {
            Thread.sleep( ThreadLocalRandom.current().nextLong(10, 50));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    void eat(){

        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm going to eat");

        System.out.println("I'm philosopher "+ philID +" I'll take the right Chopstick (chopstick "+ left.chopID +")");
        left.takeChopstick(philID);
        try {
          Thread.sleep(500); // deadlock molto facile così
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I'm philosopher "+ philID +" I'll take the right Chopstick (chopstick "+ right.chopID +")");
        right.takeChopstick(philID);

        System.err.println("I'm philosopher "+ philID  +", I am STARTING TO EAT");
        // simulo mangiata
        try {
            Thread.sleep( ThreadLocalRandom.current().nextLong(50, 100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mealCounter++;

        left.putChopsticks(philID);
        right.putChopsticks(philID);
    }

    void sleep(){
        System.out.println("I'm philosopher "+ philID +" I'll take a napv after " + mealCounter + "meals");
        try {
            sleep(1000* (int)Math.random());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    int getLeft()
    {
        return left.chopID;
    }

    int getRight(){
        return right.chopID;
    }

    @Override
    public void run() {

        System.out.println("Philosopher " + this.philID + " left chopstick => "  + getLeft());
        System.out.println("Philosopher " + this.philID + " right chopstick => "  + getRight());

       while (true)
       {
           think();
           eat();
           sleep();
       }
    }
}
