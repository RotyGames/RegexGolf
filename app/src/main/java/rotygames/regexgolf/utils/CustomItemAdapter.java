package rotygames.regexgolf.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import rotygames.regexgolf.R;
import rotygames.regexgolf.inputs.CheckerTextView;

/**
 * Created by kovi on 9/5/16.
 */
public class CustomItemAdapter extends ArrayAdapter<String> {

    private EditText regexContainer;

    private CheckerTextView.CheckType type;

    private Context context;


    public CustomItemAdapter(Context context, int textViewResourceId, String[] objects, EditText regexContainer, CheckerTextView.CheckType type) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.regexContainer = regexContainer;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.custom_list_item, null);
            CheckerTextView checkerText =(CheckerTextView) v.findViewById(R.id.word);
            checkerText.initializeWatcher(regexContainer, type);
        }
        CheckerTextView txtWord = (CheckerTextView) v.findViewById(R.id.word);
        txtWord.setText(getItem(position));
        return v;
    }
}
