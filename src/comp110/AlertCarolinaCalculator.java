package comp110;

public class AlertCarolinaCalculator {

	public static void main(String[] args) {

		Console console = new Console("Alert CAROLina Calculator");

		double degreesF = console.promptDouble("Temperature:");
		double snowFallen = console.promptDouble("Inches of snow fallen:");
		double snowForecast = console.promptDouble("Inches of snow projected:");

		double degreesC = (degreesF - 32.0) * (5.0 / 9.0);
		console.print("Temperature (C): " + degreesC);
		double totalSnow = (snowFallen + snowForecast) * 2.54;
		console.print("Total Snow (cm): " + totalSnow);
		double alertRating = -1.0 * degreesC + totalSnow * 5.0;
		console.print("Alert Carolina Risk Score: " + alertRating);

	}

}