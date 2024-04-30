package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockExplorationDAO;
import com.example.pathpilotfx.model.Exploration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockExplorationDAOTest {

    private MockExplorationDAO mockExplorationDAO;

    @BeforeEach
    public void setUp() {
        mockExplorationDAO = new MockExplorationDAO();
    }

    @Test
    public void testInsert() {
        Exploration exploration = new Exploration(1, 1, "Explored", true, false);
        mockExplorationDAO.insert(exploration);
        assertNotNull(mockExplorationDAO.getByCountryId(exploration.getCountryID()));
    }

    @Test
    public void testUpdate() {
        Exploration exploration = new Exploration(1, 1, "Explored",true, false);
        mockExplorationDAO.insert(exploration);
        exploration.setLocked(true);
        mockExplorationDAO.update(exploration);
        assertTrue(mockExplorationDAO.getByCountryId(exploration.getCountryID()).isLocked());
    }

    @Test
    public void testDeleteCountryData() {
        Exploration exploration1 = new Exploration(1, 1, "Explored", true, false);
        Exploration exploration2 = new Exploration(2, 1, "Explored",true, false);
        mockExplorationDAO.insert(exploration1);
        mockExplorationDAO.insert(exploration2);
        mockExplorationDAO.deleteCountryData(1);
        assertNull(mockExplorationDAO.getByCountryId(1));
        assertNull(mockExplorationDAO.getByCountryId(2));
    }

    @Test
    public void testGetAll() {
        Exploration exploration1 = new Exploration(1, 1, "Explored",true, false);
        Exploration exploration2 = new Exploration(2, 2, "Explored", true, false);
        mockExplorationDAO.insert(exploration1);
        mockExplorationDAO.insert(exploration2);
        List<Exploration> allExplorations = mockExplorationDAO.getAll();
        assertEquals(2, allExplorations.size());
    }

    @Test
    public void testGetAllLocked() {
        Exploration exploration1 = new Exploration(1, 1, "Explored", true, false);
        Exploration exploration2 = new Exploration(2, 2, "Explored", false, false);
        mockExplorationDAO.insert(exploration1);
        mockExplorationDAO.insert(exploration2);
        List<Exploration> lockedExplorations = mockExplorationDAO.getAllLocked();
        assertEquals(1, lockedExplorations.size());
    }

    @Test
    public void testGetAllUnlocked() {
        Exploration exploration1 = new Exploration(1, 1, "Explored",true, false);
        Exploration exploration2 = new Exploration(2, 2, "Explored", false, false);
        mockExplorationDAO.insert(exploration1);
        mockExplorationDAO.insert(exploration2);
        List<Exploration> unlockedExplorations = mockExplorationDAO.getAllUnlocked();
        assertEquals(1, unlockedExplorations.size());
    }

    @Test
    public void testGetByCountryId() {
        Exploration exploration = new Exploration(1, 1, "Explored",true, false);
        mockExplorationDAO.insert(exploration);
        assertNotNull(mockExplorationDAO.getByCountryId(exploration.getCountryID()));
    }

    @Test
    public void testGetByCountryId_NotFound() {
        assertNull(mockExplorationDAO.getByCountryId(100)); // Assuming 100 is not a valid country ID
    }
}
