package Beans;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;


@Sheet(value = "Sheet1")
public class Junctions {

    // Fields
    // ------------------------------------------------------------------------

    @SheetColumn("S No")
    private Integer sNo;

    @SheetColumn("District")
    private String district;

    @SheetColumn("Sub Division")
    private String subDivision;

    @SheetColumn("Station")
    private String station;

    @SheetColumn("Name")
    private String name;

    @SheetColumn("Signal No")
    private String signalNo;

    @SheetColumn("Feed Signals")
    private String feedSignals;

    @SheetColumn("Geo Location")
    private String geoLocations;

    // Constructors
    // ------------------------------------------------------------------------

    public Junctions() {
        super();
    }

    // Getters and Setters
    // ------------------------------------------------------------------------

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignalNo() {
        return signalNo;
    }

    public void setSignalNo(String signalNo) {
        this.signalNo = signalNo;
    }

    public String getFeedSignals() {
        return feedSignals;
    }

    public void setFeedSignals(String feedSignals) {
        this.feedSignals = feedSignals;
    }

    public String getGeoLocations() {
        return geoLocations;
    }

    public void setGeoLocations(String geoLocations) {
        this.geoLocations = geoLocations;
    }

    @Override
    public String toString() {
        return "Junctions [sNo=" + sNo + ", district=" + district + ", subDivision=" + subDivision + ", station="
                + station + ", name=" + name + ", signalNo=" + signalNo + ", feedSignals=" + feedSignals
                + ", geoLocations=" + geoLocations + "]";
    }

}
