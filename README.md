*CheckTextList*
======

CheckTextList is a library for controlling a list of CheckedTextView. CheckTextList can be used in fragments, or Dialogs
as another View in your layout.
CheckTextList has a callback (Optional) called onClick on each item and it receives a list of selected items.
There are two types of control, SINGLE and MULTI, which allow you to select only one item or more on the same list.

<img src="https://github.com/ch4vi/android-CheckTextList/blob/master/screenshots/fragment.png" width="270" style="margin-right:10px;">
<img src="https://github.com/ch4vi/android-CheckTextList/blob/master/screenshots/dialog.png" width="270">

CheckTextList can be used with Android API 16 and above.

*Setup*
======

Working on it

*Features*
======

Using CheckTextList as a list in a layout:

``` java
CheckedTextListView checkList = (CheckedTextListView)findViewById(R.id.check_list);
checkList.setStringList(getList());
checkList.setCheckListType(CommonConstants.CheckListType.SINGLE);
checkList.setCallback(this);
```

If you want to add an object to an item, you can replace:
``` java
checkList.setStringList(getList());
```
by
``` java
ArrayList<CheckItem> checkList = new ArrayList<>();
for(Custom custom : customList){
            CheckItem checkItem = new CheckItem(custom.getTitle());
            checkItem.setExtra(custom);
            checkList.add(checkItem);
}
checkList.setList(checkList);
```
And in the callback:
``` java
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
```



