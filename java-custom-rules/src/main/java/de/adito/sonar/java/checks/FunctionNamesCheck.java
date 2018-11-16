package de.adito.sonar.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import java.util.List;

/**
 * Prüft ob die Methoden-Namen eine bestimmte Form haben.
 * - Private Methoden haben zwingend einen Unterstrich als erstes Zeichen
 * - Andere Methoden haben zwingend keinen Unterstrich als erstes Zeichen
 *
 * @author a.mayr, 09.11.2017.
 */
@Rule(
        key = "AditoFunctionNamesCheck",
        name = "Private methods should start with _.",
        description = "Private methods should start with _, all other methods are not allowed to do so.",
        priority = Priority.MAJOR,
        tags = {"codesmell"})
public class FunctionNamesCheck extends IssuableSubscriptionVisitor {

  @Override
  public void visitNode(Tree tree) {
    // Wir haben uns für Kind.METHOD registriert, also haben wir immer einen MethodTree.
    MethodTree method = (MethodTree) tree;
    if (method.symbol().isPrivate()) {
      if (!_startsWithUnderscore(method))
      {
        reportIssue(method.simpleName(), "Private methods should start with a '_' (underscore)!");
      }
    }
    else
    {
      if (_startsWithUnderscore(method))
      {
        reportIssue(method.simpleName(), "Non-private methods shouldn't start with a '_' (underscore)!");
      }
    }
  }

  private boolean _startsWithUnderscore(MethodTree pMethod)
  {
//    return pMethod.simpleName().name().startsWith("_");
    return pMethod.symbol().name().startsWith("_");
  }

  @Override
  public List<Kind> nodesToVisit() {
    return ImmutableList.of(Kind.METHOD);
  }

}