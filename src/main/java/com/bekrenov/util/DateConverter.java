package com.bekrenov.util;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateConverter {

    public static LocalDate toLocalDate(java.util.Date utilDate){
        return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
