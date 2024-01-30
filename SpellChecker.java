
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	// The function accepts a word and returns it except for the first character. If
	// the length of the string is 1, then it returns an empty string.
	public static String tail(String str) {

		return str.substring(1);
	}

	// The function accepts two strings as input and returns the edit distance
	// between these two words, as an integer.
	public static int levenshtein(String word1, String word2) {

		// Converts all letters in a string to lowercase
		String lowerCaseWord1 = word1.toLowerCase();
		String lowerCaseWord2 = word2.toLowerCase();

		// The length of the strings
		int lengthWord1 = lowerCaseWord1.length();
		int lengthWord2 = lowerCaseWord2.length();

		// Levenshtein distance function
		if (lengthWord1 == 0) {
			return lengthWord2;
		} else if (lengthWord2 == 0) {
			return lengthWord1;
		} else if (lowerCaseWord1.substring(0, 1).equals(lowerCaseWord2.substring(0, 1))) {
			return levenshtein(tail(lowerCaseWord1), tail(lowerCaseWord2));
		} else {
			return 1 + minValue(levenshtein(tail(lowerCaseWord1), lowerCaseWord2),
					levenshtein(lowerCaseWord1, tail(lowerCaseWord2)),
					levenshtein(tail(lowerCaseWord1), tail(lowerCaseWord2)));
		}
	}

	// The function accepts 3 integer and returns the minimum number among them
	public static int minValue(int num1, int num2, int num3) {

		int maxMin = Math.min(num1, num2);
		if (maxMin > num3) {
			maxMin = num3;
		}
		return maxMin;
	}

	// The function building an array of 3,000 strings and then reads each line from
	// the file, and stores it in the array.
	public static String[] readDictionary(String fileName) {

		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	// The function calculates the edit distance between the given word and every
	// word in the dictionary. It detects the word with the minimum distance, and
	// checks if this distance exceeds the given threshold, if so returns the
	// original word otherwise returns the new word.
	public static String spellChecker(String word, int threshold, String[] dictionary) {

		String newWord = word.toLowerCase();
		int minDistance = threshold;

		for (int i = 0; i < dictionary.length; i++) {
			int num = levenshtein(dictionary[i], word);
			if (num <= threshold) {
				if (minDistance >= num) {
					newWord = dictionary[i];
					minDistance = num;
				}
			}
		}
		return newWord;
	}
}
