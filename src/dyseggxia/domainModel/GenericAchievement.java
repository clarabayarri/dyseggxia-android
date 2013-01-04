package dyseggxia.domainModel;

import dyseggxia.domainControllers.CompletedProblemController;

public abstract class GenericAchievement {

	public static final double MAX_VALUE = 100.0;
	
	protected String code;
	protected double value;
	protected CompletedProblemController controller;
	
	public boolean valueWillChange() {
		return (this.value != calculateValue());
	}

	public void updateValue() {
		double newValue = calculateValue();
	    if (newValue > value) {
	        value = newValue;
	    }
	}

	public boolean isComplete() {
		return value >= 99.5;
	}

	public abstract int animationImages();

	public abstract double calculateValue();

	public abstract int animationDuration();
}
