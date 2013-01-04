package dyseggxia.providers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ProviderFactory;

public class ProblemProvider extends AbstractProblemProvider implements ProblemProviderI {
	
	private ProviderFactory providerFactory;
	private DatabaseHelper helper;
	
	public ProblemProvider(Context context, DatabaseHelper helper) {
		providerFactory = ProviderFactory.getInstance(context);
		this.helper = helper;
	}
	
	public Problem findProblem(int levelNumber, String language, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(ProblemTable.TABLE_NAME, ProblemTable.ALL_COLUMNS, 
				ProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				ProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber +
				" AND " + ProblemTable.COLUMN_LEVEL_LANGUAGE + " = '" + language + "'", null, null, null, null);
		cursor.moveToFirst();
		if(cursor.isAfterLast()) {
			cursor.close();
			throw new Exception("no problem found");
		}
		Problem problem = mapProblem(cursor);
		database.close();
		return problem;
	}

	@Override
	protected Problem mapProblem(Cursor cursor) {
		String type = cursor.getString(ProblemTable.COLUMN_TYPE_INDEX);
		return providerFactory.getProblemProvider(type).mapProblem(cursor);
	}

}
