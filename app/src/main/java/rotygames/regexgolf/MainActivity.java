package rotygames.regexgolf;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetQuoteTask().execute();

    }

    class GetQuoteTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("http://52.208.157.181:1994/api/Emperor");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String quote = bufferedReader.readLine();
                    bufferedReader.close();
                    return quote;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);

            TextView quoteOfTheDay = (TextView) findViewById(R.id.quoteOfTheDay);

            quoteOfTheDay.setText("\t                  " + response.replaceAll("^\"|\"$", ""));
        }
    }


    public void onClickPlay(View view) {
        Intent intent = new Intent(this, PlayMenuActivity.class);
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        Toast.makeText(this, "You clicked SETTINGS!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickLeaderboard(View view) {
        Toast.makeText(this, "You clicked LEADERBOARD!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickAboutUs(View view) {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void onClickHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void onClickQuote(View view) {
        TextView textView = (TextView) view;

        if (textView.getEllipsize() != null) {
            textView.setText(textView.getText().toString().trim());
            textView.setMaxLines(4);
            textView.setSingleLine(false);
            textView.setEllipsize(null);
        } else {
            textView.setText("\t                 " + textView.getText().toString());
            textView.setMaxLines(1);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        }

    }

    public void onClickExit(View view) {
        finish();
    }
}
