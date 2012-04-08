package domainControllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.providers.ProblemProviderI;

@RunWith(RobolectricTestRunner.class)
public class ProblemControllerTest {

	private ProblemController controller;
	private static int levelId = 1;
	private static int problemId = 5;
	
	@Test
	public void getProblemCallsProvider() throws Exception {
		ProblemProviderI mock = mock(ProblemProviderI.class);
		controller = new ProblemController(mock);
		controller.getProblem(levelId,problemId,InsertionProblem.class);
		verify(mock).findProblem(levelId,problemId, InsertionProblem.class);
	}

}
