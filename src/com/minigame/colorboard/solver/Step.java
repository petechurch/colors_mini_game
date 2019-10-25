package com.minigame.colorboard.solver;

public enum Step {
    First       (0),
    Second      (1),
    Third       (2),
    Fourth      (3),
    Fifth       (4),
    Sixth       (5),
    Seventh     (6),
    Eighth      (7),
    Ninth       (8),
    Tenth       (9),
    Eleventh    (10),
    Twelfth     (11),
    Thirteenth  (12),
    Fourteenth  (13),
    Fifteenth   (14),
    Sixteenth   (15),
    Seventeenth (16),
    Eighteenth  (17),
    Nineteenth  (18),
    Tweentieth  (19);

    private int stepValue;

    private Step(int value) {
        this.stepValue = value;
    }

    public int getValue() {
        return stepValue;
    }

    public static Step nextStep(Step currentStep) {
        for (Step nextStep : Step.values()) {
            if (nextStep.stepValue > currentStep.stepValue) {
                return nextStep;
            }
        }
        throw new IllegalArgumentException("No enums after: " + currentStep.name());
    }
}
