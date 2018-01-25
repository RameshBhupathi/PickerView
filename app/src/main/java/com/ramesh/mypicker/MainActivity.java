package com.ramesh.mypicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.MyOptionsPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ramesh Bhupathi on 17/05/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTime, singleTVOptions, twoTVOptions, threeTVOptions;
    TimePickerView pvTime;
    MyOptionsPickerView singlePicker, twoPicker, threePicker;
    View vMasker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vMasker = findViewById(R.id.vMasker);
        tvTime = (TextView) findViewById(R.id.tvTime);
        singleTVOptions = (TextView) findViewById(R.id.tvsingleOptions);
        twoTVOptions = (TextView) findViewById(R.id.tvTwoOptions);
        threeTVOptions = (TextView) findViewById(R.id.tvThreeOptions);

        singleTVOptions.setOnClickListener(this);
        twoTVOptions.setOnClickListener(this);
        threeTVOptions.setOnClickListener(this);

        /*pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        Log.v("dateee", "" + new Date());
        pvTime.setTime(new Date(),TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        //DatePicker
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                Log.v("selected Date", "" + date);
                tvTime.setText(getTime(date));
            }
        });*/
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });

        //Single String Picker
        singlePicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        singlePicker.setPicker(items);
        singlePicker.setTitle("Single Picker");
        singlePicker.setBtnCancelText("取消");
        singlePicker.setBtnSubmitText("确认");
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
        singleTVOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                singlePicker.show();
            }
        });


        //Two Options PickerView
        twoPicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> twoItemsOptions1 = new ArrayList<String>();
        twoItemsOptions1.add("AA");
        twoItemsOptions1.add("BB");
        twoItemsOptions1.add("CC");
        twoItemsOptions1.add("DD");
        twoItemsOptions1.add("EE");
        final ArrayList<String> twoItemsOptions2 = new ArrayList<String>();
        twoItemsOptions2.add("00");
        twoItemsOptions2.add("11");
        twoItemsOptions2.add("22");
        twoItemsOptions2.add("33");
        twoItemsOptions2.add("44");

        twoPicker.setPicker(twoItemsOptions1, twoItemsOptions2, false);
        twoPicker.setTitle("Picker");
        twoPicker.setBtnCancelText("取消");
        twoPicker.setBtnSubmitText("确认");
        twoPicker.setCyclic(false, false, false);
        twoPicker.setSelectOptions(0, 0);
        twoPicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // twoTVOptions.setText("Two Options " + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2));
                vMasker.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2), Toast.LENGTH_SHORT).show();
            }
        });
        twoTVOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                twoPicker.show();
            }
        });


        //Three Options PickerView
        threePicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> threeItemsOptions1 = new ArrayList<String>();
        threeItemsOptions1.add("AA");
        threeItemsOptions1.add("BB");
        threeItemsOptions1.add("CC");
        threeItemsOptions1.add("DD");
        threeItemsOptions1.add("EE");
        final ArrayList<String> threeItemsOptions2 = new ArrayList<String>();
        threeItemsOptions2.add("00");
        threeItemsOptions2.add("11");
        threeItemsOptions2.add("22");
        threeItemsOptions2.add("33");
        threeItemsOptions2.add("44");

        final ArrayList<String> threeItemsOptions3 = new ArrayList<String>();
        threeItemsOptions3.add("FF");
        threeItemsOptions3.add("GG");
        threeItemsOptions3.add("HH");
        threeItemsOptions3.add("II");
        threeItemsOptions3.add("JJ");

        threePicker.setPicker(threeItemsOptions1, threeItemsOptions2, threeItemsOptions3, false);
        threePicker.setTitle("Picker");
        threePicker.setBtnCancelText("取消");
        threePicker.setBtnSubmitText("确认");
        threePicker.setCyclic(false, false, false);
        threePicker.setSelectOptions(0, 0, 0);
        threePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                vMasker.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + threeItemsOptions1.get(options1) + " " + threeItemsOptions2.get(option2) + " " + threeItemsOptions3.get(options3), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvsingleOptions:
                singlePicker.show();
                break;
            case R.id.tvTwoOptions:
                twoPicker.show();
                break;
            case R.id.tvThreeOptions:
                threePicker.show();
                break;

        }
    }
}

