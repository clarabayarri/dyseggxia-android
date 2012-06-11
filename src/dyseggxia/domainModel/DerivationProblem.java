package dyseggxia.domainModel;

import java.util.List;

import dyseggxia.activities.R;

public class DerivationProblem extends WordProblem {

	private static String typeName = "derivation";
	
	public DerivationProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}
	
	public DerivationProblem(int level, int number, String word, int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex, endIndex);
		addAnswers(wrongAnswers);
	}
	
	@Override
	protected String createProblemFromCorrectWord() {
		return correctWord.substring(0, wordExtractStartIndex);
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	protected void addCorrectAnswerToAnswers() {
		int index = getRandomAnswerIndex();
		String correctAnswer = correctWord.substring(wordExtractStartIndex,correctWord.length());
		answers.add(index, correctAnswer);
		answerIndex = index;
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.derivation;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.derivationpenguin;
	}

}
