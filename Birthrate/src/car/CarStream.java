
package car;
import static com.sun.prism.shape.ShapeRep.InvalidationType.LOCATION;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CarStream {
   static List<car> nameList = new ArrayList();

    public static void main(String[] args) {
        Path p = Paths.get("car.txt");

        List<String> lines = null;
        try {
            lines = Files.readAllLines(p);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        if (lines == null || lines.isEmpty()) {
            System.err.println("ERROR: The requested file appears to be empty");
            System.exit(0);
        }    
            
        lines.remove(0); //remove file header with the col headings
        
        for (String line : lines) {
            nameList.add(splitRecord(line)); //convert each record in the file to a Name object
        }

        ExecutorService exe = Executors.newCachedThreadPool();
        String searchLOCATION = "Limerick";
        int MILEAGE1 = 100000;
        
        int MILEAGE2 = 20000;
        
        
        exe.submit(new Print(nameList, searchLOCATION));
        
        
        Future<Double> f1 = exe.submit(new Search(nameList, searchLOCATION, MILEAGE1, MILEAGE2));

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        try {
           System.out.format("Percentage Diff for '%s' when using %d and %d for comparision is %s\n", searchLOCATION, MILEAGE1, MILEAGE2, nf.format(f1.get()));
        } catch (Exception ex) {
            System.err.println(ex);
        }
       

        exe.shutdown();
              
    }

    private static car splitRecord(String line) {

      String  CARREG;
    String MAKE; //could use a char instead of a String
    String MODEL;
    String COLOUR;
    String LOCATION;
     int PRICE;
     int OWNERS;
    int MILEAGE;

        StringTokenizer st = new StringTokenizer(line);

        while (st.hasMoreElements()) {

            CARREG = st.nextToken();
            MAKE = st.nextToken();
            MODEL = st.nextToken();
            COLOUR = st.nextToken();
            LOCATION = st.nextToken();
            PRICE = Integer.parseInt(st.nextToken());
            OWNERS = Integer.parseInt(st.nextToken());
            MILEAGE = Integer.parseInt(st.nextToken());
           return new car(CARREG, MAKE, MODEL, COLOUR, LOCATION,PRICE,OWNERS,MILEAGE);

        }

        return null; //shouldn't get here
    }//end method splitRecord

}//end class


class Print implements Runnable {
    List<car> list;
    String LOCATION;
 
    public Print(List<car> list, String LOCATION) {
        this.list = list;
        this.LOCATION = LOCATION;
    }
     
    @Override
    public void run() {
        int counter = 0;
        for (car n : list) {
            if(n.getLOCATION().equalsIgnoreCase(LOCATION)) {
                System.out.println(n.getLOCATION() + "\t" + n.getOWNERS() + "\t" + n.getMILEAGE() + "\t"+n.getCARREG());
                counter++;
            }
            
        }
     System.out.println(LOCATION + " was found " + counter + " times");
    }//end run
}//end class Print
 
 
class Search implements Callable<Double> {
 
    List<car> list;
    String LOCATION;
    int y1;
    int y2;
 
    public Search(List<car> list, String LOCATION, int y1, int y2) {
        this.LOCATION = LOCATION;
        this.list = list;
        this.y1 = y1;
        this.y2 = y2;
    }
 
    @Override
    public Double call() throws Exception {
        int totalYr1 = 0, totalYr2 = 0;
         
        for (car aLOCATION : list) {
            if (aLOCATION.getOWNERS() == y1 & aLOCATION.getLOCATION().equalsIgnoreCase(LOCATION)) {
               totalYr1 = aLOCATION.getMILEAGE();
            }
            if (aLOCATION.getOWNERS() == y2 & aLOCATION.getLOCATION().equalsIgnoreCase(LOCATION)) {
                totalYr2 = aLOCATION.getMILEAGE();
            }
        }
 
        int diff = totalYr1 + totalYr2;
        return  (double) totalYr2 + totalYr1;
         
         
             
    }
 
  }
