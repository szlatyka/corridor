package front;

import front.control.GameField;
import front.menu.StatisticsPanel;
import front.menu.RulesPanel;
import front.menu.MenuPanel;
import back.Game;
import back.GameDifficulty;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Peter
 */
public class Window extends JFrame {

    private MenuPanel menu = new MenuPanel(this);
    private StatisticsPanel stats = new StatisticsPanel(this);
    private RulesPanel rules = new RulesPanel(this);
    private GameField gameField;

    public Window() {
        setTitle("Corrydor");
        setMinimumSize(new Dimension(820, 660));
		setResizable(true);
        setContentPane(menu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startLocalGame(GameDifficulty difficulty) {
        gameField = new GameField(this, new Game(difficulty));
        setContentPane(gameField);
        gameField.updateUI();
	}
	
    public void startGame(String map)
    {
        gameField = new GameField(this, new Game(map));
        setContentPane(gameField);
        gameField.updateUI();
    }

    public void showMainMenu() {
        setContentPane(menu);
        menu.updateUI();
    }

    public void showRules() {
        setContentPane(rules);
        rules.updateUI();
    }

    public void showStats() {
        stats.loadStats();
        setContentPane(stats);
        stats.updateUI();
    }
}
