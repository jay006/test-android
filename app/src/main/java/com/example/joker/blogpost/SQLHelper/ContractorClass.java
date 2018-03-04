package com.example.joker.blogpost.SQLHelper;

import android.provider.BaseColumns;

/**
 * Created by joker on 4/3/18.
 */

public class ContractorClass {

    public final class BlogPost implements BaseColumns{

        public static final String TABLE_NAME = "post";
        public static final String USER_NAME = "userName";
        public static final String IMAGE_URI = "imageUri";
        public static final String POST_TITLE = "postTitle";

    }

}
