package edu.temple.bookshelf;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {


    public BookDetailsFragment() {
        // Required empty public constructor
    }

    HashMap<String, String> book;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            book = (HashMap)bundle.getSerializable("book_details_key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    public static BookDetailsFragment newInstance(HashMap book) {
        BookDetailsFragment newFragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("book_details_key", book);
        newFragment.setArguments(bundle);
        return newFragment;
    }

}
