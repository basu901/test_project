package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by shaunak basu on 14-08-2016.
 */

@Database(version = UserDetailsDatabase.VERSION)
public class UserDetailsDatabase {

    private UserDetailsDatabase(){}

    public static final int VERSION = 1;

    @Table(UserDetailsColumns.class) public static final String USER = "user";
}
