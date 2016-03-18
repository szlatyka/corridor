package front;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 *
 * @author Szlatyka
 */
public class Button extends JButton implements MouseListener {

    private final int NORMAL = 0;
    private final int HOVER = 1;
    private final int ACTIVE = 2;
    private final int DISABLED = 3;
    private String pack;
    private int state = NORMAL;
    private Image[] images;
    private Graphics2D g2 = null;
    private Color originalColor;

    public Button(String text, String imgPack, Font font, Color color) {

        this.originalColor = color;
        this.setFont(font);
        this.setForeground(color);
        this.setText(text);
        this.pack = imgPack;
        this.images = new Image[4];
        try {
            this.images[NORMAL] = ImageIO.read(new File(pack + File.separator + "normal.png"));
            this.images[HOVER] = ImageIO.read(new File(pack + File.separator + "hover.png"));
            this.images[ACTIVE] = ImageIO.read(new File(pack + File.separator + "active.png"));
            this.images[DISABLED] = ImageIO.read(new File(pack + File.separator + "disabled.png"));
        } catch (IOException ex) {
        }
        this.setPreferredSize(new Dimension(this.images[NORMAL].getWidth(this), this.images[NORMAL].getHeight(this)));
        this.setMinimumSize(new Dimension(this.images[NORMAL].getWidth(this), this.images[NORMAL].getHeight(this)));
        this.setMaximumSize(new Dimension(this.images[NORMAL].getWidth(this), this.images[NORMAL].getHeight(this)));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.addMouseListener(this);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.setForeground(this.originalColor);
        } else {
            this.setForeground(new Color(50, 50, 50));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        setContentAreaFilled(false);
        if (ui != null) {
            Graphics scratchGraphics = (g == null) ? null : g.create();
            this.g2 = (Graphics2D) scratchGraphics;
            if (this.isEnabled()) {
                g2.drawImage(this.images[this.state], TOP, TOP, this);
            } else {
                g2.drawImage(this.images[DISABLED], TOP, TOP, this);
            }
            try {
                ui.update(g2, this);
            } finally {
                scratchGraphics.dispose();
                g2.dispose();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.state = ACTIVE;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.state = HOVER;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.state = HOVER;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.state = NORMAL;
    }
}
