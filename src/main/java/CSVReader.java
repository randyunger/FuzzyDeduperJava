import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */

//Not really CSV, \n separated! Kind of makes this class unnecessary, but since I've already started...

public class CSVReader {
    List <String> namesList = new ArrayList<String>();

    public CSVReader(String fileName) {
        try {
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current dir using System:" +currentDir);

            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                namesList.add(strLine);
            }
            in.close();
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List <String> getNames(){
        return namesList;
    }
}
