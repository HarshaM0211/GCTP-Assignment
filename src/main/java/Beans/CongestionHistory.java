package Beans;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;


@Sheet(value = "Congestion History Data")
public class CongestionHistory {

    // Fields
    // ------------------------------------------------------------------------

    @SheetColumn(value = "District")
    private String district;

    @SheetColumn(value = "Sub Division")
    private String subDivision;

    @SheetColumn(value = "Station")
    private String station;

    @SheetColumn(value = "Junction Signal no.")
    private String junctionNo;

    @SheetColumn(value = "Junction Name")
    private String junctionName;

    @SheetColumn(value = "Feed Junction Signal no.")
    private String feedJunctionNo;

    @SheetColumn(value = "Feed Junction Name")
    private String feedJunctionName;

    @SheetColumn(value = "Delay")
    private String delay;

    @SheetColumn(value = "Date")
    private String date;

    @SheetColumn(value = "Time")
    private String time;

    // Constructors
    // ------------------------------------------------------------------------

    public CongestionHistory() {
        super();
    }

    // Getters and Setters
    // ------------------------------------------------------------------------

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(String subDivision) {
        this.subDivision = subDivision;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getJunctionNo() {
        return junctionNo;
    }

    public void setJunctionNo(String junctionNo) {
        this.junctionNo = junctionNo;
    }

    public String getJunctionName() {
        return junctionName;
    }

    public void setJunctionName(String junctionName) {
        this.junctionName = junctionName;
    }

    public String getFeedJunctionNo() {
        return feedJunctionNo;
    }

    public void setFeedJunctionNo(String feedJunctionNo) {
        this.feedJunctionNo = feedJunctionNo;
    }

    public String getFeedJunctionName() {
        return feedJunctionName;
    }

    public void setFeedJunctionName(String feedJunctionName) {
        this.feedJunctionName = feedJunctionName;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
