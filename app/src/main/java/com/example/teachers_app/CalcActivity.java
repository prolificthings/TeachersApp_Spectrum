package com.example.teachers_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {
    Button ac,power,back,one,two,three,four,five,six,seven,eight,nine,zero,divide,into,minus,plus,point,equal,ans;
    private String input="", answer;
    TextView screen;
    //private boolean clearResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        screen = findViewById(R.id.screen);
        ac = findViewById(R.id.ac);
        power = findViewById(R.id.power);
        back = findViewById(R.id.back);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine =findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        divide = findViewById(R.id.div);
        into = findViewById(R.id.mul);
        minus = findViewById(R.id.min);
        plus = findViewById(R.id.plus);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        ans = findViewById(R.id.ans);

    }


    public void ButtonClick(View view){
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){
            case"AC":
                input = "";
                break;
            case"Ans":
                //clearResult=false;
                input+=answer;
                break;
            case"x":
                //clearResult=false;
                solve();
                input+="*";
                break;
            case"^":
                //clearResult=false;
                solve();
                input+="^";
                break;
            case"=":

                solve();
                //clearResult=true;
                answer = input;
                break;
            case"⬅":
                //clearResult=false;
                String newTxt = input.substring(0,input.length()-1);
                input = newTxt;
                break;
            default:
                if (input==null){
                    input="";
                }
                if (data.equals("+")||data.equals("-")||data.equals("/")){
                    //clearResult=false;
                    solve();
                }
                /*else if(clearResult){
                    input="";
                    clearResult=false;
                }*/
                input+=data;
        }
        screen.setText(input);
    }

    private void solve() {
        if (input.split("\\*").length==2){
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul+"";
            }catch (Exception e){

            }
        }
        else if (input.split("/").length==2){
            String number[] = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div+"";
            }catch (Exception e){

            }
        }
        else if (input.split("^").length==2){
            String number[] = input.split("\\^");
            try {
                double pow = Math.pow(Double.parseDouble(number[0]),Double.parseDouble(number[1]));
                input = pow+"";
            }catch (Exception e){

            }
        }
        else if (input.split("\\+").length==2){
            String number[] = input.split("\\+");
            try {
                double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = sum+"";
            }catch (Exception e){

            }
        }
        else if (input.split("-").length==2){
            String number[] = input.split("-");
            if(number[0]=="" && number.length==2){
                number[0]=0+"";
            }
            try {
                double sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                input = sub+"";
            }catch (Exception e){

            }
        }
        String n[]=input.split("\\.");
        if (n.length>1){
            if (n[1].equals("0")){
                input=n[0];
            }
        }
        screen.setText(input);
    }
}