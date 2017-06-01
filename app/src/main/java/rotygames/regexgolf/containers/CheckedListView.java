package rotygames.regexgolf.containers;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import rotygames.regexgolf.R;

/**
 * Created by Kovi on 2017. 06. 02..
 */

public class CheckedListView extends ListView {

    private int regexContainerId;

    private EditText regexContainer;

    public CheckedListView(Context context) {
        super(context);
        throw new RuntimeException("Can instantiate without attributes!");
    }

    public CheckedListView(Context context, AttributeSet attrs ){
        super(context, attrs);
        regexContainer = null;
        processAttributes(attrs);
    }

    public CheckedListView(Context context, AttributeSet attrs, int defStyle ){
        super(context, attrs, defStyle);
        regexContainer = null;
        processAttributes(attrs);
    }

    private void processAttributes(AttributeSet attrs){
        TypedArray typedAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.CheckedListView);
        regexContainerId = typedAttrs.getResourceId(R.styleable.CheckedListView_regexContainer, 0);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (regexContainerId != 0) {
            regexContainer = (EditText)getRootView().findViewById(regexContainerId);
            regexContainer.addTextChangedListener(this);
        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // TODO check regex matches
}
}
