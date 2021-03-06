package edu.temple.bookshelf;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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
    ArrayList<HashMap> books;

    final static String BOOK_LIST_KEY = "book_list_key";

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            books = (ArrayList<HashMap>)bundle.getSerializable(BOOK_LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView view = (ListView)inflater.inflate(R.layout.fragment_book_list, container, false);

        BookAdapter ba = new BookAdapter(this.getContext(), books);
        view.setAdapter(ba);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.bookSelected(position);
            }
        });

        return view;
    }

    public static BookListFragment newInstance(ArrayList<HashMap> books) {
        BookListFragment newFragment = new BookListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_LIST_KEY, books);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    interface BookSelectedInterface {
        public void bookSelected(int index);
    }

    //Shakeel Alibhai
    //CIS 3515

}
