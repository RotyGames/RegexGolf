package rotygames.regexgolf.containers;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ListView;

import rotygames.regexgolf.R;
import rotygames.regexgolf.inputs.CheckerTextView;
import rotygames.regexgolf.utils.RegexMatcher;

import static rotygames.regexgolf.containers.CheckedListView.CheckType.TO_MATCH;
import static rotygames.regexgolf.containers.CheckedListView.CheckType.TO_NOT_MATCH;

/**
 * Created by Kovi on 2017. 06. 02..
 */

public class CheckedListView extends ListView {
    public enum CheckType {
        TO_MATCH,
        TO_NOT_MATCH
    }

    private int matchColor;

    private CheckType checkType;

    private int matchedTextColor;

    private boolean requiredMatchResult;

    private RegexMatcher regexMatcher;

    private int regexContainerId;

    private EditText regexContainer;

    public CheckedListView(Context context) {
        super(context);
        throw new RuntimeException("Can instantiate without attributes!");
    }

    public CheckedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        regexContainer = null;
        processAttributes(attrs);
    }

    public CheckedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        regexContainer = null;
        processAttributes(attrs);
    }

    private void processAttributes(AttributeSet attrs) {
        TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CheckedListView, 0, 0);
        regexContainerId = typedAttrs.getResourceId(R.styleable.CheckedListView_regexContainer, -1);
        matchColor = typedAttrs.getColor(R.styleable.CheckedListView_matchColor, -1);
        requiredMatchResult = typedAttrs.getBoolean(R.styleable.CheckedListView_requiredMatchResult, true);
        checkType = requiredMatchResult ? TO_MATCH : TO_NOT_MATCH;
        regexMatcher = new RegexMatcher(requiredMatchResult, matchColor);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (regexContainerId != 0) {
            regexContainer = (EditText) getRootView().findViewById(regexContainerId);
            regexContainer.addTextChangedListener(this);
        }
    }

    private void setCheckType(CheckType newCheckType) {
        if (checkType == newCheckType)
            return;

        this.checkType = newCheckType;
        this.requiredMatchResult = checkType == TO_MATCH;
        regexMatcher = new RegexMatcher(checkType == TO_MATCH, matchedTextColor);
    }

    public void highlightNewItem(CheckerTextView checkedTextView) {
        RegexMatcher.MatchInfo matchInfo = regexMatcher.calculatekMatchInfo(regexContainer.getText().toString(), checkedTextView.getText().toString());
        regexMatcher.highlightMatch(checkedTextView, matchInfo);
    }

    public RegexMatcher getMatcher() {
        return regexMatcher;
    }

    public void highlightTextViews(String regex) {
        int childCount = getChildCount();
        for (int childPos = 0; childPos < childCount; childPos++) {
            CheckerTextView textView = (CheckerTextView) getChildAt(childPos);
            RegexMatcher.MatchInfo matchInfo = regexMatcher.calculatekMatchInfo(regex, textView.getText().toString());
            regexMatcher.highlightMatch(textView, matchInfo);
        }
    }

    public void setBackground(String regex) {
        if (regex.length() == 0) {
            setBackgroundResource(0);
            return;
        }

        boolean allMatchIsCorrect = true;
        int itemCount = getAdapter().getCount();
        for (int itemPos = 0; itemPos < itemCount && allMatchIsCorrect; itemPos++) {
            String item = (String) getAdapter().getItem(itemPos);
            RegexMatcher.MatchInfo matchInfo = regexMatcher.calculatekMatchInfo(regex, item);
            if (regexMatcher.checkMatchResultIsCorrect(matchInfo) == false) {
                allMatchIsCorrect = false;
                break;
            }
        }

        if (allMatchIsCorrect == false) {
            setBackgroundResource(0);
        } else {
            // TODO set background that tells information about list view state
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String regex = editable.toString();
        highlightTextViews(regex);

        setBackground(regex);

    }
}
