package com.example.shaunakbasu.finalproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by shaunak basu on 21-08-2016.
 */

@ContentProvider(authority = EnglishProvider.AUTHORITY, database = EnglishDatabase.class)
public class EnglishProvider {

    public static final String AUTHORITY = "com.example.shaunakbasu.finalproject.data.EnglishProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String ENGLISH = "english";
    }

    private static Uri buildUri(String... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path:paths){
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = EnglishDatabase.ENGLISH)
    public static class English{
        @ContentUri(
                path = Path.ENGLISH,
                type = "vnd.android.cursor.dir/user"
        )
        public static final Uri CONTENT_URI = buildUri(Path.ENGLISH);

        @InexactContentUri(
                name = "ENGLISH_ID",
                path = Path.ENGLISH + "/*",
                type = "vnd.android.cursor.item/user",
                whereColumn = EnglishColumns._ID,
                pathSegment = 1
        )
        public static Uri withID(int id){
            return buildUri(Path.ENGLISH, Integer.toString(id));
        }
    }
}
