package com.example.shaunakbasu.finalproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by shaunak basu on 21-08-2016.
 */
@Database(version = RegisteredCoursesDatabase .VERSION)
public class RegisteredCoursesDatabase {

    private RegisteredCoursesDatabase (){}

    public static final int VERSION = 1;

    @Table(RegisteredCoursesColumns.class) public static final String REGISTERED_COURSES = "registered_courses";
}
