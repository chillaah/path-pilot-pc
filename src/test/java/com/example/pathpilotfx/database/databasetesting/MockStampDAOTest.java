package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockStampDAO;
import com.example.pathpilotfx.model.Stamp;
import org.junit.jupiter.api.Test;

public class MockStampDAOTest {


    @Test
    public void testInsertStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true);
        mockStampDAO.insert(stamp);
        assert mockStampDAO.getAll().size() == 1;
    }


    @Test
    public void testUpdateStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true);
        mockStampDAO.insert(stamp);
        stamp.setObtained(true); // Update stamp data
        mockStampDAO.update(stamp);
        assert mockStampDAO.getByStampId(stamp.getStampID()).isObtained();
    }


    @Test
    public void testDeleteStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true);
        mockStampDAO.insert(stamp);
        mockStampDAO.deleteStamp(stamp.getStampID());
        assert mockStampDAO.getAll().isEmpty();
    }


    @Test
    public void testGetAllStamps_NotEmpty() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true);
        mockStampDAO.insert(stamp);
        assert !mockStampDAO.getAll().isEmpty();
    }

    @Test
    public void testGetAllStamps_SetSize() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp1 = new Stamp(1, 1,true);
        Stamp stamp2 = new Stamp(2, 1,true);
        mockStampDAO.insert(stamp1);
        mockStampDAO.insert(stamp2);
        assert mockStampDAO.getAll().size() == 2;
    }

}
