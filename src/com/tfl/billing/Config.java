package com.tfl.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Config {

    private final BigDecimal peakLongJourneyPrice;
    private final BigDecimal peakShortJourneyPrice;
    private final BigDecimal offPeakLongJourneyPrice;
    private final BigDecimal offPeakShortJourneyPrice;
    private final int longJourneyDurationInMinutes;
    private final int secondsInAMinute;
    private final BigDecimal offPeakCap;
    private final BigDecimal peakCap;

    private final int millisecondsInASecond;
    private HashMap<String, String> rawConstants = new HashMap<>();

    private ArrayList<Peak> peaks = new ArrayList<>();

    public Config() {
        ConfigReader configReader = new ConfigReader();
        rawConstants = configReader.getRawConstants();

        String rawPeakLongJourneyPrice = rawConstants.get("PEAK_LONG_JOURNEY_PRICE");
        peakLongJourneyPrice = new BigDecimal(rawPeakLongJourneyPrice);

        String rawPeakShortJourneyPrice = rawConstants.get("PEAK_SHORT_JOURNEY_PRICE");
        peakShortJourneyPrice = new BigDecimal(rawPeakShortJourneyPrice);

        String rawOffPeakLongJourneyPrice = rawConstants.get("OFF_PEAK_LONG_JOURNEY_PRICE");
        offPeakLongJourneyPrice = new BigDecimal(rawOffPeakLongJourneyPrice);

        String rawOffPeakShortJourneyPrice = rawConstants.get("OFF_PEAK_SHORT_JOURNEY_PRICE");
        offPeakShortJourneyPrice = new BigDecimal(rawOffPeakShortJourneyPrice);

        String rawLongJourneyDurationInMinutes = rawConstants.get("LONG_JOURNEY_DURATION_IN_MINUTES");
        longJourneyDurationInMinutes = Integer.parseInt(rawLongJourneyDurationInMinutes);

        String rawSecondsInAMinute = rawConstants.get("SECONDS_IN_A_MINUTE");
        secondsInAMinute = Integer.parseInt(rawSecondsInAMinute);

        String rawOffPeakCap = rawConstants.get("OFF_PEAK_CAP");
        offPeakCap = new BigDecimal(rawOffPeakCap);

        String rawPeakCap = rawConstants.get("PEAK_CAP");
        peakCap = new BigDecimal(rawPeakCap);

        String rawMillisecondsInASecond = rawConstants.get("MILLISECONDS_IN_A_SECOND");
        millisecondsInASecond = Integer.parseInt(rawMillisecondsInASecond);

        for (HashMap.Entry<String, String> rawConstant : rawConstants.entrySet()) {
            String key = rawConstant.getKey();
            String value = rawConstant.getValue();
            if (key.contains("_PEAK_START")) {
                String peakName = key.substring(0, key.indexOf("_PEAK_START"));
                String endKey = peakName + "_PEAK_END";
                Peak peak = new Peak(value, rawConstants.get(endKey));
                peaks.add(peak);
            }
        }
    }

    BigDecimal getPeakLongJourneyPrice() {
        return peakLongJourneyPrice;
    }

    BigDecimal getPeakShortJourneyPrice() {
        return peakShortJourneyPrice;
    }

    BigDecimal getOffPeakLongJourneyPrice() {
        return offPeakLongJourneyPrice;
    }

    BigDecimal getOffPeakShortJourneyPrice() {
        return offPeakShortJourneyPrice;
    }

    int getLongJourneyDurationInMinutes() {
        return longJourneyDurationInMinutes;
    }

    int getSecondsInAMinute() {
        return secondsInAMinute;
    }


    BigDecimal getOffPeakCap() {
        return offPeakCap;
    }

    BigDecimal getPeakCap() {
        return peakCap;
    }

    int getMillisecondsInASecond() {
        return millisecondsInASecond;
    }

    public ArrayList<Peak> getPeaks() {
        return peaks;
    }

}
