package dyseggxia.databaseTableDefinitions;

import android.database.sqlite.SQLiteDatabase;

public abstract class DatabaseTable {

	public static final String COLUMN_ID = "_id";
	
	public abstract void populateTable(SQLiteDatabase database);

	public abstract String getCreateTableSentence();
	
	public abstract String getTableName();
}
