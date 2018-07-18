package com.bigkoo.pickerview.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ramesh Bhupathi on 28/05/16.
 */
public class MyWheelTime {
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    private WheelView wv_seconds;
    private WheelView wv_ampm;

    private TimePickerView.Type type;
    public static final int DEFULT_START_YEAR = 1990;
    public static final int DEFULT_END_YEAR = 2100;
    private int startYear = DEFULT_START_YEAR;
    private int endYear = DEFULT_END_YEAR;

    public static int s_year, s_month, s_day, s_h, s_m, s_s, s_ampm;
    public static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    public MyWheelTime(View view) {
        super();
        this.view = view;
        type = TimePickerView.Type.ALL;
        setView(view);
    }

    public MyWheelTime(View view, TimePickerView.Type type) {
        super();
        this.view = view;
        this.type = type;
        setView(view);
    }

    public void setPicker(int first, int second, int third, TimePickerView.Type type) {
        if (type == TimePickerView.Type.YEAR_MONTH_DAY)
            this.setPicker(first, second, third, 0, 0, 0, 0);
        else if (type == TimePickerView.Type.HOUR_MIN_SEC)
            this.setPicker(0, 0, 0, first, second, third, 0);
    }
    /*public void setPicker(int hour, int min, int sec) {
        this.setPicker(0, 0, 0, hour, min, sec);
    }*/

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void setPicker(int year, int month, int day, int h, int m, int s, int ampm) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        String[] month_names_big = {"January", "March", "May", "July", "August", "October", "December"};
        String[] month_names_little = {"April", "June", "September", "November"};

        final List<String> list_big = Arrays.asList(month_names_big);
        final List<String> list_little = Arrays.asList(month_names_little);

        Context context = view.getContext();
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
        wv_year.setLabel(context.getString(R.string.pickerview_year));// 添加文字
        wv_year.setCurrentItem(year - startYear);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        //wv_month.setAdapter(new NumericWheelAdapter(1, 12));
        ArrayList<String> monthNames = new ArrayList<String>(Arrays.asList(months));
        wv_month.setAdapter(new ArrayWheelAdapter(monthNames));
        wv_month.setLabel(context.getString(R.string.pickerview_month));
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) view.findViewById(R.id.day);
        // 判断大小月及是否闰年,用来确定"日"的数据
        String monthName = months[month + 1];
        if (list_big.contains(String.valueOf(monthName))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(monthName))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
        }
        wv_day.setLabel(context.getString(R.string.pickerview_day));
        wv_day.setCurrentItem(day - 1);

        if (type == TimePickerView.Type.HOUR_MIN_APPM) {
            wv_hours = (WheelView) view.findViewById(R.id.hour);
            wv_hours.setAdapter(new NumericWheelAdapter(1, 12));
            wv_hours.setLabel(context.getString(R.string.pickerview_hours));// 添加文字
            wv_hours.setCurrentItem(h);
        } else {
            wv_hours = (WheelView) view.findViewById(R.id.hour);
            wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
            wv_hours.setLabel(context.getString(R.string.pickerview_hours));// 添加文字
            wv_hours.setCurrentItem(h);
        }
        wv_mins = (WheelView) view.findViewById(R.id.min);
        wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
        wv_mins.setLabel(context.getString(R.string.pickerview_minutes));// 添加文字
        wv_mins.setCurrentItem(m);

        wv_seconds = (WheelView) view.findViewById(R.id.sec);
        wv_seconds.setAdapter(new NumericWheelAdapter(0, 59));
        wv_seconds.setLabel(context.getString(R.string.pickerview_seconds));// 添加文字


        String[] amPM = {"AM", "PM"};
        ArrayList<String> ampmlist = new ArrayList<String>(Arrays.asList(amPM));
        wv_ampm = (WheelView) view.findViewById(R.id.amPm);
        wv_ampm.setAdapter(new ArrayWheelAdapter(ampmlist, ampmlist.size()));
        wv_ampm.setLabel(context.getString(R.string.pickerview_ampm));// 添加文字
        wv_ampm.setCurrentItem(ampm);
        // 添加"年"监听
        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int year_num = index + startYear;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem = 30;
                if (list_big
                        .contains(wv_month.getCurrentItem())) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (list_little.contains(wv_month
                        .getCurrentItem())) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }
            }
        };
        // 添加"月"监听
        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int month_num = index + 1;

                int maxItem = 30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(months[index])) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (list_little.contains(months[index])) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if (((wv_year.getCurrentItem() + startYear) % 4 == 0 && (wv_year
                            .getCurrentItem() + startYear) % 100 != 0)
                            || (wv_year.getCurrentItem() + startYear) % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }

            }
        };

        wv_year.setOnItemSelectedListener(wheelListener_year);
        wv_month.setOnItemSelectedListener(wheelListener_month);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 6;
        switch (type) {
            case ALL:
                textSize = textSize * 3;
                break;
            case YEAR_MONTH_DAY:
                textSize = textSize * 4;
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                wv_ampm.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = textSize * 4;
                wv_year.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                wv_ampm.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = textSize * 3;
                wv_year.setVisibility(View.GONE);
                wv_ampm.setVisibility(View.GONE);
                break;
            case YEAR_MONTH:
                textSize = textSize * 4;
                wv_day.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                wv_ampm.setVisibility(View.GONE);
                break;
            case HOUR_MIN_SEC:
                textSize = textSize * 3;
                wv_year.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                wv_ampm.setVisibility(View.GONE);
                break;
            case HOUR_MIN_APPM:
                textSize = textSize * 3;
                wv_year.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                wv_seconds.setVisibility(View.GONE);

        }
        wv_day.setTextSize(textSize);
        wv_month.setTextSize(textSize);
        wv_year.setTextSize(textSize);
        wv_hours.setTextSize(textSize);
        wv_mins.setTextSize(textSize);
        wv_seconds.setTextSize(textSize);
        wv_ampm.setTextSize(textSize);

    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wv_year.setCyclic(cyclic);
        wv_month.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
        wv_hours.setCyclic(cyclic);
        wv_mins.setCyclic(cyclic);
        wv_seconds.setCyclic(cyclic);
        wv_ampm.setCyclic(cyclic);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        if (type == TimePickerView.Type.HOUR_MIN_SEC) {
            sb.append(wv_hours.getCurrentItem()).append(":")
                    .append(wv_mins.getCurrentItem()).append(wv_seconds.getCurrentItem());
        } else if (type == TimePickerView.Type.HOUR_MIN_APPM) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            sb.append(calendar.get(Calendar.YEAR)).append("-")
                    .append(calendar.get(Calendar.MONTH) + 1).append("-")
                    .append(calendar.get(Calendar.DAY_OF_MONTH)).
                    append(" ").append(wv_hours.getCurrentItem()).append(":")
                    .append(wv_mins.getCurrentItem()).append(" ").append(wv_ampm.getCurrentItem());

        } else {
            sb.append((wv_year.getCurrentItem() + startYear)).append("-")
                    .append((wv_month.getCurrentItem() + 1)).append("-")
                    .append((wv_day.getCurrentItem() + 1)).append(" ")
                    .append(wv_hours.getCurrentItem()).append(":")
                    .append(wv_mins.getCurrentItem()).append(wv_seconds.getCurrentItem()).append(wv_ampm.getCurrentItem());

        }
        Log.v("get timee", sb.toString());
        return sb.toString();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setCustomTypeface(@NonNull Typeface typeface) {
        wv_ampm.setFont(typeface);
        wv_day.setFont(typeface);
        wv_hours.setFont(typeface);
        wv_mins.setFont(typeface);
        wv_month.setFont(typeface);
        wv_seconds.setFont(typeface);
        wv_year.setFont(typeface);
    }


}

