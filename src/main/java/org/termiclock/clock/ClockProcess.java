package org.termiclock.clock;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.termiclock.number.Number;
import org.termiclock.util.ArrayUtils;
import org.termiclock.util.JsonParser;

import java.text.SimpleDateFormat;
public class ClockProcess {
    private Timestamp dateTime;
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat dateFormatter;
    private String font;
    private List<List<String>> timeDots;
    private List<List<String>> text;

    public ClockProcess() throws Exception{
        JsonParser parser = new JsonParser();
        this.dateTime = new Timestamp(Date.from(Instant.now()).getTime());
        this.timeFormatter = new SimpleDateFormat("HH:mm:ss");
        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        this.font = "hashtag";
        this.timeDots = parser.getTimeDots(this.font);
    }

    public void launch()throws Exception {

        while (true) {
            this.printTime();
            Thread.sleep(1000);
            this.dateTime.setTime(this.dateTime.getTime() + 1000);
        }
    }

    public String getTimeAsString() {
        return this.timeFormatter.format(this.dateTime);
    }

    public String getDateAsString() {
        return this.dateFormatter.format(new Date(this.dateTime.getTime()));
    }
    private void printTime()throws Exception{
        String time = this.getTimeAsString();

        String[] timeParts = time.split(":");

        Number hour = new Number(timeParts[0], this.font); 
        Number minutes = new Number(timeParts[1], this.font); 
        Number seconds = new Number(timeParts[2], this.font); 

        List<List<String>> toPrinList = null;

        toPrinList = ArrayUtils.mergeArray(toPrinList, hour.getText());
        toPrinList = ArrayUtils.mergeArray(toPrinList, this.timeDots);

        toPrinList = ArrayUtils.mergeArray(toPrinList, minutes.getText());
        toPrinList = ArrayUtils.mergeArray(toPrinList, this.timeDots);

        
        toPrinList = ArrayUtils.mergeArray(toPrinList, seconds.getText());

        ArrayUtils.printArray(toPrinList);
        ArrayUtils.clearVariable(hour, minutes, seconds,time,timeParts,toPrinList);
    }

    
}
