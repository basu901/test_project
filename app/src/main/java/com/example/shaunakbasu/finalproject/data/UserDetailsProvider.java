package com.example.shaunakbasu.finalproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by shaunak basu on 14-08-2016.
 */
@ContentProvider(authority = UserDetailsProvider.AUTHORITY, database = UserDetailsDatabase.class)
    public class UserDetailsProvider {
        public static final String AUTHORITY = "com.example.shaunakbasu.finalproject.data.UserDetailsProvider";

        static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

        interface Path{
            String USER = "user";
        }

        private static Uri buildUri(String... paths){
            Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
            for (String path:paths){
                builder.appendPath(path);
            }
            return builder.build();
        }

        @TableEndpoint(table = UserDetailsDatabase.USER)
        public static class User{
            @ContentUri(
                    path = Path.USER,
                    type = "vnd.android.cursor.dir/user"
            )
            public static final Uri CONTENT_URI = buildUri(Path.USER);

            @InexactContentUri(
                    name = "USER_ID",
                    path = Path.USER + "/*",
                    type = "vnd.android.cursor.item/user",
                    whereColumn = UserDetailsColumns._ID,
                    pathSegment = 1
            )
            public static Uri withID(int id){
                return buildUri(Path.USER, Integer.toString(id));
            }
        }
    }

