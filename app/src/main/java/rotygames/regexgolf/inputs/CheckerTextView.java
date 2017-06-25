package rotygames.regexgolf.inputs;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.EditText;

import rotygames.regexgolf.utils.RegexMatcher;

/**
 * Created by kovi on 9/5/16.
 */
public class CheckerTextView extends AppCompatTextView {

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

}
