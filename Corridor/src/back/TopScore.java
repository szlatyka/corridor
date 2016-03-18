package back;

import java.io.Serializable;

/**
 *
 * @author Szlatyka
 */
public class TopScore implements Comparable<TopScore>, Serializable {

    private String name;
    private int score;
    private GameDifficulty difficulty;

    public TopScore(String name, int score, GameDifficulty difficulty) {
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return this.score;
    }

    public GameDifficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public int compareTo(TopScore o) {
        return this.getScore().compareTo(o.getScore());
    }
}
