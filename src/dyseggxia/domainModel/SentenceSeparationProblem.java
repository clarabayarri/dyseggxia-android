package dyseggxia.domainModel;

import dyseggxia.activities.R;


public class SentenceSeparationProblem extends SentenceProblem {

	private static String typeName = "sentence separation";
	
	public SentenceSeparationProblem(int level, String language, int number, String sentence) {
		super(level, language, number, sentence);
	}

	@Override
	protected String generateProblem() {
		return sentence.replace(" ", "");
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	public int getNumberOfSpacesInProblem() {
		String[] chunks = sentence.split(" ");
		return chunks.length - 1;
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
