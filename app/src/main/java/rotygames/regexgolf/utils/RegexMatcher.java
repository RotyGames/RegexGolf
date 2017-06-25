package rotygames.regexgolf.utils;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import rotygames.regexgolf.inputs.CheckerTextView;

/**
 * Created by kovi on 9/5/16.
 */
public class RegexMatcher {

    public class MatchInfo {
        public int matchStartIndex;

        public int matchEndIndex;

        public MatchInfo() {
            matchStartIndex = -1;
            matchEndIndex = -1;
        }

        public boolean isMatched() {
            return matchStartIndex >= 0 && matchEndIndex >= matchStartIndex;
        }
    }

    protected boolean requiredMatchResult;

    protected int matchColor;

    protected ForegroundColorSpan m_span;

    public RegexMatcher(boolean requiredMatchResult, int matchColor) {
        this.requiredMatchResult = requiredMatchResult;
        this.matchColor = matchColor;
        m_span = null;
    }

    public boolean checkMatchResultIsCorrect(MatchInfo matchInfo) {
        return matchInfo.isMatched() == requiredMatchResult;
    }

    public void highlightMatch(CheckerTextView textViewToHighlight, MatchInfo matchInfo) {
        if (matchInfo.isMatched() == true)
            handleMatch(textViewToHighlight, matchInfo);
        else
            handleDoesntMatch(textViewToHighlight);
    }

    public MatchInfo calculatekMatchInfo(String regex, String textToCheck) {
        MatchInfo matchInfo = new MatchInfo();
        if (regex.length() != 0) {
            try {
                Matcher matcher = Pattern.compile(regex).matcher(textToCheck);

                if (matcher.find()) {
                    matchInfo.matchStartIndex = matcher.start();
                    matchInfo.matchEndIndex = matcher.end();
                }
            } catch (PatternSyntaxException e) {
                Log.wtf("RegexMatcher", e.getMessage());
            }
        }
        return matchInfo;
    }

    protected void handleMatch(CheckerTextView textViewToCheck, MatchInfo matchInfo) {
        handleDefault(textViewToCheck);
        SpannableString styledString = new SpannableString(textViewToCheck.getText());
        styledString.setSpan(getSpan(), matchInfo.matchStartIndex, matchInfo.matchEndIndex, 0);
        textViewToCheck.setText(styledString);
    }

    protected void handleDoesntMatch(CheckerTextView textViewToCheck) {
        handleDefault(textViewToCheck);
    }

    protected void handleDefault(CheckerTextView textViewToCheck) {
        SpannableString styledString = new SpannableString(textViewToCheck.getText());
        styledString.removeSpan(getSpan());
        textViewToCheck.setText(styledString);
    }

    public ForegroundColorSpan getSpan() {
        if (m_span == null)
            m_span = new ForegroundColorSpan(matchColor);
        return m_span;
    }
}
