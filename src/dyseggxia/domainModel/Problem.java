package dyseggxia.domainModel;




public abstract class Problem {
	
	private int level;
	private String language;
	private int number;
	protected String displayedText;
	
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
	
	public String getDisplayedText() {
		if(displayedText == null) {
			displayedText = generateProblem();
		}
		return displayedText;
	}
	
	protected abstract String generateProblem();
	
	public abstract String getTypeName();
	
	public abstract int getLocalizedTypeName();
	
	public abstract int getProblemTypeImageIdentifier();
	
}
