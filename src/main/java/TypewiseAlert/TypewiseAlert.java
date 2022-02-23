package TypewiseAlert;

import TypewiseAlert.AlertTargetContext.AlertTarget;
import TypewiseAlert.CoolingTypeContext.CoolingType;

public class TypewiseAlert {


  public enum BreachType {
                          NORMAL,
                          TOO_LOW,
                          TOO_HIGH
  };

  public static BreachType inferBreach(final double value, final double lowerLimit, final double upperLimit) {
    if (value < lowerLimit) {
      return BreachType.TOO_LOW;
    }
    if (value > upperLimit) {
      return BreachType.TOO_HIGH;
    }
    return BreachType.NORMAL;
  }


  public static BreachType classifyTemperatureBreach(final CoolingType coolingType, final double temperatureInC) {
    CoolingTypeContext coolingTypeContext = new CoolingTypeContext(coolingType);
    return coolingTypeContext.inferBreachBasedOnCooling(temperatureInC);
  }

  public static void checkAndAlert(final AlertTarget alertTarget, final CoolingType coolingType,
      final double temperatureInC) {

    BreachType breachType = TypewiseAlert.classifyTemperatureBreach(coolingType, temperatureInC);

    AlertTargetContext alertContext = new AlertTargetContext(alertTarget);
    alertContext.sendAlertBasedOnTarget(breachType);
  }

  public static void sendToController(final BreachType breachType) {
    int header = 0xfeed;
    System.out.printf("%i : %i\n", header, breachType);
  }

  public static void sendToEmail(final BreachType breachType) {
    String recepient = "a.b@c.com";
    switch (breachType) {
      case TOO_LOW:
        System.out.printf("To: %s\n", recepient);
        System.out.println("Hi, the temperature is too low\n");
        break;
      case TOO_HIGH:
        System.out.printf("To: %s\n", recepient);
        System.out.println("Hi, the temperature is too high\n");
        break;
      case NORMAL:
        break;
    }
  }
}

