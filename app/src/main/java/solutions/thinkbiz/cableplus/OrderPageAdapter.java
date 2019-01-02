package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import solutions.thinkbiz.cableplus.ProductListing.SQLiteHelper;

/**
 * Created by User on 26-Nov-18.
 */

public class OrderPageAdapter extends RecyclerView.Adapter<OrderPageAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<OrderPageModel> productList;
    public static SQLiteHelper sqLiteHelper;

    public OrderPageAdapter(Context mCtx, List<OrderPageModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public OrderPageAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.orderpagelayout, null);
        return new OrderPageAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderPageAdapter.ProductViewHolder holder, final int position) {
        final OrderPageModel product = productList.get(position);

        holder.textViewTitle.setText(product.getName());
         holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = productList.get(position).getQuantity();
                i++;
                holder.countr.setText(String.valueOf("" + i));
                productList.get(position).setQuantity(i);

            }

        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = productList.get(position).getQuantity();

                if (i > 1) {
                    i--;
                    holder.countr.setText(String.valueOf("" + i));
                    productList.get(position).setQuantity(i);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;
        ImageButton add, remove;
        TextView countr;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mCtx = itemView.getContext();
            textViewTitle = itemView.findViewById(R.id.prodname);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remov);
            imageView = itemView.findViewById(R.id.compid);
            countr = itemView.findViewById(R.id.editqnty);


        }
    }

}