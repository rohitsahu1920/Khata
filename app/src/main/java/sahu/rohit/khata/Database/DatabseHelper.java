package sahu.rohit.khata.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import sahu.rohit.khata.Model.customer;

public class DatabseHelper extends SQLiteOpenHelper {

    public DatabseHelper(Context context) {
        super(context,"khata1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(UserName text primary key,email text, password text)");
        db.execSQL("Create table customer(fname text,lname text,gender text,mobile_num text,whatsapp_num text,address text,profile BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists customer");
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
