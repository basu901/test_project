package com.example.shaunakbasu.finalproject.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.shaunakbasu.finalproject.R;
import com.example.shaunakbasu.finalproject.data.UserDetailsColumns;
import com.example.shaunakbasu.finalproject.data.UserDetailsDatabase;
import com.example.shaunakbasu.finalproject.data.UserDetailsProvider;


/**
 * Created by shaunak basu on 20-08-2016.
 */
public class DataInsert {
    static Context context;
    public static String id;

    public static String setID(String set_id){
        id=set_id;
        return id;
    }


 public static void loadUsers(Context mContext){

     Cursor user_row=mContext.getContentResolver().query(UserDetailsProvider.User.CONTENT_URI,
             new String[]{UserDetailsColumns.EMAIL,UserDetailsColumns.PASSWORD},
             null,
             null,null);
     if(user_row.moveToFirst()){
         return;
     }
     else{
         Uri newRow;
         context=mContext;
         ContentValues userValues=new ContentValues();
         userValues.put(UserDetailsColumns.FIRST_NAME,"Harleen");
         userValues.put(UserDetailsColumns.LAST_NAME,"Quinzel");
         userValues.put(UserDetailsColumns.EMAIL,"hq@email.com");
         userValues.put(UserDetailsColumns.PHONE,"00000001");
         userValues.put(UserDetailsColumns.IMAGE, R.mipmap.harleen);
         userValues.put(UserDetailsColumns.PASSWORD,"harleyquinn");
         userValues.put(UserDetailsColumns.PORTRAIT, R.drawable.harleen_quinzel);

         newRow=context.getContentResolver().insert(UserDetailsProvider.User.CONTENT_URI,userValues);

         userValues.put(UserDetailsColumns.FIRST_NAME,"Katherine");
         userValues.put(UserDetailsColumns.LAST_NAME,"Kane");
         userValues.put(UserDetailsColumns.EMAIL,"kk@email.com");
         userValues.put(UserDetailsColumns.PHONE,"00000002");
         userValues.put(UserDetailsColumns.IMAGE, R.mipmap.batwoman);
         userValues.put(UserDetailsColumns.PASSWORD,"batwoman");
         userValues.put(UserDetailsColumns.PORTRAIT, R.drawable.batwoman);

         newRow=context.getContentResolver().insert(UserDetailsProvider.User.CONTENT_URI,userValues);

         userValues.put(UserDetailsColumns.FIRST_NAME,"Selina");
         userValues.put(UserDetailsColumns.LAST_NAME,"Kyle");
         userValues.put(UserDetailsColumns.EMAIL,"sk@email.com");
         userValues.put(UserDetailsColumns.PHONE,"00000003");
         userValues.put(UserDetailsColumns.IMAGE, R.mipmap.catwoman);
         userValues.put(UserDetailsColumns.PASSWORD,"catwoman");
         userValues.put(UserDetailsColumns.PORTRAIT, R.drawable.catwoman);

         newRow=context.getContentResolver().insert(UserDetailsProvider.User.CONTENT_URI,userValues);
     }

 }
}
