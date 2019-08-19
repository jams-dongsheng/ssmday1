package cn.itcast.ssm.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//全局日期类型转换
public class DateConverter implements Converter<String, Date> {
    public Date convert(String s) {
        //SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");



        Date date = null;
        try {
            date = format.parse(s);
            // date1 = format2.parse(s);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
