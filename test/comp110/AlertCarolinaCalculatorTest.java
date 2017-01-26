package comp110;

import java.util.Random;

import org.junit.Test;

import comp110.test.OutOfCallsException;
import comp110.test.RecordedConsole;
import comp110.test.TestConsole;

public class AlertCarolinaCalculatorTest {

	private TestConsole _test;
	private RecordedConsole _expected;
	private Random _r = new Random(110L);;

	private double _degreesF, _snowFallen, _snowForecast;
	private double _degreesC, _totalSnow, _alertRating;

	private void p1_1_expected() {
		_degreesF = _r.nextDouble() * 100.0;
		_expected.promptDouble("Temperature:", _degreesF);
	}

	@Test
	public void p1_1_promptTemperature() {
		test(this::p1_1_expected);
	}

	private void p1_2_expected() {
		p1_1_expected();
		_snowFallen = _r.nextDouble() * 100.0;
		_expected.promptDouble("Inches of snow fallen:", _snowFallen);
	}

	@Test
	public void p1_2_promptSnowFallen() {
		test(this::p1_2_expected);
	}

	private void p1_3_expected() {
		p1_2_expected();
		_snowForecast = _r.nextDouble() * 100.0;
		_expected.promptDouble("Inches of snow projected:", _snowForecast);
	}

	@Test
	public void p1_3_promptSnowProjected() {
		test(this::p1_3_expected);
	}

	private void p1_4_expected() {
		p1_3_expected();
		_degreesC = (_degreesF - 32.0) * (5.0 / 9.0);
		_expected.print("Temperature (C): " + _degreesC);
	}

	@Test
	public void p1_4_printTemperatureC() {
		test(this::p1_4_expected);
	}

	private void p1_5_expected() {
		p1_4_expected();
		_totalSnow = (_snowFallen + _snowForecast) * 2.54;
		_expected.print("Total Snow (cm): " + _totalSnow);
	}

	@Test
	public void p1_5_printTotalSnow() {
		test(this::p1_5_expected);
	}

	private void p1_6_expected() {
		p1_5_expected();
		_alertRating = -1.0 * _degreesC + _totalSnow * 5.0;
		_expected.print("Alert Carolina Risk Score: " + _alertRating);
	}

	@Test
	public void p1_6_printAlertCarolinaScore() {
		test(this::p1_6_expected);
	}

	private void test(Runnable test) {
		try {
			for (int i = 0; i < 1; i++) {
				ConsoleFactory.resetLast();
				ConsoleFactory.setConsoleClass(TestConsole.class);
				ConsoleFactory.setSingleton(true);
				_test = (TestConsole) ConsoleFactory.getConsole();
				_expected = new RecordedConsole();
				test.run();
				_test.setExpected(_expected.getSession());
				AlertCarolinaCalculator.main(null);
			}
		} catch (OutOfCallsException e) {
			// Ignore
		} catch (AssertionError e) {

			System.out.println("Output");
			_test.getSession().print();

			System.out.println("");
			System.out.println(e.getMessage());

			throw e;
		}
	}

}
