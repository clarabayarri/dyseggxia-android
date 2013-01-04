package dyseggxia.domainControllers;

import dyseggxia.domainModel.Problem;
import dyseggxia.providers.CompletedProblemProvider;

public class CompletedProblemController {

	private CompletedProblemProvider provider;
	
	public CompletedProblemController(CompletedProblemProvider provider) {
		this.provider = provider;
	}
	
	public int getNumCompletedProblems() {
		return provider.getNumCompletedProblems();
	}
	
	public void saveCompletedProblem(Problem problem, String solutions, int intents) {
		provider.saveCompletedProblem(problem, solutions, intents);
	}
}
