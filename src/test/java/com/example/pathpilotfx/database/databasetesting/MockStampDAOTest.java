package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockStampDAO;
import com.example.pathpilotfx.model.Stamp;
import org.junit.jupiter.api.Test;

public class MockStampDAOTest {

    // Test Case 1: InsertStamp_Success
    @Test
    public void testInsertStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true); // Example stamp data
        mockStampDAO.insert(stamp);
        assert mockStampDAO.getAll().size() == 1;
    }

    // Test Case 2: UpdateStamp_Success
    @Test
    public void testUpdateStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true); // Example stamp data
        mockStampDAO.insert(stamp);
        stamp.setObtained(true); // Update stamp data
        mockStampDAO.update(stamp);
        assert mockStampDAO.getByStampId(stamp.getStampID()).isObtained();
    }

    // Test Case 3: DeleteStamp_Success
    @Test
    public void testDeleteStamp_Success() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true); // Example stamp data
        mockStampDAO.insert(stamp);
        mockStampDAO.deleteStamp(stamp.getStampID());
        assert mockStampDAO.getAll().isEmpty();
    }

    // Test Case 4: GetAllStamps_NotEmpty
    @Test
    public void testGetAllStamps_NotEmpty() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp = new Stamp(1, 1,true); // Example stamp data
        mockStampDAO.insert(stamp);
        assert !mockStampDAO.getAll().isEmpty();
    }

    // Test Case 5: GetAllStamps_CorrectSize
    @Test
    public void testGetAllStamps_SetSize() {
        MockStampDAO mockStampDAO = new MockStampDAO();
        Stamp stamp1 = new Stamp(1, 1,true); // Example stamp data
        Stamp stamp2 = new Stamp(2, 1,true); // Example stamp data
        mockStampDAO.insert(stamp1);
        mockStampDAO.insert(stamp2);
        assert mockStampDAO.getAll().size() == 2;
    }

}
