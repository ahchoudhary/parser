package com.ef.common;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by husain on 9/19/18.
 */
public class ParserUtils {

    public static Date getDate(String inDateString, String dateFormat){
        SimpleDateFormat formatter = new SimpleDateFormat((!StringUtils.isEmpty(dateFormat))? dateFormat : Constants.DATE_FORMAT);
        try {
            return formatter.parse(inDateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
