package hanssem.p377;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;


public class CalendarMonthView extends GridView {   // 그리드뷰를 상속하여 클래스 정의

    private OnDataSelectionListener selectionListener;

    CalendarMonthAdapter adapter;

    public CalendarMonthView(Context context) {
        super(context);

        init();
    }

    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    // 뷰를 초기화하는 메소드를 만들고, 그 안에서 칼럼의 개수 설정
    private void init() {
        setBackgroundColor(Color.GRAY);
        setVerticalSpacing(1);
        setHorizontalSpacing(1);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        // 칼럼의 갯수 설정
        setNumColumns(7);

        // 그리드뷰의 원래 이벤트 처리 리스너 설정
        setOnItemClickListener(new OnItemClickAdapter());
    }

    public void setAdapter(BaseAdapter adapter) {
        super.setAdapter(adapter);

        this.adapter = (CalendarMonthAdapter) adapter;
    }

    public BaseAdapter getAdapter() {
        return (BaseAdapter)super.getAdapter();
    }

    public void setOnDataSelectionListener(OnDataSelectionListener listener) {
        this.selectionListener = listener;
    }

    public OnDataSelectionListener getOnDataSelectionListener() {
        return selectionListener;
    }

    class OnItemClickAdapter implements OnItemClickListener {

        public OnItemClickAdapter() {

        }

        public void onItemClick(AdapterView parent, View v, int position, long id) {

            if (adapter != null) {
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetInvalidated();
            }

            if (selectionListener != null) {
                selectionListener.onDataSelected(parent, v, position, id);
            }


        }

    }

}