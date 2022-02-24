package TypewiseAlert;

import TypewiseAlert.AlertTargetContext.AlertTarget;
import TypewiseAlert.CoolingTypeContext.CoolingType;

public class TypewiseAlert {


  public enum BreachType {
                          NORMAL("normal"),
                          TOO_LOW("too low"),
                          TOO_HIGH("too high");

    private final String type;

    private BreachType(final String type) {
      this.type = type;
    }


    public String getType() {
      return this.type;
    }
  }

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
    System.out.printf("%d : %s\n", header, breachType);
  }

  public static void mailContent(final String recepient, final BreachType breachType) {
    System.out.printf("To: %s\n", recepient);
    System.out.println("Hi, the temperature is " + breachType.getType() + "\n");
  }

  public static void sendToEmail(final BreachType breachType) {
    String recepient = "a.b@c.com";
    if (!breachType.equals(BreachType.NORMAL)) {
      mailContent(recepient, breachType);
    }
  }
}

