package front;

import front.control.GameField;
import back.Map;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Peter
 */
public class MapView extends JPanel implements MouseListener {

    private GameField parent;
    private Map map;
    private MapTile[][] tiles = new MapTile[19][19];
    private JLabel thumb = new JLabel();
    private JLabel content = new JLabel();
    private GridBagConstraints c = new GridBagConstraints();

    public MapView(GameField gf) {
        parent = gf;
        map = parent.getModel().getMap();
        for (int j = 0; j < tiles.length; j++) {
            for (int i = 0; i < tiles[0].length; i++) {
                tiles[j][i] = new MapTile();
            }
        }
        content.setIcon(new ImageIcon("./img/background.png"));
        thumb.setLayout(new GridBagLayout());
        content.add(thumb);
        content.setLayout(new GridLayout(1, 1));
        add(content);
        c.fill = GridBagConstraints.VERTICAL;
        content.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
        UpdateMap();
    }

    public void setAvailableTiles(ArrayList<Point> points) {
        //Sárga overlay-t kap minden léphető mező + egy actionlistenert
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            tiles[p.y][p.x].setOverlay(new Color(255, 255, 0, 100));
            tiles[p.y][p.x].addMouseListener(this);
        }
        Point player = parent.getModel().getActivePlayer().getPosition();
        tiles[player.y][player.x].setOverlay(new Color(255, 0, 0, 100));
    }

    public void resetOverlays() {
        //Összes tile-overlay kikapcsolása + eventlistener eltávolítása
        //Elvileg erre a kettős ciklusra sincs szükség, eleve újraarajzojuk a térképet
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                tiles[y][x].setOverlay(false);
                tiles[y][x].removeMouseListener(this);
            }
        }
        UpdateMap();
    }

    private void UpdateMap() {
        //thumb.setVisible(false);
        thumb.removeAll();
        c.gridx = 0;
        c.gridy = 0;
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                //A Point a falaknál kell a vízszintes-függőleges eldöntéséhez
                tiles[y][x] = new MapTile(map.getTile(x, y), new Point(x, y));
                thumb.add(tiles[y][x], c);
                c.gridx++;
            }
            c.gridx = 0;
            c.gridy++;
        }
        Point p = parent.getModel().getPlayer(0).getPosition();
        tiles[p.y][p.x].setIcon(new ImageIcon("./img/player1.png"));
        p = parent.getModel().getPlayer(1).getPosition();
        tiles[p.y][p.x].setIcon(new ImageIcon("./img/player2.png"));
        thumb.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MapTile clickedTile = (MapTile) e.getSource();
        clickedTile.setOverlay(false);
        parent.playerAction(clickedTile.getPosition());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
