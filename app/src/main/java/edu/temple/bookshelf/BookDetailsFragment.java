package edu.temple.bookshelf;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {


    public BookDetailsFragment() {
        // Required empty public constructor
    }

    Book book;
    TextView titleView;
    TextView authorView;

    final static String BOOK_DETAILS_KEY = "book_details_key";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            int id = bundle.getInt("id");
            String title = bundle.getString("title");
            String author = bundle.getString("author");
            String coverURL = bundle.getString("coverURL");
            book = new Book(id, title, author, coverURL);
//            book = (Book)bundle.getSerializable(BOOK_DETAILS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);

        titleView = v.findViewById(R.id.titleView);
        authorView = v.findViewById(R.id.authorView);

        if(book != null) {
            displayBook(book);
        }

        return v;
    }

    public static BookDetailsFragment newInstance(int id, String title, String author, String coverURL) {
        BookDetailsFragment newFragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);
        bundle.putString("author", author);
        bundle.putString("coverURL", coverURL);
//        bundle.putSerializable(BOOK_DETAILS_KEY, book);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    public void displayBook(Book book) {
        titleView.setText(book.getTitle());
        authorView.setText(book.getAuthor());
    }

}
