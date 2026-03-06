package com.example.memopad.Controller;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.memopad.Model.Memo;
import com.example.memopad.Model.MemoDataHandler;
import com.example.memopad.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private MemoDataHandler db;
    private MemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = new MemoDataHandler(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.addButton.setOnClickListener(this);
        refreshMemos();
    }

    @Override
        public void onClick(View view){
            int id = view.getId();
            if (id == binding.addButton.getId()){
                String memoText = binding.memoInput.getText().toString();

                if (!memoText.isEmpty()){
                    db.addMemo(new Memo(memoText));
                    binding.memoInput.setText("");
                    refreshMemos();
                }
            }
    }

    private void refreshMemos() {
        List<Memo> memos = db.getAllMemos();

        if (adapter == null) {
            adapter = new MemoAdapter(memos);
            binding.recyclerView.setAdapter(adapter);
        } else {
            adapter.setData(memos);
            int newPosition = memos.size() - 1;
            adapter.notifyItemInserted(newPosition);
            binding.recyclerView.smoothScrollToPosition(newPosition);
        }
    }
}