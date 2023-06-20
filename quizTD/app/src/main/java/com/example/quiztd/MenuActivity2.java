package com.example.quiztd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity2 extends AppCompatActivity {
    private static final int SCORE_CODE = 3;
    Button jouerBtn;
TextView scoreTxt;
ActivityResultLauncher activityLauncher= registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {

        if(result.getResultCode()==RESULT_OK ) {
          Intent data= result.getData();
            if(data!=null) {
                int score=data.getIntExtra("score",-1);
                scoreTxt.setText(score+"");
            }
        }else if (result.getResultCode()==RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "quiz cancelled", Toast.LENGTH_LONG).show();
        }
    }
});
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       jouerBtn=(Button) findViewById(R.id.jouer);
       scoreTxt=(TextView) findViewById(R.id.score);




       jouerBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MenuActivity2.this, QuizActivity.class);

               activityLauncher.launch(intent);
           }
       });
    }

}