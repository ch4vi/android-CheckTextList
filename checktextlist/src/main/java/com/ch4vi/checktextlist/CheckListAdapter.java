package com.ch4vi.checktextlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;


import java.util.ArrayList;

/**
 * Created by Chavi.
 */
public class CheckListAdapter extends ArrayAdapter<CheckItem> {
    private static final String TAG = CheckListAdapter.class.getSimpleName();


    private ArrayList<CheckItem> items = new ArrayList<>();
    private ArrayList<CheckItem> selectedItems = new ArrayList<>();
    private int checkListType = CommonConstants.CheckListType.MULTI;

    private CheckListAdapterCallback listener;
    public interface CheckListAdapterCallback {
        void itemsSelected(ArrayList<CheckItem> items);

    }

    private Context context;

    public CheckListAdapter(Context context, int resource, ArrayList<CheckItem> items,
                            CheckListAdapterCallback listener) {
        super(context, resource);
        this.context = context;
        this.items = items;
        this.listener = listener;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CheckItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCheckListType(int checkListType){
        this.checkListType = checkListType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		final ViewHolder holder;
		final CheckItem title = items.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.check_row, null);
			holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkRow.setText(title.getTitle());
        holder.checkRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.checkRow.toggle();

                if (checkListType == CommonConstants.CheckListType.SINGLE) {

                    if (holder.checkRow.isChecked()) {

                        if (selectedItems.size() <= 1) {
                            selectedItems.add(title);
                        } else if (selectedItems.size() > 0) {
                            selectedItems.remove(0);
                            selectedItems.add(title);
                        }

                    } else {
                        selectedItems.remove(title);
                    }

                } else {

                    if (holder.checkRow.isChecked()) {
                        selectedItems.add(title);
                    } else {
                        selectedItems.remove(title);
                    }
                }

                if (listener != null) {
                    listener.itemsSelected(selectedItems);
                }
            }
        });

        return convertView;
    }


    static class ViewHolder {
        CheckedTextView checkRow;

        ViewHolder(View view) {
            checkRow = (CheckedTextView)view.findViewById(R.id.check_row);
        }
    }
}
