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

import java.util.Scanner;

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
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(START_YEAR);
        graph.getViewport().setMaxX(END_YEAR);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(MAX_RANK);

        // read example.sql into database named "example"
        /*SQLiteDatabase db = openOrCreateDatabase("babynames");
        Scanner scan = new Scanner(getResources()
                .openRawResource(R.raw.babynames));

        String query = "";
        while (scan.hasNextLine()) { // build and execute queries
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                db.execSQL(query);
                query = "";
            }
        }*/
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
        GraphView graph = (GraphView) findViewById(R.id.graph);
        EditText edit = (EditText) findViewById(R.id.name);
        String name = edit.getText().toString();
        Switch sexSwitch = (Switch) findViewById(R.id.sex);
        String sex = sexSwitch.isChecked() ? "F" : "M";

        // TODO
        /* Database name = "babynames"
         * Tables:
         *   ranks: name,   sex, year, rank
         *         "Martin, "M", 1880, 145
         */

        String query =  "SELECT * " +
                        "FROM ranks " +
                        "WHERE name = \"" + name + "\" " +
                        "AND sex = \"" + sex + "\"";

        SQLiteDatabase db = openOrCreateDatabase("babynames");
        Cursor cr = db.rawQuery(query, null);

        LineGraphSeries<DataPoint> line = new LineGraphSeries<>();

        try {
            for (SimpleRow row : SimpleDatabase.rows(cr)) {
                int year = row.get("name");
                int rank = row.get("rank");

                DataPoint point = new DataPoint(year, rank);
                line.appendData(point, false, 13);
            }
        } catch (Exception e) {
            toast("There was an error in your query");
        }

        graph.addSeries(line);

    }
}
