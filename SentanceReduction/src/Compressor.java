import java.util.Arrays;

public class Compressor {
	
	public static String compressSentence(String sentence) {
		sentence = repeatedWordReplacer(sentence);
		sentence = andReplacer(sentence);
		return sentence;
	}
	private static String replaceAnd(String word) {
		return (word.equals("and")) ? "&" : word;
	}
	
	private static String andReplacer(String sentence) {
		String[] wordsInSentence = sentence.split(" ");
		String[] andReplaced = Arrays.asList(wordsInSentence).stream()
				.map(Compressor::replaceAnd)
				.toArray(String[]:: new);
		return stringArrayToString(andReplaced);
	}

	private static String repeatedWordReplacer(String sentence) {
		String[] wordsInSentence = sentence.split(" ");
		String[] compressedWords = sentence.split(" ");
		int count = 0;
		
		for(int i = 0; i < wordsInSentence.length; i++) {
			String currentWord = wordsInSentence[i];
			boolean isCurrentWordCompressed = false;
			for(int j = i + 1; j < wordsInSentence.length; j++) {
				String otherWord = wordsInSentence[j];
				 if (currentWord.equals(otherWord)){
					compressedWords[j] = count + "";
					isCurrentWordCompressed = true;
				}
			}
			if (isCurrentWordCompressed) {
				compressedWords[i] = currentWord + count++;
			}
		}
		return stringArrayToString(compressedWords);
	}
	private static String stringArrayToString(String[] compressedWords) {
		StringBuilder resultBuilder = new StringBuilder();
        for(int i=0;i<compressedWords.length;i++) {
        	resultBuilder.append(compressedWords[i] + " ");
        }
		return resultBuilder.toString();
	}
	
	
}
