package TypewiseAlert;

import TypewiseAlert.TypewiseAlert.BreachType;


public class AlertTargetContext {

  AlertTarget alertTarget;

  public AlertTargetContext(final AlertTarget alertTarget) {
    this.alertTarget = alertTarget;
  }

  public void sendAlertBasedOnTarget(final BreachType breachType) {
    this.alertTarget.sendAlert(breachType);
  }

  public interface AlertTarget {

    public void sendAlert(final BreachType breachType);

    MailAlert mailAlert = TypewiseAlert::sendToEmail;
    ControllerAlert contrlAlert = TypewiseAlert::sendToController;

  }

  public interface MailAlert extends AlertTarget {

    public void sendAlert(final BreachType breachType);
  }

  public interface ControllerAlert extends AlertTarget {

    public void sendAlert(final BreachType breachType);
  }
}
