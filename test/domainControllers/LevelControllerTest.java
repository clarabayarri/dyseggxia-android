package domainControllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import dyseggxia.domainControllers.LevelController;
import dyseggxia.providers.LevelProviderI;

@RunWith(RobolectricTestRunner.class)
public class LevelControllerTest {

	private LevelController controller;
	private static int levelNumber = 2;

	@Test
	public void getLevelCallsProvider() {
		LevelProviderI mock = mock(LevelProviderI.class);
		controller = new LevelController(mock);
		controller.getLevel(levelNumber);
		verify(mock).findLevel(levelNumber);
	}

}
