package org.sonar.samples.java.checks;

import de.adito.sonar.java.checks.FunctionNamesCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @author a.mayr, 09.11.2017.
 */
public class FunctionNamesCheckTest {

  @Test
  public void test() {
    JavaCheckVerifier.verify("src/test/files/FunctionNamesCheck.java", new FunctionNamesCheck());
  }

}