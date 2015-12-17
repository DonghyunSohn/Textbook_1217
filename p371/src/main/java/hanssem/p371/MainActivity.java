package hanssem.p371;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * 날짜와 시간을 한꺼번에 선택할 수 있는 복합위젯을 만드는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    TextView textView1;
    DateTimePicker dateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        dateTimePicker = (DateTimePicker)findViewById(R.id.dateTimePicker);

        // DateTimePicker 객체에 리스너 설정하여 날짜나 시간이 변경될 때마다 텍스트에 표시
        dateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimePicker view, int year,
                                          int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfYear, hourOfDay, minute);

                // 텍스트에 현재 날짜와 시간 정보 표시
                textView1.setText(dateFormat.format(calendar.getTime()));
            }
        });

        // 현재 시간 텍스트뷰에 표시
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateTimePicker.getYear(), dateTimePicker.getMonth(), dateTimePicker.getDayOfMonth(), dateTimePicker.getCurrentHour(), dateTimePicker.getCurrentMinute());
        textView1.setText(dateFormat.format(calendar.getTime()));

    }
}
