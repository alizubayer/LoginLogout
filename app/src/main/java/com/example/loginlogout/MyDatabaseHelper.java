package com.example.loginlogout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Login.db";
    private static final String TABLE_NAME = "Login_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USER_NAME = "User_Name";
    private static final String PASSWORD = "Password";
    private static final String CONFIRM_PASSWORD = "Confirm_Password";
    private static  final int VERSION_NUMBER =2;
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+"( "+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255)," +EMAIL+" VARCHAR(255),"+USER_NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255),"+CONFIRM_PASSWORD+" VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM "+ TABLE_NAME;

    private Context context;
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch(Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(String name, String email, String user_name, String password, String confirm_password) {

        SQLiteDatabase db = this.getWritableDatabase(); //to write or read in database we need to help getWriteableDatabase method and it will return a SqLiteDatabase
        //to store all the data together we need to use ContentValues class.So we need to make an object of ContentValues
        ContentValues contentValues = new ContentValues();
        //by using put method we get the data from the user and store them all together
        contentValues.put(NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(USER_NAME,user_name);
        contentValues.put(PASSWORD,password);
        contentValues.put(CONFIRM_PASSWORD,confirm_password);

        //now we can insert the data into database finally
        //to insert the data into database we need to use db.insert() method
        long rowId= db.insert(TABLE_NAME,null,contentValues); //insert method returns a id of a row if the row is successfully stored into database and the id is a long data type
        return  rowId;   //the row is not successfully stored it will return -1

    }

    public boolean findPassword(String user_name,String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result = false;

        if(cursor.getCount() == 0)
        {

            Toast.makeText(context,"No data is found",Toast.LENGTH_SHORT).show();

        }

        else {
            while (cursor.moveToNext())
            {
                //to get the username & password from the cursor
                String username = cursor.getString(2);
                String password = cursor.getString(3);

                if(username.equals(user_name) && password.equals(pass))
                {
                    result = true;
                    break;
                }
            }

        }
        return result;

    }

}