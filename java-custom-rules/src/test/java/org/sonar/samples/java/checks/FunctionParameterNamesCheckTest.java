package org.sonar.samples.java.checks;

import de.adito.sonar.java.checks.FunctionParameterNamesCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @author a.mayr, 09.11.2017.
 */
public class FunctionParameterNamesCheckTest {

  @Test
  public void test() {
    JavaCheckVerifier.verify("src/test/files/FunctionParameterNamesCheck.java", new FunctionParameterNamesCheck());
  }

}