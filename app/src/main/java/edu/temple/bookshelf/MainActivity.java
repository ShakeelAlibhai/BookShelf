package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap> books = getBooks();
        BookListFragment bookListFragment = BookListFragment.newInstance(books);

        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.add(R.id.frame1, bookListFragment);
        t.commit();
    }

    private ArrayList<HashMap> getBooks() {
        ArrayList<HashMap> arrayList = new ArrayList<>();

        HashMap<String, String> book1 = new HashMap<String, String>();
        book1.put("Title 1", "Author 1");
        arrayList.add(book1);

        HashMap<String, String> book2 = new HashMap<String, String>();
        book1.put("Title 2", "Author 2");
        arrayList.add(book2);

        HashMap<String, String> book3 = new HashMap<String, String>();
        book1.put("Title 3", "Author 3");
        arrayList.add(book3);

        HashMap<String, String> book4 = new HashMap<String, String>();
        book1.put("Title 4", "Author 4");
        arrayList.add(book4);

        HashMap<String, String> book5 = new HashMap<String, String>();
        book1.put("Title 5", "Author 5");
        arrayList.add(book5);

        HashMap<String, String> book6 = new HashMap<String, String>();
        book1.put("Title 6", "Author 6");
        arrayList.add(book6);

        HashMap<String, String> book7 = new HashMap<String, String>();
        book1.put("Title 7", "Author 7");
        arrayList.add(book7);

        HashMap<String, String> book8 = new HashMap<String, String>();
        book1.put("Title 8", "Author 8");
        arrayList.add(book8);

        HashMap<String, String> book9 = new HashMap<String, String>();
        book1.put("Title 9", "Author 9");
        arrayList.add(book9);

        HashMap<String, String> book10 = new HashMap<String, String>();
        book1.put("Title 10", "Author 10");
        arrayList.add(book10);

        return arrayList;
    }

    @Override
    public void bookSelected() {

    }
}
