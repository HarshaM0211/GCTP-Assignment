package WriteBeans;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;


@Sheet(value = "Junctions Count")
public class JunctionsCount {

    @SheetColumn(value = "Signal No")
    private String signalNo;

    @SheetColumn(value = "Name")
    private String name;

    @SheetColumn(value = "No Of Congestions")
    private Double count;

    public JunctionsCount() {
        super();
    }

    public JunctionsCount(String signalNo, String name, Double count) {
        super();
        this.signalNo = signalNo;
        this.name = name;
        this.count = count;
    }

    public String getSignalNo() {
        return signalNo;
    }

    public void setSignalNo(String signalNo) {
        this.signalNo = signalNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

}
