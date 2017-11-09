package com.example.hp.cabpool_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class BookCabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cab);
        ArrayList<Book> Books = new ArrayList<Book>();
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));
        Books.add(new Book("Deepak Auto","Rs 400"));

        BooksAdapter booksAdapter = new BooksAdapter(this,Books);
        ListView listView = (ListView) findViewById(R.id.books_list);
        listView.setAdapter(booksAdapter);
    }
}
