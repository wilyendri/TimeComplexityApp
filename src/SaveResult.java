import java.io.*;

public class SaveResult implements Serializable {
    private File file;
    private String text;
    FileOutputStream fileOutputStream;

    public SaveResult(File file, String text) {
        this.file = file;
        this.text = text;
    }

    public void writeToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(text);
        printWriter.close();

    }
}
