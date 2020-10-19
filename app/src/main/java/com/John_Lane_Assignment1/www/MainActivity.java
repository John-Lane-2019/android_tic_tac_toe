/*
 PROG 3210 Assignment 1
 Tic Tac Toe Application
 Revision History:
        John Lane, 2020.02.13: Created
 */

package com.John_Lane_Assignment1.www;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener{

    //declare controls and global variables
    private TextView textView;
    private String buttonSymbol = "X";
    private Button button;
    private Button buttonArray [][]= new Button[3][3];
    private Integer numberOfClicks = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign buttons to button array
        buttonArray[0][0] = findViewById(R.id.btn00);
        buttonArray[0][1] = findViewById(R.id.btn01);
        buttonArray[0][2] = findViewById(R.id.btn02);
        buttonArray[1][0] = findViewById(R.id.btn10);
        buttonArray[1][1] = findViewById(R.id.btn11);
        buttonArray[1][2] = findViewById(R.id.btn12);
        buttonArray[2][0] = findViewById(R.id.btn20);
        buttonArray[2][1] = findViewById(R.id.btn21);
        buttonArray[2][2] = findViewById(R.id.btn22);

        textView = findViewById(R.id.txtMessageText);
        //iterate through button array to assign click listeners
        for (int i = 0; i < buttonArray.length; i++){

            for (int j= 0; j< buttonArray[i].length; j++){

                buttonArray[i][j].setOnClickListener(this);
            }
        }

        //check if savedInstanceState is null, retrieve values using keys
        if(savedInstanceState != null){
            buttonSymbol = savedInstanceState.getString("buttonSymbol");
            textView.setText(savedInstanceState.getString("textView"));
            numberOfClicks = savedInstanceState.getInt("numberOfClicks");

            buttonArray[0][0].setText(savedInstanceState.getString("button00Text"));
            buttonArray[0][0].setTag(savedInstanceState.getString("button00Tag"));

            buttonArray[0][1].setText(savedInstanceState.getString("button01Text"));
            buttonArray[0][1].setTag(savedInstanceState.getString("button01Tag"));

            buttonArray[0][2].setText(savedInstanceState.getString("button02Text"));
            buttonArray[0][2].setTag(savedInstanceState.getString("button02Tag"));

            buttonArray[1][0].setText(savedInstanceState.getString("button10Text"));
            buttonArray[1][0].setTag(savedInstanceState.getString("button10Tag"));

            buttonArray[1][1].setText(savedInstanceState.getString("button11Text"));
            buttonArray[1][1].setTag(savedInstanceState.getString("button11Tag"));

            buttonArray[1][2].setText(savedInstanceState.getString("button12Text"));
            buttonArray[1][2].setTag(savedInstanceState.getString("button12Tag"));

            buttonArray[2][0].setText(savedInstanceState.getString("button20Text"));
            buttonArray[2][0].setTag(savedInstanceState.getString("button20Tag"));

            buttonArray[2][1].setText(savedInstanceState.getString("button21Text"));
            buttonArray[2][1].setTag(savedInstanceState.getString("button21Tag"));

            buttonArray[2][2].setText(savedInstanceState.getString("button22Text"));
            buttonArray[2][2].setTag(savedInstanceState.getString("button22Tag"));


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instantiate MenuInflater object
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    //manage click events
    @Override
    public void onClick(View view) {

        numberOfClicks++;
        button = (Button)view;

        if (buttonSymbol == "X" && button.getTag() != "O"){

            button.setText("X");
            button.setTag("X");
            buttonSymbol = "O";
            textView.setText("It's player O's turn");

        }

        else if (buttonSymbol == "O" && button.getTag() != "X"){

            button.setText("O");
            button.setTag("O");
            buttonSymbol = "X";
            textView.setText("It's player X's turn");
        }

        CheckGameOver(button);
    }

    private void CheckGameOver(Button button){

        //check for horizontal victory
       if(buttonArray[0][0].getTag() == button.getTag() &&
          buttonArray[0][1].getTag() == button.getTag() &&
          buttonArray[0][2].getTag() == button.getTag()){
          textView.setText("Player " + button.getTag().toString() + " has won!");
           disableButtonArray();
       }

        else if(buttonArray[1][0].getTag() ==  button.getTag() &&
            buttonArray[1][1].getTag() == button.getTag() &&
            buttonArray[1][2].getTag() == button.getTag()){
            textView.setText("Player " + button.getTag().toString() + " has won!");
           disableButtonArray();
        }
        else if(buttonArray[2][0].getTag() == button.getTag() &&
               buttonArray[2][1].getTag() == button.getTag() &&
               buttonArray[2][2].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
        }

        //check for vertical victory
       else if(buttonArray[0][0].getTag() == button.getTag() &&
               buttonArray[1][0].getTag() == button.getTag() &&
               buttonArray[2][0].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
       }

       else if(buttonArray[0][1].getTag() == button.getTag() &&
               buttonArray[1][1].getTag() == button.getTag() &&
               buttonArray[2][1].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
       }

       else if(buttonArray[0][2].getTag() == button.getTag() &&
               buttonArray[1][2].getTag() == button.getTag() &&
               buttonArray[2][2].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
       }
        //check for diagonal victory
       else if(buttonArray[0][0].getTag() == button.getTag() &&
               buttonArray[1][1].getTag() == button.getTag() &&
               buttonArray[2][2].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
       }
       else if(buttonArray[0][2].getTag() == button.getTag() &&
               buttonArray[1][1].getTag() == button.getTag() &&
               buttonArray[2][0].getTag() == button.getTag()){
               textView.setText("Player " + button.getTag().toString() + " has won!");
               disableButtonArray();
       }
       else if (numberOfClicks == 9){
           textView.setText("Tie Game!");
           disableButtonArray();
       }

    }

    // disable all buttons when victory condition met
    private void disableButtonArray(){

        for (int i = 0; i < buttonArray.length; i++){

            for (int j= 0; j< buttonArray[i].length; j++){

                buttonArray[i][j].setEnabled(false);
            }
        }
    }

    //call beginNewGame() when menu item clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item2:
               beginNewGame();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    // set button text and tag properties to null and  button enabled property to true
    private void beginNewGame(){

        for (int i = 0; i < buttonArray.length; i++){

            for (int j = 0; j< buttonArray[i].length; j++){

                buttonArray[i][j].setEnabled(true);
                buttonArray[i][j].setText(null);
                buttonArray[i][j].setTag(null);

            }
        }
        textView.setText("It's player X's turn");
        buttonSymbol = "X";
        numberOfClicks = 0;
    }

    //store values for orientation change using key-value pairs
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

      outState.putString("buttonSymbol",buttonSymbol);
      outState.putString("textView",textView.getText().toString());
      outState.putInt("numberOfClicks",numberOfClicks);

      if(buttonArray[0][0].getTag() != null){
          outState.putString("button00Text",buttonArray[0][0].getText().toString());
          outState.putString("button00Tag",buttonArray[0][0].getTag().toString());
      }

      if(buttonArray[0][1].getTag() !=null){
          outState.putString("button01Text",buttonArray[0][1].getText().toString());
          outState.putString("button01Tag",buttonArray[0][1].getTag().toString());
      }

      if(buttonArray[0][2].getTag() !=null){
          outState.putString("button02Text",buttonArray[0][2].getText().toString());
          outState.putString("button02Tag",buttonArray[0][2].getTag().toString());

      }

      if (buttonArray[1][0].getTag() !=null){
          outState.putString("button10Text",buttonArray[1][0].getText().toString());
          outState.putString("button10Tag",buttonArray[1][0].getTag().toString());
      }

        if (buttonArray[1][1].getTag() !=null){
            outState.putString("button11Text",buttonArray[1][1].getText().toString());
            outState.putString("button11Tag",buttonArray[1][1].getTag().toString());
        }

        if (buttonArray[1][2].getTag() !=null){
            outState.putString("button12Text",buttonArray[1][2].getText().toString());
            outState.putString("button12Tag",buttonArray[1][2].getTag().toString());
        }

        if (buttonArray[2][0].getTag() !=null){
            outState.putString("button20Text",buttonArray[2][0].getText().toString());
            outState.putString("button20Tag",buttonArray[2][0].getTag().toString());
        }

        if (buttonArray[2][1].getTag() !=null){
            outState.putString("button21Text",buttonArray[2][1].getText().toString());
            outState.putString("button21Tag",buttonArray[2][1].getTag().toString());
        }

        if (buttonArray[2][2].getTag() !=null){
            outState.putString("button22Text",buttonArray[2][2].getText().toString());
            outState.putString("button22Tag",buttonArray[2][2].getTag().toString());
        }

    }
}
