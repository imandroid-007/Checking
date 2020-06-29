package com.example.realestate;

import java.util.ArrayList;

public class POJO_Main {

    ArrayList<POJO_PropList> list;
    String title;

    public POJO_Main(ArrayList<POJO_PropList> list, String title) {
        this.list = list;
        this.title = title;
    }

    public ArrayList<POJO_PropList> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }
}
