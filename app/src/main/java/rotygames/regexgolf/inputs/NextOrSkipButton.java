package rotygames.regexgolf.inputs;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import rotygames.regexgolf.PlayingFieldActivity;
import rotygames.regexgolf.R;
import rotygames.regexgolf.utils.CustomItemAdapter;

/**
 * Created by Kovi on 2016. 09. 06..
 */
public class NextOrSkipButton extends Button implements View.OnClickListener, TextWatcher {

    private Boolean isCorrectRegex;

    private PlayingFieldActivity playingField;

    private EditText regexContainer;

    private CustomItemAdapter leftAdapter;

    private CustomItemAdapter rightAdapter;

    public NextOrSkipButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.playingField = (PlayingFieldActivity) context;
        isCorrectRegex = false;
        setOnClickListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        regexContainer = EditText.class.cast(getRootView().findViewById(R.id.regexContainer));
        regexContainer.addTextChangedListener(this);
        leftAdapter = CustomItemAdapter.class.cast(ListView.class.cast(playingField.findViewById(R.id.leftColumn)).getAdapter());
        rightAdapter = CustomItemAdapter.class.cast(ListView.class.cast(playingField.findViewById(R.id.rightColumn)).getAdapter());
    }

    @Override
    public void onClick(View v) {
        if (isCorrectRegex)
            Toast.makeText(playingField, "You reached the next level!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(playingField, "You skipped this level!", Toast.LENGTH_SHORT).show();
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
        Boolean tempIsCorrect = Boolean.TRUE;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher leftMatcher = pattern.matcher("");
            Matcher rightMatcher = pattern.matcher("");
            for (int index = 0; index < 20; index++) {
                leftMatcher.reset(leftAdapter.getItem(index));
                rightMatcher.reset(rightAdapter.getItem(index));
                if (!leftMatcher.find() || rightMatcher.find()) {
                    tempIsCorrect = false;
                    break;
                }
            }
            isCorrectRegex = tempIsCorrect;
        } catch (PatternSyntaxException e) {
            isCorrectRegex = false;
        }
        if (isCorrectRegex)
            this.setText("NEXT LEVEL");
        else
            this.setText("SKIP LEVEL");

    }
}
