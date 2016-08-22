package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by shaunak basu on 14-08-2016.
 */
public class UserDetailsColumns {

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String EMAIL = "email";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String PASSWORD = "password";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String FIRST_NAME = "first_name";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String LAST_NAME = "last_name";
    @DataType(DataType.Type.INTEGER)
    public static final String PHONE = "phone";
    @DataType(DataType.Type.INTEGER)
    public static final String IMAGE = "image";
    @DataType(DataType.Type.INTEGER)
    public static final String PORTRAIT = "portrait";

}
