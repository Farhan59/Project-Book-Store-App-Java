package edu.ewubd.projectbookstoreapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String imageUrl;
    private String title;
    private String author;
    private double rating;
    private double price;
    private String description;
    private String publishdate;

    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(Book.class)
    }

    public Book(String imageUrl, String title, String author, double rating, double price, String description, String publishdate) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.price = price;
        this.description = description;
        this.publishdate = publishdate;
    }

    // Getters
    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishdate() {
        return publishdate;
    }

    // Parcelable implementation
    protected Book(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
        author = in.readString();
        rating = in.readDouble();
        price = in.readDouble();
        description = in.readString();
        publishdate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeDouble(rating);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeString(publishdate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
