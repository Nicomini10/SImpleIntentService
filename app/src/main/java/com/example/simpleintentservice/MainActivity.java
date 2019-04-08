package com.example.simpleintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private TextView percelText;

    private ProgressBar progressBar;
    private Intent serviceIntent;

    private ResponseReceiver receiver = new ResponseReceiver();

    public class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SimpleIntentService.ACTION_1)) {
                int value = intent.getIntExtra("percel", -1);

                new ShowProgressBarTask().execute(value);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.startButton = (Button) this.findViewById(R.id.button);
        this.stopButton = (Button) this.findViewById(R.id.button2);
        this.percelText = (TextView) this.findViewById(R.id.textView);
        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(receiver, new IntentFilter(SimpleIntentService.ACTION_1));
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(receiver);
    }


    class ShowProgressBarTask extends AsyncTask<Integer,Integer,Integer> {


        @Override
        protected Integer doInBackground(Integer... integers) {
            return integers[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            progressBar.setProgress(result);
            percelText.setText(result + "% Loaded");

            if(result == 100){
                percelText.setText("Completed");
                startButton.setEnabled(true);
            }


        }
    }


    public void startButtonClicked(View view){

        startButton.setEnabled(false);

        serviceIntent = new Intent(this, SimpleIntentService.class);
        startService(serviceIntent);

    }

    public void stopButtonClicked(View view){

        if(serviceIntent != null){

        }

    }
}
