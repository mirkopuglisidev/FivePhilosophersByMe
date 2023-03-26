public class MainPhilosophers {
    public static void main(String[] args) {
        Philosopher[] phils = new Philosopher[5];
        ChopStick[] chops = new ChopStick[5];

        for(int i=0; i<chops.length; i++)
        {
            chops[i] = new ChopStick(i+1); // cosi indice comincia da 1
        }

        for (int i=0; i<phils.length; i++){
                phils[i] = new Philosopher(i+1, chops[i%phils.length], chops[(i+1)%phils.length], phils.length );
                phils[i].start();
        }

    }
}
