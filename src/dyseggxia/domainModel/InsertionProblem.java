package dyseggxia.domainModel;

import java.util.List;

public class InsertionProblem extends WordProblem {

	private static String typeName = "insertion";
	
	public InsertionProblem(int level, int number, String word, int wordIndex) {
		super(level, number, word, wordIndex);
	}

	public InsertionProblem(int level, int number, String word, int wordIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex);
		addAnswers(wrongAnswers);
	}
	
	@Override
	protected List<String> createProblemFromCorrectWord() {
		return changeWordIndexLetterToCharacter(correctWord, ' ');
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public boolean isCorrectAnswer(int wordIndex, int answerIndex) {
		return (this.answerIndex == answerIndex && this.wordIndex == wordIndex);
	}
}
