package dyseggxia.providers;

import dyseggxia.domainModel.Problem;

public interface ProblemProviderI {

	public Problem findProblem(int levelNumber, int problemId, Class<?> type) throws Exception;
	public int findNumProblemsForLevel(int levelNumber, Class<?> type);
}
