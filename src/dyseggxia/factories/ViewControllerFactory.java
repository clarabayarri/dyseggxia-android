package dyseggxia.factories;

import dyseggxia.activities.CubesActivity;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.viewControllers.CubeController;
import dyseggxia.viewControllers.DerivationCubeController;
import dyseggxia.viewControllers.InsertionCubeController;
import dyseggxia.viewControllers.OmissionCubeController;
import dyseggxia.viewControllers.SubstitutionCubeController;

public class ViewControllerFactory {

	public static CubeController getCorrectCubeController(CubesActivity context, Problem problem) {
		if(problem.getClass().equals(InsertionProblem.class)) {
			return new InsertionCubeController(context, (InsertionProblem)problem);
		}
		if(problem.getClass().equals(OmissionProblem.class)) {
			return new OmissionCubeController(context, (OmissionProblem)problem);
		}
		if(problem.getClass().equals(SubstitutionProblem.class)) {
			return new SubstitutionCubeController(context, (SubstitutionProblem)problem);
		}
		if(problem.getClass().equals(DerivationProblem.class)) {
			return new DerivationCubeController(context, (DerivationProblem)problem);
		}
		return null;
	}
}
