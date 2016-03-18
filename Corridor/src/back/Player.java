package back;

import java.awt.Point;

/**
 *
 * @author Szlatyka
 */
public class Player {

    private Point position;
    private Point winningPosition;
    private int score;
    private int wallsRemaining;

    public Player(Point position, Point winningPosition, int walls) {
        this.position = position;
        this.winningPosition = winningPosition;
        this.wallsRemaining = walls;
        this.score = 3000;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public int getRemainingWalls() {
        return this.wallsRemaining;
    }

    public boolean removeWall() {
        if (this.wallsRemaining > 0) {
            this.wallsRemaining--;
            return true;
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(PlayerAction action, int moves) {
        switch (action) {
            case MOVE:
                this.score -= moves * 20;
                break;
            case BUILDWALL:
                this.score -= 100;
                break;
            case CUTHEDGE:
                this.score -= 50;
                break;
            case TIME:
                this.score -= 1000;
                break;
        }
    }

    public boolean hasWon() {
        return this.position.equals(this.winningPosition);
    }
}
