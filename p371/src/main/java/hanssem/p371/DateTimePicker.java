package hanssem.p371;


import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;


// 리니어 레이아웃을 상속하여 새로운 클래스 정의
public class DateTimePicker extends LinearLayout {

    // 날짜나 시간이 변경되는 이벤트를 함께 처리하기 위한 새로운 리스너 인터페이스 정의
    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfYear, int hourOfDay, int minute);
    }

    private OnDateTimeChangedListener onDateTimeChangedListener;

    private final DatePicker datePicker;

    private final CheckBox enableTimeCheckBox;

    private final TimePicker timePicker;


    public DateTimePicker(final Context context) {
        this(context, null);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 새로 정의한 클래스를 위한 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.datetimepicker, this, true);

        // 시간 정보 참조
        Calendar calendar = Calendar.getInstance();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DataTimePicker);
        final int _currentYear = a.getInt(R.styleable.DataTimePicker_year, calendar.get(Calendar.YEAR));
        final int _currentMonth = a.getInt(R.styleable.DataTimePicker_month, calendar.get(Calendar.MONTH));
        final int _currentDay = a.getInt(R.styleable.DataTimePicker_day, calendar.get(Calendar.DAY_OF_MONTH));
        final int _currentHour = a.getInt(R.styleable.DataTimePicker_hour, calendar.get(Calendar.HOUR_OF_DAY));
        final int _currentMinute = a.getInt(R.styleable.DataTimePicker_minute, calendar.get(Calendar.MINUTE));

        // 날짜선택 위젯 초기화
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(_currentYear, _currentMonth, _currentDay, new OnDateChangedListener() {

            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 날짜가 변경되는 이벤트가 발생했을 때 새로운 리스너의 메소드 호출
                if (onDateTimeChangedListener != null) {
                    onDateTimeChangedListener.onDateTimeChanged(
                            DateTimePicker.this, year, monthOfYear, dayOfMonth,
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        // 체크박스 이벤트 처리
        enableTimeCheckBox = (CheckBox) findViewById(R.id.enableTimeCheckBox);
        enableTimeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                timePicker.setVisibility((enableTimeCheckBox).isChecked() ? View.VISIBLE : View.INVISIBLE);
            }
        });

        // 시간선택 위젯 이벤트 처리
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                // 시간이 변경되는 이벤트가 발생했을 때 새로운 리스너의 메소드 호출
                if (onDateTimeChangedListener != null) {
                    onDateTimeChangedListener.onDateTimeChanged(
                            DateTimePicker.this, datePicker.getYear(),
                            datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });

        timePicker.setCurrentHour(_currentHour);
        timePicker.setCurrentMinute(_currentMinute);
        timePicker.setEnabled(enableTimeCheckBox.isChecked());
        timePicker.setVisibility((enableTimeCheckBox.isChecked() ? View.VISIBLE : View.INVISIBLE));
    }

    // 새로운 리스너 객체 설정 메소드 정의
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener onDateTimeChangedListener) {
        this.onDateTimeChangedListener = onDateTimeChangedListener;
    }

    public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int currentHour, int currentMinute) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
        timePicker.setCurrentHour(currentHour);
        timePicker.setCurrentMinute(currentMinute);
    }

    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    public void setIs24HourView(final boolean is24HourView) {
        timePicker.setIs24HourView(is24HourView);
    }

    public int getYear() {
        return datePicker.getYear();
    }

    public int getMonth() {
        return datePicker.getMonth();
    }

    public int getDayOfMonth() {
        return datePicker.getDayOfMonth();
    }

    public int getCurrentHour() {
        return timePicker.getCurrentHour();
    }

    public int getCurrentMinute() {
        return timePicker.getCurrentMinute();
    }

    public boolean enableTime() {
        return enableTimeCheckBox.isChecked();
    }
}