package back;

import java.util.Random;

/**
 *
 * @author Szlatyka
 */
public class Dice {

    private Random random_seed;
    private int value;

    public Dice() {
        this.random_seed = new Random();
    }

    public int roll() {
        this.value = this.random_seed.nextInt(6) + 1;
        return this.value;
    }

    public int getValue() {
        return this.value;
    }
}
