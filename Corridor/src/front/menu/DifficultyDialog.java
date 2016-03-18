package front.menu;

import back.GameDifficulty;
import front.Button;
import front.MapFileFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Szlatyka
 */
public class DifficultyDialog extends JDialog implements ActionListener {

    private JFrame parent;
    private JDialog dialog;
    
    private GameDifficulty difficulty = null;
    private String customMap = null;
    
    private Button easy = new Button("Könnyű", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private Button medium = new Button("Közepes", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private Button hard = new Button("Nehéz", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private Button custom = new Button("Saját pálya...", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);

    public DifficultyDialog(JFrame parent) {
        this.parent = parent;
        this.setModal(true);
        this.setResizable(false);
        this.setTitle("Mehézségi fokozat");
    }

    private JPanel buildGUI() {
        JPanel panel = new JPanel();
        this.easy.addActionListener(this);
        panel.add(this.easy);
        this.medium.addActionListener(this);
        panel.add(this.medium);
        this.hard.addActionListener(this);
        panel.add(this.hard);
        this.custom.addActionListener(this);
        panel.add(this.custom);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean close = false;
        
        if (e.getSource().equals(this.easy))
        {
            this.difficulty = GameDifficulty.EASY;
            close = true;
        }
        else if (e.getSource().equals(this.medium))
        {
            this.difficulty = GameDifficulty.NORMAL;
            close = true;
        }
        else if (e.getSource().equals(this.hard))
        {
            this.difficulty = GameDifficulty.HARD;
            close = true;
        }
        else if (e.getSource().equals(this.custom))
        {
            JFileChooser dialog = new JFileChooser();
            dialog.setFileFilter(new MapFileFilter());
            dialog.setDialogTitle("Térkép kiválasztása");
            dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
            dialog.setMultiSelectionEnabled(false);
            int result = dialog.showDialog(this, "Kiválaszt");
            
            if(result == JFileChooser.APPROVE_OPTION)
            {
                this.difficulty = GameDifficulty.CUSTOM;
                this.customMap = dialog.getSelectedFile().getAbsolutePath();
                close = true;
            }
        }
        else
        {
            return;
        }
        if(close)
        {
            dialog.dispose();
        }
    }

    public GameDifficulty getDifficulty() {
        return this.difficulty;
    }
    
    public String getMapFile(){
        return this.customMap;
    }

    public void display() {
        dialog = new JDialog(this.parent, "Nehézségi fokozat", true);
        dialog.setSize(300, 320);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(buildGUI());
        dialog.setLocationRelativeTo(this.parent);
        dialog.setVisible(true);
    }
}
