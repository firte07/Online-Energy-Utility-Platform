package utcn;

import reader.FileReader;
import reader.ValueReader;
import service.Service;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        ValueReader valueReader = new ValueReader();
        Service service = new Service();
        System.out.println(service.values(valueReader.readValues()));
    }
}
