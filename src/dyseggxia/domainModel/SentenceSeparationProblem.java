package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;


public class SentenceSeparationProblem extends SentenceProblem {

	public static final String typeName = "separation";
	
	public SentenceSeparationProblem(int level, String language, int number, String sentence) {
		super(level, language, number, sentence);
	}

	@Override
	protected void generateProblem() {
		String noBlanks = sentence.replace(" ", "");
		List<String> text = new ArrayList<String>();
		for (int i = 0; i < noBlanks.length(); ++i) {
			text.add(noBlanks.substring(i,i+1));
		}
		this.displayedText = text;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	public boolean isCorrectPartialAnswer(String partialAnswer) {
		String[] partialParts = partialAnswer.split(" ");
		String[] sentenceParts = sentence.split(" ");
		int index = 0;
		for (int i = 0; i < partialParts.length; ++i) {
			index += partialParts[i].length();
			int sum = 0;
			for (int j = 0; j < sentenceParts.length && sum < index; ++j) {
				sum += sentenceParts[j].length();
			}
			if (sum > index) return false;
		}
		return true;
	}
	
	public int getNumberOfSpacesInProblem() {
		String[] chunks = sentence.split(" ");
		return chunks.length - 1;
	}

}
