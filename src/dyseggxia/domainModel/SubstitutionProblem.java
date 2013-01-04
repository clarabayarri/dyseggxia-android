package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubstitutionProblem extends WordProblem {

	public static final String typeName = "substitution";
	
	public SubstitutionProblem(int id, int level, String language, int number, String word, int wordIndex, int endIndex) {
		super(id, level, language, number, word, wordIndex, endIndex);
	}
	
	public SubstitutionProblem(int id, int level, String language, int number, String word, 
			int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(id, level, language, number, word, wordIndex, endIndex);
		this.answers = new ArrayList<String>(wrongAnswers);
	}

	@Override
	protected void generateProblem() {
		changeWordRangeToContents(getRandomAnswer());
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public List<String> getDisplayAnswers() {
		List<String> displayAnswers = new ArrayList<String>();
		Random random = new Random();
		int randomIndex = 0;
		for(String s : answers) {
			displayAnswers.add(randomIndex,s);
			randomIndex = (random.nextInt(100) % displayAnswers.size()+1);
		}
		displayAnswers.add(randomIndex, correctWordRangeAsAnswerString());
		return displayAnswers;
	}
	
}
