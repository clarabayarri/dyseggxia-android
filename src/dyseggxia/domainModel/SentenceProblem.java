package dyseggxia.domainModel;

public abstract class SentenceProblem extends Problem {

	protected String sentence;
	
	public SentenceProblem(int level, String language, int number, String sentence) {
		super(level, language, number);
		this.sentence = sentence;
	}

	@Override
	public boolean isCorrectAnswer(String answer) {
		return answer.equals(sentence);
	}

}
