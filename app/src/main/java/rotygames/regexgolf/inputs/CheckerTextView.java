package rotygames.regexgolf.inputs;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import rotygames.regexgolf.R;
import rotygames.regexgolf.utils.RegexWatcher;

/**
 * Created by kovi on 9/5/16.
 */
public class CheckerTextView extends TextView {

    public enum CheckType{
        TO_MATCH,
        TO_NOT_MATCH
    }

    private RegexWatcher regexWatcher;

    private CheckType type;


    public CheckerTextView(Context context) {
        super(context);
    }

    public CheckerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

    public void initializeWatcher(EditText regexContainer, CheckType type){
        if (hasWatcher())
            return;
        this.type = type;
        this.regexWatcher = new RegexWatcher(this, regexContainer, type);
    }

    public boolean hasWatcher(){
        return regexWatcher != null;
    }

}
