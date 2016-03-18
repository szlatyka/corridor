/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Szlatyka
 */
public class MapFileFilter extends FileFilter
{

    @Override
    public boolean accept(File f)
    {
        return (f.isDirectory() || getExtension(f.getName()).equals("map"));
    }

    @Override
    public String getDescription()
    {
        return "Corridor térkép";
    }
    
    private String getExtension(String filename)
    {
        int i = filename.toLowerCase().lastIndexOf(".");
        return i > 0 ? filename.substring(i+1) : "";
    }
}
