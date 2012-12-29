package dyseggxia.domainControllers;

import java.util.Random;

import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.Problem;
import dyseggxia.providers.ProblemProviderI;

public class ProblemController {

	private ProblemProviderI provider;
	
	public ProblemController(ProblemProviderI provider) {
		this.provider = provider;
	}
	
	public Problem getRandomProblemForLevel(Level level) {
		int randomNumber = getRandomNumber(level.getNumProblems());
		try {
			Problem problem = getProblem(level.getNumber(), level.getLanguage(), randomNumber);
			return problem;
		}
		catch(Exception e) {
			System.out.println(randomNumber);
			e.printStackTrace();
		}
		return null;
	}
	
	private Problem getProblem(int levelNumber, String language, int problemId) throws Exception {
		return provider.findProblem(levelNumber, language, problemId);
	}
	
	private int getRandomNumber(int maximum) {
		Random random = new Random();
		int randomNumber = Math.abs(random.nextInt()) % maximum;
		return randomNumber;
	}
	
}
