
public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	// Returns true if the word is found within the dictionary array, and false if
	// it is not.
	public static boolean existInDictionary(String word, String[] dictionary) {

		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(word)) {
				return true;
			}
		}
		return false;
	}

	// This function accepts two inputs: a hashtag (as a string) and a dictionary
	// (an array of strings), and prints each word embedded within the hashtag on a
	// separate line.
	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
		int N = hashtag.length();
		String hashtagLowerCase = hashtag.toLowerCase();
		for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtagLowerCase.substring(0, i), dictionary)) {
				System.out.println(hashtagLowerCase.substring(0, i));
				breakHashTag(hashtagLowerCase.substring(i), dictionary);
				break;
			}
		}
	}
}
