package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by shaunak basu on 21-08-2016.
 */

@Database(version = EnglishDatabase.VERSION)
public class EnglishDatabase {

    private EnglishDatabase(){}

    public static final int VERSION = 1;

    @Table(EnglishColumns.class) public static final String ENGLISH = "english";
}
