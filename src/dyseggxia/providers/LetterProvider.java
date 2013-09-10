package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.LetterTable;
import dyseggxia.domainModel.Problem;

public class LetterProvider implements LetterProviderI {
	
	private DatabaseHelper helper;
	
	public LetterProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public List<String> getLettersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(LetterTable.TABLE_NAME, LetterTable.ALL_COLUMNS, 
				LetterTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				LetterTable.COLUMN_LEVEL_LANGUAGE + "='" + problem.getLanguage() + "' AND " + 
				LetterTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, LetterTable.COLUMN_LETTER_INDEX);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			try {
				String answer = new String(cursor.getString(LetterTable.COLUMN_LETTER_INDEX_INDEX).getBytes(), "UTF-8");
				answers.add(answer);
				cursor.moveToNext();
			} catch (Exception e) {}
		}
		cursor.close();
		database.close();
		return answers;
	}
}
