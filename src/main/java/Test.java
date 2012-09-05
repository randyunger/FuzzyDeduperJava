import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {

        HashSet sampleList = new HashSet<String>();
        sampleList.add("one");
        sampleList.add("onetwo");
        sampleList.add("network");
        sampleList.add("new york");

        FuzzyDeduper fd = new FuzzyDeduper((new CSVReader("advertisers.csv")).getNames(), 3);
        int i=0;
        Map<Pair<String,String>,Double> ranks =  fd.candidatesByRank();
        Iterator it = ranks.keySet().iterator();
        while(it.hasNext() && i<1000){
            i++;
            Pair p = (Pair)it.next();
            System.out.println("(" + i + ")  " + ranks.get(p) + "   " + p.getLeft() + " == " + p.getRight());
        }
    }
}
