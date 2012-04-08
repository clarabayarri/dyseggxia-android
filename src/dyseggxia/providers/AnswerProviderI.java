package dyseggxia.providers;

import java.util.List;

import dyseggxia.domainModel.Problem;

public interface AnswerProviderI {

	public List<String> getAnswersForProblem(Problem problem);
	
}
