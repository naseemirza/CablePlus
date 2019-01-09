package solutions.thinkbiz.cableplus.sqlitebds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 13-Dec-18.
 */

public class DBAdapter {

    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }

    //OPEN
    public DBAdapter openDB()
    {

        try {
            db=helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    //CLOSE
    public void closeDB()
    {
        try {
            helper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SAVE
    public long add(String name, String url, String price, String Pid)
    {
        try {
            ContentValues cv=new ContentValues();
            cv.put(Constants.NAME, name);
            cv.put(Constants.URL, url);
            cv.put(Constants.PRICE, price);
            cv.put(Constants.PROD_ID, Pid);

            db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }



    //Delete

    public void deleteItem(String get_ID)
    {

        db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM CablePlus WHERE Pid='"+get_ID+"'");

    }

    //delete All
    public void deleteAll() {
        db.execSQL("delete from "+ Constants.TB_NAME);
    }

    //Update
    public long UpdateItem(String pid, String s) {

        try {
            helper=new DBHelper(c);
            db=helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(Constants.PRICE, s);
            db.update(Constants.TB_NAME, cv, Constants.PROD_ID + "=" + pid, null);

            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public long ReplaceItem(String name, String image, String s, String pid) {

        try {
            helper=new DBHelper(c);
            db=helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, name);
            cv.put(Constants.URL, image);
            cv.put(Constants.PRICE, s);
            cv.put(Constants.PROD_ID, pid);
           db.update(Constants.TB_NAME, cv, Constants.PROD_ID + "=" + pid, null);


            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }


 //Log.e("pid",Constants.ROW_ID);
    //RETRIEVE
    public Cursor getTVShows()
    {
        String[] columns={Constants.ROW_ID, Constants.NAME, Constants.URL, Constants.PRICE, Constants.PROD_ID};

        return db.query(Constants.TB_NAME, columns,null,null,null,null,null);

    }



}
