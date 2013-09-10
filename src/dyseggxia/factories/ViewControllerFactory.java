package dyseggxia.factories;

import dyseggxia.activities.CubesActivity;
import dyseggxia.domainModel.Problem;
import dyseggxia.viewControllers.DerivationCubeController;
import dyseggxia.viewControllers.GenericCubesProblemViewController;
import dyseggxia.viewControllers.InsertionCubeController;
import dyseggxia.viewControllers.OmissionCubeController;
import dyseggxia.viewControllers.SentenceSeparationCubeController;
import dyseggxia.viewControllers.SubstitutionCubeController;
import dyseggxia.viewControllers.howto.HowToViewControllerI;
import dyseggxia.viewControllers.howto.InsertionHowToViewController;

public class ViewControllerFactory {

	public static GenericCubesProblemViewController getCorrectCubeController(CubesActivity context, Problem problem) {
		System.out.println(problem.getNumber());
		if(problem.getType().equals(Problem.INSERTION1)) {
			return new InsertionCubeController(context, problem);
		}
		if(problem.getType().equals(Problem.OMISSION)) {
			return new OmissionCubeController(context, problem);
		}
		if(problem.getType().equals(Problem.SUBSTITUTION)) {
			return new SubstitutionCubeController(context, problem);
		}
		if(problem.getType().equals(Problem.DERIVATION)) {
			return new DerivationCubeController(context, problem);
		}
		if(problem.getType().equals(Problem.SEPARATION)) {
			return new SentenceSeparationCubeController(context, problem);
		}
		return null;
	}
	
	public static HowToViewControllerI getCorrectHowToViewController(CubesActivity context, Problem problem) {
		if(problem.getType().equals(Problem.INSERTION1)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getType().equals(Problem.OMISSION)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getType().equals(Problem.SUBSTITUTION)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getType().equals(Problem.DERIVATION)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getType().equals(Problem.SEPARATION)) {
			return new InsertionHowToViewController(context);
		}
		return null;
	}
}
