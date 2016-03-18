package front.menu;

import back.GameDifficulty;
import front.Button;
import front.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Peter
 */
public class MenuPanel extends JPanel implements ActionListener {

    private Window parent;
	
    private JButton newgame = new Button("Helyi játék", ".\\img\\button\\green", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
	private JButton netgame = new Button("Hálózati játék", ".\\img\\button\\green", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private JButton stats = new Button("Statisztika", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private JButton rules = new Button("Játékszabályok", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private JButton quit = new Button("Kilépés", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);

    public MenuPanel(Window window) {
        
        parent = window;
        setMinimumSize(new Dimension(900, 660));
        setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.VERTICAL;
        cont.gridx = 0;
        cont.gridy++;
		add(new JLabel("<html><br><br></html>"), cont);
		cont.gridy++;
		
        newgame.addActionListener(this);
        add(newgame, cont);
        cont.gridy++;
        add(new JLabel("<html><br></html>"), cont);
        cont.gridy++;
		
		netgame.addActionListener(this);
        add(netgame, cont);
        cont.gridy++;
        add(new JLabel("<html><br></html>"), cont);
        cont.gridy++;
		
        stats.addActionListener(this);
        add(stats, cont);
        cont.gridy++;
        add(new JLabel("<html><br></html>"), cont);
        cont.gridy++;
		
        rules.addActionListener(this);
        add(rules, cont);
        cont.gridy++;
		
        add(new JLabel("<html><br></html>"), cont);
        cont.gridy++;
        quit.addActionListener(this);
        add(quit, cont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newgame)) {
            //Új játék
            DifficultyDialog dialog = new DifficultyDialog(parent);
            dialog.display();
            if(dialog.getDifficulty()!=null)
            {
                if(dialog.getDifficulty() != GameDifficulty.CUSTOM)
                {
                    parent.startLocalGame(dialog.getDifficulty());
                }
                else
                {
                    parent.startGame(dialog.getMapFile());
                }
            }
        }
		else if(e.getSource().equals(netgame))
		{
			NetworkDialog dialog = new NetworkDialog(parent);
            dialog.display();
			if(dialog.getAction() != 0)
			{
				if(dialog.getAction() == 1)
				{
					JOptionPane.showMessageDialog(this, "Ez a funkció még nem érhető el!");
				}
				else if(dialog.getAction() == 2)
				{
					JOptionPane.showMessageDialog(this, "Ez a funkció még nem érhető el!");
				}
			}
		}
		else if (e.getSource().equals(rules)) {
            //Szabályok
            parent.showRules();
        } else if (e.getSource().equals(stats)) {
            //Statisztika
            parent.showStats();
        } else if (e.getSource().equals(quit)) {
            //Kilépés
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("./img/title.png")), 0, 0, this);
        } catch (IOException ex) {
        }
    }
}
