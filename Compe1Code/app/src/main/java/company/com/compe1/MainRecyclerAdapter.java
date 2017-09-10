package company.com.compe1;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private OnItemClickListener mItemClickListener;
    private List<String> mWords;
    private List<Integer> mColorList;
    private List<Integer> mColors;
    private Context mContext;
    private Random rnd;

    private List<String> originWords;
    private List<Integer> originColors;
    public MainRecyclerAdapter(Context context, List<String> words, List<Integer> colorList) {
        rnd = new Random();
        mContext = context;
        originWords = words;
        originColors = colorList;
        mWords = new ArrayList<>(words);
        mColorList = new ArrayList<>(colorList);
        generateColors();
    }
    public void refresh(){
        mWords = new ArrayList<>(originWords);
        mColorList = new ArrayList<>(originColors);
        generateColors();
    }

    public void generateColors(){
        if (mColorList == null || mColorList.size() <= 0 || mWords == null || mWords.size() <= 0) return;
        int length = getItemCount();
        mColors = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mColors.add(mColorList.get(rnd.nextInt(mColorList.size())));
        }
    }

    public void deleteEntry(int entry){
        if (getItemCount() > entry){
            mWords.remove(entry);
            mColors.remove(entry);
            this.notifyItemRemoved(entry);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText( mWords.get(position) );
        holder.textView.setTextColor( ContextCompat.getColor(mContext, mColors.get(position)) );
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_item_text);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.recycler_item_text:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(itemView, getAdapterPosition());
                    }
                    break;
                default:

            }

        }
    }


    public void setOnItemClickListener(final OnItemClickListener mOnImageClickListener) {
        this.mItemClickListener = mOnImageClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
