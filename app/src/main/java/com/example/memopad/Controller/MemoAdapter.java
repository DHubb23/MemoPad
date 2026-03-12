package com.example.memopad.Controller;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import com.example.memopad.Model.Memo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.memopad.R;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private List<Memo> memoList;
    private final MainActivity activity;


    public MemoAdapter(MainActivity activity, List<Memo> memos) {
        this.activity = activity;
        this.memoList = memos;
    }
    public void setData(List<Memo> memos) {
        this.memoList = memos;
        notifyDataSetChanged();
    }
    public Memo getItem(int position){
        return memoList.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item, parent, false);
        view.setOnClickListener(activity.getItemClick());
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo memo = memoList.get(position);

        holder.bindData(memo);
    }
    @Override
    public int getItemCount(){
        return memoList == null ? 0 : memoList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView memoText;

        public ViewHolder(View itemView){
            super(itemView);
            memoText = itemView.findViewById(R.id.memoText);
        }
        public void bindData(Memo memo) {
            if (memo != null) {
                memoText.setText(memo.toString());
            }
        }
    }

}
