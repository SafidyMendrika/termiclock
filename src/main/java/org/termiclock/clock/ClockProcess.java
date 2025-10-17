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
    private List<List<String>> dateDots;
    private JsonParser parser;
    
    private boolean justPrint = false;
    private boolean addDate = false;
    private boolean stop = false;

    
    public ClockProcess() throws Exception{
        this.parser = new JsonParser();
        this.dateTime = new Timestamp(Date.from(Instant.now()).getTime());
        this.timeFormatter = new SimpleDateFormat("HH:mm:ss");
        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        this.font = "hashtag";
        this.timeDots = this.parser.getTimeDots(this.font);
    }

    public void launch()throws Exception {
        if (stop) {
            return;
        }
        if(this.addDate){
            this.dateDots = this.parser.getDateDots(this.font);
        }
        if (this.justPrint) {
            this.printTime();
            return;
        }
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

        if (this.addDate) {
            toPrinList = this.addDateToArray(toPrinList);
        }

        toPrinList = ArrayUtils.mergeArray(toPrinList, hour.getText());
        toPrinList = ArrayUtils.mergeArray(toPrinList, this.timeDots);

        toPrinList = ArrayUtils.mergeArray(toPrinList, minutes.getText());
        toPrinList = ArrayUtils.mergeArray(toPrinList, this.timeDots);

        
        toPrinList = ArrayUtils.mergeArray(toPrinList, seconds.getText());

        ArrayUtils.printArray(toPrinList);
        ArrayUtils.clearVariable(hour, minutes, seconds,time,timeParts,toPrinList);
    }

    public static ClockProcess handleArguments(String [] args) throws Exception{
        ClockProcess clockProcess = new ClockProcess();
        for (String arg : args) {
            if (arg.equals("-p")) {
                clockProcess.setJustPrint(true);
            }
            if(arg.equals("-t")){
                clockProcess.setFont("hashtag");
            }
            if (arg.equals("-s")) {
                clockProcess.setFont("star");
            }
            if (arg.equals("-c")) {
                clockProcess.setFont("clock");
            }

            if(arg.equals("-d")){
                clockProcess.addDate(true);
            }
            if (arg.equals("--help") || arg.equals("-h")) {
                ClockProcess.help();
                clockProcess.setStop(true);
            }
        }
        return clockProcess;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setJustPrint(boolean justPrint) {
        this.justPrint = justPrint;
    }

    public void addDate(boolean addDate) {
        this.addDate = addDate;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private List<List<String>> addDateToArray(List<List<String>> list)throws Exception{
        System.out.println("haaaa");
        String date = this.getDateAsString();

        String[] dateparts = date.split("-");

        List<List<String>> result = list;

        Number day = new Number(dateparts[0], this.font); 
        Number month = new Number(dateparts[1], this.font); 
        Number year = new Number(dateparts[2], this.font); 

        result = ArrayUtils.mergeArray(result, day.getText());
        result = ArrayUtils.mergeArray(result, this.dateDots);
        result = ArrayUtils.mergeArray(result, month.getText());
        result = ArrayUtils.mergeArray(result, this.dateDots);
        result = ArrayUtils.mergeArray(result, year.getText());
        ArrayUtils.addCharSpace(result, "         ");

        ArrayUtils.clearVariable(date,dateparts,day,month,year);

        return result;
    }

    public static void help(){
        System.out.println("============================================");
        System.out.println("              TERMICLOCK HELP              ");
        System.out.println("============================================");
        System.out.println("Usage: TermiClock.exe [options]");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  -p            Print the current time once and exit (no live update).");
        System.out.println("  -t            Use the 'hashtag' font style for digits (# symbols (default)).");
        System.out.println("  -s            Use the 'star' font style for digits (* symbols).");
        System.out.println("  -c            Use the 'clock' font style for digits (| - / \\ styles).");
        System.out.println("  -d            Display the current date next to the time.");
        System.out.println("  -h, --help    Show this help message and exit.");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  TermiClock.exe -t -d");
        System.out.println("      Displays the clock using the 'hashtag' font and shows the date.");
        System.out.println();
        System.out.println("  TermiClock.exe -p");
        System.out.println("      Prints the current time once without continuous update.");
        System.out.println();
        System.out.println("============================================");
        System.out.println("Tip: You can combine options, e.g. 'Termiclock.exe -c -d' to use");
        System.out.println("     the clock font and display the date together.");
        System.out.println("============================================");
    }
}
