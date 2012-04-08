package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;

public class SentenceSeparationProblem extends Problem {

	private static String typeName = "sentence separation";
	List<Integer> separationPositions;
	
	public SentenceSeparationProblem(int level, int number, String word) {
		super(level, number, word);
		separationPositions = new ArrayList<Integer>();
	}

	@Override
	protected List<String> createProblemFromCorrectWord() {
		char[] letters = correctWord.toCharArray();
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < letters.length; ++i) {
			if(letters[i] == ' ') separationPositions.add(i);
			else result.add(String.valueOf(letters[i]));
		}
		return result;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	public boolean isCorrectAnswer(List<Integer> separations) {
		boolean correct = (separations.size() == separationPositions.size());
		for(int i = 0; i < separations.size() && correct; ++i) {
			correct = separationPositions.contains(separations.get(i));
		}
		return correct;
	}

}
