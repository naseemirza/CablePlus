package solutions.thinkbiz.cableplus.sqlitebds;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import solutions.thinkbiz.cableplus.R;
import solutions.thinkbiz.cableplus.RecyclerViewItemClickListener;


/**
 * Created by User on 13-Dec-18.
 */
                                                //implements View.OnClickListener
public class MyHolder extends RecyclerView.ViewHolder  {

    TextView nameTxt;
    TextView priceTxt;
    ImageView img;
    ImageView deleteviw;
    ImageButton add,remove;


    //private RecyclerViewItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.prodname);
        priceTxt= (TextView) itemView.findViewById(R.id.editqnty);
        img= (ImageView) itemView.findViewById(R.id.compid);
        deleteviw= (ImageView) itemView.findViewById(R.id.deletebtn);
        add=(ImageButton)itemView.findViewById(R.id.add);
        remove=(ImageButton)itemView.findViewById(R.id.remov);


       // itemView.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View v) {
//        this.itemClickListener.onClick(v,getLayoutPosition());
//    }
//
//    public void setItemClickListener(RecyclerViewItemClickListener ic)
//    {
//        this.itemClickListener=ic;
//
//    }
}
