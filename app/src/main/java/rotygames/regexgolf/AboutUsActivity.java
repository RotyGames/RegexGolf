package rotygames.regexgolf;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView aboutUs = (TextView) findViewById(R.id.textViweAboutUs);
        aboutUs.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<div>Contact us via <a href='mailto:r.tamas.szabo@gmail.com'>e-mail!</a></div><div>View the source code on <a href='http://www.github.com/RotyGames/RegexGolf'>GitHub!</a></div>";
        aboutUs.setText(Html.fromHtml(text));


    }


    public void onClickDonate(View view) {
        Uri donateUri = Uri.parse("https://www.paypal.com/yt/cgi-bin/webscr?cmd=_xclick");
        Intent intent = new Intent(Intent.ACTION_VIEW, donateUri);
        startActivity(intent);
    }
}