package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;

public class DerivationProblem extends WordProblem {

	private static String typeName = "derivation";
	
	public DerivationProblem(int level, int number, String word, int wordIndex) {
		super(level,number,word,wordIndex);
	}
	
	public DerivationProblem(int level, int number, String word, int wordIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex);
		addAnswers(wrongAnswers);
	}
	
	@Override
	protected List<String> createProblemFromCorrectWord() {
		List<String> newWord = new ArrayList<String>();
		for(int i = 0; i < wordIndex; ++i) {
			Character character = correctWord.charAt(i);
			newWord.add(character.toString());
		}
		return newWord;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public boolean isCorrectAnswer(int wordIndex, int answerIndex) {
		return this.answerIndex == answerIndex;
	}
	
	@Override
	protected void addCorrectAnswerToAnswers() {
		int index = getRandomAnswerIndex();
		String correctAnswer = correctWord.substring(wordIndex,correctWord.length());
		answers.add(index, correctAnswer);
		answerIndex = index;
	}

}
