package com.example.shaunakbasu.finalproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by shaunak basu on 21-08-2016.
 */
@ContentProvider(authority = RegisteredCoursesProvider.AUTHORITY, database = RegisteredCoursesDatabase.class)
public class RegisteredCoursesProvider {
    public static final String AUTHORITY = "com.example.shaunakbasu.finalproject.data.RegisteredCoursesProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String REGISTERED_COURSES = "registered_courses";
    }

    private static Uri buildUri(String... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path:paths){
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = RegisteredCoursesDatabase.REGISTERED_COURSES)
    public static class Registered_Courses{
        @ContentUri(
                path = Path.REGISTERED_COURSES,
                type = "vnd.android.cursor.dir/user"
        )
        public static final Uri CONTENT_URI = buildUri(Path.REGISTERED_COURSES);

        @InexactContentUri(
                name = "REGISTERED_COURSES_ID",
                path = Path.REGISTERED_COURSES + "/*",
                type = "vnd.android.cursor.item/user",
                whereColumn = RegisteredCoursesColumns._ID,
                pathSegment = 1
        )
        public static Uri withID(int id){
            return buildUri(Path.REGISTERED_COURSES, Integer.toString(id));
        }
    }
}

