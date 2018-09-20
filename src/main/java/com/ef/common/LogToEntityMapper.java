package com.ef.common;

import com.ef.entity.LogEntry;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by husain on 9/18/18.
 */
public class LogToEntityMapper {
    public static LogEntry mapLineToLogEntry(String line){
        LogEntry returnEntry = new LogEntry();
        StringTokenizer tokenizer = new StringTokenizer(line, "|");
        returnEntry.setEntryDateTime(ParserUtils.getDate(tokenizer.nextToken(),""));
        returnEntry.setIp(tokenizer.nextToken());
        returnEntry.setHttpMethod(tokenizer.nextToken());
        returnEntry.setHttpStatus(Integer.valueOf(tokenizer.nextToken()));
        returnEntry.setLogDetails(tokenizer.nextToken());
        return returnEntry;
    }

}
