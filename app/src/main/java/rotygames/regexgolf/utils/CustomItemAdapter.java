package rotygames.regexgolf.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import rotygames.regexgolf.inputs.CheckerTextView;

/**
 * Created by kovi on 9/5/16.
 */
public class CustomItemAdapter extends ArrayAdapter<String> {

    private EditText regexContainer;

    private CheckerTextView.CheckType type;

    public CustomItemAdapter(Context context, int textViewResourceId, String[] objects, EditText regexContainer, CheckerTextView.CheckType type) {
        super(context, textViewResourceId, objects);
        this.regexContainer = regexContainer;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CheckerTextView v = (CheckerTextView) super.getView(position, convertView, parent);
        if (!v.hasWatcher())
            v.initializeWatcher(regexContainer, type);
        return v;
    }
}
