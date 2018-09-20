package com.ef;

import com.ef.common.Constants;
import com.ef.common.exception.ProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by husain on 9/18/18.
 */
@Component
public class ParserStarter implements ApplicationRunner {

    @Autowired
    LogFileProcessor logFileProcessor;

    @Autowired
    LogAnalyzer logAnalyzer;

    @Override
    public void run(ApplicationArguments cmdLineArgs) throws Exception {

        logFileProcessor.cleanup();
        List<String> accessLogLocation =  cmdLineArgs.getOptionValues(Constants.ACCESS_LOG);

        String startDateString = (cmdLineArgs.getOptionValues(Constants.START_DATE) != null)? cmdLineArgs.getOptionValues(Constants.START_DATE).get(0): "" ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);

        String duration =  (cmdLineArgs.getOptionValues(Constants.DURATION) != null)? cmdLineArgs.getOptionValues(Constants.DURATION).get(0): Constants.DAILY ;;
        Long threshold =  (cmdLineArgs.getOptionValues(Constants.THRESHOLD) != null)? Long.valueOf(cmdLineArgs.getOptionValues(Constants.THRESHOLD).get(0)): 0l;
        if(accessLogLocation == null || accessLogLocation.isEmpty()){
            throw new ProcessingException("Unable to read File Location");
        }else {
            logFileProcessor.processFile(accessLogLocation.get(0));
        }

        logAnalyzer.analyze(startDate, duration, threshold);

    }
}
