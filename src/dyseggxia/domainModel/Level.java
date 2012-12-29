package dyseggxia.domainModel;


public class Level {

	private int number;
	private String language;
	private int numProblems;
	
	public Level(int number, String language, int numProblems) {
		this.number = number;
		this.language = language;
		this.numProblems = numProblems;
	}

	public int getNumber() {
		return this.number;
	}

	public String getLanguage() {
		return this.language;
	}
	
	public void setNumProblems(int numProblems) {
		this.numProblems = numProblems;
	}
	
	public int getNumProblems() {
		return this.numProblems;
	}

}
