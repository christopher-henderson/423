package assign3.cghende1.bsse.asu.edu.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
    private static String dbName = "routes";
    private String dbPath;
    private SQLiteDatabase routeDB;
    private final Context context;

    public Database(Context context){
        super(context,dbName, null, DATABASE_VERSION);
        this.context = context;
        dbPath = context.getFilesDir().getPath()+"/";
        android.util.Log.d(this.getClass().getSimpleName(),"dbpath: "+dbPath);
    }

    public PlaceLibrary getLibrary() {
        PlaceLibrary placeLibrary = new PlaceLibrary();
        SQLiteDatabase cursor = this.openDB();
        Cursor cur = cursor.rawQuery("select * from Places", new String[]{});
        while (cur.moveToNext()) {
            PlaceDescription place = new PlaceDescription(cur);
            placeLibrary.put(place.getName(), place);
        }
        cur.close();
        return placeLibrary;
    }

    public PlaceDescription getPlace(long ID) {
        PlaceDescription place = null;
        SQLiteDatabase cursor = this.openDB();
        Cursor cur = cursor.rawQuery("select * from Places where id = ?", new String[]{new Long(ID).toString()});
        if (cur.moveToNext()) {
            place = new PlaceDescription(cur);
        }
        cur.close();
        return place;
    }

    public void updatePlace(PlaceDescription place) {
        SQLiteDatabase cursor = this.openDB();
        String query = "UPDATE Places SET name = ? description = ?, category = ?, address_title = ?, address_street = ?, elevation = ?, longitude = ?, latitude = ?, image = ? WHERE id = ?";
        Cursor cur = cursor.rawQuery(query, new String[]{
                place.getName(),
                place.getDescription(),
                place.getCategory(),
                place.getAddress_title(),
                place.getAddress_street(),
                new Double(place.getElevation()).toString(),
                new Double(place.getLongitude()).toString(),
                new Double(place.getLatitude()).toString(),
                place.getImage(),
                new Integer(place.getID()).toString()
        });
    }

    //        String query = "INSERT INTO Places (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        String query = String.format("INSERT INTO Places (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
//                place.getName(),
//                place.getDescription(),
//                place.getCategory(),
//                place.getAddress_title(),
//                place.getAddress_street(),
//                new Double(place.getElevation()).toString(),
//                new Double(place.getLongitude()).toString(),
//                new Double(place.getLatitude()).toString(),
//                place.getImage()
//        );
//        Cursor cur = cursor.rawQuery(query, new String[]{
//                place.getName(),
//                place.getDescription(),
//                place.getCategory(),
//                place.getAddress_title(),
//                place.getAddress_street(),
//                new Double(place.getElevation()).toString(),
//                new Double(place.getLongitude()).toString(),
//                new Double(place.getLatitude()).toString(),
//                place.getImage()
//        });
//        cursor.execSQL(query);


    public void insertPlace(PlaceDescription place) {
        android.util.Log.w("hello", "from saving");
        SQLiteDatabase cursor = this.openDB();

        SQLiteDatabase cursor = this.getWritableDatabase();
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
        long id = cursor.insertOrThrow("Places", null, cvs);
        cursor.close();
        PlaceDescription newPlace = this.getPlace(id);
        android.util.Log.w("add/save", newPlace.getName());
    }

    public void deletePlace(PlaceDescription place) {
        SQLiteDatabase cursor = this.openDB();
        String query = "DELETE FROM Places WHERE ID = ?";
        cursor.rawQuery(query, new String[]{new Integer(place.getID()).toString()});
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

    /**
     * Does the database exist and has it been initialized? This method determines whether
     * the database needs to be copied to the data/data/pkgName/files directory by
     * checking whether the file exists. If it does it checks to see whether the db is
     * uninitialized or whether it has the course table.
     * @return false if the database file needs to be copied from the assets directory, true
     * otherwise.
     */
    private boolean checkDB(){    //does the database exist and is it initialized?
        SQLiteDatabase checkDB = null;
        boolean ret = false;
        try{
            String path = dbPath + dbName + ".db";
            debug("RouteDB --> checkDB: path to db is", path);
            File aFile = new File(path);
            if(aFile.exists()){
                checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
                if (checkDB!=null) {
                    debug("RouteDB --> checkDB","opened db at: "+checkDB.getPath());
                    Cursor tabChk = checkDB.rawQuery("SELECT name FROM sqlite_master where type='table' and name='course';", null);
                    boolean crsTabExists = false;
                    if(tabChk == null){
                        debug("RouteDB --> checkDB","check for course table result set is null");
                    }else{
                        tabChk.moveToNext();
                        debug("RouteDB --> checkDB","check for course table result set is: " +
                                ((tabChk.isAfterLast() ? "empty" : (String) tabChk.getString(0))));
                        crsTabExists = !tabChk.isAfterLast();
                    }
                    if(crsTabExists){
                        Cursor c= checkDB.rawQuery("SELECT * FROM course", null);
                        c.moveToFirst();
                        while(! c.isAfterLast()) {
                            String crsName = c.getString(0);
                            int crsid = c.getInt(1);
                            debug("RouteDB --> checkDB","Course table has CourseName: "+
                                    crsName+"\tCourseID: "+crsid);
                            c.moveToNext();
                        }
                        ret = true;
                    }
                }
            }
        }catch(SQLiteException e){
            android.util.Log.w("RouteDB->checkDB",e.getMessage());
        }
        if(checkDB != null){
            checkDB.close();
        }
        return ret;
    }

    public void copyDB() throws IOException{
        try {
            if(!checkDB()){
                // only copy the database if it doesn't already exist in my database directory
                debug("RouteDB --> copyDB", "checkDB returned false, starting copy");
                InputStream ip =  context.getResources().openRawResource(R.raw.routes);
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
        if(checkDB()) {
            routeDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            debug("CourseDB --> openDB", "opened db at path: " + routeDB.getPath());
        }else{
            try {
                this.copyDB();
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
