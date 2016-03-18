package front.menu;

import back.FileProvider;
import back.TopScoreList;
import front.Button;
import front.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Peter
 */
public class StatisticsPanel extends JPanel implements ActionListener {

    private Window parent;
    private TopScoreList list = new TopScoreList();
    private JLabel toplist = new JLabel();
    private Button back = new Button("Vissza", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);
    private JTable topscoreTable;
    
    public StatisticsPanel(Window window)
    {
        parent = window;
        
        setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.VERTICAL;
        
        topscoreTable = new JTable();
        loadStats();
        
        JScrollPane tableSP = new JScrollPane(topscoreTable);
        tableSP.setPreferredSize(new Dimension(400, 300));
        
        cont.gridx = 0;
        cont.gridy = 0;
        
        JLabel title = new JLabel("Toplista\n\n");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        add(title, cont);
        cont.gridy++;
        add(new JLabel(" "), cont);
        cont.gridy++;
        
        add(topscoreTable.getTableHeader(), cont);
        cont.gridy++;
        add(tableSP, cont);
        cont.gridy++;
        
        add(new JLabel(" "), cont);
        cont.gridy++;
        
        add(back, cont);
        back.addActionListener(this);
        
        //MakeStatistics();
    }

    public void loadStats()
    {
        list = (TopScoreList) FileProvider.loadObjectFromFile(TopScoreList.SAVEFILE);
        if (list == null) {
            list = new TopScoreList();
        }
        topscoreTable.setModel(list);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            parent.showMainMenu();
        }
    }
}
