/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import front.control.GameField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Peter
 */
public class MenuOptions extends JPanel implements ActionListener {
    
    private final JButton next=new JButton();
    private final JButton effects=new JButton();
    private final JButton music=new JButton();
    private final JButton options=new JButton();
    private GameField parent;
    
    public MenuOptions(GameField gf){
        parent=gf;
        this.setLayout(new FlowLayout());
        next.setName("next");
        next.setIcon(new ImageIcon("./img/next_track.png"));
        next.setMaximumSize(new Dimension(24, 24));
        next.setPreferredSize(new Dimension(24, 24));
        next.setMinimumSize(new Dimension(24, 24));
        next.setBorder(new EmptyBorder(0,0,0,0));
        music.setName("music");
        music.setIcon(new ImageIcon("./img/music_on.png"));
        music.setMaximumSize(new Dimension(24, 24));
        music.setPreferredSize(new Dimension(24, 24));
        music.setMinimumSize(new Dimension(24, 24));
        music.setBorder(new EmptyBorder(0,0,0,0));
        options.setName("options");
        options.setIcon(new ImageIcon("./img/options.png"));
        options.setMaximumSize(new Dimension(24, 24));
        options.setPreferredSize(new Dimension(24, 24));
        options.setMinimumSize(new Dimension(24, 24));
        options.setBorder(new EmptyBorder(0,0,0,0));
        
        next.addActionListener(this);
        music.addActionListener(this);
        options.addActionListener(this);
        
        
        add(next);
        add(new JLabel());
        add(new JLabel());
        add(music);
        add(new JLabel());
        add(new JLabel());
        add(options);
        
        this.setPreferredSize(new Dimension(148, 44));
        this.setMaximumSize(new Dimension(148, 44));
        this.setMinimumSize(new Dimension(148, 44));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(50, 50, 50)));
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        String name = o.getName();
        switch (name){
            case "next": parent.NextMusic();
                //TODO next
                        break;
            case "music": if (music.getIcon().toString()=="./img/music_on.png"){
                            parent.Volume(0, 0);
                            music.setIcon(new ImageIcon("./img/music_off.png"));
                        } else {
                //TODO zene be
                            parent.Volume(1, 1);
                            music.setIcon(new ImageIcon("./img/music_on.png"));
                        }
                        break;
            case "options":
                //TODO sliderek
                        break;
            default:
                        break;
            
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
