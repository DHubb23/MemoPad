package com.example.memopad.Model;

public class Memo {
    private int id;
    private String memo;

    public Memo(int id, String memo){
        this.id = id;
        this.memo = memo;
    }
    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getMemo(){
        return memo;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(id).append(": ").append(memo);
        return s.toString();

    }

}
