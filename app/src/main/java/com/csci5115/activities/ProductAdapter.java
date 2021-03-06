package com.csci5115.activities;

/**
 * Created by karanjaswani on 1/12/18.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<ProductViewHolder> mViewHolders;

    ArrayList<Boolean> isUps;
    ArrayList<Boolean> isDowns;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Product> productList;

    public ImageButton mBtSongUpvote;
    public ImageButton mBtSongDownvote;



    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        mViewHolders = new ArrayList<>();

        isUps = new ArrayList<>();
        isDowns = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            isDowns.add(false);
            isUps.add(false);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        ProductViewHolder mViewHolder = new ProductViewHolder(view);
        mViewHolders.add(mViewHolder);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        //holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.textViewVotes.setText(String.valueOf(""+product.getVotes()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRating, textViewPrice, textViewVotes;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.song_name);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.song_artist);
            textViewPrice = itemView.findViewById(R.id.song_time);
            textViewVotes = itemView.findViewById(R.id.song_votes);
            imageView = itemView.findViewById(R.id.imageView);
            mBtSongUpvote = (ImageButton) itemView.findViewById(R.id.upvote);
            mBtSongDownvote = (ImageButton) itemView.findViewById(R.id.downvote);
            mBtSongUpvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    upvoteAt(getPosition());
                }
            });
            mBtSongDownvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downvoteAt(getPosition());
                }
            });
        }
    }

    public void upvoteAt(int position) {
//        mValues.remove(position);
//        notifyItemRemoved(position);
        if (isUps.get(position)){
            return;
        }
        isUps.set(position, true);

        int pos = position;
        if (position-1 >= 0 && productList.get(pos).votes + 1 > productList.get(pos-1).votes) {
            notifyItemMoved(pos, pos-1);
            Collections.swap(productList, pos, pos-1);
            Collections.swap(mViewHolders, pos, pos-1);
            Collections.swap(isUps, pos, pos-1);
            Collections.swap(isDowns, pos, pos-1);
            pos = pos - 1;
        }

        productList.get(pos).votes += 1;
        mViewHolders.get(pos).textViewVotes.setText(String.valueOf(""+productList.get(pos).getVotes()));
//        notifyItemRangeChanged(position, mValues.size());
    }

    public void downvoteAt(int position) {
        if (isDowns.get(position)){
            return;
        }
        isDowns.set(position, true);
//        mValues.remove(position);
//        notifyItemRemoved(position);
        int pos = position;
        if (position+1 <= productList.size()-1 && productList.get(pos).votes - 1 < productList.get(pos+1).votes) {
            notifyItemMoved(position, position+1);
            Collections.swap(productList, position, position+1);
            Collections.swap(mViewHolders, position, position+1);
            Collections.swap(isUps, pos, pos+1);
            Collections.swap(isDowns, position, position+1);
            pos = pos + 1;
        }
        if (productList.get(pos).votes > 1){
            productList.get(pos).votes -= 1;
        }
        mViewHolders.get(pos).textViewVotes.setText(String.valueOf(""+productList.get(pos).getVotes()));
        //holder.textViewVotes.setText(String.valueOf(""+product.getVotes()));

//        notifyItemRangeChanged(position, mValues.size());
    }
}
