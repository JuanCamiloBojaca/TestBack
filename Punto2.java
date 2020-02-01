import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

/**
 * 
 * @author Juan Camilo Bojaca
 *
 */
public class Punto2 {

	/**
	 * 
	 * @param string input string
	 * @return the count of distinct case-insensitive alphabetic characters and
	 *         numeric digits occurring more than once in an input string
	 */
	public static Map<Character, Integer> count(String string) {
		string = string.toLowerCase();
		// separate the accents
		string = Normalizer.normalize(string, Normalizer.Form.NFD);

		int[] numbersCount = new int['9' - '0' + 1];
		int[] lettersCount = new int['z' - 'a' + 1];

		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			if (Character.isAlphabetic(character))
				lettersCount[character - 'a']++;
			if (Character.isDigit(character))
				numbersCount[character - '0']++;
		}

		Map<Character, Integer> ans = new HashMap<>();
		updateCounterMap(ans, numbersCount, '0');
		updateCounterMap(ans, lettersCount, 'a');
		return ans;
	}

	/**
	 * Insert into the map the key and value of the elements in the character counts
	 * with more than one element
	 * 
	 * @param map
	 * @param characterCounts
	 * @param base            original start position of the character
	 */
	private static void updateCounterMap(Map<Character, Integer> map, int[] characterCounts, char base) {
		for (char i = 0; i < characterCounts.length; i++) {
			int count = characterCounts[i];
			if (count > 1)
				map.put((char) (base + i), count);
		}
	}

	public static void main(String[] args) {
		System.out.println(count("abcde"));
		System.out.println(count("aábbcde"));
		System.out.println(count("aabBcde"));
		System.out.println(count("indivisibility"));
		System.out.println(count("Indivisibilities"));
		System.out.println(count("aA11"));
		System.out.println(count("aA11"));
		System.out.println(count("...non__only#alphanumeric_charcters@@"));
	}

}
