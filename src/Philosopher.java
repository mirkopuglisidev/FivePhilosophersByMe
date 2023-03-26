import java.sql.Time;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    int philID;
    //ChopStick c1, c2;

    void think() {
        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm going to think");
        ThreadLocalRandom.current().nextLong(500, 1000);
    }

    void getHungry(){
        System.out.println("Hello there, i'm philosopher "+ philID  +" I'm starting to get hungry");
        ThreadLocalRandom.current().nextLong(500, 1000);
    }

    int takeChopsticks(){
        return 1;
    }

    int putChopsticks(){
        return 1;
    }

    void eat(){
            System.out.println("Hello there, i'm philosopher "+ philID  +" I'm going to eat");
            ThreadLocalRandom.current().nextLong(500, 2000);
        }

    @Override
    public void run() {
        think();
        getHungry();
        takeChopsticks();
        eat();
        putChopsticks();
    }
}
