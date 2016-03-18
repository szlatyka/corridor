package front;

import front.control.GameField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Peter
 */
public class GameMenu extends JPanel implements ActionListener {

    final private GameField parent;
    final private JPanel wallhelp0 = new JPanel();
    final private JPanel wallhelp1 = new JPanel();
    final private JLabel info = new JLabel("Info:");
    final private JLabel infohelp = new JLabel("Az 1. játékos dob.");
    final private JPanel actionpanel = new JPanel();
    final private JButton roll = new Button("Dobás", ".\\img\\button\\green_small", new Font("Serif", Font.PLAIN, 16), Color.WHITE);
    
    final private JButton build = new Button("Falépítés", ".\\img\\button\\red_small", new Font("Serif", Font.PLAIN, 16), Color.WHITE);
    final private JButton destroy = new Button("Sövényvágás", ".\\img\\button\\red_small", new Font("Serif", Font.PLAIN, 16), Color.WHITE);
    final private JButton move = new Button("Mozgás", ".\\img\\button\\red_small", new Font("Serif", Font.PLAIN, 16), Color.WHITE);
    
    final private JLabel diceIcon = new JLabel();
    private int rolledNumber = 0;
    final private JPanel gamemenupan = new JPanel();
    
    private JLabel time=new JLabel();
    

    public GameMenu(GameField gf) {
        parent = gf;
        add(gamemenupan);
        gamemenupan.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel gamer0 = new JLabel("1. Játékos");
        gamemenupan.add(gamer0, c);
        c.gridy++;
        wallhelp0.setLayout(new FlowLayout());
        gamemenupan.add(wallhelp0, c);
        c.gridy++;
        wallhelp1.setLayout(new FlowLayout());
        JLabel gamer1 = new JLabel("2. Játékos");
        gamemenupan.add(gamer1, c);
        c.gridy++;
        gamemenupan.add(wallhelp1, c);
        c.gridy++;
        updateWallInfo();
        actionpanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        d.fill = GridBagConstraints.VERTICAL;
        d.gridx = 0;
        d.gridy = 0;
        actionpanel.add(roll, d);
        d.gridy++;
        actionpanel.add(new JLabel(" "), d);
        d.gridy++;
        actionpanel.add(build, d);
        d.gridy++;
        actionpanel.add(new JLabel(" "), d);
        d.gridy++;
        actionpanel.add(destroy, d);
        d.gridy++;
        actionpanel.add(new JLabel(" "), d);
        d.gridy++;
        actionpanel.add(move, d);
        d.gridy++;
        actionpanel.add(new JLabel(" "), d);
        d.gridy++;
        diceIcon.setIcon(new ImageIcon("./img/dice.png"));
        actionpanel.add(diceIcon, d);
        d.gridy++;
        actionpanel.add(new JLabel(" "), d);
        d.gridy++;
        
        
        
        //tim=new Countdown(this.parent);
        actionpanel.add(time, d);
        //tim.start();

        build.setEnabled(false);
        destroy.setEnabled(false);
        move.setEnabled(false);
        roll.addActionListener(this);
        build.addActionListener(this);
        move.addActionListener(this);
        destroy.addActionListener(this);
        gamemenupan.add(info, c);
        c.gridy++;
        gamemenupan.add(infohelp, c);
        c.gridy++;
        gamemenupan.add(actionpanel, c);
        gamemenupan.setPreferredSize(new Dimension(148, 556));
        gamemenupan.setMaximumSize(new Dimension(148, 556));
        gamemenupan.setMinimumSize(new Dimension(148, 556));
        gamemenupan.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
    }

    public void updateWallInfo() {
        updateWallInfo(0);
        updateWallInfo(1);
    }

    private void updateWallInfo(int act) {
        ImageIcon imageIcon = new ImageIcon("./img/WallV.png");
        JPanel panel = (act == 0) ? wallhelp0 : wallhelp1;
        panel.removeAll();
        for (int i = 0; i < parent.getModel().getPlayer(act).getRemainingWalls(); i++) {
            JLabel seg = new JLabel();
            seg.setIcon(imageIcon);
            panel.add(seg);
            panel.add(new JLabel(" "));
        }
        if (parent.getModel().getPlayer(act).getRemainingWalls() == 0) {
            panel.add(new JLabel("<html><br><br></html>"));
        }
    }

    public void resetButtons() {
        infohelp.setText((parent.getModel().getActivePlayerNum() + 1) + ". játékos dob.");
        roll.setEnabled(true);
        destroy.setEnabled(false);
        move.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(roll)) {
            //Dobás
            infohelp.setText("Válassz cselekvést!");
            rolledNumber = parent.getModel().getDice().roll();
            diceIcon.setIcon(new ImageIcon("./img/dice" + rolledNumber + ".png"));
            roll.setEnabled(false);
            move.setEnabled(true);
            Point playerpos = parent.getModel().getActivePlayer().getPosition();
            if (parent.getModel().getMap().getAvailableHedges(playerpos).size() > 0) {
                destroy.setEnabled(true);
            }
            if (rolledNumber > 3 && parent.getModel().getActivePlayer().getRemainingWalls() > 0) {
                build.setEnabled(true);
            }
        } else if (e.getSource().equals(move)) {
            //Mozgás választás
            infohelp.setText("Mozgás...");
            build.setEnabled(false);
            destroy.setEnabled(false);
            move.setEnabled(false);
            parent.setAvailableTiles(parent.getModel().getActivePlayer().getPosition());
        } else if (e.getSource().equals(destroy)) {
            //Sövényvágás választás
            infohelp.setText("Sövényvágás...");
            build.setEnabled(false);
            destroy.setEnabled(false);
            move.setEnabled(false);
            parent.setAvailableHedges(parent.getModel().getActivePlayer().getPosition());
        } else if (e.getSource().equals(build)) {
            //Falépítés választás
            infohelp.setText("Falépítés...");
            build.setEnabled(false);
            destroy.setEnabled(false);
            move.setEnabled(false);
            parent.setAvailableWalls();
        }
    }
    
    public void SetTime(int t){
        if(t<10){
            time.setText("<html><font color='red'; size='6'>Ido: "+t+"</font></html>");
        } else {
            time.setText("<html><font color='black'; size='6'>Ido: "+t+"</font></html>");
        }
        
        
    }
    
}
