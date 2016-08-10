package rotygames.regexgolf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Toast.makeText(this, "You clicked ABOUT US!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickHelp(View view) {
        Toast.makeText(this, "You clicked HELP!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickExit(View view) {
        finish();
    }
}
