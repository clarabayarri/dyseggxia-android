package dyseggxia.providers;

import java.util.List;

import dyseggxia.domainModel.Problem;

public interface LetterProviderI {

	public List<String> getLettersForProblem(Problem problem);
	
}
