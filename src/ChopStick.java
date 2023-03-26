public class ChopStick {
    enum states {LIBERO, OCCUPATO};
    states state;

    int chopID;

    ChopStick(int chopID){
       state = states.LIBERO;
       this.chopID = chopID;
    }

    synchronized int takeChopstick(int philID){
        if(state.equals(states.LIBERO))
        {
            this.state = states.OCCUPATO;
            System.err.println("chopstick " + chopID + " taken by philosopher " + philID);
        }
        else
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }

    synchronized int putChopsticks(int philID){
        this.state = states.LIBERO;
        System.out.println("chopstick " + chopID + " put down by philosopher " + philID);
        notifyAll();
        return 1;
    }


}
