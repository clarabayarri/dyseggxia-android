package dyseggxia.factories;

import android.content.Context;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.ProblemController;

public class ControllerFactory {
	
	private Context context;
	
	public ControllerFactory(Context context) {
		this.context = context;
	}

	public ProblemController getProblemController() {
		return new ProblemController(ProviderFactory.getInstance(context).getProblemProvider());
	}
	
	public LevelController getLevelController() {
		return new LevelController(ProviderFactory.getInstance(context).getLevelProvider());
	}
}
