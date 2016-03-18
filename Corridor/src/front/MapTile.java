package front;

import back.TileType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Szlatyka
 */
public class MapTile extends JLabel {

    private Color overlayColor = new Color(0, 255, 0, 50);
    private Point position;
    private boolean overlay = false;

    public MapTile() {
        super();
    }

    public MapTile(TileType type, Point pos) {
        super();
        this.position = pos;
        this.setType(type);
    }

    public void setType(TileType type) {
        //Ha x = 2n+1, akkor függőleges, különben vízszintes - csak falaknál van jelentősége
        String orientation = (this.position.x % 2 == 1) ? "V" : "H";
        ImageIcon icon;
        switch (type) {
            case MOUNTAIN:
                icon = new ImageIcon("./img/mountain.png");
                break;
            case NORMAL:
                icon = new ImageIcon("./img/tile.png");
                break;
            case HEDGE:
                icon = new ImageIcon("./img/Hedge" + orientation + ".png");
                break;
            case WALL:
                icon = new ImageIcon("./img/Wall" + orientation + ".png");
                break;
            case EMTPYWALL:
                icon = new ImageIcon("./img/Emtpywall" + orientation + ".png");
                break;
            case MIDDLE:
                icon = new ImageIcon("./img/Middle.png");
                break;
            //Nem fordulhat elő!
            default:
                icon = new ImageIcon("./img/tile.png");
                break;
        }
        this.setIcon(icon);
    }

    public Point getPosition() {
        return this.position;
    }

    public void setOverlay(boolean value) {
        this.overlay = value;
        this.repaint();
    }

    public void setOverlay(Color color) {
        this.overlay = true;
        this.overlayColor = color;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.overlay) {
            this.drawOverlay(this.overlayColor, g);
        }
    }

    private void drawOverlay(Color color, Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(this.overlayColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
