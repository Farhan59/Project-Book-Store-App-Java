package edu.ewubd.projectbookstoreapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BuyBookAdapter extends RecyclerView.Adapter<BuyBookAdapter.BuyBookViewHolder> {

    private List<Book> bookList;

    public BuyBookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BuyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new BuyBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyBookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvBookAuthorName.setText("Author: " + book.getAuthor());
        holder.tvBookPrice.setText("৳" + book.getPrice());
        Glide.with(holder.itemView.getContext()).load(book.getImageUrl()).into(holder.imgCover);

        // Hide the "Remove" button
        holder.btnRemoveBook.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BuyBookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView tvBookTitle, tvBookAuthorName, tvBookPrice;
        Button btnRemoveBook;

        public BuyBookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthorName = itemView.findViewById(R.id.tvBookAuthorName);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            btnRemoveBook = itemView.findViewById(R.id.btnRemoveBook);
        }
    }
}