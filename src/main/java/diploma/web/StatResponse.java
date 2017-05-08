package diploma.web;

import diploma.statistics.MacroClusteringStatistics;
import diploma.statistics.TimeAndProcessedPerUnit;

import java.util.List;

/**
 * @author Никита
 */
public class StatResponse {
    private MacroClusteringStatistics statistics;
    private List<TimeAndProcessedPerUnit> timeAndProcessedPerUnitList;

    public StatResponse(MacroClusteringStatistics statistics, List<TimeAndProcessedPerUnit> timeAndProcessedPerUnitList) {
        this.statistics = statistics;
        this.timeAndProcessedPerUnitList = timeAndProcessedPerUnitList;
    }

    public MacroClusteringStatistics getStatistics() {
        return statistics;
    }

    public List<TimeAndProcessedPerUnit> getTimeAndProcessedPerUnitList() {
        return timeAndProcessedPerUnitList;
    }
}
