package rotygames.regexgolf.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import rotygames.regexgolf.R;
import rotygames.regexgolf.inputs.CheckerTextView;

/**
 * Created by kovi on 9/5/16.
 */
public class RegexWatcher implements TextWatcher {

    private CheckerTextView text;

    private CheckerTextView.CheckType type;

    private int matchColor;
    private int doesntMatchColor;

    public RegexWatcher(CheckerTextView checkThis, EditText regexContainer, CheckerTextView.CheckType type) {
        this.text = checkThis;
        this.type = type;
        if (type == CheckerTextView.CheckType.TO_MATCH){
            matchColor = R.color.correct_match;
            doesntMatchColor = R.color.transparent;
        } else {
            doesntMatchColor = R.color.transparent;
            matchColor = R.color.incorrect_match;
        }
        regexContainer.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String regex = editable.toString();
        if (regex.length() != 0) {
            try {
                Matcher matcher = Pattern.compile(regex).matcher(text.getText());

                if (matcher.find())
                    text.setBackgroundResource(matchColor);
                else
                    text.setBackgroundResource(doesntMatchColor);
            } catch (PatternSyntaxException e) {
                text.setBackgroundResource(R.color.transparent);
            }
        } else {
            text.setBackgroundResource(R.color.transparent);
        }
        text.invalidate();
    }
}
