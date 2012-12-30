package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;


public class OmissionProblem extends WordProblem {

	public static final String typeName = "omission";
	
	public OmissionProblem(int level, String language, int number, String word, int wordIndex, int endIndex) {
		super(level, language, number, word, wordIndex, endIndex);
	}
	
	public OmissionProblem(int level, String language, int number, String word, 
			int wordIndex, int endIndex, String insertedLetter) {
		this(level, language, number, word, wordIndex, endIndex);
		this.answers = new ArrayList<String>();
		this.answers.add(insertedLetter);
	}
	
	public void setInsertedLetter(String insertedLetter) {
		this.answers = new ArrayList<String>();
		this.answers.add(insertedLetter);
	}

	@Override
	protected void generateProblem() {
		List<String> text = new ArrayList<String>();
		String beggining = correctWord.substring(0, wordExtractStartIndex);
		for (int i = 0; i < beggining.length(); ++i) {
			text.add(beggining.substring(i,i+1));
		}
		text.add(answers.get(0));
		String end = correctWord.substring(wordExtractEndIndex, correctWord.length());
		for (int i = 0; i < end.length(); ++i) {
			text.add(end.substring(i,i+1));
		}
		this.displayedText = text;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public List<String> getDisplayAnswers() {
		return null;
	}

}
