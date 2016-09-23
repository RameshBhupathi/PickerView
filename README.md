# PickerView
OptionsPickerView Like Ios
Three Types of Options Pickers we
1. Single OptionPicker
2. Double OptionPicker
3. Triple OptionPicker

# Screen Shot
![alt tag](https://github.com/RameshBhupathi/PickerView/blob/master/device-2016-09-23-083825.png)
![alt tag](https://github.com/RameshBhupathi/PickerView/blob/master/two%20options.png)
![alt tag](https://github.com/RameshBhupathi/PickerView/blob/master/device-2016-09-23-084005.png)

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

   compile 'compile 'com.ramesh.mypicker:pickerview:1.0'
 
```


#Thanks to
[Android-PickerView](https://github.com/saiwu-bigkoo/Android-PickerView) 
