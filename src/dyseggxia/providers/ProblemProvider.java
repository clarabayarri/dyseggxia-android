package dyseggxia.providers;

import android.content.Context;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ProviderFactory;

public class ProblemProvider implements ProblemProviderI {
	
	private ProviderFactory providerFactory;
	
	public ProblemProvider(Context context) {
		providerFactory = ProviderFactory.getInstance(context);
	}
	
	public int findNumProblemsForLevel(int levelNumber, Class<?> type) {
		return providerFactory.getProblemProvider(type).findNumProblemsForLevel(levelNumber);
	}
	
	public Problem findProblem(int levelNumber, int problemId, Class<?> type) throws Exception {
		return providerFactory.getProblemProvider(type).findProblem(levelNumber, problemId);
	}

}
