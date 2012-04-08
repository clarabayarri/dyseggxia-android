package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;

public class OmissionProblem extends Problem {

	private static String typeName = "omission";
	private Character insertedLetter;
	
	public OmissionProblem(int level, int number, String word, int wordIndex) {
		super(level, number, word, wordIndex);
	}
	
	public OmissionProblem(int level, int number, String word, int wordIndex, Character insertedLetter) {
		this(level, number, word, wordIndex);
		this.insertedLetter = insertedLetter;
	}
	
	public void setInsertedLetter(Character insertedLetter) {
		this.insertedLetter = insertedLetter;
	}

	@Override
	protected List<String> createProblemFromCorrectWord() {
		return insertExtraLetter(correctWord);
	}
	
	private List<String> insertExtraLetter(String word) {
		char[] viewableWord = word.toCharArray();
		List<String> letters = new ArrayList<String>();
		for(char letter : viewableWord) {
			Character character = new Character(letter);
			letters.add(character.toString());
		}
		letters.add(wordIndex, insertedLetter.toString());
		return letters;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public boolean isCorrectAnswer(int wordIndex, int answerIndex) {
		return this.wordIndex == wordIndex;
	}

}
