package dyseggxia.domainModel;

import dyseggxia.activities.R;


public class SentenceSeparationProblem extends Problem {

	private static String typeName = "sentence separation";
	int separations = -1;
	
	public SentenceSeparationProblem(int level, int number, String word) {
		super(level, number, word);
	}

	@Override
	protected String createProblemFromCorrectWord() {
		int i = 0;
		while(i > -1) {
			i = correctWord.indexOf(" ", i+1);
			++separations;
		}
		return correctWord.replace(" ", "");
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	public int getNumAnswers() {
		return separations;
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.sentenceseparation;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.sentenceseparationpenguin;
	}

}
