package sahu.rohit.khata.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sahu.rohit.khata.Model.customer;
import sahu.rohit.khata.Model.transaction;

public class DatabseHelper extends SQLiteOpenHelper {

    public DatabseHelper(Context context) {
        super(context,"khata4.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(UserName text primary key,email text, password text)");
        db.execSQL("Create table customer(fname text,lname text,gender text,mobile_num text,whatsapp_num text,address text,profile BLOB)");
        db.execSQL("Create table trans(trans INTEGER PRIMARY KEY AUTOINCREMENT,username text,amount text,description text,datetime text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists trans");
    }

    public boolean insert(String UserName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",UserName);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?",new String[]{email});

        if(cursor.getCount() > 0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean emailpass(String user, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where UserName = ? and password = ? ", new String[]{user,pass});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public  boolean insert_customer(String fname,String lname,String gender,String phone, String whatsapp,String address,byte[] profile)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname",fname);
        cv.put("lname",lname);
        cv.put("gender",gender);
        cv.put("mobile_num",phone);
        cv.put("whatsapp_num",whatsapp);
        cv.put("address",address);
        cv.put("profile",profile);
        long ins = db.insert("customer",null,cv);
        if(ins == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insert_transaction(String name, String amount, String desc, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",name);
        cv.put("amount",amount);
        cv.put("description",desc);
        cv.put("datetime",date);
        long ins = db.insert("trans",null,cv);
        if(ins == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<customer> getDetails()
    {
        List<customer> renti_model_List = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select * from customer",null);
        if(cursor.moveToFirst())
        {
            do
            {
                String first_name = cursor.getString(0);
                String last_name = cursor.getString(1);
                String gender = cursor.getString(2);
                String mobile = cursor.getString(3);
                String whatsapp_mobile = cursor.getString(4);
                String permanent_add = cursor.getString(5);
                byte[] id_image = cursor.getBlob(6);

                customer customer = new customer(first_name,last_name,gender,mobile,whatsapp_mobile,permanent_add,id_image);
                renti_model_List.add(customer);
            }
            while (cursor.moveToNext());
        }
        return renti_model_List;
    }

    public ArrayList<transaction> gettransaction()
    {
        ArrayList<transaction> transaction_model_List = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select * from trans",null);
        if(cursor.moveToFirst())
        {
            do
            {
                int trans_id = cursor.getInt(0);
                String username = cursor.getString(1);
                String amount = cursor.getString(2);
                String description = cursor.getString(3);
                String date = cursor.getString(4);

                transaction transaction = new transaction(trans_id,username,amount,description,date);
                transaction_model_List.add(transaction);
            }
            while (cursor.moveToNext());
        }
        return transaction_model_List;
    }

    public String get_total_loan()
    {
        String count1 = "";
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select count(amount) from trans",null);
        if(cursor.moveToNext())
        {
            count1 = cursor.getString(2);
        }
        return count1;
    }

    public List<customer> getUsername()
    {
        List<customer> user_name = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select fname, lname from customer",null);
        if(cursor.moveToNext())
        {
            do
            {
                String first = cursor.getString(0);
                String last = cursor.getString(1);
                customer customer =  new customer(first,last);
                user_name.add(customer);
            }
            while (cursor.moveToNext());
        }
        return user_name;
    }

    public boolean delete_all()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete("customer", null,null);
        if(delete == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean delete_all_user()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete("user", null,null);
        if(delete == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public boolean delete(String fname,String lname,String phone,String w_phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete("customer", "fname = ? and lname = ? and mobile_num = ? and whatsapp_num = ?",new String[]{fname,lname,phone,w_phone});
        if(delete == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
