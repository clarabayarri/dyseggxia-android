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
	
	public int getNumberOfSpacesInProblem() {
		String[] chunks = sentence.split(" ");
		return chunks.length - 1;
	}

}
