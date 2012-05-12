package dyseggxia.domainModel;

import java.util.List;

public class InsertionProblem extends WordProblem {

	private static String typeName = "insertion";
	
	public InsertionProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}

	public InsertionProblem(int level, int number, String word, int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex, endIndex);
		addAnswers(wrongAnswers);
	}
	
	@Override
	protected String createProblemFromCorrectWord() {
		return changeWordIndexLetterToCharacter(correctWord, " ");
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
}
