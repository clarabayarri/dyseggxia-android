package dyseggxia.factories;

import android.content.Context;
import dyseggxia.providers.AbstractProblemProvider;
import dyseggxia.providers.AnswerProvider;
import dyseggxia.providers.AnswerProviderI;
import dyseggxia.providers.CompletedProblemProvider;
import dyseggxia.providers.DatabaseHelper;
import dyseggxia.providers.DerivationProblemProvider;
import dyseggxia.providers.InsertionProblemProvider;
import dyseggxia.providers.LevelProvider;
import dyseggxia.providers.LevelProviderI;
import dyseggxia.providers.OmissionProblemProvider;
import dyseggxia.providers.ProblemProvider;
import dyseggxia.providers.ProblemProviderI;
import dyseggxia.providers.SentenceSeparationProblemProvider;
import dyseggxia.providers.SubstitutionProblemProvider;

public class ProviderFactory {

	private Context context;
	
	private static ProviderFactory singletonInstance;
	
	private DatabaseHelper helper;
	private AnswerProviderI answerProvider;
	private ProblemProviderI problemProvider;
	private CompletedProblemProvider completedProblemProvider;
	private LevelProviderI levelProvider;
	
	private InsertionProblemProvider insertionProblemProvider;
	private OmissionProblemProvider omissionProblemProvider;
	private SubstitutionProblemProvider substitutionProblemProvider;
	private DerivationProblemProvider derivationProblemProvider;
	private SentenceSeparationProblemProvider sentenceSeparationProblemProvider;
	
	private ProviderFactory(Context context) {
		this.context = context;
	}
	
	public static ProviderFactory getInstance(Context context) {
		if(singletonInstance == null) {
			singletonInstance = new ProviderFactory(context);
		}
		return singletonInstance;
	}
	
	private InsertionProblemProvider getInsertionProblemProvider() {
		if(insertionProblemProvider == null) {
			insertionProblemProvider = new InsertionProblemProvider(getAnswerProvider());
		}
		return insertionProblemProvider;
	}
	
	private OmissionProblemProvider getOmissionProblemProvider() {
		if(omissionProblemProvider == null) {
			omissionProblemProvider = new OmissionProblemProvider(getAnswerProvider());
		}
		return omissionProblemProvider;
	}
	
	private SubstitutionProblemProvider getSubstitutionProblemProvider() {
		if(substitutionProblemProvider == null) {
			substitutionProblemProvider = new SubstitutionProblemProvider(getAnswerProvider());
		}
		return substitutionProblemProvider;
	}
	
	private DerivationProblemProvider getDerivationProblemProvider() {
		if(derivationProblemProvider == null) {
			derivationProblemProvider = new DerivationProblemProvider(getAnswerProvider());
		}
		return derivationProblemProvider;
	}
	
	private SentenceSeparationProblemProvider getSentenceSeparationProblemProvider() {
		if(sentenceSeparationProblemProvider == null) {
			sentenceSeparationProblemProvider = new SentenceSeparationProblemProvider();
		}
		return sentenceSeparationProblemProvider;
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
	
	public AbstractProblemProvider getProblemProvider(String type) {
		if(type.equals("insertion")) {
			return getInsertionProblemProvider();
		}
		if(type.equals("omission")) {
			return getOmissionProblemProvider();
		}
		if(type.equals("substitution")) {
			return getSubstitutionProblemProvider();
		}
		if(type.equals("derivation")) {
			return getDerivationProblemProvider();
		}
		if(type.equals("separation")) {
			return getSentenceSeparationProblemProvider();
		}
		return null;
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
