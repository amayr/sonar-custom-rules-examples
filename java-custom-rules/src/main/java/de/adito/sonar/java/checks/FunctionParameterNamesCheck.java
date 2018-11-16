package de.adito.sonar.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.List;

/**
 * Prüft ob die Methoden-Parameter-Namen eine bestimmte Form haben.
 * Parameter müssen mit p beginnen. (Der Rest CamelCase.)
 *
 * @author a.mayr, 09.11.2017.
 */
@Rule(
        key = "AditoFunctionParameterNamesCheck",
        name = "Method parameters should start with p.",
        description = "Method parameters should start with p.",
        priority = Priority.MAJOR,
        tags = {"codesmell"})
public class FunctionParameterNamesCheck extends IssuableSubscriptionVisitor {

  @Override
  public void visitNode(Tree tree) {
    // Wir haben uns für Kind.METHOD registriert, also haben wir immer einen MethodTree.
    MethodTree method = (MethodTree) tree;
    method.parameters().forEach(parameter -> check(method, parameter));
  }

  private void check(MethodTree pMethod, VariableTree pParameter) {

    String name = pParameter.symbol().name();
    if (!name.startsWith("p"))
    {
      reportIssue(pMethod.simpleName(), "Parameter names always have to start with 'p'.");
    }
    else
    {
      if (name.length() >= 2)
      {
        String secondChar = name.substring(1, 2);
        char toCheck = secondChar.charAt(0);
        boolean isUpperCase = Character.isUpperCase(toCheck);
        if (!isUpperCase)
        {
          reportIssue(pMethod.simpleName(), "Second character has to be upper case (CamelCase)!");
        }
      }
    }
  }


  @Override
  public List<Kind> nodesToVisit() {
    return ImmutableList.of(Kind.METHOD);
  }

}