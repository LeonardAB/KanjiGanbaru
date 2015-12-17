package com.diptamahardhika.kanjiganbaru;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.ContactsContract;
        import android.widget.Toast;

/**
 * Created by JS on 2015/11/20.
 */
public class DatabaseAdapter  {

    DatabaseHelper helper;
    public DatabaseAdapter(Context context){
        helper = new DatabaseHelper(context);
    }
    public long insertData (String searchTerm) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, searchTerm);
        contentValues.put(DatabaseHelper.COL_3, searchTerm);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData () {
        //select id, name from TABLE_NAME
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DatabaseHelper.COL_1, DatabaseHelper.COL_2};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DatabaseHelper.COL_1); //karena letak kolom bisa berubah misal karena kita ubah struktur table atau karena kita select kombinasi kolom berbeda
            int cid = cursor.getInt(index1);
            String firstName = cursor.getString(1); // klo di hardcoded gini juga bisa sih tapi bahaya
            buffer = buffer.append("id " + cid + ", name: " + firstName + "\n");
        }
        return buffer.toString();
    }

    public String getData(String id){
//select name from TABLE_NAME where id=id
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DatabaseHelper.COL_2};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.COL_1+" = '"+id+"'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            String firstName = cursor.getString(0);
            buffer = buffer.append("id " + id + ", name: " + firstName + "\n");
        }
        return buffer.toString();
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "student.db";
        private static final String TABLE_NAME = "student_table";
        private static final int VERSION = 2;
        private static final String COL_1 = "ID";
        private static final String COL_2 = "NAME";
        private static final String COL_3 = "SURNAME";
        private static final String COL_4 = "MARKS";
        private static final String CREATE = "CREATE table "+TABLE_NAME+" ("
                +COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT, "
                +COL_3+" TEXT, "
                +COL_4+" INTEGER)";
        private Context context;

        public DatabaseHelper(Context context) {

            super(context, DATABASE_NAME, null, VERSION);
            this.context = context;
            Message.message (context,"constructor called");
            SQLiteDatabase db=this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE);
                Message.message(context, "onCreate called");
            } catch (SQLException e) {
                Message.message(context, "" +e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
                Message.message(context, "onUpgrade called");
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }

}
