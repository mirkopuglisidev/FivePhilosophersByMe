public class MainPhilosophers {
    public static void main(String[] args) {

        final int philsNumber = 5;

        Philosopher[] phils = new Philosopher[philsNumber];
        ChopStick[] chops = new ChopStick[philsNumber];

        // inizializzazione istanze di Chopstick
        for(int i=0; i<chops.length; i++)
        {
            chops[i] = new ChopStick(i+1); //in questo modo l'indice comincia da 1
        }

        // inizializzazione istanze di Philosopher
        for (int i=0; i<phils.length; i++){
                phils[i] = new Philosopher(i+1, chops[i%phils.length], chops[(i+1)%phils.length], phils.length );
                phils[i].start();
        }

    }
}
