import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Deduper {
//    public void load(List<String> candidates);
    public Pair<String, String> nextCandidate();
    public List<Pair<Integer, Pair <String,String>>> candidatesByRank();
}