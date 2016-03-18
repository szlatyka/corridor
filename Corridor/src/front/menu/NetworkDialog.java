/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class NetworkDialog extends JDialog implements ActionListener
{
	private JFrame parent;
    private JDialog dialog;
    
    private int result = 0;
    
    private Button create = new Button("Szerver indítása", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private Button join = new Button("Szerver keresése", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);

    public NetworkDialog(JFrame parent) {
        this.parent = parent;
        this.setModal(true);
        this.setResizable(false);
        this.setTitle("Hálózati játék");
    }

    private JPanel buildGUI() {
        JPanel panel = new JPanel();
        this.create.addActionListener(this);
        panel.add(this.create);
        this.join.addActionListener(this);
        panel.add(this.join);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean close = false;
        
        if (e.getSource().equals(this.create))
        {
            this.result = 1;
            close = true;
        }
        else if (e.getSource().equals(this.join))
        {
            this.result = 2;
            close = true;
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

    public int getAction()
	{
        return this.result;
    }

    public void display() {
        dialog = new JDialog(this.parent, "Hálózati játék", true);
        dialog.setSize(300, 180);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(buildGUI());
        dialog.setLocationRelativeTo(this.parent);
        dialog.setVisible(true);
    }
}
