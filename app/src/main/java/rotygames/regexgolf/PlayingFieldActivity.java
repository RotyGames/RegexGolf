package rotygames.regexgolf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import rotygames.regexgolf.containers.CheckedListView;
import rotygames.regexgolf.inputs.CheckerTextView;
import rotygames.regexgolf.utils.CustomItemAdapter;

public class PlayingFieldActivity extends AppCompatActivity {

    CheckedListView leftColumn;
    CheckedListView rightColumn;
    EditText regexContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);
        leftColumn = (CheckedListView) findViewById(R.id.leftColumn);
        rightColumn = (CheckedListView) findViewById(R.id.rightColumn);
        regexContainer = (EditText) findViewById(R.id.regexContainer);
        fillListViews();
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    private void fillListViews(){
        try {
            String[] leftWords = getResources().getStringArray(R.array.leftColumnWords);
            CustomItemAdapter leftAdapter = new CustomItemAdapter(this, R.layout.custom_list_item,  leftWords);
            leftColumn.setAdapter(leftAdapter);


            String[] rightWords = getResources().getStringArray(R.array.rightColumnWords);
            CustomItemAdapter rightArrayAdapter = new CustomItemAdapter(this, R.layout.custom_list_item,  rightWords);
            rightColumn.setAdapter(rightArrayAdapter);
            
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}


