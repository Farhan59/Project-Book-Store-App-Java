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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Book> cartList;

    public CartAdapter(List<Book> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Book book = cartList.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvBookAuthorName.setText("Author: " + book.getAuthor());
        holder.tvBookPrice.setText("Price: ৳" + book.getPrice());
        Glide.with(holder.itemView.getContext()).load(book.getImageUrl()).into(holder.imgCover);
        holder.btnRemoveBook.setOnClickListener(v -> {
            // Implement logic to remove the book from the cart
            cartList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView tvBookTitle, tvBookAuthorName, tvBookPrice;
        Button btnRemoveBook;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthorName = itemView.findViewById(R.id.tvBookAuthorName);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            btnRemoveBook = itemView.findViewById(R.id.btnRemoveBook);
        }
    }
}
