package com.vngrs.assignment.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author alperkopuz
 * Util class used for date operations
 */
public final class DateUtil {


    /***
     * Converts java.util.Date to Local Date
     * @param dateToConvert
     * @return
     */
    public static final LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
