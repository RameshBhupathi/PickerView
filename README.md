# PickerView [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PickerView-green.svg?style=true)](https://android-arsenal.com/details/1/4399)
**OptionsPickerView** Like Ios
Three Types of Options Pickers 
- Single OptionPicker
- Double OptionPicker
- Triple OptionPicker

# Screen Shot
<img src="https://github.com/RameshBhupathi/PickerView/blob/master/device-2016-09-23-083825.png" width=245,height=245/>
     <img src="https://github.com/RameshBhupathi/PickerView/blob/master/two%20options.png" width=200,height=200 />
<img src="https://github.com/RameshBhupathi/PickerView/blob/master/device-2016-09-23-084005.png" width=200,height=200/>

#How to Use
 ```java
MyOptionsPickerView singlePicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        singlePicker.setPicker(items);
        singlePicker.setTitle("Single Picker");
        singlePicker.setCyclic(false);
        singlePicker.setSelectOptions(0);
        singlePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //  singleTVOptions.setText("Single Picker " + items.get(options1));
                Toast.makeText(MainActivity.this, "" + items.get(options1), Toast.LENGTH_SHORT).show();
                vMasker.setVisibility(View.GONE);
            }
        });
```
##Dependencies
Add this to Main build.gradle
```java
   repositories {
        jcenter()
        maven { url 'https://bintray.com/rameshbhupathi/maven' }
    }
    
```
    
##Add this to app gradle file
```java 

  compile 'com.ramesh.mypicker:pickerview:1.0'
  
```


#Thanks to
[Android-PickerView](https://github.com/saiwu-bigkoo/Android-PickerView) 
#License

    Copyright 2016 Ramesh Bhupathi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
