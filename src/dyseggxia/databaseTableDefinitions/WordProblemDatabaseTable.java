package dyseggxia.databaseTableDefinitions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.utilities.AssetsReader;

public abstract class WordProblemDatabaseTable extends DatabaseTable {

	@Override
	public void populateTable(Context context, SQLiteDatabase database) {
		AssetsReader reader = new AssetsReader(context);
		for(int i = 1; i <= 3; ++i) {
			loadLevelData(reader, database, i);
		}
		
	}
	
	protected abstract void loadLevelData(AssetsReader reader, SQLiteDatabase database, int level);

}
