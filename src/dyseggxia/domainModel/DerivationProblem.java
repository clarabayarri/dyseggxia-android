package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dyseggxia.activities.R;

public class DerivationProblem extends WordProblem {

	private static String typeName = "derivation";
	
	public DerivationProblem(int level, String language, int number, String word, int wordIndex, int endIndex) {
		super(level, language, number, word, wordIndex, endIndex);
	}
	
	public DerivationProblem(int level, String language, int number, String word, int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(level, language, number, word, wordIndex, endIndex);
		this.answers = new ArrayList<String>(wrongAnswers);
	}
	
	@Override
	protected String generateProblem() {
		return correctWord.substring(0, wordExtractStartIndex);
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.derivation;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.derivationpenguin;
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
		displayAnswers.add(randomIndex, correctWord.substring(wordExtractStartIndex+1,correctWord.length()));
		return displayAnswers;
	}

}
