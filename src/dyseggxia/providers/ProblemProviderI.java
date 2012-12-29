package dyseggxia.providers;

import dyseggxia.domainModel.Problem;

public interface ProblemProviderI {

	public Problem findProblem(int levelNumber, String language, int problemId) throws Exception;

}
