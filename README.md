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

License
--------

    Copyright 2015 Chavi Anyó

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

