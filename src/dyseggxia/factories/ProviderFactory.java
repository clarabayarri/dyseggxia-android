package dyseggxia.factories;

import android.content.Context;
import dyseggxia.providers.AnswerProvider;
import dyseggxia.providers.AnswerProviderI;
import dyseggxia.providers.CompletedProblemProvider;
import dyseggxia.providers.DatabaseHelper;
import dyseggxia.providers.LetterProvider;
import dyseggxia.providers.LetterProviderI;
import dyseggxia.providers.LevelProvider;
import dyseggxia.providers.LevelProviderI;
import dyseggxia.providers.ProblemProvider;
import dyseggxia.providers.ProblemProviderI;

public class ProviderFactory {

	private Context context;
	
	private static ProviderFactory singletonInstance;
	
	private DatabaseHelper helper;
	private LetterProviderI letterProvider;
	private AnswerProviderI answerProvider;
	private ProblemProviderI problemProvider;
	private CompletedProblemProvider completedProblemProvider;
	private LevelProviderI levelProvider;
	
	private ProviderFactory(Context context) {
		this.context = context;
	}
	
	public static ProviderFactory getInstance(Context context) {
		if(singletonInstance == null) {
			singletonInstance = new ProviderFactory(context);
		}
		return singletonInstance;
	}
	
	public LevelProviderI getLevelProvider() {
		if(levelProvider == null) {
			levelProvider = new LevelProvider(getDatabaseHelper());
		}
		return levelProvider;
	}
	
	public ProblemProviderI getProblemProvider() {
		if(problemProvider == null) {
			problemProvider = new ProblemProvider(context, getDatabaseHelper());
		}
		return problemProvider;
	}
	
	public CompletedProblemProvider getCompletedProblemProvider() {
		if(completedProblemProvider == null) {
			completedProblemProvider = new CompletedProblemProvider(getDatabaseHelper());
		}
		return completedProblemProvider;
	}
	
	public LetterProviderI getLetterProvider() {
		if(letterProvider == null) {
			letterProvider = new LetterProvider(getDatabaseHelper());
		}
		return letterProvider;
	}
	
	public AnswerProviderI getAnswerProvider() {
		if(answerProvider == null) {
			answerProvider = new AnswerProvider(getDatabaseHelper());
		}
		return answerProvider;
	}
	
	public DatabaseHelper getDatabaseHelper() {
		if(this.helper == null) {
			this.helper = new DatabaseHelper(context);
		}
		return helper;
	}
}
