package com.example.gameoflife;
/*
 * Uzair Foolat
 * Game of Life
 * BBC Assessment
 * 04/02/2019
 * Android Studio
 */

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView currentState;
    GridView gridView;
    Cells cells;
    String[] chars = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    List<String> grid = new ArrayList<>();
    Button state;
    Button reset;
    Button run;
    boolean running = false;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        title.setText("Game of Life");
        state = findViewById(R.id.state);
        state.setText("Next State");
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextState();
            }
        });
        reset = findViewById(R.id.reset);
        reset.setText("Reset Grid");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cells.liveCells.isEmpty()) {
                    resetGrid();
                } else {
                    AlertBox("Error.", "The grid doesn't need to be reset.");
                }
            }
        });
        run = findViewById(R.id.run);
        run.setText("Run X");
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    runDialog();
                    running = true;
                } else {
                    run(0);
                    running = false;
                }
            }
        });
        currentState = findViewById(R.id.current);
        currentState.setText("Current State: " + current);
        setGrid(); //sets the grid on creation
    }

    private void setList() {
        for (String i : chars) {
            grid.add(i);
        }
    }

    private void nextState() { //evolves the game by one state at a time.
        current++;
        currentState.setText("Current State: " + current); //increments the state count and displays it
        cells.getNeighbours(); //calls the getneighbours method to initialise values
        int neighbours = cells.neighbours;
        int gap = cells.getGap(); //finds out which cell needs to be filled in
        boolean spec = false; //not a special case on call
        if (cells.liveCells.isEmpty()) {
            return;
        }
        if (cells.c > 14) {
            spec = true; //cases above 14 are special cases
            cells.handleSpecial();
        }
        if (neighbours == 3 && !cells.stable && !spec) {
            cells.handleState(gap); //fills in the gap with the int returned from the getgap method
        }
        if (!cells.stable && !spec && neighbours <= 2 || !cells.stable && !spec && cells.neighbours > 3) {
            cells.handleDeath(cells.liveCells.get(0)); //kills the cells if the neighbour count is insufficient
        }
    }

    private void setGrid() { //sets the grid values
        gridView = findViewById(R.id.cells);
        setList();
        cells = new Cells(grid, this);
        gridView.setAdapter(cells);
    }

    private void resetGrid() { //resets the grid
        while (!grid.isEmpty()) {
            grid.remove(0);
        }
        cells.liveCells.clear();
        cells.cellList.clear();
        cells.buttonList.clear();
        current = 0;
        currentState.setText("Current State: " + current);
        setGrid();
    }

    private void runDialog() { //asks how many states the user wishes to execute
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //pops up an alert dialog.
        builder.setTitle("How many states would you like to run?"); //asks for number of states.
        final EditText editText = new EditText(this); //new edittext
        editText.setInputType(InputType.TYPE_CLASS_TEXT); //text
        builder.setView(editText); //allows the user to input a number
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString(); //gets the value and strings it.
                int states = Integer.parseInt(text); //turns string into int
                run(states); //runs
            }
        });
        builder.show(); //shows the dialog box
    }

    public void run(int states) { //runs the states automatically, but without visuals
        while (states > 0 && states < 500) {
            try {
                nextState();
                Thread.sleep(500);
                states--;
            } catch (InterruptedException e) {
                e = new InterruptedException();
            }
        }
    }

    public void AlertBox(String title, String alert) { //dynamically sets the AlertBox with an alert and title on call.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title); //sets the title.
        builder.setMessage(alert); //sets the alert.
        builder.setPositiveButton("OK", null); //what happens when the button on the alert is clicked.
        AlertDialog dialog = builder.create(); //creates the alert dialog.
        dialog.show(); //displays the alert.
    }
}
