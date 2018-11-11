package simplewebapp;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import daoImplentations.CarDAOImplementation;
import entities.Car;

import org.junit.Before;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarDaoTest {

	 @Mock
	    private DataSource ds;
	    @Mock
	    private Connection c;
	    @Mock
	    private PreparedStatement stmt;
	    @Mock
	    private ResultSet rs;
	    
	    private Car cr;
	    @Before
	    public void setUp() throws Exception {
	        assertNotNull(ds);
	        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
	        when(ds.getConnection()).thenReturn(c);
	        cr = new Car();
	        cr.setId(1);
	        cr.setModel("m8");
	        cr.setMark("BMW");
	        cr.setCarClass("sedan");	        
	        cr.setCost(100);
	        when(rs.first()).thenReturn(true);
	        when(rs.getInt(1)).thenReturn(1);
	        when(rs.getString(2)).thenReturn(cr.getModel());
	        when(rs.getString(3)).thenReturn(cr.getMark());
	        when(rs.getString(4)).thenReturn(cr.getCarClass());
	        when(rs.getInt(5)).thenReturn(cr.getCost());
	        when(stmt.executeQuery()).thenReturn(rs);
	    }
	    @Test(expected=IllegalArgumentException.class)
	    public void nullCreateThrowsException() {
	        new CarDAOImplementation(null);
	    }
	    @Test
	    public void addCar() {
	        new CarDAOImplementation(ds).addCar(cr.getModel(), cr.getMark(), cr.getCarClass(), cr.getCost());;
	    }
	    @Test
	    public void CreateAndRetrieveCar() throws Exception {
	        CarDAOImplementation dao = new CarDAOImplementation(ds);
	        dao.addCar(cr.getModel(), cr.getMark(), cr.getCarClass(), cr.getCost());;
	        Car car = dao.findCarById(1);       
	        assertEquals(cr, car);

	    }

}
