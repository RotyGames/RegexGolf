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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HelpActivity extends AppCompatActivity {

    int collapsedMaxLines = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTextViewEventListeners();
    }



    private void setTextViewEventListeners() {
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.linearLayoutRoot);
        int viewCount = rootLayout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            View currentView = rootLayout.getChildAt(i);
            final GestureDetector currentGestureDetector = createGestureDetector(currentView);
            currentView.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    currentGestureDetector.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    private GestureDetector createGestureDetector(final View view) {
        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                final TextView textView = (TextView) view;
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
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
        return gestureDetector;
    }

    private boolean textViewIsCollapsed(TextView textView){
        return textView.getMaxLines() == collapsedMaxLines;
    }
}
