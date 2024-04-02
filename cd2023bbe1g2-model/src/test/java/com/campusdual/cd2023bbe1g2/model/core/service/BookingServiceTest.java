package com.campusdual.cd2023bbe1g2.model.core.service;


import com.campusdual.cd2023bbe1g2.model.core.dao.BookingDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @InjectMocks
    BookingService bookingService;

    @Mock
    RoomService roomService;

    @Mock
    BookingDao bookingDao;


    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class BookingServiceQuery {
        @Test
        void bookingQueryTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            Map<String, Object> bookingToQuery = new HashMap<>();
            bookingToQuery.put("id", 1);
            when(daoHelper.query(any(BookingDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = bookingService.bookingQuery(bookingToQuery, new ArrayList<>());
            assertEquals(0, result.getCode());
            assertEquals("", result.getMessage());
            verify(daoHelper, times(1)).query(any(BookingDao.class), anyMap(), anyList());
        }

        @Test
        void bookingQueryTestNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            Map<String, Object> bookingToQuery = new HashMap<>();
            bookingToQuery.put("id", 1);
            when(daoHelper.query(any(BookingDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = bookingService.bookingQuery(bookingToQuery, new ArrayList<>());
            assertEquals(1, result.getCode());
            assertEquals("The booking doesn't exist", result.getMessage());
            verify(daoHelper, times(1)).query(any(BookingDao.class), anyMap(), anyList());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class BookingServiceInsert {
        @Test
        void bookingInsertTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            Map<String, Object> bookingToInsert = new HashMap<>();
            bookingToInsert.put("id", 1);
            bookingToInsert.put("room", 1);
            EntityResult res = new EntityResultMapImpl();
            res.put("price", 100);
            res.put("hotel", List.of(1));
            when(roomService.roomQuery(any(), anyList())).thenReturn(res);
            when(daoHelper.insert(any(BookingDao.class), anyMap())).thenReturn(er);
            EntityResult result = bookingService.bookingInsert(bookingToInsert);
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).insert(any(BookingDao.class), anyMap());
            assertEquals("Successful booking insertion", result.getMessage());
        }

        @Test
        void bookingInsertTestFail() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            er.put("id", 1);
            Map<String, Object> bookingToInsert = new HashMap<>();
            bookingToInsert.put("id", 1);
            bookingToInsert.put("room", 1);
            EntityResult res = new EntityResultMapImpl();
            res.put("price", 100);
            res.put("hotel", List.of(1));
            when(roomService.roomQuery(any(), anyList())).thenReturn(res);
            when(daoHelper.insert(any(BookingDao.class), anyMap())).thenThrow(new RuntimeException(""));
            EntityResult result = bookingService.bookingInsert(bookingToInsert);
            assertEquals(1, result.getCode());
            verify(daoHelper, times(1)).insert(any(BookingDao.class), anyMap());
            assertNotEquals("Successful booking insertion", result.getMessage());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class BookingServiceUpdate {
        @Test
        void bookingUpdateTest() {
            EntityResult er = new EntityResultMapImpl();
            EntityResult er2 = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            er.put("totalprice", 650);
            er2.put("price", List.of(BigDecimal.valueOf(650)));
            er2.put("hotel", List.of(1));

            EntityResult er3 = new EntityResultMapImpl();
            er3.put("id", List.of(1));
            er3.put("checkindate",List.of("2023-06-03 00:00:00.0"));
            er3.put("checkoutdate",List.of("2023-06-06 00:00:00.0"));
            er3.put("arrivaldate",List.of("2023-06-03"));
            er3.put("departuredate",List.of("2023-06-06"));
            er3.put("room",List.of(1));
            er3.put("guest", List.of(1));


            Map<String, Object> bookingToUpdate = new HashMap<>();
            bookingToUpdate.put("room", 1);
            bookingToUpdate.put("arrivaldate", "2023-06-03");
            bookingToUpdate.put("departuredate", "2023-06-06");
            Map<String, Object> bookingKey = new HashMap<>();
            bookingKey.put("id", 1);
            when(daoHelper.update(any(BookingDao.class), anyMap(), anyMap())).thenReturn(er);
            when(roomService.roomQuery(anyMap(), anyList())).thenReturn(er2);
            when(daoHelper.query(any(BookingDao.class), anyMap(), anyList())).thenReturn(er3, er3);
            EntityResult result = bookingService.bookingUpdate(bookingToUpdate, bookingKey);
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).update(any(BookingDao.class), anyMap(), anyMap());
            assertEquals("Successful booking update", result.getMessage());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class BookingServiceDelete {
        @Test
        void bookingDeleteTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.setMessage("");
            er.put("id", 1);
            er.put("totalprice", List.of(new BigDecimal("650.5")));
            er.put("arrivaldate", List.of("2025-06-06"));

            EntityResult er2 = new EntityResultMapImpl();
            er2.put("id", List.of(1));
            er2.put("room", List.of(1));
            er2.put("hotel", List.of(1));
            LocalDate now = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateNow = now.format(format);
            er2.put("arrivaldate", List.of(dateNow));
            er2.put("totalprice", List.of(new BigDecimal("650.5")));
            er2.put("guest", List.of(1));

            Map<String, Object> bookingKey = new HashMap<>();
            bookingKey.put("id", 1);
            when(daoHelper.delete(any(BookingDao.class), anyMap())).thenReturn(er);
            when(daoHelper.query(any(BookingDao.class), anyMap(), anyList())).thenReturn(er2);
            when(roomService.roomQuery(anyMap(), anyList())).thenReturn(er2);
            EntityResult result = bookingService.bookingDelete(bookingKey);
            assertEquals("Successful booking delete", result.getMessage());
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).delete(any(BookingDao.class), anyMap());
        }

        @Test
        void bookingDeleteTestBookingNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            er.setMessage("");
            Map<String, Object> bookingKey = new HashMap<>();
            bookingKey.put("id", 1);
            when(daoHelper.delete(any(BookingDao.class), anyMap())).thenReturn(er);
            when(daoHelper.query(any(BookingDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = bookingService.bookingDelete(bookingKey);
            assertEquals(1, result.getCode());
            verify(daoHelper, times(1)).delete(any(BookingDao.class), anyMap());
            assertEquals("Booking not found", result.getMessage());
        }
    }


}
