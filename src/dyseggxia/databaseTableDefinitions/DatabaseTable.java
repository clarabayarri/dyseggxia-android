package dyseggxia.databaseTableDefinitions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DatabaseTable {

	public static final String COLUMN_ID = "_id";
	
	public abstract void populateTable(Context context, SQLiteDatabase database);

	public abstract String getCreateTableSentence();
	
	public abstract String getTableName();
}
