package hanssem.p384;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    LinearLayout viewerContainer;

    ImageDisplayView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        viewerContainer = (LinearLayout) findViewById(R.id.viewerContainer);
        Bitmap sourceBitmap = loadImage();
        if (sourceBitmap != null) {
            displayView = new ImageDisplayView(this);

            displayView.setImageData(sourceBitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            viewerContainer.addView(displayView, params);
        }
    }

    private Bitmap loadImage() {
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.beach);

        return bitmap;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

