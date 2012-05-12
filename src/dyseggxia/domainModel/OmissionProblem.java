package dyseggxia.domainModel;


public class OmissionProblem extends WordProblem {

	private static String typeName = "omission";
	private String insertedLetter;
	
	public OmissionProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}
	
	public OmissionProblem(int level, int number, String word, int wordIndex, int endIndex, String insertedLetter) {
		this(level, number, word, wordIndex, endIndex);
		this.insertedLetter = insertedLetter;
	}
	
	public void setInsertedLetter(String insertedLetter) {
		this.insertedLetter = insertedLetter;
	}

	@Override
	protected String createProblemFromCorrectWord() {
		String beggining = correctWord.substring(0, wordExtractStartIndex);
		String end = correctWord.substring(wordExtractEndIndex, correctWord.length());
		String composedWord = beggining + insertedLetter + end;
		return composedWord;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

}
