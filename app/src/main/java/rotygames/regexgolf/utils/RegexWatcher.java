package rotygames.regexgolf.utils;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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

    protected CheckerTextView textView;

    protected CheckerTextView.CheckType type;

    protected int matchColor;

    protected SpannableString m_styledString;

    protected ForegroundColorSpan m_span;

    public RegexWatcher(CheckerTextView checkThis, EditText regexContainer, CheckerTextView.CheckType type) {
        this.textView = checkThis;
        this.type = type;
        if (type == CheckerTextView.CheckType.TO_MATCH) {
            matchColor = ContextCompat.getColor(textView.getContext(), R.color.green_correct_match);
        } else {
            matchColor = ContextCompat.getColor(textView.getContext(), R.color.green_incorrect_match);
        }
        m_styledString = null;
        m_span = null;
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
                Matcher matcher = Pattern.compile(regex).matcher(getStyledString());

                if (matcher.find())
                    handleMatch(matcher.start(), matcher.end());
                else
                    handleDoesntMatch();
            } catch (PatternSyntaxException e) {
                handleDefault();
            }
        } else {
            handleDefault();
        }
        textView.invalidate();
    }

    protected void handleMatch(int matchStart, int matchEnd) {
        handleDefault();
        SpannableString styledString = new SpannableString(textView.getText());
        styledString.setSpan(getSpan(), matchStart, matchEnd, 0);
        textView.setText(styledString);
    }

    protected void handleDoesntMatch() {
        handleDefault();
    }

    protected void handleDefault() {
        SpannableString styledString = getStyledString();
        styledString.removeSpan(getSpan());
        textView.setText(styledString);
    }

    protected SpannableString getStyledString() {
        if (m_styledString == null) {
            m_styledString = new SpannableString(textView.getText());
        }
        return m_styledString;
    }

    public ForegroundColorSpan getSpan() {
        if (m_span == null)
            m_span = new ForegroundColorSpan(matchColor);
        return m_span;
    }
}
