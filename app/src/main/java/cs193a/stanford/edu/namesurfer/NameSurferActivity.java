/*
 * CS 193A, Marty Stepp
 * This program performs a query of a 'babynames' database to find out
 * all rankings for a given baby name and draws them as a graph.
 * In today's lecture we improved the program by adding a graph of the
 * data using a library we found online called Android GraphView.
 */

package cs193a.stanford.edu.namesurfer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.*;
import stanford.androidlib.*;
import stanford.androidlib.data.*;

public class NameSurferActivity extends SimpleActivity {
    private static final int START_YEAR = 1880;
    private static final int END_YEAR = 2010;
    private static final int MAX_RANK = 1000;

    /* Runs when activity is created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_surfer);
        handleEnterKeyPress(findEditText(R.id.name));

        GraphView graph = (GraphView) findViewById(R.id.graph);
    }

    // Called when the user presses Enter on the text box. Runs a query.
    @Override
    public void onEnterKeyPress(View editText) {
        doQuery();
    }

    // Called when the user clicks the Search button. Runs a query.
    public void searchClick(View view) {
        doQuery();
    }

    // This method performs a query on the babynames database
    // for the given name/sex and prints the results.
    private void doQuery() {
        EditText edit = (EditText) findViewById(R.id.name);
        Switch sexSwitch = (Switch) findViewById(R.id.sex);

        // TODO
    }
}
