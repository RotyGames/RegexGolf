package rotygames.regexgolf;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


//TODO: Make open / close TextViews on double tap only
//TODO: Set scrollbars

public class HelpActivity extends AppCompatActivity {

    int collapsedMaxLines = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void onClickCollapsibleTextView(View view){
        TextView textView = (TextView) view;
        int newMaxLines;
        if(textViewIsCollapsed(textView))
        {
            newMaxLines = textView.getLineCount();
            textView.setTextColor(Color.parseColor(getResources().getString(R.color.MeatBrown)));
        } 
        else
        {
            newMaxLines = collapsedMaxLines;
            textView.setTextColor(Color.parseColor(getResources().getString(R.color.OkkerYellow)));
        }
        ObjectAnimator animation = ObjectAnimator.ofInt(textView, "maxLines",newMaxLines);
        animation.setDuration(100).start();
    }

    /*
    private void setColorOfFirstRow(TextView textView, String colorInHex) {
        String originalText = textView.getText().toString();
        String firstRow = originalText.split("(?=[\n])", 2)[0];

        Spannable newText = new SpannableString(originalText);
        newText.setSpan(new ForegroundColorSpan(Color.parseColor(colorInHex)), 0, firstRow.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView.setText(newText);
    }
    */


    private boolean textViewIsCollapsed(TextView textView){
        return textView.getMaxLines() == collapsedMaxLines;
    }
}
