package solutions.thinkbiz.cableplus.History;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;
import solutions.thinkbiz.cableplus.R;

/**
 * Created by User on 28-Dec-18.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<HistoryModel> productList;

    public HistoryAdapter(Context mCtx, List<HistoryModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public HistoryAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.historylayout, null);
        return new HistoryAdapter.ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final HistoryAdapter.ProductViewHolder holder, final int position) {
        final HistoryModel product = productList.get(position);

        holder.textViewTitle.setText(product.getName());
        holder.textqty.setText("    :   "+product.getQuantity());
        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Glide.with(mCtx)
                .load(product.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        ImageView imageView;
        TextView textqty;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mCtx=itemView.getContext();

            textViewTitle = itemView.findViewById(R.id.prodname);
            textqty = itemView.findViewById(R.id.prodqty);
            imageView = itemView.findViewById(R.id.compid);
        }

    }

}
