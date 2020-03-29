package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    ArrayList<Book> books;
    boolean twoPanes;
    BookDetailsFragment bookDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.detailsFrame) != null);

        books = getBooks();
        BookListFragment bookListFragment = BookListFragment.newInstance(books);

        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.add(R.id.frame1, bookListFragment);

        if(twoPanes) {
            bookDetailsFragment = new BookDetailsFragment();
            t.add(R.id.detailsFrame, bookDetailsFragment);
        }

        t.commit();

        f.executePendingTransactions();
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

        Book toPass = books.get(index);

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
