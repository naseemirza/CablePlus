package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 26-Nov-18.
 */

public class OrderPageAdapter extends RecyclerView.Adapter<OrderPageAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<OrderPageModel> productList;
    //AsyncResult<Integer> asyncResult_addNewConnection;
  //  int contr=0;
    // , ,AsyncResult<Integer> asyncResult_addNewConnection
    public OrderPageAdapter(Context mCtx, List<OrderPageModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.asyncResult_addNewConnection= asyncResult_addNewConnection;
        //this.arraylist = arraylist;
    }

    @Override
    public OrderPageAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.orderpagelayout, null);
        return new OrderPageAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderPageAdapter.ProductViewHolder holder, final int position) {
        final OrderPageModel product = productList.get(position);

        holder.textViewTitle.setText(product.getName());
       // holder.textstock.setText(product.getStock());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=productList.get(position).getCount();
                i++;
                holder.countr.setText(String.valueOf(""+ i));
                productList.get(position).setCount(i);
                // Log.e("counter", String.valueOf(i));

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

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    // implements View.OnClickListener
    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewTitle;
        //TextView textstock;
        ImageView imageView;
        ImageButton add,remove;
        TextView countr;
        //TextView CartItem;


        //private RecyclerViewItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mCtx=itemView.getContext();

            textViewTitle = itemView.findViewById(R.id.prodname);
           // textstock = itemView.findViewById(R.id.prodstock1);
            add=itemView.findViewById(R.id.add);
            remove=itemView.findViewById(R.id.remov);
            imageView = itemView.findViewById(R.id.compid);
            countr=itemView.findViewById(R.id.editqnty);

            // itemView.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View v) {
//            this.itemClickListener.onClick(v,getLayoutPosition());
//
//        }
//
//        public void setItemClickListener(RecyclerViewItemClickListener ic)
//        {
//            this.itemClickListener=ic;
//
//        }
    }
}