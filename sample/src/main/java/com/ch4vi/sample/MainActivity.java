package com.ch4vi.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ch4vi.checktextlist.CheckItem;
import com.ch4vi.checktextlist.CheckedTextListView;
import com.ch4vi.checktextlist.CommonConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView itemSelectedDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemSelectedDesc = (TextView)findViewById(R.id.item_selected_description);

        final ArrayList<String> stringList = new ArrayList<String>(){{
            add("William Hartnell");
            add("Patrick Troughton");
            add("Jon Pertwee");
            add("Tom Baker");
            add("Peter Davison");
            add("Colin Baker");
            add("Sylvester McCoy");
            add("Paul McGann");
            add("Christopher Eccleston");
            add("David Tennant");
            add("Matt Smith");
            add("Peter Capaldi");
        }};

        Button simple = (Button)findViewById(R.id.simple);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheckListActivity.class);
                startActivity(i);
            }
        });

        Button multi = (Button)findViewById(R.id.multi);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = getSimpleDialog(v.getId(), CommonConstants.CheckListType.MULTI,
                        "Who is your favorite Doctor?", stringList);
                d.show();
            }
        });
    }

    private void onDialogDismiss(ArrayList<CheckItem> items) {
        if (items != null && !items.isEmpty()) {
            StringBuilder build = new StringBuilder();
            build.append(getString(R.string.item_selected_title)).append("\n");
            for(CheckItem item : items){
                build.append(item.getTitle())
                        .append("\n");
            }
            itemSelectedDesc.setText(build.toString());

        } else {
            itemSelectedDesc.setText(getString(R.string.item_no_selected));
        }
    }


    public Dialog getSimpleDialog(final int dialogId, int type,String title, ArrayList<String> list) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View content = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_check_text_list, null);

        final CheckedTextListView checkList = (CheckedTextListView) content.findViewById(R.id.check_list);
        checkList.setStringList(list);
        checkList.setCheckListType(type);

        builder.setView(content);
        builder.setTitle(title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(dialogId == R.id.simple){
                            //It's only an example, R.id.simple never call this method

                        }else if(dialogId == R.id.multi){
                            onDialogDismiss(checkList.getSelectedItems());
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}
