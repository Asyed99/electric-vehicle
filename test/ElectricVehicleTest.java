import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the function and conditional functionality of the ElectricVehicle class.
 */
public class ElectricVehicleTest {

  private ElectricVehicle ev;

  /**
     * This method will make sure that the EV object gets initialized correctly.
     */
  @Before
    public void setUp() {
    // Ensuring proper initialization of Electric Vehicle object with initial values
    ev = new ElectricVehicle("Porsche Taycan", 120.00, 0.9, 3.0);
  }

  /**
     * This method ensures that the initialization occurs correctly.
     */
  @Test
    public void testElectricVehicle_Initialization() {
    // Making sure that the initialization is correct
    assertEquals("Porsche Taycan", ev.getName());
    assertEquals(120.00, ev.getBatterySize(), 0.001);
    assertEquals(0.9, ev.getStateOfCharge(), 0.001);
    assertEquals(3.0, ev.getEfficiency(), 0.001);
  }

  /**
     * This method tests the ElectricVehicle class's clamping with higher-than-accepted values.
     */
  @Test
    public void testElectricVehicle_UpperClamp() {
    ElectricVehicle evClamped = new ElectricVehicle("", 160, 1.1, 5.0);
    assertEquals("unknown EV", evClamped.getName());
    assertEquals(150.00, evClamped.getBatterySize(), 0.001);
    assertEquals(1.0, evClamped.getStateOfCharge(), 0.001);
    assertEquals(4.5, evClamped.getEfficiency(), 0.001);
  }

  /**
     * This method tests the ElectricVehicle class's clamping with lower-than-accepted values.
     */
  @Test
    public void testElectricVehicle_LowerClamp() {
    ElectricVehicle evClamped = new ElectricVehicle("", 5, 0.1, 0.3);
    assertEquals("unknown EV", evClamped.getName());
    assertEquals(10, evClamped.getBatterySize(), 0.001);
    assertEquals(0.15, evClamped.getStateOfCharge(), 0.001);
    assertEquals(0.50, evClamped.getEfficiency(), 0.001);
  }

  /**
     * This method ensures proper calculation of the Range.
     */
  @Test
    public void testRange_Calculation() {
    double expectedRange = 120 * 0.9 * 3.0;
    // checking calculation of batterySize * stateOfCharge * efficiency
    assertEquals(expectedRange, ev.range(), 0.001);
  }

  /**
     * This method tests the UpdateEfficiency functionality with valid temperatures.
     */
  @Test
    public void testUpdateEfficiency_CorrectTemp() {
    // checking UpdateEfficiency method with valid temperature
    ev.updateEfficiency(70.0);
    // The 3.0 represents 100% of the default efficiency
    assertEquals(3.0, ev.getEfficiency(), 0.001);
  }

  /**
     * This method tests the UpdateEfficiency functionality with high temperatures.
     */
  @Test
    public void testUpdateEfficiency_HighTemp() {
    // checking UpdateEfficiency method with high temperature
    ev.updateEfficiency(80.0);
    // The 2.55 represents 85% of the default efficiency
    assertEquals(2.55, ev.getEfficiency(), 0.001);
  }

  /**
     * This method tests the UpdateEfficiency functionality with low temperatures.
     */
  @Test
    public void testUpdateEfficiency_LowTemp() {
    // checking UpdateEfficiency method with low temperature
    ev.updateEfficiency(60.0);
    // The 2.85 represents a 5 reduction in default efficiency
    assertEquals(2.85, ev.getEfficiency(), 0.001);
  }

  /**
     * This method tests the UpdateEfficiency functionality with super low temperature.
     * This method ensures a max of 50% reduction in default efficiency.
     */
  @Test
    public void testUpdateEfficiency_SuperLowTemp() {
    // checking to make sure maximum of 50% decrease in default efficiency
    ev.updateEfficiency(0.0);
    // The 1.5 represents 50% reduction in default efficiency
    assertEquals(1.5, ev.getEfficiency(), 0.001);
  }

  /**
     * This tests the functionality of the GetEfficiency method.
     */
  @Test
    public void testGetEfficiency() {
    // testing the GetEfficiency method
    ev.updateEfficiency(70.0);
    assertEquals(3.0, ev.getEfficiency(), 0.001);
  }

  /**
     * This tests the functionality of the GetBatterySize method.
     */
  @Test
    public void testGetBatterySize() {
    // testing the GetBatterySize method
    assertEquals(120.00, ev.getBatterySize(), 0.001);
  }

  /**
     * This tests the functionality of the GetStateOfCharge method.
     */
  @Test
    public void testGetStateOfCharge() {
    // testing the GetStateOfCharge method
    assertEquals(0.9, ev.getStateOfCharge(), 0.001);
  }

  /**
     * This tests the functionality of the GetName function (Porsche Taycan).
     */
  @Test
    public void testGetName() {
    // testing the GetName method
    assertEquals("Porsche Taycan", ev.getName());
  }

  /**
     * This ensures that name is set to "unknown EV" if given an empty name.
     */
  @Test
    public void testEmptyName() {
    // making sure if empty, name is set to "unknown EV"
    ElectricVehicle evEmptyName = new ElectricVehicle("", 120.00, 0.9, 3.0);
    assertEquals("unknown EV", evEmptyName.getName());
  }

  /**
     * This ensures that the name is set to "unknown EV" if name is null.
     */
  @Test
    public void testNullName() {
    // making sure if null, name is set to "unknown EV"
    ElectricVehicle evNullName = new ElectricVehicle(null, 120.00, 0.9, 3.0);
    assertEquals("unknown EV", evNullName.getName());
  }

  /**
     * This tests the SetStateOfCharge method.
     */
  @Test
    public void testSetStateOfCharge() {
    ev.setStateOfCharge(0.6);
    assertEquals(0.6, ev.getStateOfCharge(), 0.001);
  }

  /**
     * This tests the testToString method.
     * It should provide the name, SOC, and range (in miles).
     */
  @Test
    public void testToString() {
    String expectedString = "Porsche Taycan SOC: 90.0% Range (miles): 324.0";
    assertEquals(expectedString, ev.toString());
  }
}
