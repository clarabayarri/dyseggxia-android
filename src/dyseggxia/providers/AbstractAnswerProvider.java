package dyseggxia.providers;

import java.util.List;

import dyseggxia.domainModel.Problem;

public abstract class AbstractAnswerProvider {

	protected DatabaseHelper helper;

	public abstract List<String> getAnswersForProblem(Problem problem);
	
}
