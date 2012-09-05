import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Deduper {
//    public void load(List<String> candidates);
//    public Pair<String, String> nextCandidate();
    public Map<Pair<String,String>,Double> candidatesByRank();
}