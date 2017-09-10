package company.com.compe1;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Has a leading ""
    public static final String[] WORD = ((String)"SIGMOBILE").split("");

    RecyclerView mRecyclerView;
    MainRecyclerAdapter mRecyclerAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        OverScrollLLM llm = new OverScrollLLM(this, LinearLayoutManager.VERTICAL, false);
        llm.setOverScrollListener(overScrollListener);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        List<Integer> mColorList = new ArrayList<>();
        mColorList.add(R.color.blue);
        mColorList.add(R.color.purple);
        mColorList.add(R.color.green);
        mColorList.add(R.color.red);
        mColorList.add(R.color.orange);
        List<String> mWords = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mWords.add(WORD[i % (WORD.length - 1) + 1]);
        }

        mRecyclerAdapter = new MainRecyclerAdapter(this, mWords, mColorList);
        mRecyclerAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });


    }

    public void refresh(){
        mRecyclerAdapter.refresh();
        mRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    MainRecyclerAdapter.OnItemClickListener onItemClickListener = new MainRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mRecyclerAdapter.deleteEntry(position);
        }
    };
    OverScrollLLM.OverScrollListener overScrollListener = new OverScrollLLM.OverScrollListener() {
        @Override
        public void onTopOverScroll(int magnitude) {

        }

        @Override
        public void onBottomOverScroll(int magnitude) {

        }
    };

}
