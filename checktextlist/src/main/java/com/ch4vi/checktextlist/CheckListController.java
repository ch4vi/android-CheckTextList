package com.ch4vi.checktextlist;

import java.util.ArrayList;

/**
 * Created by Chavi on 19/05/2015,17:13
 */
public class CheckListController {
    public static final String TAG = CheckListController.class.getSimpleName();

    public static ArrayList<CheckItem> toCheckItemList(ArrayList<String> list){
        ArrayList<CheckItem> checkItems = new ArrayList<>(list.size());
        for (String title : list){
            CheckItem checkItem = new CheckItem(title);
            checkItems.add(checkItem);
        }
        return checkItems;

    }



}
