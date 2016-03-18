package back;

/**
 *
 * @author Szlatyka
 */
public enum TileType {

    NORMAL(1),
    PORTAL(5),
    MOUNTAIN(3),
    EMTPYWALL(0),
    WALL(1),
    HEDGE(2),
    MIDDLE(8);
    private int value;

    private TileType(int value) {
        this.value = value;
    }
}
