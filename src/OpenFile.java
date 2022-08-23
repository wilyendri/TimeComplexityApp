import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenFile {
    File file;
    StringBuilder output;

    public OpenFile(File file) {
        this.file = file;
        output = new StringBuilder();
    }

    public String readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            output.append(scanner.nextLine()).append("\n");
        }

        return output.toString();
    }
}
