package dyseggxia.domainModel;

import java.util.List;

public class Problem {
	
	public static final String INSERTION = "insertion";
	public static final String INSERTION1 = "insertion1";
	public static final String OMISSION = "omission";
	public static final String SUBSTITUTION = "substitution";
	public static final String DERIVATION = "derivation";
	public static final String TRANSPOSITION = "transposition";
	public static final String SEPARATION = "separation";
	
	private int id;
	private int level;
	private String language;
	private int number;
	private String type;
	private String word;
	private List<String> displayedText;
	private List<String> answers;
	
	public Problem(int id, int level, String language, int number, String type, String word) {
		this.id = id;
		this.level = level;
		this.language = language;
		this.number = number;
		this.type = type;
		this.word = word;
	}
	
	public int getId() {
		return id;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getType() {
		return type;
	}

	public String getLanguage() {
		return language;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public List<String> getDisplayedText() {
		return displayedText;
	}
	
	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public void setDisplayedText(List<String> displayedText) {
		this.displayedText = displayedText;
	}

	public boolean isCorrectAnswer(String answer) {
		return word.equals(answer);
	}
	
	public boolean isCorrectPartialAnswer(String answer) {
		// TODO: implement
		return true;
	}
	
	public int getNumberOfSpacesInProblem() {
		String[] chunks = word.split(" ");
		return chunks.length - 1;
	}
	
}
