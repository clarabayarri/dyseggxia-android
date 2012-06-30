package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;

import dyseggxia.activities.R;


public class OmissionProblem extends WordProblem {

	private static String typeName = "omission";
	
	public OmissionProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}
	
	public OmissionProblem(int level, int number, String word, int wordIndex, int endIndex, String insertedLetter) {
		this(level, number, word, wordIndex, endIndex);
		this.answers = new ArrayList<String>();
		this.answers.add(insertedLetter);
	}
	
	public void setInsertedLetter(String insertedLetter) {
		this.answers = new ArrayList<String>();
		this.answers.add(insertedLetter);
	}

	@Override
	protected String generateProblem() {
		String beggining = correctWord.substring(0, wordExtractStartIndex);
		String end = correctWord.substring(wordExtractEndIndex, correctWord.length());
		String composedWord = beggining + answers.get(0) + end;
		return composedWord;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.omission;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.omissionpenguin;
	}

	@Override
	public List<String> getDisplayAnswers() {
		return null;
	}

}
