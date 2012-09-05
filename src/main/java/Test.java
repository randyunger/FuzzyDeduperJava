import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hey world");

        HashSet sampleList = new HashSet<String>();
        sampleList.add("one");
        sampleList.add("onetwo");
        sampleList.add("network");
        sampleList.add("new york");

        FuzzyDeduper fd = new FuzzyDeduper(sampleList, 3);
        fd.candidatesByRank();
    }
}
