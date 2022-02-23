/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package TypewiseAlert;

import TypewiseAlert.TypewiseAlert.BreachType;

public class CoolingTypeContext {

  static final double PASSIVE_COOL_LOWER_LIMIT = 0;
  static final double PASSIVE_COOL_UPPER_LIMIT = 35;
  static final double HI_ACTIVE_COOL_LOWER_LIMIT = 0;
  static final double HI_ACTIVE_COOL_UPPER_LIMIT = 45;
  static final double MED_ACTIVE_COOL_LOWER_LIMIT = 0;
  static final double MED_ACTIVE_COOL_UPPER_LIMIT = 40;

  private final CoolingType coolingType;

  public CoolingTypeContext(final CoolingType coolingType) {
    this.coolingType = coolingType;
  }

  BreachType inferBreachBasedOnCooling(final double value) {
    return this.coolingType.inferBreachForCoolingType(value);
  }

  public interface PassiveCooling extends CoolingType {

    @Override
    BreachType inferBreachForCoolingType(double value);
  }

  public interface HighActiveCooling extends CoolingType {

    @Override
    BreachType inferBreachForCoolingType(final double value);
  }

  public interface MediumActiveCooling extends CoolingType {

    @Override
    BreachType inferBreachForCoolingType(final double value);
  }

  public interface CoolingType {

    BreachType inferBreachForCoolingType(double value);

    PassiveCooling passiveCoolingType =
        value -> TypewiseAlert.inferBreach(value, PASSIVE_COOL_LOWER_LIMIT, PASSIVE_COOL_UPPER_LIMIT);


    HighActiveCooling hiActiveCoolingType =
        value -> TypewiseAlert.inferBreach(value, HI_ACTIVE_COOL_LOWER_LIMIT, HI_ACTIVE_COOL_UPPER_LIMIT);


    MediumActiveCooling medActiveCoolingType =
        value -> TypewiseAlert.inferBreach(value, MED_ACTIVE_COOL_LOWER_LIMIT, MED_ACTIVE_COOL_UPPER_LIMIT);

  }


}

