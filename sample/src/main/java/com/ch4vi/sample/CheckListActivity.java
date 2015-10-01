package com.ch4vi.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ch4vi.checktextlist.CheckItem;
import com.ch4vi.checktextlist.CheckListCallback;
import com.ch4vi.checktextlist.CheckedTextListView;
import com.ch4vi.checktextlist.CommonConstants;

import java.util.ArrayList;

public class CheckListActivity extends AppCompatActivity implements CheckListCallback {

    private TextView itemSelectedDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        itemSelectedDesc = (TextView)findViewById(R.id.item_selected_description);


        CheckedTextListView checkList = (CheckedTextListView)findViewById(R.id.check_list);
        checkList.setList(getCustomList());
        checkList.setCheckListType(CommonConstants.CheckListType.SINGLE);
        checkList.setCallback(this);
    }

    private ArrayList<CheckItem> getCustomList(){

        final ArrayList<Custom> customList = new ArrayList<Custom>(){{
            add(new Custom(1,"William Hartnell","23 November 1963 – 29 October 1966"));
            add(new Custom(1,"Patrick Troughton","29 October 1966 – 21 June 1969"));
            add(new Custom(1,"Jon Pertwee","3 January 1970 – 8 June 1974"));
            add(new Custom(1,"Tom Baker","8 June 1974 – 21 March 1981"));
            add(new Custom(1,"Peter Davison","21 March 1981 – 16 March 1984"));
            add(new Custom(1,"Colin Baker","16 March 1984 – 6 December 1986"));
            add(new Custom(1,"Sylvester McCoy","7 September 1987 – 6 December 1989"));
            add(new Custom(1,"Paul McGann","27 May 1996"));
            add(new Custom(1,"Christopher Eccleston","26 March – 18 June 2005"));
            add(new Custom(1,"David Tennant","18 June 2005 – 1 January 2010"));
            add(new Custom(1,"Matt Smith","1 January 2010 – 25 December 2013"));
            add(new Custom(1,"Peter Capaldi","25 December 2013 – present"));
        }};




        ArrayList<CheckItem> checkCustomList = new ArrayList<>();
        for(Custom custom : customList){
            CheckItem checkItem = new CheckItem(custom.getTitle());
            checkItem.setExtra(custom);
            checkCustomList.add(checkItem);
        }

        return checkCustomList;
    }

    @Override
    public void itemsSelected(ArrayList<CheckItem> items) {
        StringBuilder build = new StringBuilder();
        build.append(getString(R.string.item_selected_title)).append("\n");
        for(CheckItem item : items){
            build.append(item.getTitle())
                    .append(" - ")
                    .append(((Custom)item.getExtra()).getDate())
                    .append("\n");
        }

        itemSelectedDesc.setText(build.toString());
    }
}
