package dyseggxia.factories;

import android.content.Context;
import dyseggxia.providers.AbstractProblemProvider;
import dyseggxia.providers.AnswerProvider;
import dyseggxia.providers.AnswerProviderI;
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
import dyseggxia.providers.TrackingDataProvider;

public class ProviderFactory {

	private Context context;
	
	private static ProviderFactory singletonInstance;
	
	private DatabaseHelper helper;
	private AnswerProviderI answerProvider;
	private ProblemProviderI problemProvider;
	private LevelProviderI levelProvider;
	private TrackingDataProvider trackingDataProvider;
	
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
	
	public TrackingDataProvider getTrackingDataProvider() {
		if(trackingDataProvider == null) {
			trackingDataProvider = new TrackingDataProvider(getDatabaseHelper());
		}
		return trackingDataProvider;
	}
	
	public DatabaseHelper getDatabaseHelper() {
		if(this.helper == null) {
			this.helper = new DatabaseHelper(context);
		}
		return helper;
	}
}
