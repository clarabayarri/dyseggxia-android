package dyseggxia.providers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.TrackingData;
import dyseggxia.domainModel.Problem;

public class TrackingDataProvider {

	private DatabaseHelper helper;
	
	public TrackingDataProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public void trackData(Problem problem, boolean correct, String answer, int time, String userID) {
		SQLiteDatabase database = helper.getWritableDatabase();
		
		ContentValues dadesAEnviar = new ContentValues();
		dadesAEnviar.put(TrackingData.COLUMN_LEVEL_NUMBER, problem.getLevel());
		dadesAEnviar.put(TrackingData.COLUMN_PROBLEM_NUMBER, problem.getNumber());
		dadesAEnviar.put(TrackingData.COLUMN_WORD, problem.getDisplayedText());
		if(correct) {
			dadesAEnviar.put(TrackingData.COLUMN_CORRECT, "S");
		}
		else dadesAEnviar.put(TrackingData.COLUMN_CORRECT, "N");
		dadesAEnviar.put(TrackingData.COLUMN_ANSWER, answer);
		dadesAEnviar.put(TrackingData.COLUMN_PROBLEM_TYPE, problem.getTypeName());
		dadesAEnviar.put(TrackingData.COLUMN_TIME, time);
		dadesAEnviar.put(TrackingData.COLUMN_USER_ID, userID);
		
		database.insert(TrackingData.TABLE_NAME, null, dadesAEnviar);
	}
}
