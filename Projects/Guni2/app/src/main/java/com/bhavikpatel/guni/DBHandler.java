package com.bhavikpatel.guni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bhavikpatel.guni.balance.Invoice;
import com.bhavikpatel.guni.study.Study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 */

public class DBHandler {

    // database
    public static class DBIncoice extends SQLiteOpenHelper {

        static final String DBNAME = "invoice.db";
        static final int DBVERSION = 4;

        public DBIncoice(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DBNAME, factory, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query;
            query = "CREATE TABLE " + TableWallet.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableWallet.COLUMN_AMOUNT + " INTEGER," +
                    TableWallet.COLUMN_DATE + " TEXT," +
                    TableWallet.COLUMN_DESCRIPTION + " TEXT," +
                    TableWallet.COLUMN_BOOKMARK + " INTEGER," +
                    TableWallet.COLUMN_TYPE + " INTEGER," +
                    TableWallet.COLUMN_TITLE + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableBalance.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableBalance.COLUMN_AMOUNT + " INTEGER," +
                    TableBalance.COLUMN_DATE + " TEXT," +
                    TableBalance.COLUMN_DESCRIPTION + " TEXT," +
                    TableBalance.COLUMN_BOOKMARK + " INTEGER" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableData.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableData.COLUMN_AMOUNT + " INTEGER," +
                    TableData.COLUMN_DATE + " TEXT," +
                    TableData.COLUMN_DESCRIPTION + " TEXT," +
                    TableData.COLUMN_BOOKMARK + " INTEGER" + ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query;
            query = "DROP TABLE IF EXISTS " + TableData.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableBalance.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableWallet.NAME + ";";
            db.execSQL(query);
            onCreate(db);
        }

        //Table
        public class TableWallet {
            public static final String NAME = "wallet_invoice";
            public static final String COLUMN_TITLE = "title";
            public static final String COLUMN_AMOUNT = "amount";
            public static final String COLUMN_DATE = "date";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_BOOKMARK = "bookmark";
            public static final String COLUMN_TYPE = "type";

            public ArrayList<Invoice.Wallet>  getList(){
                ArrayList<Invoice.Wallet> walletList = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Invoice.Wallet walletInvoice = new Invoice.Wallet();
                    walletInvoice.setAmount(c.getInt(c.getColumnIndex(COLUMN_AMOUNT)));
                    walletInvoice.setBookmark(c.getInt(c.getColumnIndex(COLUMN_BOOKMARK)));
                    walletInvoice.setType(c.getInt(c.getColumnIndex(COLUMN_TYPE)));
                    walletInvoice.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    walletInvoice.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                    walletInvoice.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    walletList.add(walletInvoice);
                    c.moveToNext();
                }
                db.close();
                return walletList;
            }
            public ArrayList<String>  getListTitle(boolean spendType){
                int type = spendType ? 0 : 1;
                ArrayList<String> list = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT DISTINCT " + COLUMN_TITLE + " FROM " + NAME + " WHERE " + COLUMN_TYPE + " == \"" + type + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String element;
                    element = c.getString(c.getColumnIndex(COLUMN_TITLE));
                    list.add(element);
                    c.moveToNext();
                }
                db.close();
                return list;
            }
            public void addIncoice(Invoice.Wallet invoice){
                SQLiteDatabase db = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_TITLE,invoice.getTitle());
                values.put(COLUMN_AMOUNT,invoice.getAmount());
                values.put(COLUMN_BOOKMARK,invoice.getBookmark());
                values.put(COLUMN_TYPE,invoice.getType());
                values.put(COLUMN_DESCRIPTION,invoice.getDescription());
                values.put(COLUMN_DATE,invoice.getDate());
                db.insert(NAME,null,values);
                db.close();
            }
            public void saveInvoice(ArrayList<Invoice.Wallet> walletList){
                getWritableDatabase().execSQL("DELETE FROM " + NAME + " WHERE 1;");
                for(Invoice.Wallet invoice : walletList){
                    addIncoice(invoice);
                }
            }
            public void delete(int position){
                SQLiteDatabase db = getWritableDatabase();
                String query = "DELETE FROM " + NAME + " WHERE _id IN ( SELECT _id FROM " + NAME + " LIMIT 1 OFFSET " + position + " );";
                db.execSQL(query);
                db.close();
            }
            public void delete(ArrayList<Integer> positions,boolean inDescending){
                if(!inDescending){
                    Collections.sort(positions);
                    Collections.reverse(positions);
                }
                for(Integer position : positions) {
                    delete(position);
                }
            }
            public void editBookmark(int position,boolean add){
                int bookmark = add?1:0;
                SQLiteDatabase db = getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_BOOKMARK,bookmark);
                db.update(NAME,contentValues," _id IN ( SELECT _id FROM " + NAME + " LIMIT 1 OFFSET " + position + " );",null);
                db.close();
            }
            public void updatePosition(int position, Invoice.Wallet updatedWallet){
                SQLiteDatabase db = getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_TITLE,updatedWallet.getTitle());
                contentValues.put(COLUMN_AMOUNT,updatedWallet.getAmount());
                contentValues.put(COLUMN_DESCRIPTION,updatedWallet.getDescription());
                contentValues.put(COLUMN_TYPE,updatedWallet.getType());
                contentValues.put(COLUMN_DATE,updatedWallet.getDate());
                db.update(NAME,contentValues," _id IN ( SELECT _id FROM " + NAME + " LIMIT 1 OFFSET " + position + " );",null);
                db.close();
            }
        }
        public class TableBalance {
            static final String NAME = "balance_invoice";
            static final String COLUMN_AMOUNT = "amount";
            static final String COLUMN_DATE = "date";
            static final String COLUMN_DESCRIPTION = "description";
            static final String COLUMN_BOOKMARK = "bookmark";

            public ArrayList<Invoice.Balance>  getList(){
                ArrayList<Invoice.Balance> balanceList = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Invoice.Balance balanceInvoice = new Invoice.Balance();
                    balanceInvoice.setAmount(c.getInt(c.getColumnIndex(COLUMN_AMOUNT)));
                    balanceInvoice.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    balanceInvoice.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                    balanceList.add(balanceInvoice);
                    c.moveToNext();
                }
                db.close();
                return balanceList;
            }
        }
        public class TableData {
            static final String NAME = "data_invoice";
            static final String COLUMN_AMOUNT = "amount";
            static final String COLUMN_DATE = "date";
            static final String COLUMN_DESCRIPTION = "description";
            static final String COLUMN_BOOKMARK = "bookmark";

            public ArrayList<Invoice.Data>  getList(){
                ArrayList<Invoice.Data> dataList = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Invoice.Data dataInvoice = new Invoice.Data();
                    dataInvoice.setAmount(c.getInt(c.getColumnIndex(COLUMN_AMOUNT)));
                    dataInvoice.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    dataInvoice.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                    dataList.add(dataInvoice);
                    c.moveToNext();
                }
                db.close();
                return dataList;
            }
        }
    }

    public static class DBStudy extends SQLiteOpenHelper {

        public static String DBNAME = "study.db";
        public static int DBVERSION = 5;

        public DBStudy(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DBNAME, factory, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query;
            query = "CREATE TABLE " + TableMaterialPDF.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableMaterialPDF.COLUMN_SUB + " TEXT," +
                    TableMaterialPDF.COLUMN_TITLE + " TEXT," +
                    TableMaterialPDF.COLUMN_AUTHOR + " TEXT," +
                    TableMaterialPDF.COLUMN_TYPE + " TEXT," +
                    TableMaterialPDF.COLUMN_ADDRESS + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableMaterialVideo.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableMaterialVideo.COLUMN_SUB + " TEXT," +
                    TableMaterialVideo.COLUMN_TITLE + " TEXT," +
                    TableMaterialVideo.COLUMN_TYPE + " TEXT," +
                    TableMaterialVideo.COLUMN_ADDRESS + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableMaterialPhoto.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableMaterialPhoto.COLUMN_SUB + " TEXT," +
                    TableMaterialPhoto.COLUMN_TITLE + " TEXT," +
                    TableMaterialPhoto.COLUMN_TYPE + " TEXT," +
                    TableMaterialPhoto.COLUMN_ADDRESS + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableResult.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableResult.COLUMN_SUB + " TEXT," +
                    TableResult.COLUMN_DATE + " TEXT," +
                    TableResult.COLUMN_OBTAINED_MARKS + " REAL," +
                    TableResult.COLUMN_TOTAL_MARKS + " REAL," +
                    TableResult.COLUMN_EXAM_NAME + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableAttendence.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableAttendence.COLUMN_SUB + " TEXT," +
                    TableAttendence.COLUMN_DATE + " TEXT," +
                    TableAttendence.COLUMN_PRESENT + " INT," +
                    TableAttendence.COLUMN_NO_OF_CLASS + " INT" +");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableSyllabus.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableSyllabus.COLUMN_SUB + " TEXT," +
                    TableSyllabus.COLUMN_TITLE + " TEXT" + ");";
            db.execSQL(query);
            query = "CREATE TABLE " + TableSubject.NAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    TableSubject.COLUMN_TYPE + " TEXT," +
                    TableSubject.COLUMN_TITLE + " TEXT" + ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query;
            query = "DROP TABLE IF EXISTS " + TableMaterialPDF.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableMaterialVideo.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableMaterialPhoto.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableResult.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableAttendence.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableSyllabus.NAME + ";";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS " + TableSubject.NAME + ";";
            db.execSQL(query);
            onCreate(db);
        }

        //Table Material
        public class TableMaterialPDF {

            static final String NAME = "material_pdf";
            static final String COLUMN_SUB = "sub";
            static final String COLUMN_TITLE = "title";
            static final String COLUMN_AUTHOR = "author";
            static final String COLUMN_ADDRESS = "address";
            static final String COLUMN_TYPE = "type";

            public ArrayList<Study.Material.PDF>  getListChild(String groupName){
                ArrayList<Study.Material.PDF> listPDF = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE " + COLUMN_SUB + " == \"" + groupName + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Material.PDF pdf = new Study.Material.PDF();
                    pdf.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                    pdf.setAuthor(c.getString(c.getColumnIndex(COLUMN_AUTHOR)));
                    pdf.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    pdf.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listPDF.add(pdf);
                    c.moveToNext();
                }
                db.close();
                return listPDF;
            }

            public ArrayList<String>  getListGroup(){
                ArrayList<String> listGroup = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT DISTINCT " + COLUMN_SUB + " FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String name = null;
                    name = c.getString(c.getColumnIndex(COLUMN_SUB));
                    listGroup.add(name);
                    c.moveToNext();
                }
                db.close();
                return listGroup;
            }
        }
        public class TableMaterialVideo{

            static final String NAME = "material_video";
            static final String COLUMN_SUB = "sub";
            static final String COLUMN_TITLE = "title";
            static final String COLUMN_ADDRESS = "address";
            static final String COLUMN_TYPE = "type";

            public ArrayList<Study.Material.Video>  getListChild(String groupName){
                ArrayList<Study.Material.Video> listVideo = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE " + COLUMN_SUB + " == \"" + groupName + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Material.Video video = new Study.Material.Video();
                    video.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                    video.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    video.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listVideo.add(video);
                    c.moveToNext();
                }
                db.close();
                return listVideo;
            }

            public ArrayList<String>  getListGroup(){
                ArrayList<String> listGroup = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT DISTINCT " + COLUMN_SUB + " FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String name = null;
                    name = c.getString(c.getColumnIndex(COLUMN_SUB));
                    listGroup.add(name);
                    c.moveToNext();
                }
                db.close();
                return listGroup;
            }
        }
        public class TableMaterialPhoto{

            static final String NAME = "material_photo";
            static final String COLUMN_SUB = "sub";
            static final String COLUMN_TITLE = "title";
            static final String COLUMN_ADDRESS = "address";
            static final String COLUMN_TYPE = "type";

            public ArrayList<Study.Material.Photo>  getList(String sub){
                ArrayList<Study.Material.Photo> listPhoto = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE " + COLUMN_SUB + " == \"" + sub + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Material.Photo photo = new Study.Material.Photo();
                    photo.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                    photo.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    photo.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listPhoto.add(photo);
                    c.moveToNext();
                }
                db.close();
                return listPhoto;
            }

            public ArrayList<Study.Material.Photo>  getList(){
                ArrayList<Study.Material.Photo> listPhoto = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Material.Photo photo = new Study.Material.Photo();
                    photo.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                    photo.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    photo.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listPhoto.add(photo);
                    c.moveToNext();
                }
                db.close();
                return listPhoto;
            }
            public ArrayList<String>  getListPhotoSub(){
                ArrayList<String> listPhoto = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                //Log.i("BHAVIK","SELECTING * from photo table for list");
                String query = "SELECT DISTINCT " + COLUMN_SUB + " FROM " + NAME + " WHERE 1==1;";
                //Log.i("BHAVIK","SELECTED * from photo table for list");
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                   // Log.i("BHAVIK","IN WHILE LOOP , GOING THROUNH CURSOR,  photo table for list");
                    String photo = new String();
                    photo = c.getString(c.getColumnIndex(COLUMN_SUB));
                    listPhoto.add(photo);
                    c.moveToNext();
                }
                db.close();
                return listPhoto;
            }
            public void addPhoto(Study.Material.Photo photo){
                ContentValues values = new ContentValues();
                values.put(COLUMN_SUB,photo.getSub());
                values.put(COLUMN_ADDRESS,photo.getAddress());
                values.put(COLUMN_TITLE,photo.getTitle());
                values.put(COLUMN_TYPE,photo.getType());
                SQLiteDatabase db = getWritableDatabase();
                db.insert(NAME,null,values);
                db.close();
            }
            public void addPhoto(ArrayList<Study.Material.Photo> photos){
                for(Study.Material.Photo photo : photos){
                    addPhoto(photo);
                }
            }

            public void deletePhotoWithSub(String sub){
                SQLiteDatabase db = getWritableDatabase();
                db.delete(NAME, COLUMN_SUB + " == \"" + sub + "\";",null);
                db.close();
            }
            public void delete(Study.Material.Photo photo){
                SQLiteDatabase db = getWritableDatabase();
                db.delete(NAME,COLUMN_ADDRESS + " == \"" + photo.getAddress() + "\";",null);
                db.close();
            }
            public void delete(ArrayList<Study.Material.Photo> photos){
                for(Study.Material.Photo photo : photos){
                    delete(photo);
                }
            }

        }

        //Table Result
        public class TableResult{

            static final String NAME = "result";
            static final String COLUMN_DATE = "date";
            static final String COLUMN_SUB = "sub";
            static final String COLUMN_EXAM_NAME = "exam_name";
            static final String COLUMN_OBTAINED_MARKS = "obtained_marks";
            static final String COLUMN_TOTAL_MARKS = "total_marks";

            public ArrayList<Study.Result>  getList(){
                ArrayList<Study.Result> listResult = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Result result = new Study.Result();
                    result.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    result.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    result.setExamName(c.getString(c.getColumnIndex(COLUMN_EXAM_NAME)));
                    result.setObtainedMarks(c.getFloat(c.getColumnIndex(COLUMN_OBTAINED_MARKS)));
                    result.setTotalMarks(c.getFloat(c.getColumnIndex(COLUMN_TOTAL_MARKS)));
                    listResult.add(result);
                    c.moveToNext();
                }
                db.close();
                return listResult;
            }
        }

        //Table Attendence
        public class TableAttendence{

            static final String NAME = "attendence";
            static final String COLUMN_DATE = "date";
            static final String COLUMN_SUB = "sub";
            static final String COLUMN_PRESENT = "present";
            static final String COLUMN_NO_OF_CLASS = "no_of_class";

            public ArrayList<Study.Attendence>  getList(){
                ArrayList<Study.Attendence> listAttendence = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Attendence attendence = new Study.Attendence();
                    attendence.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    attendence.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    attendence.setPresent(c.getInt(c.getColumnIndex(COLUMN_PRESENT)));
                    attendence.setNoOfClass(c.getInt(c.getColumnIndex(COLUMN_NO_OF_CLASS)));
                    listAttendence.add(attendence);
                    c.moveToNext();
                }
                db.close();
                return listAttendence;
            }
            public ArrayList<Study.Attendence>  getList(String sub){
                ArrayList<Study.Attendence> listAttendence = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE " + COLUMN_SUB + " == \"" + sub + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Attendence attendence = new Study.Attendence();
                    attendence.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    attendence.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                    attendence.setPresent(c.getInt(c.getColumnIndex(COLUMN_PRESENT)));
                    attendence.setNoOfClass(c.getInt(c.getColumnIndex(COLUMN_NO_OF_CLASS)));
                    listAttendence.add(attendence);
                    c.moveToNext();
                }
                db.close();
                return listAttendence;
            }
            public ArrayList<Study.AttendenceGroup>  getListGroup(){
                ArrayList<Study.AttendenceGroup> listGroup = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.AttendenceGroup group = new Study.AttendenceGroup();
                    group.setTitle(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    group.setAtt("");
                    listGroup.add(group);
                    c.moveToNext();
                }
                db.close();
                return listGroup;
            }
            /*private float getPercentAtt(int present, int abscent){
                int total = present + abscent;

            }*/
        }

        //Table Syllabus
        public class TableSyllabus{

            static final String NAME = "syllabus";
            static final String COLUMN_TITLE = "title";
            static final String COLUMN_SUB = "sub";

            public ArrayList<Study.Syllabus>  getList(){
                ArrayList<Study.Syllabus> listSyllabus = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Syllabus syllabus = new Study.Syllabus();
                    syllabus.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    syllabus.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listSyllabus.add(syllabus);
                    c.moveToNext();
                }
                db.close();
                return listSyllabus;
            }
            public ArrayList<Study.Syllabus>  getList(String sub){
                ArrayList<Study.Syllabus> listSyllabus = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT * FROM " + NAME + " WHERE " + COLUMN_SUB + " == \"" + sub + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Syllabus syllabus = new Study.Syllabus();
                    syllabus.setSub(c.getString(c.getColumnIndex(COLUMN_SUB)));
                    syllabus.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listSyllabus.add(syllabus);
                    c.moveToNext();
                }
                db.close();
                return listSyllabus;
            }
            public ArrayList<String>  getListGroup(){
                ArrayList<String> listSyllabus = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT DISTINCT " + COLUMN_SUB + " FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String syllabus = c.getString(c.getColumnIndex(COLUMN_SUB));
                    listSyllabus.add(syllabus);
                    c.moveToNext();
                }
                db.close();
                return listSyllabus;
            }
        }

        public class TableSubject{

            public static final String TYPE_MATERIAL_PHOTO_LIST = "material_photo_list";

            static final String NAME = "subject";
            static final String COLUMN_TITLE = "title";
            static final String COLUMN_TYPE = "type";

            public ArrayList<Study.Subject>  getSubject(){
                ArrayList<Study.Subject> listSubject = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT DISTINCT " + COLUMN_TITLE + " FROM " + NAME + " WHERE 1==1;";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Subject subject = new Study.Subject();
                    subject.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    listSubject.add(subject);
                    c.moveToNext();
                }
                db.close();
                return listSubject;
            }
            public ArrayList<Study.Subject>  getSubject(String type){
                ArrayList<Study.Subject> listSubject = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT " + "*" + " FROM " + NAME + " WHERE " + COLUMN_TYPE + " == \"" + type + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    Study.Subject subject = new Study.Subject();
                    //Log.i("BHAVIK","" + TYPE_MATERIAL_PHOTO_LIST + " = " + subject);
                    subject.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    subject.setType(c.getString(c.getColumnIndex(COLUMN_TYPE)));
                    listSubject.add(subject);
                    c.moveToNext();
                }
                db.close();
                return listSubject;
            }
            public ArrayList<String> getSubTitle(String type){
                ArrayList<String> listSubject = new ArrayList<>();
                SQLiteDatabase db = getWritableDatabase();
                String query = "SELECT " + COLUMN_TITLE + " FROM " + NAME + " WHERE " + COLUMN_TYPE + " == \"" + type + "\";";
                Cursor c = db.rawQuery(query,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String subject = c.getString(c.getColumnIndex(COLUMN_TITLE));
                    listSubject.add(subject);
                    c.moveToNext();
                }
                db.close();
                return listSubject;
            }
            public void setSubTitle(String type,String newTitle,String oldTitle){
                SQLiteDatabase db = getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_TITLE,newTitle);
                db.update(NAME,cv,COLUMN_TYPE + " == \"" + type + "\" AND " + COLUMN_TITLE + " == \"" + oldTitle + "\";",null);
                db.close();
            }
            public void addSubject(Study.Subject subject){
                ContentValues values = new ContentValues();
                values.put(COLUMN_TITLE,subject.getTitle());
                values.put(COLUMN_TYPE,subject.getType());
                SQLiteDatabase db = getWritableDatabase();
                db.insert(NAME,null,values);
                db.close();
            }
            public void delete(String type,int position){
                SQLiteDatabase db = getWritableDatabase();
                String query = "DELETE FROM " + NAME +
                        " WHERE _id IN (SELECT _id FROM " + "(SELECT * FROM " + NAME + " WHERE " + COLUMN_TYPE + " == \"" + type + "\")" +
                        " LIMIT 1 OFFSET " + position + " );";
                db.execSQL(query);
                db.close();
            }
            public void delete(String type,ArrayList<Integer> positions,boolean inDescending){
                if(!inDescending){
                    Collections.sort(positions);
                    Collections.reverse(positions);
                }
                for(Integer position : positions) {
                    delete(type,position);
                }
            }
        }


        /*public static class TableMaterialResult extends SQLiteOpenHelper{

            public TableMaterialPDF(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
        public static class TableMaterialAttendence extends SQLiteOpenHelper{

            public TableMaterialPDF(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
        public static class TableMaterialSyllabus extends SQLiteOpenHelper{

            public TableMaterialPDF(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
        public static class TableMaterialTimeTable extends SQLiteOpenHelper{

            public TableMaterialPDF(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
        public static class TableMaterialCalander extends SQLiteOpenHelper{

            public TableMaterialPDF(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
*/

    }
}
