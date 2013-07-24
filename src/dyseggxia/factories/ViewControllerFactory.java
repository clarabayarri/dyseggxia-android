package dyseggxia.factories;

import dyseggxia.activities.CubesActivity;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.domainModel.SubstitutionProblem;
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
		if(problem.getTypeName().equals(InsertionProblem.typeName)) {
			return new InsertionCubeController(context, (InsertionProblem) problem);
		}
		if(problem.getTypeName().equals(OmissionProblem.typeName)) {
			return new OmissionCubeController(context, (OmissionProblem) problem);
		}
		if(problem.getTypeName().equals(SubstitutionProblem.typeName)) {
			return new SubstitutionCubeController(context, (SubstitutionProblem) problem);
		}
		if(problem.getTypeName().equals(DerivationProblem.typeName)) {
			return new DerivationCubeController(context, (DerivationProblem) problem);
		}
		if(problem.getTypeName().equals(SentenceSeparationProblem.typeName)) {
			return new SentenceSeparationCubeController(context, (SentenceSeparationProblem) problem);
		}
		return null;
	}
	
	public static HowToViewControllerI getCorrectHowToViewController(CubesActivity context, Problem problem) {
		if(problem.getTypeName().equals(InsertionProblem.typeName)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getTypeName().equals(OmissionProblem.typeName)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getTypeName().equals(SubstitutionProblem.typeName)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getTypeName().equals(DerivationProblem.typeName)) {
			return new InsertionHowToViewController(context);
		}
		if(problem.getTypeName().equals(SentenceSeparationProblem.typeName)) {
			return new InsertionHowToViewController(context);
		}
		return null;
	}
}
