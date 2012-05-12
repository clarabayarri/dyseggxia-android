package dyseggxia.domainModel;



public abstract class Problem {
	
	private int level;
	private int number;
	protected String displayedWord;
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
	
	public String getWord() {
		if(displayedWord == null) {
			displayedWord = createProblemFromCorrectWord();
		}
		return displayedWord;
	}
	
	protected abstract String createProblemFromCorrectWord();
	
	public abstract String getTypeName();

	public String getCorrectWord() {
		return correctWord;
	}
	
	public boolean isCorrectAnswer(String word) {
		return word.equals(correctWord);
	}
	
}
