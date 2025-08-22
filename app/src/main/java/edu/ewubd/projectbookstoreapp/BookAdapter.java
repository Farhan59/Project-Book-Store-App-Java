package edu.ewubd.projectbookstoreapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private OnItemClickListener onItemClickListener;

    public BookAdapter(List<Book> bookList, OnItemClickListener onItemClickListener) {
        this.bookList = bookList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvAuthorName.setText("Author: " + book.getAuthor());
        holder.tvRating.setText(String.valueOf("Rating : "+book.getRating()));
        holder.tvPrice.setText(String.valueOf("৳"+book.getPrice())+" Taka");
        Glide.with(holder.itemView.getContext()).load(book.getImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvBookTitle, tvAuthorName, tvRating, tvPrice;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
