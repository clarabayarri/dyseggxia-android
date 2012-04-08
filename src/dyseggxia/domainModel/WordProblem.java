package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WordProblem extends Problem {

	protected int wordIndex;
	protected int answerIndex;
	protected List<String> answers;
	
	public WordProblem(int level, int number, String word, int wordIndex) {
		super(level, number, word);
		this.wordIndex = wordIndex;
	}
	
	protected List<String> changeWordIndexLetterToCharacter(String word, Character newCharacter) {
		char[] viewableWord = word.toCharArray();
		viewableWord[wordIndex] = newCharacter;
		List<String> letters = new ArrayList<String>();
		for(char letter : viewableWord) {
			Character character = new Character(letter);
			letters.add(character.toString());
		}
		return letters;
	}
	
	public void addAnswers(List<String> answers) {
		this.answers = new ArrayList<String>(answers);
		addCorrectAnswerToAnswers();
	}
	
	protected void addCorrectAnswerToAnswers() {
		int index = getRandomAnswerIndex();
		Character character = correctWord.charAt(wordIndex);
		answers.add(index, character.toString());
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
	
	public abstract boolean isCorrectAnswer(int wordIndex, int answerIndex);
	
}
