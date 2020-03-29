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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    ArrayList<Book> booksToDisplay;
    boolean twoPanes;
    BookDetailsFragment bookDetailsFragment;
    BookListFragment bookListFragment;

    EditText textView;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.detailsFrame) != null);

        booksToDisplay = getBooks();

        if(savedInstanceState != null) {
            booksToDisplay = (ArrayList<Book>)savedInstanceState.getSerializable("key");
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
                String searchTerm = textView.getText().toString();
                booksToDisplay = new ArrayList<>();

                ArrayList<Book> allBooks = getBooks();
                for(int i = 0; i < allBooks.size(); i++) {
                    Book currentBook = allBooks.get(i);
                    if(currentBook.getTitle().contains(searchTerm)) {
                        booksToDisplay.add(currentBook);
                    } else if(currentBook.getAuthor().contains(searchTerm)) {
                        booksToDisplay.add(currentBook);
                    }
                }

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

        super.onSaveInstanceState(outState, outPersistentState);
    }

    private ArrayList<Book> getBooks() {
        ArrayList<Book> arrayList = new ArrayList<>();
//        ArrayList<HashMap> arrayList = new ArrayList<>();

//        HashMap<String, String> book1 = new HashMap<String, String>();
//        book1.put("Title 1", "Author 1");
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
