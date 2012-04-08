package dyseggxia.domainControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.providers.ProblemProviderI;

public class ProblemController {

	private ProblemProviderI provider;
	private static final String[] problemClassNames = {"insertion", "omission", "substitution","derivation"};
	private static final Class<?>[] problemTypes = {InsertionProblem.class, OmissionProblem.class, SubstitutionProblem.class, DerivationProblem.class};
	
	public ProblemController(ProblemProviderI provider) {
		this.provider = provider;
	}
	
	public List<Problem> getThreeRandomProblemsForLevel(Level level) {
		List<Problem> problems = new ArrayList<Problem>();
		for(int i = 0; i < 3; ++i) {
			Class<?> type = getRandomProblemType();
			int randomNumber = getRandomNumber(provider.findNumProblemsForLevel(level.getNumber(), type));
			try {
				Problem problem = getProblem(level.getNumber(), randomNumber+1, type);
				problems.add(problem);
			}
			catch(Exception e) {
				System.out.println(randomNumber);
				e.printStackTrace();
			}
		}
		return problems;
	}
	
	public Problem getProblem(int levelNumber, int problemId, Class<?> problemType) throws Exception {
		return provider.findProblem(levelNumber,problemId,problemType);
	}
	
	public Problem getProblem(int levelNumber, int problemId, String problemType) throws Exception {
		for(int i = 0; i < problemClassNames.length; ++i) {
			if(problemClassNames[i].equals(problemType)) {
				return getProblem(levelNumber, problemId, problemTypes[i]);
			}
		}
		return null;
	}

	private Class<?> getRandomProblemType() {
		return problemTypes[getRandomNumber(problemTypes.length)];
	}
	
	private int getRandomNumber(int maximum) {
		Random random = new Random();
		int randomNumber = Math.abs(random.nextInt()) % maximum;
		return randomNumber;
	}
	
}
