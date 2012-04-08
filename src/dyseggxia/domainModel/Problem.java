package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Problem {
	
	private int level;
	private int number;
	protected List<String> word;
	protected String correctWord;
	protected int wordIndex;
	protected int answerIndex;
	protected List<String> answers;
	
	public Problem(int level, int number, String word, int wordIndex) {
		this.level = level;
		this.number = number;
		this.correctWord = word;
		this.wordIndex = wordIndex;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public List<String> getWord() {
		if(word == null) {
			word = createProblemFromCorrectWord();
		}
		return word;
	}
	
	protected abstract List<String> createProblemFromCorrectWord();
	
	public abstract String getTypeName();
	
	public abstract boolean isCorrectAnswer(int wordIndex, int answerIndex);
	
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
}
