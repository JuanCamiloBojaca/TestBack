import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Juan Camilo Bojaca
 *
 */
public class Punto1 {

	/**
	 * 
	 * @param strings the collection of strings
	 * @param pattern regex pattern
	 * @return the lexicographical sorted strings that matches with the pattern
	 */

	public static List<String> filterAndSort(Collection<String> strings, String pattern) {
		return filterAndSort(strings, String::compareTo, pattern);
	}

	/**
	 * 
	 * @param strings    the collection of strings
	 * @param comparator the comparator to sort
	 * @param pattern    regex pattern
	 * @return the sorted strings that matches with the pattern
	 */
	public static List<String> filterAndSort(Collection<String> strings, Comparator<String> comparator,
			String pattern) {
		return strings.stream().filter((s) -> s.matches(pattern)).sorted(comparator).collect(Collectors.toList());
	}

}
