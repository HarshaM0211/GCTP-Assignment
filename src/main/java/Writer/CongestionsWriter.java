package Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(CongestionsWriter.class);

    // Output filepaths
    final String fromSignalOutputPath = "outputs/FromSignalsCount.xlsx";
    final String toSignalOutputPath = "outputs/ToSignalsCount.xlsx";

    // Input filepaths
    final String junctions_file_path = "src/main/resources/junctions_chennai.xlsx";
    final String congestion_history_file_path = "src/main/resources/congestion_history_jun_2023_to_jan_2024.xlsx";

    @Test
    public void writeFromSignalCounts() throws SpreadsheetReadException, IOException {

        final SpreadsheetWriter ssw = new SpreadsheetWriter(fromSignalOutputPath);

        // List to write fields into Excel File
        final List<JunctionsCount> fromJuncnsCountRecords = new ArrayList<JunctionsCount>();

        // Reading Excel files
        final XlsxReader reader = new XlsxReader();

        final List<Junctions> junctions = reader.read(Junctions.class, new File(junctions_file_path));
        final List<CongestionHistory> congHistoryList =
                reader.read(CongestionHistory.class, new File(congestion_history_file_path));


        // Map to LOG counts (in console)
        final Map<String, Double> signalNoCountMap = new HashMap<String, Double>();

        // Both files row data into Nested loops
        for (Junctions jncn : junctions) {
            if (StringUtils.isEmpty(jncn.getSignalNo())) {
                continue;
            }

            double count = 0;
            for (CongestionHistory congHistory : congHistoryList) {
                if (jncn.getSignalNo().equals(congHistory.getJunctionNo())) { // Incrementing count on equality
                    count++;
                    signalNoCountMap.put(jncn.getSignalNo(), count);
                }
            }
            LOGGER.info("#FROM-Signal No. :: NoOf. Congestions = {} :: {}", jncn.getSignalNo(),
                    signalNoCountMap.get(jncn.getSignalNo()));


            fromJuncnsCountRecords.add(new JunctionsCount(jncn.getSignalNo(), jncn.getName(), Double.valueOf(count)));
        }

        ssw.addSheet(JunctionsCount.class, fromJuncnsCountRecords, true);
        ssw.write();
    }


    @Test
    public void writeToSignalCounts() throws SpreadsheetReadException, IOException {

        final SpreadsheetWriter ssw = new SpreadsheetWriter(toSignalOutputPath);

        // List to write fields into Excel File
        final List<JunctionsCount> toJuncnsCountRecords = new ArrayList<JunctionsCount>();

        // Reading Excel files
        final XlsxReader reader = new XlsxReader();

        final List<Junctions> junctions = reader.read(Junctions.class, new File(junctions_file_path));
        final List<CongestionHistory> congHistoryList =
                reader.read(CongestionHistory.class, new File(congestion_history_file_path));

        // Map to LOG counts (in console)
        final Map<String, Double> signalNoCountMap = new HashMap<String, Double>();

        // Both files row data into Nested loops
        for (Junctions jncn : junctions) {
            if (StringUtils.isEmpty(jncn.getSignalNo())) {
                continue;
            }

            double count = 0;
            for (CongestionHistory congHistory : congHistoryList) {
                if (jncn.getSignalNo().equals(congHistory.getFeedJunctionNo())) { // Incrementing count on equality
                    count++;
                    signalNoCountMap.put(jncn.getSignalNo(), count);
                }
            }
            LOGGER.info("#To-Signal No. :: NoOf. Congestions = {} :: {}", jncn.getSignalNo(),
                    signalNoCountMap.get(jncn.getSignalNo()));

            toJuncnsCountRecords.add(new JunctionsCount(jncn.getSignalNo(), jncn.getName(), Double.valueOf(count)));
        }

        ssw.addSheet(JunctionsCount.class, toJuncnsCountRecords, true);
        ssw.write();
    }

}

