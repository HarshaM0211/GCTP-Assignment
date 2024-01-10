package Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Beans.CongestionHistory;
import Beans.Junctions;
import WriteBeans.JunctionsCount;
import io.github.millij.poi.SpreadsheetReadException;
import io.github.millij.poi.ss.reader.XlsxReader;
import io.github.millij.poi.ss.writer.SpreadsheetWriter;


public class CongestionsWriter {

    // Fields
    // ------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(CongestionsWriter.class);

    // Output filepaths
    final String sourceSignalOutputPath = "outputs/FromSignalsCount.xlsx";
    final String feedSignalOutputPath = "outputs/ToSignalsCount.xlsx";

    // Input filepaths
    final String junctions_file_path = "src/main/resources/junctions_chennai.xlsx";
    final String congestion_history_file_path = "src/main/resources/congestion_history_jun_2023_to_jan_2024.xlsx";



    @Test
    public void writeSourceSignalCounts() throws SpreadsheetReadException, IOException {

        this.writeSignalsCounts(true);

    }

    @Test
    public void writeFeedSignalCounts() throws SpreadsheetReadException, IOException {

        this.writeSignalsCounts(false);

    }



    private void writeSignalsCounts(boolean isSourceSignal) throws SpreadsheetReadException, IOException {

        // Setting output file for Writer.
        final String outputPath = isSourceSignal ? sourceSignalOutputPath : feedSignalOutputPath;
        final SpreadsheetWriter sourceSignalWriter = new SpreadsheetWriter(outputPath);


        // List to write fields into Excel File
        final List<JunctionsCount> juncnsCountRecords = new ArrayList<JunctionsCount>();

        // Map to LOG counts (in console)
        final Map<String, Double> signalNoCountMap = new HashMap<String, Double>();


        // Reading Excel files
        final XlsxReader xlsxReader = new XlsxReader();
        final List<Junctions> junctions = xlsxReader.read(Junctions.class, new File(junctions_file_path));
        final List<CongestionHistory> congHistoryList =
                xlsxReader.read(CongestionHistory.class, new File(congestion_history_file_path));


        // Both files row data into Nested FOR loops
        for (Junctions jncn : junctions) {

            if (Objects.isNull(jncn) || StringUtils.isBlank(jncn.getSignalNo())) {
                continue;
            }
            
            final String signalNo = jncn.getSignalNo();

            double jncnCount = 0;

            for (CongestionHistory congHistory : congHistoryList) { // Incrementing count on equality

                if (isSourceSignal && Objects.nonNull(congHistory)
                        && signalNo.equals(congHistory.getJunctionNo())) {
                    jncnCount++;
                }

                if ((!isSourceSignal) && Objects.nonNull(congHistory)
                        && signalNo.equals(congHistory.getFeedJunctionNo())) {
                    jncnCount++;
                }
            }
            // Setting count values of SignalNo. into Map & List
            signalNoCountMap.put(signalNo, jncnCount);
            LOGGER.info("Signal No. :: NoOf. Congestions = {} :: {}", signalNo, jncnCount);

            juncnsCountRecords.add(new JunctionsCount(signalNo, jncn.getName(), jncnCount));
        }
        // Loop ends


        // Writing final List<JunctionsCount> into files
        sourceSignalWriter.addSheet(JunctionsCount.class, juncnsCountRecords, true);
        sourceSignalWriter.write();
    }
}
