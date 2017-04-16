package assign3.cghende1.bsse.asu.edu.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chris on 4/12/17.
 */

public class Database extends SQLiteOpenHelper {

    private static final boolean debugon = false;
    private static final int DATABASE_VERSION = 3;
    private static String dbName = "places";
    private String dbPath;
    private SQLiteDatabase routeDB;
    private final Context context;

    private static boolean created = false;

    public Database(Context context){
        super(context,dbName, null, DATABASE_VERSION);
        this.context = context;
        dbPath = context.getFilesDir().getPath()+"/";
        android.util.Log.d(this.getClass().getSimpleName(),"dbpath: "+dbPath);
    }

    public PlaceLibrary getLibrary() {
        PlaceLibrary placeLibrary = new PlaceLibrary();
        SQLiteDatabase cursor = this.openDB();
        cursor.beginTransaction();
        try {
            Cursor cur = cursor.rawQuery("select * from Places", new String[]{});
            while (cur.moveToNext()) {
                PlaceDescription place = new PlaceDescription(cur);
                placeLibrary.put(place.getName(), place);
            }
            cur.close();
            cursor.setTransactionSuccessful();
        } finally {
            cursor.endTransaction();
            cursor.close();
        }
        return placeLibrary;
    }

    public PlaceDescription getPlace(long ID) {
        PlaceDescription place = null;
        SQLiteDatabase cursor = this.openDB();
        cursor.beginTransaction();
        try {
            Cursor cur = cursor.rawQuery("select * from Places where id = ?", new String[]{new Long(ID).toString()});
            if (cur.moveToNext()) {
                place = new PlaceDescription(cur);
            }
            cur.close();
            cursor.setTransactionSuccessful();
        } finally {
            cursor.endTransaction();
            cursor.close();
        }
        return place;
    }

    public void updatePlace(PlaceDescription place) {
        SQLiteDatabase cursor = this.openDB();
        cursor.beginTransaction();
        ContentValues cvs = new ContentValues();
        cvs.put("name", place.getName());
        cvs.put("description", place.getDescription());
        cvs.put("category", place.getCategory());
        cvs.put("address_title", place.getAddress_title());
        cvs.put("address_street", place.getAddress_street());
        cvs.put("elevation", place.getElevation());
        cvs.put("longitude", place.getLongitude());
        cvs.put("latitude", place.getLatitude());
        cvs.put("image", place.getImage());
        try {
            cursor.update("Places", cvs, "id = ?", new String[]{new Integer(place.getID()).toString()});
            cursor.setTransactionSuccessful();
        } finally {
            cursor.endTransaction();
            cursor.close();
        }
    }

    public void insertPlace(PlaceDescription place) {
        android.util.Log.w("hello", "from saving");

        SQLiteDatabase cursor = this.openDB();
        cursor.beginTransaction();
        ContentValues cvs = new ContentValues();
        cvs.put("name", place.getName());
        cvs.put("description", place.getDescription());
        cvs.put("category", place.getCategory());
        cvs.put("address_title", place.getAddress_title());
        cvs.put("address_street", place.getAddress_street());
        cvs.put("elevation", place.getElevation());
        cvs.put("longitude", place.getLongitude());
        cvs.put("latitude", place.getLatitude());
        cvs.put("image", place.getImage());
        try {
            cursor.insertOrThrow("Places", null, cvs);
            cursor.setTransactionSuccessful();
        } finally {
            cursor.endTransaction();
            cursor.close();
        }

    }

    public void deletePlace(PlaceDescription place) {
        android.util.Log.w("seriously", "the heck");
        SQLiteDatabase cursor = this.openDB();
        cursor.beginTransaction();
        try {
            cursor.delete("Places", "id = ?", new String[]{new Integer(place.getID()).toString()});
            cursor.setTransactionSuccessful();
        } finally {
            cursor.endTransaction();
            cursor.close();
        }
    }

    public void createDB() throws IOException {
        this.getReadableDatabase();
        try {
            copyDB();
        } catch (IOException e) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "createDB Error copying database " + e.getMessage());
        }
    }

    public void copyDB() throws IOException{
        try {
            if(!created){
                android.util.Log.w("ASDADGASD", "WHARRGGGGLE");
                // only copy the database if it doesn't already exist in my database directory
                debug("RouteDB --> copyDB", "checkDB returned false, starting copy");
                InputStream ip =  context.getResources().openRawResource(R.raw.places);
                // make sure the database path exists. if not, create it.
                File aFile = new File(dbPath);
                if(!aFile.exists()){
                    aFile.mkdirs();
                }
                String op=  dbPath  +  dbName +".db";
                OutputStream output = new FileOutputStream(op);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = ip.read(buffer))>0){
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                ip.close();
            }
        } catch (IOException e) {
            android.util.Log.w("CourseDB --> copyDB", "IOException: "+e.getMessage());
        }
    }

    public SQLiteDatabase openDB() throws SQLException {
        String myPath = dbPath + dbName + ".db";
        if(created) {
            routeDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            debug("CourseDB --> openDB", "opened db at path: " + routeDB.getPath());
        }else{
            try {
                this.copyDB();
                created = true;
                routeDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            }catch(Exception ex) {
                android.util.Log.w(this.getClass().getSimpleName(),"unable to copy and open db: "+ex.getMessage());
            }
        }
        return routeDB;
    }

    @Override
    public synchronized void close() {
        if(routeDB != null)
            routeDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void debug(String hdr, String msg){
        if(debugon){
            android.util.Log.d(hdr,msg);
        }
    }

}
