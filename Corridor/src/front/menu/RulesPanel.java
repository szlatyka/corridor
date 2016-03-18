package front.menu;

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

/**
 *
 * @author Peter
 */
public class RulesPanel extends JPanel implements ActionListener {

    private Window parent;
    private JLabel rules;
    private Button back = new Button("Vissza", ".\\img\\button\\brown", new Font("Serif", Font.PLAIN, 30), Color.WHITE);

    public RulesPanel(Window window) {
        this.parent = window;
        setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.VERTICAL;
        cont.gridx = 0;
        cont.gridy = 0;
        String content = "<html><h1>Játékszabályok</h1>"
                + "<p>A játék célja, hogy a másik játékos kezdőpontjához elérjünk, illetve ebben a másik "
                + "játékost akadályozzuk.</p>"
                + "<p>A játék körökre osztott. A kör elején dokókockával dobunk. A dobás "
                + "eredménye határozza meg a körben végezhető cselekvéseket:</p>"
                + "<ul><li>Léphetünk a kockán szereplő számú mezőt</li>"
                + "<li>Egy szomszédos sövényt vághatunk át, szabaddá téve az utat</li>"
                + "<li>Ha 3-nál nagyobbat dobtunk, falat építhetünk egy üres falterületre</li></ul>"
                + "Minden játékos 1000 pontról indul. Minden cselekvéssel a játkos pontokat veszít."
                + " Érdemes tehát a játékot minél kevesebb cselekvéssel megynerni. A Levont pontok a következők:"
                + "<ul><li>Minden lépett mezőért 20 pont</li>"
                + "<li>Falépítés 100 pont</li>"
                + "<li>Sövényvágás 50 pont</li></ul>"
                + "<p>A játék nyilvántartja a legeredményesebb játékosokat. A toplistára az első 10 legjobb pontszám kerül fel.</p>"
                + "</html>";

        this.rules = new JLabel(content);
        this.rules.setMinimumSize(new Dimension(800, 400));
        add(rules, cont);
        cont.gridx = 0;
        cont.gridy = 1;
        back.addActionListener(this);
        add(back, cont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.back)) {
            parent.showMainMenu();
        }
    }
}
