package back;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Szlatyka
 */
public class TopScoreList extends AbstractTableModel implements Serializable {

    public static final String SAVEFILE = "topscores.dat";
    private String[] colNames = new String[] {"Helyezés", "Név", "Pontszám", "Nehézség"};
    
    private ArrayList<TopScore> topscores = new ArrayList<>();

    public TopScoreList() {
    }

    public ArrayList<TopScore> getTopScores() {
        return this.topscores;
    }

    public boolean isTopScore(int score) {
        TopScore tmp = new TopScore("", score, GameDifficulty.NORMAL);
        if (this.topscores.size() < 10 || this.topscores.get(this.topscores.size() - 1).getScore() < tmp.getScore()) {
            return true;
        }
        return false;
    }

    public boolean addTopScore(String name, int score, GameDifficulty difficulty) {
        return this.addTopScore(new TopScore(name, score, difficulty));
    }

    public Boolean addTopScore(TopScore score) {
        if (this.isTopScore(score.getScore())) {
            this.topscores.add(score);
            Collections.sort(this.topscores);
            Collections.reverse(this.topscores);
            if (this.topscores.size() > 10) {
                this.topscores.remove(10);
            }
            return true;
        }
        return false;
    }

    public void Save() {
        FileProvider.saveObjectToFile(SAVEFILE, this);
    }

    @Override
    public int getRowCount()
    {
        return this.topscores.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4; //Ez nem változik
    }
    
    @Override public Class getColumnClass(int column) 
    {
        switch(column)
        {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Integer.class;
            case 3: return String.class;
            
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return rowIndex + 1;
            case 1: return this.topscores.get(rowIndex).getName();
            case 2: return this.topscores.get(rowIndex).getScore();
            
            case 3:
                switch(this.topscores.get(rowIndex).getDifficulty())
                {
                    case EASY: return "Könnyű";
                    case NORMAL: return "Közepes";
                    case HARD: return "Nehéz";
                    
                    default: return "";
                }
            
            default: return "";
        }
    }

    @Override
    public String getColumnName(int col)
    {
        return colNames[col];
    }
}
