package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WordProblem extends Problem {

	protected int wordExtractStartIndex;
	protected int wordExtractEndIndex;
	protected int answerIndex;
	protected List<String> answers;
	
	public WordProblem(int level, int number, String word, int wordStartIndex, int wordEndIndex) {
		super(level, number, word);
		this.wordExtractStartIndex = wordStartIndex;
		this.wordExtractEndIndex = wordEndIndex;
	}
	
	protected String changeWordIndexLetterToCharacter(String word, String newCharacter) {
		String beggining = word.substring(0, wordExtractStartIndex);
		String end = word.substring(wordExtractEndIndex+1, word.length());
		String composedWord = beggining + newCharacter + end;
		return composedWord;
	}
	
	public void addAnswers(List<String> answers) {
		this.answers = new ArrayList<String>(answers);
		addCorrectAnswerToAnswers();
	}
	
	protected void addCorrectAnswerToAnswers() {
		int index = getRandomAnswerIndex();
		String character = correctWord.substring(wordExtractStartIndex, wordExtractEndIndex+1);
		answers.add(index, character);
		answerIndex = index;
	}

	public List<String> getAnswers() {
		return answers;
	}
	
	protected int getRandomAnswerIndex() {
		Random random = new Random();
		int randomIndex = random.nextInt(100);
		return randomIndex % (answers.size()+1);
	}
	
}
