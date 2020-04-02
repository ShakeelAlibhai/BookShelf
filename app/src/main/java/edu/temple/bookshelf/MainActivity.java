package edu.temple.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    ArrayList<Book> booksToDisplay;
    boolean twoPanes;
    BookDetailsFragment bookDetailsFragment;
    BookListFragment bookListFragment;

    EditText textView;
    Button searchButton;

    int currentBookId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.detailsFrame) != null);

        booksToDisplay = getBooks();
        
        if(savedInstanceState != null) {
            booksToDisplay = (ArrayList<Book>)savedInstanceState.getSerializable("key");
            currentBookId = savedInstanceState.getInt("currentBookId");

            //Attempt to display the last-displayed book
            if(currentBookId != 0) {
                bookSelected(currentBookId);
            }
        }

        bookListFragment = BookListFragment.newInstance(booksToDisplay);

        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.add(R.id.frame1, bookListFragment);

        if(twoPanes) {
            bookDetailsFragment = new BookDetailsFragment();
            t.add(R.id.detailsFrame, bookDetailsFragment);
        }

        t.commit();

        f.executePendingTransactions();

        textView = (EditText)findViewById(R.id.searchField);
        searchButton = (Button)findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Search books for the search term
                String searchTerm = textView.getText().toString();

                booksToDisplay.clear();
//                booksToDisplay = new ArrayList<>();

                ArrayList<Book> allBooks = getBooks();
                for(int i = 0; i < allBooks.size(); i++) {
                    Book currentBook = allBooks.get(i);
                    if(currentBook.getTitle().contains(searchTerm)) {
                        booksToDisplay.add(currentBook);
                    } else if(currentBook.getAuthor().contains(searchTerm)) {
                        booksToDisplay.add(currentBook);
                    }
                }

//                bookListFragment.updateBooks(booksToDisplay);

                FragmentManager f = getSupportFragmentManager();
                FragmentTransaction t = f.beginTransaction();

                t.addToBackStack(null).replace(R.id.frame1, BookListFragment.newInstance(booksToDisplay));

                t.commit();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable("key", booksToDisplay);
        outState.putInt("currentBookId", currentBookId);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    private ArrayList<Book> getBooks() {
        final ArrayList<Book> arrayList = new ArrayList<>();

        arrayList.add(new Book(1, "Title 1", "Author 1", ""));
        arrayList.add(new Book(2, "Title 2", "Author 2", ""));
        arrayList.add(new Book(3, "Title 3", "Author 3", ""));
        arrayList.add(new Book(4, "Title 4", "Author 4", ""));
        arrayList.add(new Book(5, "Title 5", "Author 5", ""));
        arrayList.add(new Book(6, "Title 6", "Author 6", ""));
        arrayList.add(new Book(7, "Title 7", "Author 7", ""));
        arrayList.add(new Book(8, "Title 8", "Author 8", ""));
        arrayList.add(new Book(9, "Title 9", "Author 9", ""));
        arrayList.add(new Book(10, "Title 10", "Author 10", ""));

        String url = "https://kamorris.com/lab/abp/booksearch.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "" + response.length(), Toast.LENGTH_LONG).show();
                        for(int i = 0; i < response.length(); i++) {
//                            arrayList.add(i);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        return arrayList;
    }

    //Shakeel Alibhai
    //CIS 3515

    @Override
    public void bookSelected(int index) {
        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction t = f.beginTransaction();

        Book toPass = getBooks().get(index);

        if(twoPanes) {
            bookDetailsFragment.displayBook(toPass);
        } else {
            currentBookId = toPass.getID();
            t.addToBackStack(null).replace(R.id.frame1, BookDetailsFragment.newInstance(
                    toPass.getID(),
                    toPass.getTitle(),
                    toPass.getAuthor(),
                    toPass.getCoverURL()
            ));
        }
        t.commit();
    }
}
