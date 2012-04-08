package dyseggxia.domainModel;

import java.util.List;


public abstract class Problem {
	
	private int level;
	private int number;
	protected List<String> word;
	protected String correctWord;
	
	public Problem(int level, int number, String word) {
		this.level = level;
		this.number = number;
		this.correctWord = word;
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
	
	
	
}
