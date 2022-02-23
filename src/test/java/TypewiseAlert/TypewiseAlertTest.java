package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import TypewiseAlert.AlertTargetContext.AlertTarget;
import TypewiseAlert.CoolingTypeContext.CoolingType;
import TypewiseAlert.TypewiseAlert;
import TypewiseAlert.TypewiseAlert.BreachType;


public class TypewiseAlertTest {

  @Test
  public void testClassifyTemperatureBreach() {
    /**
     * Cooling Types - Limits : Passive - 0 to 35, High Active - 0 to 45, Medium Active - 0 to 40
     */
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.passiveCoolingType, 36.0) == BreachType.TOO_HIGH);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.passiveCoolingType, 0.0) == BreachType.NORMAL);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.passiveCoolingType, -2.0) == BreachType.TOO_LOW);

    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.hiActiveCoolingType, 46.0) == BreachType.TOO_HIGH);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.hiActiveCoolingType, 36.0) == BreachType.NORMAL);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.hiActiveCoolingType, -1.0) == BreachType.TOO_LOW);

    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.medActiveCoolingType, 41.0) == BreachType.TOO_HIGH);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.medActiveCoolingType, 36.0) == BreachType.NORMAL);
    assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.medActiveCoolingType, -1.0) == BreachType.TOO_LOW);
  }

  @Test
  public void testCheckAlert() {
    TypewiseAlert.checkAndAlert(AlertTarget.mailAlert, CoolingType.passiveCoolingType, 36.0);  
    TypewiseAlert.checkAndAlert(AlertTarget.mailAlert, CoolingType.hiActiveCoolingType, 36.0);
    TypewiseAlert.checkAndAlert(AlertTarget.mailAlert, CoolingType.medActiveCoolingType, -1.0);
    TypewiseAlert.checkAndAlert(AlertTarget.contrlAlert, CoolingType.passiveCoolingType, 36.0);
  }


  @Test
  public void testInferBreachAsPerLimits() {
    assertTrue(TypewiseAlert.inferBreach(12, 20, 30) == TypewiseAlert.BreachType.TOO_LOW);
    assertTrue(TypewiseAlert.inferBreach(22, 20, 30) == TypewiseAlert.BreachType.NORMAL);
    assertTrue(TypewiseAlert.inferBreach(32, 20, 30) == TypewiseAlert.BreachType.TOO_HIGH);
  }
}
