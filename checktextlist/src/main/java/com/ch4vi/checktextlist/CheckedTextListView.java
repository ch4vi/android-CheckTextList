package com.ch4vi.checktextlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * Created by Chavi on 20/1/15
 */
public class CheckedTextListView extends LinearLayout implements CheckListAdapter.CheckListAdapterCallback {
	private static final String TAG = CheckedTextListView.class.getSimpleName();

    private CheckListAdapter checkListAdapter;
    private ArrayList<CheckItem> selectedItems = new ArrayList<>();
    private int checkListType = CommonConstants.CheckListType.MULTI;
    private ListView listView;
    private CheckListCallback callback;
//    private CheckListController checkListController = new CheckListController();


	public CheckedTextListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		setOrientation(VERTICAL);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.check_list, this, true);

        listView = (ListView)findViewById(R.id.check_list_lv);

        final ArrayList<CheckItem>list = new ArrayList<>();
		checkListAdapter = new CheckListAdapter(getContext(),
                android.R.layout.simple_list_item_1,list, this);
        listView.setAdapter(checkListAdapter);
	}

    @Override
    public void itemsSelected(ArrayList<CheckItem> items) {

        if(checkListType == CommonConstants.CheckListType.SINGLE){
            if(items.size() > 1){
                View view = listView.getRootView();
                singleToggle(view, items.get(0).getTitle());
                items.remove(0);
            }
        }
        this.selectedItems = items;

        if(callback != null){
            callback.itemsSelected(this.selectedItems);
        }
    }

    private void singleToggle(View view, String tag){
        if(view instanceof CheckedTextView) {

            CheckedTextView check = ((CheckedTextView)view);
            if(check.getText().equals(tag)){
                check.setChecked(false);
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                singleToggle(innerView, tag);
            }
        }
    }

    public ArrayList<CheckItem> getSelectedItems(){

        if(checkListType == CommonConstants.CheckListType.SINGLE){

            ArrayList<CheckItem> selectedItem = new ArrayList<>();
            if(selectedItems.size() > 1){
                selectedItem.add(selectedItems.get(1));
                return selectedItem;

            }else {
                selectedItem.add(selectedItems.get(0));
                return selectedItem;
            }

        }else{
            return selectedItems;
        }
    }

    public void setList(ArrayList<CheckItem> list){
        checkListAdapter = new CheckListAdapter(getContext(),
                android.R.layout.simple_list_item_1,list, this);
        checkListAdapter.notifyDataSetChanged();
        listView.setAdapter(checkListAdapter);
    }

    public void setStringList(ArrayList<String> list){

        ArrayList<CheckItem> checkItems = CheckListController.toCheckItemList(list);
        checkListAdapter = new CheckListAdapter(getContext(),
                android.R.layout.simple_list_item_1,checkItems, this);
        checkListAdapter.notifyDataSetChanged();
        listView.setAdapter(checkListAdapter);
    }

    public void setCheckListType(int checkListType){
        this.checkListType = checkListType;
        checkListAdapter.setCheckListType(checkListType);
    }

    public void setCallback(CheckListCallback callback){
        this.callback = callback;
    }
}
