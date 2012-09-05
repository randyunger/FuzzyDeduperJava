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
    private HashMap<String, Set<String>> phonemeMap = new HashMap<String, Set<String>>();

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
        List<String> phonemeList = new ArrayList<String>();                //todo: make an exception if startingsize>length
        for(int i=startingSize; i<=input.length() && i<=6; i++){           //todo: configurable
            phonemeList.addAll(makePhonemes(input, i));
        }
        return phonemeList;
    }

    /**
     * Compile list of candidates
     * @param candidateList
     */
    public FuzzyDeduper(Set<String> candidateList, int granularity){
        if(candidateList == null || candidateList.size()<1) throw new IllegalArgumentException();
        this.candidates = candidateList;
        for(String candidate : candidates) {
            candidate = stripIllegalChars(candidate);
            List<String> phonemes = makeAllPhonemes(candidate, granularity);
            upsertPhonemes(candidate, phonemes);    //Create map of phonemes
        }
    }

    private String stripIllegalChars(String candidate) {
//        return candidate.replaceAll(" ","");  //todo: would need a way to map this back to input data
        return candidate;
    }

    private void upsertPhonemes(String candidate, List<String> phonemeList) {
        for(String phoneme: phonemeList){
            if(phonemeMap.containsKey(phoneme)){    //Phoneme present, add candidate to set
                phonemeMap.get(phoneme).add(candidate);
            } else {
                Set<String> c = new HashSet<String>();
                c.add(candidate);
                phonemeMap.put(phoneme,c);
            }
        }
    }

    //todo: remove
    public HashMap<String, Set<String>> dumpPhonemes(){
        return phonemeMap;
    }

//    @Override
//    public Pair<String, String> nextCandidate() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }

    @Override
    public Map<Pair<String,String>,Double> candidatesByRank() {
        Map< Pair<String,String>, Double> scores = new HashMap< Pair<String,String>, Double>();
        for(String phoneme: phonemeMap.keySet()){
            Set<String> words = phonemeMap.get(phoneme);
            double score = score(phoneme.length(), words.size());
            if(score>10){   //todo: configurable
                Set<Pair<String,String>> wordPairs = makePairs(words);
                //Increment score
                for(Pair<String,String> p: wordPairs){
                    if(scores.containsKey(p)) {
                        scores.put(p, scores.get(p) + score);
                    }
                    else scores.put(p, score);
                }
            }
        }

        return MapUtil.sortByValue(scores);
    }

    private Set<Pair<String,String>> makePairs(Set<String> wordList){
        Set<Pair<String,String>> wordPairs = new HashSet<Pair<String,String>>();
        Set<String> words = new HashSet<String>();
        if(wordList.size()<2 || wordList.size()>100) return wordPairs;//>100, too common?
        words.addAll(wordList);
        while(!words.isEmpty()){                       //todo: this is horribly inefficient
            Iterator i = words.iterator();
            String word = (String)i.next();
            words.remove(word);
            for(String otherWord: words){
                wordPairs.add(new Pair<String,String>(word, otherWord));
            }
        }
        return wordPairs;
    }

    private double score(int phonemeLength, int numberOfPartners) {
        //inversely related to num of partners, directly related to length
        return (phonemeLength * phonemeLength * phonemeLength) / Math.sqrt((double)numberOfPartners);
    }
}
