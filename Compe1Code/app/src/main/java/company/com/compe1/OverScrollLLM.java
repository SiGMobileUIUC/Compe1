package company.com.compe1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class OverScrollLLM extends LinearLayoutManager {
    private OverScrollListener mOverScrollListener;

    public OverScrollLLM(Context context) {
        super(context);
    }

    public OverScrollLLM(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public OverScrollLLM(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollRange = super.scrollVerticallyBy(dy, recycler, state);
        int overscroll = dy - scrollRange;
        if (overscroll > 0) {
            if (mOverScrollListener != null){
                mOverScrollListener.onBottomOverScroll(overscroll);
            }
        } else if (overscroll < 0) {
            if (mOverScrollListener != null){
                mOverScrollListener.onTopOverScroll(-overscroll);
            }
        } else {

        }
        return scrollRange;
    }

    public void setOverScrollListener(OverScrollListener overScrollListener){
        this.mOverScrollListener = overScrollListener;
    }

    public interface OverScrollListener{
        void onTopOverScroll(int magnitude);
        void onBottomOverScroll(int magnitude);
    }

}