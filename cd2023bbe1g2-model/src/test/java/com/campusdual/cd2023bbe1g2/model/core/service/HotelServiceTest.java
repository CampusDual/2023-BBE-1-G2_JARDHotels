package com.campusdual.cd2023bbe1g2.model.core.service;

import com.campusdual.cd2023bbe1g2.model.core.dao.HotelDao;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @InjectMocks
    HotelService hotelService;

    @Mock
    HotelDao hotelDao;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class HotelServiceQuery {
        @Test
        void hotelQueryTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            Map<String, Object> hotelToQuery = new HashMap<>();
            hotelToQuery.put("id", 1);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelQuery(hotelToQuery, new ArrayList<>());
            assertEquals(0, result.getCode());
            assertEquals("", result.getMessage());
            verify(daoHelper, times(1)).query(any(HotelDao.class), anyMap(), anyList());
        }

        @Test
        void hotelQueryTestNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            Map<String, Object> hotelToQuery = new HashMap<>();
            hotelToQuery.put("id", 1);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelQuery(hotelToQuery, new ArrayList<>());
            assertEquals(1, result.getCode());
            assertEquals("The hotel doesn't exist", result.getMessage());
            verify(daoHelper, times(1)).query(any(HotelDao.class), anyMap(), anyList());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class HotelServiceInsert {
        @Test
        void hotelInsertTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            Map<String, Object> hotelToInsert = new HashMap<>();
            hotelToInsert.put("name", "Hotel 1");
            hotelToInsert.put("address", "Address 1");
            hotelToInsert.put("stars", 1);
            hotelToInsert.put("country", 1);
            when(daoHelper.insert(any(HotelDao.class), anyMap())).thenReturn(er);
            EntityResult result = hotelService.hotelInsert(hotelToInsert);
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).insert(any(HotelDao.class), anyMap());
            assertEquals("Successful hotel insertion", result.getMessage());
        }

        @Test
        void hotelInsertTestException() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            Map<String, Object> hotelToInsert = new HashMap<>();
            hotelToInsert.put("name", "Hotel 1");
            hotelToInsert.put("address", "Address 1");
            hotelToInsert.put("stars", 1);
            hotelToInsert.put("country", 1);
            when(daoHelper.insert(any(HotelDao.class), anyMap())).thenThrow(new RuntimeException("The country doesn't exist"));
            EntityResult result = hotelService.hotelInsert(hotelToInsert);
            assertEquals(1, result.getCode());
            verify(daoHelper, times(1)).insert(any(HotelDao.class), anyMap());
            assertEquals("The country doesn't exist", result.getMessage());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class HotelServiceUpdate {
        @Test
        void hotelUpdateTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            er.put("phone", List.of("111111111"));
            er.put("country", List.of(8));
            Map<String, Object> hotelToUpdate = new HashMap<>();
            hotelToUpdate.put("name", "Hotel 1");
            hotelToUpdate.put("address", "Address 1");
            hotelToUpdate.put("stars", 1);
            hotelToUpdate.put("country", 1);
            Map<String, Object> hotelKey = new HashMap<>();
            hotelKey.put("id", 1);
            when(daoHelper.update(any(HotelDao.class), anyMap(), anyMap())).thenReturn(er);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelUpdate(hotelToUpdate, hotelKey);
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).update(any(HotelDao.class), anyMap(), anyMap());
            assertEquals("Successful hotel update", result.getMessage());
        }

        @Test
        void hotelUpdateTestHotelNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            Map<String, Object> hotelToUpdate = new HashMap<>();
            hotelToUpdate.put("name", "Hotel 1");
            hotelToUpdate.put("address", "Address 1");
            hotelToUpdate.put("stars", 1);
            hotelToUpdate.put("country", 1);
            Map<String, Object> hotelKey = new HashMap<>();
            hotelKey.put("id", 1);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelUpdate(hotelToUpdate, hotelKey);
            assertEquals(1, result.getCode());
            assertEquals("Hotel not found", result.getMessage());
        }

        @Test
        void hotelUpdateTestCountryNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.put("id", 1);
            er.put("phone", List.of("111111111"));
            er.put("country", List.of(8));
            Map<String, Object> hotelToUpdate = new HashMap<>();
            hotelToUpdate.put("name", "Hotel 1");
            hotelToUpdate.put("address", "Address 1");
            hotelToUpdate.put("stars", 1);
            hotelToUpdate.put("country", 1);
            Map<String, Object> hotelKey = new HashMap<>();
            hotelKey.put("id", 1);
            when(daoHelper.update(any(HotelDao.class), anyMap(), anyMap())).thenThrow(new RuntimeException("The country doesn't exist"));
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelUpdate(hotelToUpdate, hotelKey);
            assertEquals(1, result.getCode());
            assertEquals("The country doesn't exist", result.getMessage());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class HotelServiceDelete {
        @Test
        void hotelDeleteTest() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(0);
            er.setMessage("");
            er.put("id", 1);
            Map<String, Object> hotelKey = new HashMap<>();
            hotelKey.put("id", 1);
            when(daoHelper.delete(any(HotelDao.class), anyMap())).thenReturn(er);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelDelete(hotelKey);
            assertEquals(0, result.getCode());
            verify(daoHelper, times(1)).delete(any(HotelDao.class), anyMap());
            assertEquals("Successful hotel delete", result.getMessage());
        }

        @Test
        void hotelDeleteTestHotelNotFound() {
            EntityResult er = new EntityResultMapImpl();
            er.setCode(1);
            er.setMessage("");
            Map<String, Object> hotelKey = new HashMap<>();
            hotelKey.put("id", 1);
            when(daoHelper.delete(any(HotelDao.class), anyMap())).thenReturn(er);
            when(daoHelper.query(any(HotelDao.class), anyMap(), anyList())).thenReturn(er);
            EntityResult result = hotelService.hotelDelete(hotelKey);
            assertEquals(1, result.getCode());
            verify(daoHelper, times(1)).delete(any(HotelDao.class), anyMap());
            assertEquals("Hotel not found", result.getMessage());
        }
    }


}
