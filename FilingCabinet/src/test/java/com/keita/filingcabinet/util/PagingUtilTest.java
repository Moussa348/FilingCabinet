package com.keita.filingcabinet.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagingUtilTest {

    @Test
    void convertDateToLocalDateTime(){
        //ARRANGE
        LocalDateTime date = LocalDateTime.now();

        //ACT
        LocalDateTime convertedDate = DateUtil.convertDateToLocalDateTime(new Date());

        //ASSERT
        assertEquals(date.getMonth(),convertedDate.getMonth());
        assertEquals(date.getDayOfWeek(),convertedDate.getDayOfWeek());
        assertEquals(date.getYear(),convertedDate.getYear());
    }
}
