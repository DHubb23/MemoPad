package com.example.memopad.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.memopad.Model.DAO.DAOFactory;
import com.example.memopad.Model.DAO.MemoDAO;
import com.example.memopad.Model.Memo;
import com.example.memopad.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private MemoAdapter adapter;
    private DAOFactory daoFactory;
    private MemoDAO memoDAO;
    private Integer selectedMemoId = null;
    private final MemoPadItemClickHandler itemClick = new MemoPadItemClickHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.addButton.setOnClickListener(this);
        binding.delButton.setOnClickListener(this);

        daoFactory = new DAOFactory(this);
        memoDAO = daoFactory.getMemoDao();
        refreshMemos();
    }
    public MemoPadItemClickHandler getItemClick() {
        return itemClick; }

    @Override
        public void onClick(View view){
            int id = view.getId();
            if (id == binding.addButton.getId()){
                String memoText = binding.memoInput.getText().toString();

                if (!memoText.isEmpty()){
                    memoDAO.create(new Memo(memoText));                    binding.memoInput.setText("");
                    refreshMemos();
                }
            }
            if (id == binding.delButton.getId()){
                if (selectedMemoId != null){
                    memoDAO.delete(selectedMemoId);
                    selectedMemoId = null;
                    refreshMemos();
                }
            }
    }

    private void refreshMemos() {
        List<Memo> memos = daoFactory.getMemoDao().list();

        if (adapter == null) {
            adapter = new MemoAdapter(this, memos);
            binding.recyclerView.setAdapter(adapter);
        } else {
            adapter.setData(memos);
        }
    }

    private class MemoPadItemClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = binding.recyclerView.getChildLayoutPosition(v);

            if (adapter != null) {
                Memo memo = adapter.getItem(position);
                selectedMemoId = memo.getId();
                Toast.makeText(v.getContext(), "Selected Memo: " + selectedMemoId, Toast.LENGTH_SHORT).show();
            }
        }
    }
}