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
import rotygames.regexgolf.containers.CheckedListView;
import rotygames.regexgolf.inputs.CheckerTextView;

/**
 * Created by kovi on 9/5/16.
 */
public class CustomItemAdapter extends ArrayAdapter<String> {

    private Context context;


    public CustomItemAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.custom_list_item, null);
        }
        CheckerTextView textView = (CheckerTextView) v;
        textView.setText(getItem(position));

        CheckedListView listView = (CheckedListView) parent;
        listView.highlightNewItem(textView);

        return v;
    }
}
