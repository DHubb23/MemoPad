package com.example.memopad.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memopad.Model.MemoDataHandler;
import com.example.memopad.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = new MemoDataHandler();

        binding.addButton.setOnClickListener(this);
        binding.delButton.setOnClickListener(this);
    }

    @Override
        public void onClick(View view){
            String tag = view.getTag().toString();
            switch (tag) {
                case "addButton": {
                    String result = db.addMemo();
                    binding.output.setText(result);
                    break;
                }
                case "delButton": {
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected Value: " + tag);
            }
        }
}