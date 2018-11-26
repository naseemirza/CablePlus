package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20-Nov-18.
 */

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<ProdModel> productList;
    AsyncResult<Integer> asyncResult_addNewConnection;
   // int i;
                                                                // , ArrayList<String> arraylist
    public ProdAdapter(Context mCtx, List<ProdModel> productList,AsyncResult<Integer> asyncResult_addNewConnection) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.asyncResult_addNewConnection= asyncResult_addNewConnection;
        //this.arraylist = arraylist;
    }

    @Override
    public ProdAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.productslist, null);
        return new ProdAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProdAdapter.ProductViewHolder holder, final int position) {
       final ProdModel product = productList.get(position);

        holder.textViewTitle.setText(product.getName());
        holder.textstock.setText(product.getStock());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        String[] users1 = new String[]{
                "Spool","Spool 2","Spool 3","Spool 3",
                "Spool 5","Spool 6","Spool 7","Spool 8"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mCtx,R.layout.cardspinneritem, users1);
        holder.spool.setAdapter(adapter);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=productList.get(position).getCount();
                i++;
                holder.countr.setText(String.valueOf(""+ i));
                productList.get(position).setCount(i);
                Log.e("counter", String.valueOf(i));

            }

        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=productList.get(position).getCount();

                if (i>1) {
                    i--;
                    holder.countr.setText(String.valueOf("" + i));
                    productList.get(position).setCount(i);
                }

            }
        });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //SharedPreferences pref = v.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
               // SharedPreferences.Editor edit = pref.edit();
                //edit.putString("qty", String.valueOf(i));
                asyncResult_addNewConnection.success(position);

                //contr++;
                //holder.CartItem.setText(String.valueOf(contr));
            }
        });

//        holder.setItemClickListener(new RecyclerViewItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    // implements View.OnClickListener
    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView textViewTitle;
        TextView textstock;
        ImageView imageView;
        ImageButton add,remove;
        TextView countr;
        Spinner spool;
        Button addtocart;
        //TextView CartItem;


        private RecyclerViewItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mCtx=itemView.getContext();

            textViewTitle = itemView.findViewById(R.id.prodname);
            textstock = itemView.findViewById(R.id.prodstock1);
            add=itemView.findViewById(R.id.add);
            remove=itemView.findViewById(R.id.remov);
            imageView = itemView.findViewById(R.id.compid);
            countr=itemView.findViewById(R.id.editqnty);
            spool=itemView.findViewById(R.id.spinnerSpool);
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

