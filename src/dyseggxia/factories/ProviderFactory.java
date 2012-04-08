package dyseggxia.factories;

import android.content.Context;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.providers.AbstractAnswerProvider;
import dyseggxia.providers.AbstractProblemProvider;
import dyseggxia.providers.AnswerProvider;
import dyseggxia.providers.AnswerProviderI;
import dyseggxia.providers.DatabaseHelper;
import dyseggxia.providers.DerivationAnswerProvider;
import dyseggxia.providers.DerivationProblemProvider;
import dyseggxia.providers.InsertionAnswerProvider;
import dyseggxia.providers.InsertionProblemProvider;
import dyseggxia.providers.LevelProvider;
import dyseggxia.providers.LevelProviderI;
import dyseggxia.providers.OmissionAnswerProvider;
import dyseggxia.providers.OmissionProblemProvider;
import dyseggxia.providers.ProblemProvider;
import dyseggxia.providers.ProblemProviderI;
import dyseggxia.providers.SentenceSeparationProblemProvider;
import dyseggxia.providers.SubstitutionAnswerProvider;
import dyseggxia.providers.SubstitutionProblemProvider;

public class ProviderFactory {

	private Context context;
	
	private static ProviderFactory singletonInstance;
	
	private DatabaseHelper helper;
	private AnswerProviderI answerProvider;
	private ProblemProviderI problemProvider;
	private LevelProviderI levelProvider;
	
	private InsertionProblemProvider insertionProblemProvider;
	private OmissionProblemProvider omissionProblemProvider;
	private SubstitutionProblemProvider substitutionProblemProvider;
	private DerivationProblemProvider derivationProblemProvider;
	private SentenceSeparationProblemProvider sentenceSeparationProblemProvider;
	
	private InsertionAnswerProvider insertionAnswerProvider;
	private OmissionAnswerProvider omissionAnswerProvider;
	private SubstitutionAnswerProvider substitutionAnswerProvider;
	private DerivationAnswerProvider derivationAnswerProvider;
	
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
			insertionProblemProvider = new InsertionProblemProvider(getDatabaseHelper(), getAnswerProvider());
		}
		return insertionProblemProvider;
	}
	
	private OmissionProblemProvider getOmissionProblemProvider() {
		if(omissionProblemProvider == null) {
			omissionProblemProvider = new OmissionProblemProvider(getDatabaseHelper(), getAnswerProvider());
		}
		return omissionProblemProvider;
	}
	
	private SubstitutionProblemProvider getSubstitutionProblemProvider() {
		if(substitutionProblemProvider == null) {
			substitutionProblemProvider = new SubstitutionProblemProvider(getDatabaseHelper(), getAnswerProvider());
		}
		return substitutionProblemProvider;
	}
	
	private DerivationProblemProvider getDerivationProblemProvider() {
		if(derivationProblemProvider == null) {
			derivationProblemProvider = new DerivationProblemProvider(getDatabaseHelper(), getAnswerProvider());
		}
		return derivationProblemProvider;
	}
	
	private SentenceSeparationProblemProvider getSentenceSeparationProblemProvider() {
		if(sentenceSeparationProblemProvider == null) {
			sentenceSeparationProblemProvider = new SentenceSeparationProblemProvider(getDatabaseHelper());
		}
		return sentenceSeparationProblemProvider;
	}
	
	private InsertionAnswerProvider getInsertionAnswerProvider() {
		if(insertionAnswerProvider == null) {
			insertionAnswerProvider = new InsertionAnswerProvider(getDatabaseHelper());
		}
		return insertionAnswerProvider;
	}
	
	private OmissionAnswerProvider getOmissionAnswerProvider() {
		if(omissionAnswerProvider == null) {
			omissionAnswerProvider = new OmissionAnswerProvider(getDatabaseHelper());
		}
		return omissionAnswerProvider;
	}
	
	private SubstitutionAnswerProvider getSubstitutionAnswerProvider() {
		if(substitutionAnswerProvider == null) {
			substitutionAnswerProvider = new SubstitutionAnswerProvider(getDatabaseHelper());
		}
		return substitutionAnswerProvider;
	}
	
	private DerivationAnswerProvider getDerivationAnswerProvider() {
		if(derivationAnswerProvider == null) {
			derivationAnswerProvider = new DerivationAnswerProvider(getDatabaseHelper());
		}
		return derivationAnswerProvider;
	}
	
	public LevelProviderI getLevelProvider() {
		if(levelProvider == null) {
			levelProvider = new LevelProvider(getDatabaseHelper());
		}
		return levelProvider;
	}
	
	public ProblemProviderI getProblemProvider() {
		if(problemProvider == null) {
			problemProvider = new ProblemProvider(context);
		}
		return problemProvider;
	}
	
	public AbstractProblemProvider getProblemProvider(Class<?> type) {
		if(type.equals(InsertionProblem.class)) {
			return getInsertionProblemProvider();
		}
		if(type.equals(OmissionProblem.class)) {
			return getOmissionProblemProvider();
		}
		if(type.equals(SubstitutionProblem.class)) {
			return getSubstitutionProblemProvider();
		}
		if(type.equals(DerivationProblem.class)) {
			return getDerivationProblemProvider();
		}
		if(type.equals(SentenceSeparationProblem.class)) {
			return getSentenceSeparationProblemProvider();
		}
		return null;
	}
	
	public AnswerProviderI getAnswerProvider() {
		if(answerProvider == null) {
			answerProvider = new AnswerProvider(context);
		}
		return answerProvider;
	}
	
	public AbstractAnswerProvider getAnswerProvider(Class<?> type) {
		if(type.equals(InsertionProblem.class)) {
			return getInsertionAnswerProvider();
		}
		if(type.equals(OmissionProblem.class)) {
			return getOmissionAnswerProvider();
		}
		if(type.equals(SubstitutionProblem.class)) {
			return getSubstitutionAnswerProvider();
		}
		if(type.equals(DerivationProblem.class)) {
			return getDerivationAnswerProvider();
		}
		return null;
	}
	
	public DatabaseHelper getDatabaseHelper() {
		if(this.helper == null) {
			this.helper = new DatabaseHelper(context);
		}
		return helper;
	}
}
