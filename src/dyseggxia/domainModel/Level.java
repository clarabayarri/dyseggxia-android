package dyseggxia.domainModel;


public class Level {

	private int number;
	private String description;
	private int numProblems;
	
	public Level(int number, String description) {
		this.number = number;
		this.description = description;
	}
	
	public Level(int number, String description, int numProblems) {
		this(number, description);
		this.numProblems = numProblems;
	}

	public int getNumber() {
		return this.number;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setNumProblems(int numProblems) {
		this.numProblems = numProblems;
	}
	
	public int getNumProblems() {
		return this.numProblems;
	}

}
