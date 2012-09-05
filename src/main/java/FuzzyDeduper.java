import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class FuzzyDeduper implements Deduper{

    private Set<String> candidates; //Assume input was already deduped. This allows us to use the input values as unique keys for a Set.
    private HashMap<String, Set<String>> phonemeMap;

    /**
     * Split input into segments
     * todo: Phonemer could be a separate class?
     * @param size
     * @param input
     * @return
     */
    public List<String> makePhonemes(String input, int size){
        List<String> phonemeList = new ArrayList<String>();
        int i=0;
        while(i+size  <= input.length()) {
            phonemeList.add(input.substring(i,i+size));
            i++;
        }
        return phonemeList;
    }

    public List<String> makeAllPhonemes(String input, int startingSize){   //todo: Should be a Set
        List<String> phonemeList = new ArrayList<String>();
        for(int i=startingSize; i<=input.length(); i++){
            phonemeList.addAll(makePhonemes(input, i));
        }
        return phonemeList;
    }

    /**
     * Compile list of candidates
     * @param candidateList
     */
    public FuzzyDeduper(Set<String> candidateList, int granularity){
        this.candidates = candidateList;
        for(String candidate : candidates) {
            List<String> phonemes = makePhonemes(candidate, granularity);
            upsertPhonemes(candidate, phonemes);
        }
    }

    private void upsertPhonemes(String candidate, List<String> phonemeList) {
        for(String phoneme: phonemeList){
            if(phonemeMap.containsKey(phoneme)){    //Phoneme present, add candidate to set
                phonemeMap.get(phoneme).add(candidate);
            } else {
                Set<String> c = new HashSet();
                c.add(candidate);
                phonemeMap.put(phoneme,c);
            }
        }
    }

    @Override
    public Pair<String, String> nextCandidate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Pair<Integer, Pair<String, String>>> candidatesByRank() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
