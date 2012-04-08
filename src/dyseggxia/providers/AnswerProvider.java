package dyseggxia.providers;

import java.util.List;

import android.content.Context;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ProviderFactory;

public class AnswerProvider implements AnswerProviderI {
	
	private ProviderFactory providerFactory;
	
	public AnswerProvider(Context context) {
		providerFactory = ProviderFactory.getInstance(context);
	}
	
	@Override
	public List<String> getAnswersForProblem(Problem problem) {
		return providerFactory.getAnswerProvider(problem.getClass()).getAnswersForProblem(problem);
	}

}
