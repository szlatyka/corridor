package back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Szlatyka
 */
public class FileProvider {

    public static Object loadObjectFromFile(String filename) {
        Object result = null;
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            result = inputStream.readObject();
        } catch (Exception ex) {
            System.err.println("Hiba történt! " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                System.err.println("Hiba történt! " + ex.getMessage());
            }
        }
        return result;
    }

    public static Boolean saveObjectToFile(String filename, Object o) {
        boolean result = false;
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(o);
            result = true;
        } catch (Exception ex) {
            System.err.println("Hiba történt! " + ex.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception ex) {
                System.err.println("Hiba történt! " + ex.getMessage());
            }
        }
        return result;
    }

    public static String loadStringFromFile(String filename) {
        String result = null;
        FileReader reader = null;
        try {
            File file = new File(filename);
            reader = new FileReader(file);
            char[] tmp = new char[(int) file.length()];
            reader.read(tmp);
            result = new String(tmp);
        } catch (Exception ex) {
            System.err.println("Hiba történt! " + ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                System.err.println("Hiba történt! " + ex.getMessage());
            }
        }
        return result;
    }
}
