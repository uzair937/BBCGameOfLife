package com.example.gameoflife;
/*
 * Uzair Foolat
 * Game of Life
 * BBC Assessment
 * 04/02/2019
 * Android Studio
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cells extends BaseAdapter {
    List<String> grid;
    List<Button> buttonList = new ArrayList<>(); //all buttons stored in here
    List<Integer> cellList = new ArrayList<>(); //all cells are stored in this list on creation
    List<Integer> liveCells = new ArrayList<>(); //all green/live cells are stored in here
    int neighbours;
    int c = 0;
    Context context;
    Button button;
    int x = -1; //x is used to keep track of the buttons going into the livecells list
    boolean stable = false; //cells aren't stable on creation

    public Cells(List<String> grid, Context context) {
        this.grid = grid;
        this.context = context;
    }

    @Override
    public int getCount() {
        return grid.size();
    }

    @Override
    public Object getItem(int position) {
        return grid.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void storeButton(Button button) {
        buttonList.add(button);
    }

    public void storeCell(int cell) {
        cellList.add(cell);
    }

    public void handleColor(final int position, Button button) {
        if (!cellList.isEmpty()) { //if cells exist
            if (!liveCells.isEmpty() && cellList.get(position) == liveCells.get(x)) { //if the cell is alive and clicked
                button.setBackgroundColor(Color.BLACK);
                liveCells.remove(x);
                x--;
            } else if (liveCells.isEmpty() || cellList.get(position) != liveCells.get(x)) { //if the cell is dead and clicked
                x++;
                liveCells.add(position);
                button.setBackgroundColor(Color.GREEN);
            }
        }
    }

    public void handleDeath(final int position) { //handles killing a cell on state changes
        Button button;
        button = buttonList.get(position);
        button.setBackgroundColor(Color.BLACK);
        if (!liveCells.isEmpty()) {
            liveCells.remove(0); //removed the first cell in livecells
        }
        x--;
    }

    public void handleSpecial() { //checks special cases of neighbouring cells.
        Button button0 = buttonList.get(0); //each button has been assigned ready for changes
        Button button1 = buttonList.get(1);
        Button button2 = buttonList.get(2);
        Button button3 = buttonList.get(3);
        Button button4 = buttonList.get(4);
        Button button5 = buttonList.get(5);
        Button button6 = buttonList.get(6);
        Button button7 = buttonList.get(7);
        Button button8 = buttonList.get(8);
        if (c == 15) {
            button2.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button8.setBackgroundColor(Color.GREEN);
            button0.setBackgroundColor(Color.BLACK);
            button6.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(2);
            liveCells.add(4);
            liveCells.add(8);
            x += 3;
        }
        if (c == 16) {
            button6.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button8.setBackgroundColor(Color.GREEN);
            button0.setBackgroundColor(Color.BLACK);
            button2.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(6);
            liveCells.add(4);
            liveCells.add(8);
            x += 3;
        }
        if (c == 17) {
            button0.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button6.setBackgroundColor(Color.GREEN);
            button2.setBackgroundColor(Color.BLACK);
            button8.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(0);
            liveCells.add(4);
            liveCells.add(6);
            x += 3;
        }
        if (c == 18) {
            button0.setBackgroundColor(Color.GREEN);
            button2.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button6.setBackgroundColor(Color.BLACK);
            button8.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(0);
            liveCells.add(2);
            liveCells.add(4);
            x += 3;
        }
        if (c == 19) { //horizontal to vetical
            button1.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button7.setBackgroundColor(Color.GREEN);
            button3.setBackgroundColor(Color.BLACK);
            button5.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(1);
            liveCells.add(4);
            liveCells.add(7);
            x += 3;
        }
        if (c == 20) { //vertical to horizontal
            button4.setBackgroundColor(Color.GREEN);
            button3.setBackgroundColor(Color.GREEN);
            button5.setBackgroundColor(Color.GREEN);
            button1.setBackgroundColor(Color.BLACK);
            button7.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(3);
            liveCells.add(4);
            liveCells.add(5);
            x += 3;
        }
        if (c == 21) { //diagonal
            button2.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button6.setBackgroundColor(Color.GREEN);
            button0.setBackgroundColor(Color.BLACK);
            button8.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(2);
            liveCells.add(4);
            liveCells.add(6);
            x += 3;
        }
        if (c == 22) { //diagonal
            button0.setBackgroundColor(Color.GREEN);
            button4.setBackgroundColor(Color.GREEN);
            button8.setBackgroundColor(Color.GREEN);
            button2.setBackgroundColor(Color.BLACK);
            button6.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                liveCells.remove(0);
                x--;
            }
            liveCells.add(0);
            liveCells.add(4);
            liveCells.add(8);
            x += 3;
        }
    }

    public void handleState(final int position) {
        Button button = buttonList.get(position);
        if (neighbours == 2 || neighbours == 3) {
            button.setBackgroundColor(Color.GREEN);
            stable = true; //becomes stable if it's a square
            if (!liveCells.contains(position)) {
                liveCells.add(position);
            }
            x++;
        }
    }

    public void getNeighbours() { //checks the three cell combination
        c = 0; //instead of using cases
        neighbours = 0; //initialised as 0
        if (liveCells.size() == 1) {
            neighbours = 0; //has no neighbours
        }
        if (liveCells.size() == 2) {
            neighbours = 1; // has one neighbour
        }
        if (liveCells.size() > 3) {
            neighbours = liveCells.size();
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(3))) {
            c = 1;
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(4))) {
            c = 2;
        } else if (liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(2))
                && liveCells.contains(cellList.get(4))) {
            c = 3;
        } else if (liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(2))
                && liveCells.contains(cellList.get(5))) {
            c = 4;
        } else if (liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(6))) {
            c = 5;
        } else if (liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(7))) {
            c = 6;
        } else if (liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(5))
                && liveCells.contains(cellList.get(7))) {
            c = 7;
        } else if (liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(5))
                && liveCells.contains(cellList.get(8))) {
            c = 8;
        } else if (liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(6))
                && liveCells.contains(cellList.get(7))) {
            c = 9;
        } else if (liveCells.contains(cellList.get(5))
                && liveCells.contains(cellList.get(7))
                && liveCells.contains(cellList.get(8))) {
            c = 10;
        } else if (liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(4))) {
            c = 11;
        } else if (liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(5))) {
            c = 12;
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(4))) {
            c = 13;
        } else if (liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(6))
                && liveCells.contains(cellList.get(7))) {
            c = 14;
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(6))) {
            c = 15;
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(2))
                && liveCells.contains(cellList.get(4))) {
            c = 16;
        } else if (liveCells.contains(cellList.get(2))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(8))) {
            c = 17;
        } else if (liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(6))
                && liveCells.contains(cellList.get(8))) {
            c = 18;
        } else if (liveCells.contains(cellList.get(3))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(5))) {
            c = 19;
        } else if (liveCells.contains(cellList.get(1))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(7))) {
            c = 20;
        } else if (liveCells.contains(cellList.get(0))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(8))) {
            c = 21;
        } else if (liveCells.contains(cellList.get(2))
                && liveCells.contains(cellList.get(4))
                && liveCells.contains(cellList.get(6))) {
            c = 22;
        }
        if (c > 0 && c < 15) {
            neighbours = 3; //has two neighbours but 3 cells exist hence the 3 count
        } else if (c > 14) {
            neighbours = 3;
            stable = false; //not stable if it's above case 14
        } else {
            neighbours = 2;
        }
    }

    public int getGap() { //handles creating a stable square group of cells
        int gap = 0;  //looks at each case and determines each gap that gets turned into a live cell
        if (c == 1 || c == 4 || c == 9 || c == 10) {
            gap = 4;
        } else if (c == 11) {
            gap = 0;
        } else if (c == 13) {
            gap = 1;
        } else if (c == 12) {
            gap = 2;
        } else if (c == 2 || c == 14) {
            gap = 3;
        } else if (c == 3) {
            gap = 5;
        } else if (c == 7) {
            gap = 8;
        } else if (c == 8 || c == 5) {
            gap = 7;
        } else if (c == 6) {
            gap = 6;
        }
        return gap;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(200, 200));
            button.setPadding(24, 24, 24, 24);
            button.setText(grid.get(position));
            button.setBackgroundColor(Color.BLACK);
            button.setTextColor(Color.WHITE);
            storeButton(button);
            storeCell(position);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button = buttonList.get(position);
                    Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();
                    handleColor(position, button);
                }
            });
        } else {
            button = (Button) convertView;
        }
        return button;
    }
}
