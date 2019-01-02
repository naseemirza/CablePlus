package solutions.thinkbiz.cableplus.sqlitebds;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import solutions.thinkbiz.cableplus.R;


/**
 * Created by User on 13-Dec-18.
 */

public class PicassoClient {

    public static void loadImage(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.splsh1).into(img);
        }else {
            Picasso.with(c).load(R.drawable.splsh1).into(img);
        }
    }
}
