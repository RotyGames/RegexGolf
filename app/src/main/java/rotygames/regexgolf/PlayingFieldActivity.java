package rotygames.regexgolf;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayingFieldActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);
        fillListViews();
    }

    private void fillListViews(){
        try {
            ListView leftColumn = (ListView) findViewById(R.id.leftColumn);
            ListView rightColumn = (ListView) findViewById(R.id.rightColumn);

            ArrayAdapter<CharSequence> leftArrayAdapter = ArrayAdapter.createFromResource(this, R.array.leftColumnWords, R.layout.custom_list_item);
            leftColumn.setAdapter(leftArrayAdapter);

            ArrayAdapter<CharSequence> rightArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rightColumnWords, R.layout.custom_list_item);
            rightColumn.setAdapter(rightArrayAdapter);
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}


