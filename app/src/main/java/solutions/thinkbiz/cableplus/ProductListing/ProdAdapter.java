package solutions.thinkbiz.cableplus.ProductListing;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import solutions.thinkbiz.cableplus.AsyncResult;
import solutions.thinkbiz.cableplus.R;
import solutions.thinkbiz.cableplus.RecyclerViewItemClickListener;
import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;


/**
 * Created by User on 20-Nov-18.
 */

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<ProdModel> productList;
    private AsyncResult<Integer> asyncResult_addNewConnection;
     static int i;
     String Pidib;
    String qtydb,namedb,urldb;
    String pricedb;

    public ProdAdapter(Context mCtx, List<ProdModel> productList,AsyncResult<Integer> asyncResult_addNewConnection) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.asyncResult_addNewConnection= asyncResult_addNewConnection;
    }

    @Override
    public ProdAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.productslist, null);
        return new ProdAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProdAdapter.ProductViewHolder holder, final int position) {
       final ProdModel product = productList.get(position);

        holder.textViewTitle.setText(product.getName());
        holder.textstock.setText("    :   "+product.getStock());
        holder.textspool.setText("    :   "+product.getSpool());
        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Glide.with(mCtx)
                .load(product.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.imageView);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 i= Integer.parseInt(productList.get(position).getCount());
                i++;
                holder.countr.setText(String.valueOf(""+ i));
                productList.get(position).setCount(String.valueOf(i));
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 i= Integer.parseInt(productList.get(position).getCount());
                if (i>1) {
                    i--;
                    holder.countr.setText(String.valueOf("" + i));
                    productList.get(position).setCount(String.valueOf(i));
                }
            }
        });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Pid = product.getPid();
                String name = product.getName();
                String qty = product.getCount();
                String image = product.getImage();

                DBAdapter db = new DBAdapter(mCtx);
                db.openDB();

                Cursor c=null;
                 c = db.getTVShows();

                    while (c.moveToNext()) {
                        namedb = c.getString(1);
                        urldb = c.getString(2);
                        Pidib = c.getString(4);
                        qtydb = c.getString(3);

                        if (Pid.equals(Pidib)||(qty.equals(qtydb))) {
                            long result = db.ReplaceItem(name, image, qty, Pid);
                            if (result == 1) {
                                Toast.makeText(mCtx, "Updated successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mCtx, "Not Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                if (!Pid.equals(Pidib))  {
                        asyncResult_addNewConnection.success(position, qty);
                        asyncResult_addNewConnection.SendDataMethod(name, image, qty, Pid);
                    }
                db.closeDB();
            }

        });

    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // implements View.OnClickListener
    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView textViewTitle;
        TextView textstock;
        TextView textspool;
        ImageView imageView;
        ImageButton add,remove;
        TextView countr;
        Button addtocart;

        private RecyclerViewItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mCtx=itemView.getContext();

            textViewTitle = itemView.findViewById(R.id.prodname);
            textstock = itemView.findViewById(R.id.prodstock1);
            textspool = itemView.findViewById(R.id.spooltext);
            add=itemView.findViewById(R.id.add);
            remove=itemView.findViewById(R.id.remov);
            imageView = itemView.findViewById(R.id.compid);
            countr=itemView.findViewById(R.id.editqnty);
            addtocart=itemView.findViewById(R.id.Addtocart);
           // CartItem=itemView.findViewById(R.id.cartcounter);
             itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onClick(v,getLayoutPosition());
        }
        public void setItemClickListener(RecyclerViewItemClickListener ic)
        {
            this.itemClickListener=ic;

        }
    }
}

