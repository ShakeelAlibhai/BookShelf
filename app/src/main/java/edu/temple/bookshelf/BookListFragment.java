package edu.temple.bookshelf;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {


    public BookListFragment() {
        // Required empty public constructor
    }

    BookSelectedInterface parentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof BookSelectedInterface) {
            parentActivity = (BookSelectedInterface)context;
        } else {
            throw new RuntimeException("Please implement BookSelectedInterface!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView view = (ListView)inflater.inflate(R.layout.fragment_book_list, container, false);
        return view;
    }

    public static BookListFragment newInstance(ArrayList<HashMap> books) {
        BookListFragment newFragment = new BookListFragment();
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
        return newFragment;
    }

    interface BookSelectedInterface {
        public void bookSelected();
    }

}
