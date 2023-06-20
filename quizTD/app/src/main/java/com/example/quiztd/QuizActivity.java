package com.example.quiztd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    int score=0;
    TextView question, scoretxt;
    Button b1, b2, b3;
    private String questions[] = {"Quelle est la capitale de la Tunisie?","Tunis", "Maroc", "Algérie","Tunis"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question=(TextView) findViewById(R.id.question);
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.b3);

        b1.setText(questions[1]);
        b2.setText(questions[2]);
        b3.setText(questions[3]);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String repOk=questions[4];
        Button btnReponse=(Button) findViewById(v.getId());

        if(repOk.equals(btnReponse.getText().toString())){
            score+=10;
            Intent i =new Intent();
            i.putExtra("score", score);
            setResult(RESULT_OK,i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "c'est faux",Toast.LENGTH_LONG).show();
        }
    }
}
