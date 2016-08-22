package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class RegisteredCoursesColumns {

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String C_NAME= "course_name";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String C_DESC = "course_desc";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String C_DUR = "course_duration";
    @DataType(DataType.Type.INTEGER) @NotNull
    public static final String C_IMAGE = "course_image";
    @DataType(DataType.Type.INTEGER) @NotNull
    public static final String USER_ID = "user_id";

}
