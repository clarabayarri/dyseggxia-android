package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WordProblem extends Problem {

	protected int wordExtractStartIndex;
	protected int wordExtractEndIndex;
	protected List<String> answers;
	protected String correctWord;
	
	public WordProblem(int level, String language, int number, String word, int wordStartIndex, int wordEndIndex) {
		super(level, language, number);
		this.correctWord = word;
		this.wordExtractStartIndex = wordStartIndex;
		this.wordExtractEndIndex = wordEndIndex;
	}
	
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	public abstract List<String> getDisplayAnswers();
	
	@Override
	public boolean isCorrectAnswer(String answer) {
		return answer.equals(correctWord);
	}
	
	public String getRandomAnswer() {
		Random random = new Random();
		int randomIndex = (random.nextInt(100)) % answers.size();
		return answers.get(randomIndex);
	}
	
	protected void changeWordRangeToContents(String contents) {
		List<String> text = new ArrayList<String>();
		String start = correctWord.substring(0, wordExtractStartIndex);
		for (int i = 0; i < start.length(); ++i) {
			text.add(start.substring(i,i+1));
		}
		text.add(contents);
		String end = correctWord.substring(wordExtractEndIndex+1, correctWord.length());
		for (int i = 0; i < end.length(); ++i) {
			text.add(end.substring(i,i+1));
		}
		this.displayedText = text;
	}
	
	protected String correctWordRangeAsAnswerString() {
		return correctWord.substring(wordExtractStartIndex, wordExtractEndIndex+1);
	}
	
}
