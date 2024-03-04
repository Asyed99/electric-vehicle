/**
 * This is the ElectricVehicle class.
 */
public class ElectricVehicle {
  // Variables
  private String name;
  private double batterySize;
  private double stateOfCharge;
  private final double defaultEfficiency;
  private double currentEfficiency;

  /**
     * Constructor for ElectricVehicle.
     * Initializes the EV's attributes and does clamping
     *
     * @param name Name of the EV
     * @param batterySize Size of the EV's battery (kWh).
     * @param stateOfCharge EV's state of charge
     * @param defaultEfficiency Vehicle's default efficiency.
     */
  public ElectricVehicle(
          String name,
          double batterySize,
          double stateOfCharge,
          double defaultEfficiency
  ) {
    // Set name; if not valid, set to "unknown EV"
    this.name = (name == null || name.isEmpty()) ? "unknown EV" : name;

    // Clamp the battery size to be within 10.0 and 150.0 kWh
    if (batterySize < 10.0) {
      this.batterySize = 10.0;
    } else if (batterySize > 150.0) {
      this.batterySize = 150.0;
    } else {
      this.batterySize = batterySize;
    }

    // Clamp the state of charge to be within 15% (0.15) and 100% (1.0)
    if (stateOfCharge < 0.15) {
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    } else {
      this.stateOfCharge = stateOfCharge;
    }

    // Clamp the default efficiency to be within 0.5 and 4.5 miles per kWh
    if (defaultEfficiency < 0.5) {
      this.defaultEfficiency = 0.5;
    } else if (defaultEfficiency > 4.5) {
      this.defaultEfficiency = 4.5;
    } else {
      this.defaultEfficiency = defaultEfficiency;
    }

    // Initialize current efficiency to the default efficiency
    this.currentEfficiency = this.defaultEfficiency;
  }

  /**
     * Calculates and returns the range of the electric vehicle based on its current state.
     *
     * @return The calculated range in miles.
   */
  public double range() {
    return this.currentEfficiency * this.stateOfCharge * this.batterySize;
  }

  /**
     * Updates the current efficiency based on the given temperature.
     *
     * @param currentTemp Current temperature in Fahrenheit.
     */
  public void updateEfficiency(double currentTemp) {
    if (currentTemp >= 65.0 && currentTemp <= 77.0) {
      this.currentEfficiency = this.defaultEfficiency;
    } else if (currentTemp > 77.0) {
      this.currentEfficiency = this.defaultEfficiency * 0.85;
    } else {
      double tempDifference = 65.0 - currentTemp;
      double efficiencyReduction = tempDifference * 0.01;
      efficiencyReduction = Math.min(efficiencyReduction, 0.50); // 50% reduction MAX
      this.currentEfficiency = this.defaultEfficiency * (1 - efficiencyReduction);
    }
  }

  // Getters and Setters

  /**
     * This returns the current efficiency of the EV.
     *
     * @return The current efficiency.
     */
  public double getEfficiency() {
    return this.currentEfficiency;
  }

  /**
     * This returns the battery size of the EV.
     *
     * @return The battery size.
     */
  public double getBatterySize() {
    return this.batterySize;
  }

  /**
     * This returns the current state of charge of the EV.
     *
     * @return The state of charge.
     */
  public double getStateOfCharge() {
    return this.stateOfCharge;
  }

  /**
     * This returns the name of the EV.
     *
     * @return The name of the EV
     */
  public String getName() {
    return this.name;
  }

  /**
     * Sets the state of charge of the electric vehicle, clamping it between 15% and 100%.
     *
     * @param stateOfCharge The new state of charge
     */
  public void setStateOfCharge(double stateOfCharge) {
    if (stateOfCharge < 0.15) {
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    } else {
      this.stateOfCharge = stateOfCharge;
    }
  }

  /**
     * Returns a string representation, which includes name, state of charge, and range.
     *
     * @return String representation of the ElectricVehicle.
     */
  @Override
    public String toString() {
    return this.name + " SOC: "
            + String.format("%.1f%%", this.stateOfCharge * 100)
            + " Range (miles): "
            + String.format("%.1f", range());
  }
}


