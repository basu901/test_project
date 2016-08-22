package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class EnglishColumns {

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TP_NAME = "tp_name";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TP_SRC = "tp_src";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String PARENT = "parent";

}
