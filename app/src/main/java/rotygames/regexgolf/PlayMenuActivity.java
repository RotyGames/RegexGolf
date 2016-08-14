package rotygames.regexgolf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PlayMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    public void onClickCampaign(View view) {
        Intent intent = new Intent(this, PlayingFieldActivity.class);
        startActivity(intent);
    }

    public void onClickGenerated(View view) {
        Toast.makeText(this, "NO TOUCHY!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickChallenge(View view) {
        Toast.makeText(this, "NO TOUCHY!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickDuel(View view) {
        Toast.makeText(this, "NO TOUCHY!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickWindings(View view) {
        Toast.makeText(this, "NO TOUCHY!",
                Toast.LENGTH_SHORT).show();
    }
}
