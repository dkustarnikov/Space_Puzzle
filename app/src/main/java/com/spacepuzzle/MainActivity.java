package com.spacepuzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static MainActivity mainActivity = null;
    private TextView Score;
    private TextView ScoreTextView;
    public static int score = 0;
    private TextView maxScore;
    private ImageView share;
    private Button restart;
    private Button back;
    private Button pause;
    private GameView gameView;

    private ImageView logoImageView;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScoreTextView = findViewById(R.id.tv);
        ScoreTextView.setAlpha(0.5f);




        Score = (TextView) findViewById(R.id.Score);
        maxScore = (TextView) findViewById(R.id.maxScore);
        maxScore.setText(getSharedPreferences("pMaxScore", MODE_PRIVATE).getInt("maxScore", 0) + "");
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String s = "My score in the \"2048\" game is" + maxScore.getText() + ", Do you dare to challenge? click to enter "; //TODO: Add the link to the game
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Show off"));
            }
        });
        gameView = (GameView)findViewById(R.id.gameView);
        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameView.startGame();
            }
        });
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameView.hasTouched) {
                    score = gameView.score;
                    showScore();
                    for(int y=0;y<4;++y) {
                        for(int x=0;x<4;++x) {
                            gameView.cards[y][x].setNum(gameView.num[y][x]);
                        }
                    }
                }
            }
        });
        pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    //The score is cleared
    public void clearScore() {
        score = 0;
        showScore();
    }

    //Score increase
    public void addScore(int i) {

        score += i;
        showScore();
        SharedPreferences pref = getSharedPreferences("pMaxScore", MODE_PRIVATE);

        //If the current score exceeds the highest record, update it
        if (score > pref.getInt("maxScore", 0)) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("maxScore", score);
            editor.commit();
            maxScore.setText(pref.getInt("maxScore", 0) + "");
        }

    }

    //Display current score
    public void showScore() {
        Score.setText(score + "");
    }

    @Override
    public void onBackPressed() {
        createExitTipDialog();
    }

    private void createExitTipDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Are you sure you want to leave?")
                .setTitle("Prompt")
                .setIcon(R.drawable.tip)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

}
