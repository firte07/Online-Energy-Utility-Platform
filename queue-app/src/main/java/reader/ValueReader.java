package reader;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValueReader {
    private static String path = "X:\\Faculta\\AN4\\SD\\sensor.txt";

    public List<String> readValues() throws FileNotFoundException {
        List<String> values = new ArrayList<>();
        String line = "";

        try {
            Scanner scanner = new Scanner(new FileReader(path));

            while(scanner.hasNext()){
                line = scanner.nextLine();
                values.add(line);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return values;
    }
}
