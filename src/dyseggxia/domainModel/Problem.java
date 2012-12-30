package dyseggxia.domainModel;

import java.util.List;

public abstract class Problem {
	
	private int level;
	private String language;
	private int number;
	protected List<String> displayedText;
	
	public Problem(int level, String language, int number) {
		this.level = level;
		this.language = language;
		this.number = number;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public List<String> getDisplayedText() {
		if(displayedText == null) {
			generateProblem();
		}
		return displayedText;
	}
	
	protected abstract void generateProblem();
	
	public abstract String getTypeName();
	
	public abstract boolean isCorrectAnswer(String answer);
	
}
