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

    public MemoAdapter(List<Memo> memos) {
        this.memoList = memos;
    }
    public void setData(List<Memo> memos) {
        this.memoList = memos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo memo = memoList.get(position);

        holder.setMemo(memo);
        holder.bindData();
    }
    @Override
    public int getItemCount(){
        return memoList == null ? 0 : memoList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Memo memo;
        TextView memoText;

        public ViewHolder(View itemView){
            super(itemView);
            memoText = itemView.findViewById(R.id.memoText);
        }
        public void setMemo(Memo memo) {
            this.memo = memo;
        }
        public void bindData() {
            if (memo != null) {
                memoText.setText(memo.toString());
            }
        }
    }

}
