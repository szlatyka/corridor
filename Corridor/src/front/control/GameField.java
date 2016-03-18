package front.control;

import back.Game;
import back.GameDifficulty;
import back.Music;
import back.PlayerAction;
import back.TileType;
import back.TopScore;
import front.Countdown;
import front.GameMenu;
import front.MapView;
import front.MenuOptions;
import front.Window;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Szlatyka
 */
public class GameField extends JPanel {

    private MapView mapView;
    private GameMenu gameMenu;
    private MenuOptions menuopt;
    private Window parent;
    private Game gameModel;
    private PlayerAction action;
    private Countdown countdo;
    private Boolean actiondone;
    private Music abba;
    
    private final GridBagConstraints c = new GridBagConstraints();

    public GameField(Window window, Game game) {
        
        abba=new Music(true);
        abba.start();
        this.parent = window;
        this.gameModel = game;
        this.menuopt=new MenuOptions(this);
        this.gameMenu = new GameMenu(this);
        this.mapView = new MapView(this);
        this.countdo = new Countdown(this);
        this.countdo.start();
        
        this.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=2;
        this.add(this.mapView, c);
        c.gridheight=1;
        c.gridx = 1;
        c.gridy = 0;
        this.add(this.menuopt, c);
        c.gridx = 1;
        c.gridy = 1;
        this.add(this.gameMenu, c);
        actiondone=false;
    }

    public Game getModel() {
        return this.gameModel;
        
        //countdo.start();
    }

    /* *** LÉPÉS ELŐTTI "ESEMÉNYEK" ***************************************** */
    /* Ezeket mind GameMenu hívja meg */
    public void setAvailableTiles(Point p) {
        ArrayList<Point> tmp = this.gameModel.getMap().getAvailableTiles(p, this.gameModel.getDice().getValue());
        //Két játékos nem állhat ugyanarra a mezőre
        tmp.remove(this.gameModel.getPlayer(0).getPosition());
        tmp.remove(this.gameModel.getPlayer(1).getPosition());
        this.mapView.setAvailableTiles(tmp);
        this.action = PlayerAction.MOVE;
    }

    public void setAvailableWalls() {
        ArrayList<Point> tmp = this.gameModel.getMap().getAvailableWalls();
        this.mapView.setAvailableTiles(tmp);
        this.action = PlayerAction.BUILDWALL;
    }

    public void setAvailableHedges(Point p) {
        ArrayList<Point> tmp = this.gameModel.getMap().getAvailableHedges(p);
        this.mapView.setAvailableTiles(tmp);
        this.action = PlayerAction.CUTHEDGE;
    }

    /* *** LÉPÉS "ESEMÉNYEK" ************************************************ */
    /* playerAction-t MapView hívja meg, többi belső függvény */
    public void playerAction(Point p) {
        switch (this.action) {
            case MOVE:
                movePlayer(p);
                break;
            case BUILDWALL:
                buildWall(p);
                break;
            case CUTHEDGE:
                cutHedge(p);
                break;
        }
        this.mapView.resetOverlays();
        this.switchToNextPlayer();
    }

    public void movePlayer(Point p) {
        this.gameModel.getActivePlayer().setPosition(p);
        countdo.setusedaction();
        actiondone=true;
    }

    public void buildWall(Point p) {
        this.gameModel.getMap().setWall(p, TileType.WALL);
        this.gameModel.getActivePlayer().removeWall();
        this.gameMenu.updateWallInfo();
        countdo.setusedaction();
        actiondone=true;
    }

    public void cutHedge(Point p) {
        this.gameModel.getMap().setWall(p, TileType.EMTPYWALL);
        countdo.setusedaction();
        actiondone=true;
    }

    /* *** LÉPÉS UUÁNI "ESEMÉNY" ******************************************** */
    public void switchToNextPlayer()
    {
        if (this.gameModel.getActivePlayer().hasWon())
        {
            countdo=null;
            //TODO ezt ird at mert varni kell 1-2 secet hogy behozza a nyert panelt
            abba.stopmusic();
            
            //itt nyert az emberke, vissza menübe vagy toplista
            int score = this.gameModel.getActivePlayer().getScore();
            if (this.gameModel.getTopScoreList().isTopScore(score) && this.gameModel.getDifficulty() != GameDifficulty.CUSTOM)
            {
                //Itt a pontszám elég magas, hogy a topscore-ba kerüljön
                //Névbeadás, satöbbi
                String name = "";
                name = JOptionPane.showInputDialog(this.parent, (this.gameModel.getActivePlayerNum() + 1) + ". játékos nyert! Pontszám: " + this.gameModel.getActivePlayer().getScore() + "\nAdd meg a neved:", "Gratulálok nyertél", 1);
                if (name.length() > 0)
                {
                    this.gameModel.getTopScoreList().addTopScore(new TopScore(name, score, this.gameModel.getDifficulty()));
                    this.gameModel.getTopScoreList().Save();
                }
                this.parent.showStats();
            }
            else
            {
                //A pontszám kevés volt a toplistához vagy egyéb pályán játszott, ki a főmenübe
                JOptionPane.showMessageDialog(this.parent, (this.gameModel.getActivePlayerNum() + 1) + ". játékos nyert! Pontszám: " + score);
                this.parent.showMainMenu();
            }
            
            //System.out.print("ahhha");
        } else{
            if(action==null || !actiondone){
            System.out.print("nem lepett");
            this.gameModel.getActivePlayer().updateScore(PlayerAction.TIME, this.gameModel.getDice().getValue());
            this.mapView.resetOverlays();
        } else {
            this.gameModel.getActivePlayer().updateScore(action, this.gameModel.getDice().getValue());
            
        }
        int i = (this.gameModel.getActivePlayerNum() == 0) ? 1 : 0;
        this.gameModel.setActivePlayer(i);
        this.gameMenu.resetButtons();
        
        this.countdo=new Countdown(this);
        countdo.start();
        actiondone=false;
        }
        
    }
    
    public void UpdateCountDown(int time){
        gameMenu.SetTime(time);
    }
    
    public void NextMusic(){
        abba.NextTrack();
    }
    
    public void Volume(int m, int e){
        abba.SetVolume(m, e);
    }
}
