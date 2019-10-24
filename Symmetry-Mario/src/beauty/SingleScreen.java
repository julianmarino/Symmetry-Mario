package beauty;

import java.io.Serializable;

public class SingleScreen implements Serializable{

	private String nameScreen;
	private double valueMetric;
	private int valueMetricNormalized;
		
	public SingleScreen()
	{

	}
	
	public String getNameScreen() {
		return nameScreen;
	}

	public void setNameScreen(String nameScreen) {
		this.nameScreen = nameScreen;
	}

	public double getValueMetric() {
		return valueMetric;
	}

	public void setValueMetric(double valueMetric) {
		this.valueMetric = valueMetric;
	}

	public int getValueMetricNormalized() {
		return valueMetricNormalized;
	}

	public void setValueMetricNormalized(int valueMetricNormalized) {
		this.valueMetricNormalized = valueMetricNormalized;
	}
}
