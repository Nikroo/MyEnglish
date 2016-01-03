//package by.gsu.curiosity.mybd.lists;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//
///**
// * Created by Curiosity on 17.11.2015.
// */
//
////Класс для управления БД. Класс инкапсулирует все операции с объектом SQLiteOpenHelper
//public class ContactDbManager {
//    private static ContactDbManager instance;
//    public static ContactDbManager getInstance(Context con) {
//        if (instance == null) {
//            instance = new ContactDbManager(con);
//        }
//        return instance;
//    }
//
//    private SQLiteDatabase db;
//    private final Context context;
//    private ContactDbHelper dbHelper;
//
//    // �������� - ������ � ������� ����� ������� people
//    private String[] columns = new String[] {
//            ContactDbHelper.NAME,
//            ContactDbHelper.PHONE,
//            ContactDbHelper.PHOTO,
//            ContactDbHelper.ABOUT };
//
//    public ContactDbManager(Context c) {
//        context = c;
//        dbHelper = new ContactDbHelper(context);
//    }
//
//    // �������� ��
//    public void open() {
//        db = dbHelper.getWritableDatabase();
//    }
//
//    // �������� ��
//    public void close() {
//        db.close();
//    }
//
//    // ��������� ������� ������ ���������
//    public ArrayList<ContactItem> getContacts() {
//        open();
//        Cursor cursor = db.query(ContactDbHelper.TABLE_PEOPLE, null,
//                null, null, null, null, null);
//        ArrayList<ContactItem> list = new ArrayList<ContactItem>();
//        if (cursor != null && cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                ContactItem item = new ContactItem();
//                item.setID(cursor.getInt(0));
//                item.setName(cursor.getString(1));
//                item.setPhone(cursor.getString(2));
//                item.setPhotoPath(cursor.getString(3));
//                item.setAbout(cursor.getString(4));
//
//                list.add(item);
//            }
//            while (cursor.moveToNext());
//        }
//        close();
//        return list;
//    }
//
//    // ���������� ������ �������� � ������
//    public int addContact(ContactItem entity) {
//        open();
//        ContentValues values = new ContentValues(4);
//
//        values.put(ContactDbHelper.NAME, entity.getName());
//        values.put(ContactDbHelper.PHONE, entity.getPhone());
//        values.put(ContactDbHelper.PHOTO, entity.getPhotoPath());
//        values.put(ContactDbHelper.ABOUT, entity.getAbout());
//
//        int res = (int)db.insertOrThrow(
//                ContactDbHelper.TABLE_PEOPLE, null, values);
//        close();
//        return res;
//    }
//
//    // ���������� ������������� ��������
//    public int updateContact(ContactItem entity) {
//        open();
//        ContentValues values = new ContentValues(4);
//
//        values.put(ContactDbHelper.NAME, entity.getName());
//        values.put(ContactDbHelper.PHONE, entity.getPhone());
//        values.put(ContactDbHelper.PHOTO, entity.getPhotoPath());
//        values.put(ContactDbHelper.ABOUT, entity.getAbout());
//
//        String where = String.format("%s=%d",
//                ContactDbHelper._ID, entity.getID());
//        int res = db.update(ContactDbHelper.TABLE_PEOPLE, values, where, null);
//        close();
//        return res;
//    }
//
//    // �������� ��������
//    public int deleteContact(int contactID) {
//        open();
//        String where = String.format("%s=%d",
//                ContactDbHelper._ID, contactID);
//        int res = db.delete(ContactDbHelper.TABLE_PEOPLE, where, null);
//        close();
//        return res;
//    }
//
//}
