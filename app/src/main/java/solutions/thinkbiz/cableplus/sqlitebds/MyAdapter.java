package solutions.thinkbiz.cableplus.sqlitebds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import solutions.thinkbiz.cableplus.R;


/**
 * Created by User on 13-Dec-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<TVShow> tvShows;
    DBAdapter adapter;

    //static int i;

    public MyAdapter(Context c, ArrayList<TVShow> tvShows) {
        this.c = c;
        this.tvShows = tvShows;

    }


    //INITIALIZE VH
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderpagelayout,parent,false);
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final String pid=tvShows.get(position).getPid();
        final String name=tvShows.get(position).getName();
        final String url=tvShows.get(position).getImageUrl();
        final String price=tvShows.get(position).getPrice();

        holder.nameTxt.setText(tvShows.get(position).getName());
        holder.priceTxt.setText(tvShows.get(position).getPrice());
        PicassoClient.loadImage(c,tvShows.get(position).getImageUrl(),holder.img);




//        Glide.with(c)
//                .load(tvShows.get(position).getImageUrl())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .fitCenter()
//                .into(holder.img);



        holder.deleteviw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvShows.get(position);
                adapter = new DBAdapter(c);
                adapter.deleteItem(pid); // set Dynamic
                tvShows.remove(position);
                notifyDataSetChanged();
                Toast.makeText(c, "Deleted successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               int qty= Integer.parseInt(tvShows.get(position).getPrice());
                 qty= qty+1;

              //  Log.e("finali", String.valueOf(rid));
                DBAdapter db = new DBAdapter(c);
                db.openDB();

                long result = db.UpdateItem(pid, String.valueOf(qty));
                if (result == 1) {
                    holder.priceTxt.setText(String.valueOf(""+ qty));
                    tvShows.get(position).setPrice(String.valueOf(qty));
                    Toast.makeText(c, "Updated successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(c, "Not Updated", Toast.LENGTH_SHORT).show();
                }
              db.closeDB();
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int qty1 = Integer.parseInt(tvShows.get(position).getPrice());

               if (qty1>1) {
                   qty1 = qty1 - 1;

                   DBAdapter db = new DBAdapter(c);
                   db.openDB();

                   long result = db.UpdateItem(pid, String.valueOf(qty1));

                   if (result == 1) {
                       holder.priceTxt.setText(String.valueOf("" + qty1));
                       tvShows.get(position).setPrice(String.valueOf(qty1));
                       Toast.makeText(c, "Updated successfully!", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(c, "Not Updated", Toast.LENGTH_SHORT).show();
                   }
                   db.closeDB();


//                if (finali > 1) {
//                    finali=finali-1;
//                    holder.priceTxt.setText(String.valueOf("" + finali));
//                    tvShows.get(position).setPrice(String.valueOf(finali));
//
//                }
               }
            }
        });
    }

    //TOTAL NUM TVSHOWS
    @Override
    public int getItemCount() {
        return tvShows.size();


    }

}
