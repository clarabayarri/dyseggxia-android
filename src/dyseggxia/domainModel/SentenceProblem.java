package dyseggxia.domainModel;

public abstract class SentenceProblem extends Problem {

	protected String sentence;
	
	public SentenceProblem(int level, int number, String sentence) {
		super(level, number);
		this.sentence = sentence;
	}

	public boolean isCorrectAnswer(String answer) {
		return answer.equals(sentence);
	}

}
