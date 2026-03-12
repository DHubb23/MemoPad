package com.example.memopad.Model;

import androidx.annotation.NonNull;

public class Memo {
    private int id;
    private String memo;

    public Memo(int id, String memo){
        this.id = id;
        this.memo = memo;
    }
    public Memo(String memo) {
        this.memo = memo;
    }

    public int getId(){
        return id;
    }

    public String getMemo(){
        return memo;
    }

    @NonNull
    @Override
    public String toString(){
        return id + ": " + memo;

    }
}
