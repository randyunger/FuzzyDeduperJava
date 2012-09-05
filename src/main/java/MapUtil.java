import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapUtil    //From http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
{
    public static <K, V extends Comparable<? super V>> Map<K, V>
        sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -1 * (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
