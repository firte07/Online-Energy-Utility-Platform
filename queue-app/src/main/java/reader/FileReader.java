package reader;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class FileReader {
    private final static String path = "X:\\Faculta\\AN4\\SD\\sensorId.txt";

    public UUID readSensorId() throws IOException {
        File fileRead = new File(path);
        Scanner scanner = new Scanner(fileRead);
        UUID idNow =  UUID.fromString(scanner.nextLine());
        FileWriter fileWriter = new FileWriter(path);
        try{
            BufferedWriter out = new BufferedWriter(fileWriter);
            while(scanner.hasNextLine()) {
                String next = scanner.nextLine();
                if(next.equals("\n"))
                    out.newLine();
                else
                    out.write(next);
                out.newLine();
            }
            out.close();
            return idNow;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return UUID.fromString("-1");
    }
}
