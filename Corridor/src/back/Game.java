package back;

import java.awt.Point;

/**
 *
 * @author Szlatyka
 */
public class Game {

    private Dice dice;
    private Player[] players;
    private Map map;
    private TopScoreList topscores;
    private GameDifficulty difficulty;
    private int activePlayer;

    public Game(GameDifficulty difficulty) {
        String file = "";
        switch (difficulty) {
            case EASY:
                file = "Easy.map";
                break;
            case NORMAL:
                file = "Normal.map";
                break;
            case HARD:
                file = "Hard.map";
                break;
        }
        this.init(file, difficulty);
    }
    
    public Game(String file)
    {
        this.init(file, GameDifficulty.CUSTOM);
    }

    public void init(String file, GameDifficulty difficulty) {
        this.map = new Map(file);
        this.dice = new Dice();
        this.difficulty = difficulty;
        this.topscores = (TopScoreList) FileProvider.loadObjectFromFile(TopScoreList.SAVEFILE);
        if (this.topscores == null) {
            this.topscores = new TopScoreList();
        }
        this.players = new Player[2];
        this.players[0] = new Player(new Point(0, 0), new Point(18, 18), 5);
        this.players[1] = new Player(new Point(18, 18), new Point(0, 0), 5);
    }

    public Dice getDice() {
        return this.dice;
    }

    public Player getPlayer(int i) {
        return this.players[i];
    }

    public Player getActivePlayer() {
        return this.players[this.activePlayer];
    }

    public int getActivePlayerNum() {
        return this.activePlayer;
    }

    public void setActivePlayer(int i) {
        this.activePlayer = i;
    }

    public Map getMap() {
        return this.map;
    }

    public TopScoreList getTopScoreList() {
        return this.topscores;
    }

    public GameDifficulty getDifficulty() {
        return this.difficulty;
    }
}
