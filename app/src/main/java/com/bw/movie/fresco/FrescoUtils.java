package com.bw.movie.fresco;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoUtils {
    public static void setPic(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }
}
