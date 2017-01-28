package comp110;

import java.util.Random;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;

import comp110.test.OutOfCallsException;
import comp110.test.RecordedConsole;
import comp110.test.TestConsole;
import comp110.test.values.StringTester;

public class AlertCarolinaCalculatorTest {

  private static final double EPSILON = 0.001;

  private TestConsole _test;
  private RecordedConsole _expected;
  private Random _r = new Random(110L);

  private double _degreesF, _snowFallen, _snowForecast;
  private double _degreesC, _totalSnow, _alertRating;

  private void p1_1_expected() {
    _degreesF = Math.round(_r.nextDouble() * 100.0);
    _expected.promptDouble("Temperature", _degreesF);
  }

  @Test
  public void p1_1_promptTemperature() {
    test(this::p1_1_expected);
  }

  private void p1_2_expected() {
    p1_1_expected();
    _snowFallen = Math.round(_r.nextDouble() * 100.0);
    _expected.promptDouble("Fallen", _snowFallen);
  }

  @Test
  public void p1_2_promptSnowFallen() {
    test(this::p1_2_expected);
  }

  private void p1_3_expected() {
    p1_2_expected();
    _snowForecast = Math.round(_r.nextDouble() * 100.0);
    _expected.promptDouble("Projected", _snowForecast);
  }

  @Test
  public void p1_3_promptSnowProjected() {
    test(this::p1_3_expected);
  }

  private void p1_4_expected() {
    p1_3_expected();
    _degreesC = (_degreesF - 32.0) * (5.0 / 9.0);
    final String prefix = "Temperature (C): ";
    final double degrees = _degreesC;
    Predicate<String> test = (s) -> {
      Pattern pattern = Pattern.compile("([0-9.]+)");
      Matcher matcher = pattern.matcher(s);
      if (matcher.find()) {
        double parsed = Double.parseDouble(matcher.group(0).trim());
        return parsed > degrees - EPSILON && parsed < degrees + EPSILON;
      } else {
        return false;
      }
    };
    _expected.print(new StringTester(test, prefix + degrees));
  }

  @Test
  public void p1_4_printTemperatureC() {
    test(this::p1_4_expected);
  }

  private void p1_5_expected() {
    p1_4_expected();

    _totalSnow = (_snowFallen + _snowForecast) * 2.54;
    final String prefix = "Total Snow (cm): ";
    final double totalSnow = _totalSnow;
    Predicate<String> test = (s) -> {
      Pattern pattern = Pattern.compile("([0-9.]+)");
      Matcher matcher = pattern.matcher(s);
      if (matcher.find()) {
        double parsed = Double.parseDouble(matcher.group(0).trim());
        return parsed > totalSnow - EPSILON && parsed < totalSnow + EPSILON;
      } else {
        return false;
      }
    };
    _expected.print(new StringTester(test, prefix + totalSnow));
  }

  @Test
  public void p1_5_printTotalSnow() {
    test(this::p1_5_expected);
  }

  private void p1_6_expected() {
    p1_5_expected();

    _alertRating = -1.0 * _degreesC + _totalSnow * 5.0;
    final String prefix = "Alert Carolina Risk Score: ";
    final double alertRating = _alertRating;
    Predicate<String> test = (s) -> {
      Pattern pattern = Pattern.compile("([0-9.]+)");
      Matcher matcher = pattern.matcher(s);
      if (matcher.find()) {
        double parsed = Double.parseDouble(matcher.group(0).trim());
        return parsed > alertRating - EPSILON && parsed < alertRating + EPSILON;
      } else {
        return false;
      }
    };
    _expected.print(new StringTester(test, prefix + alertRating));

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
        _test.endOfTest();
      }
    } catch (OutOfCallsException e) {
      // Ignore when user output goes beyond output we're testing
    } catch (AssertionError e) {
      String error = "Test Log:\n=========\n";
      error += _test.getSession().toString();
      error += "=========\n";
      error += e.getMessage();
      throw new AssertionError(error);
    }
  }

}
