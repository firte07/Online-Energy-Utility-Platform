package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.UUID;

public class FileReader {
    private static String path = "X:\\Faculta\\AN4\\SD\\sensorId.txt";

    public UUID readSensorId() throws FileNotFoundException {
        File file = new File(path);
        try{
            Scanner scanner = new Scanner(file);
            return UUID.fromString(scanner.nextLine());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return UUID.fromString("-1");
    }
}
