package rotygames.regexgolf;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import rotygames.regexgolf.inputs.CheckerTextView;
import rotygames.regexgolf.utils.CustomItemAdapter;

public class PlayingFieldActivity extends AppCompatActivity {

    ListView leftColumn;
    ListView rightColumn;
    EditText regexContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HEREE", "on create eleje");
        setContentView(R.layout.activity_playing_field);
        leftColumn = (ListView) findViewById(R.id.leftColumn);
        rightColumn = (ListView) findViewById(R.id.rightColumn);
        regexContainer = (EditText) findViewById(R.id.regexContainer);
        Log.d("HEREE", "on create vége");
        fillListViews();
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    private void fillListViews(){
        try {
            CharSequence[] leftWords = getResources().getTextArray(R.array.leftColumnWords);
            CustomItemAdapter leftAdapter = new CustomItemAdapter(this, R.layout.custom_list_item,  leftWords, regexContainer, CheckerTextView.CheckType.TO_MATCH);
            leftColumn.setAdapter(leftAdapter);


            CharSequence[] rightWords = getResources().getTextArray(R.array.rightColumnWords);
            CustomItemAdapter rightArrayAdapter = new CustomItemAdapter(this, R.layout.custom_list_item,  rightWords, regexContainer, CheckerTextView.CheckType.TO_NOT_MATCH);
            rightColumn.setAdapter(rightArrayAdapter);
            
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}


