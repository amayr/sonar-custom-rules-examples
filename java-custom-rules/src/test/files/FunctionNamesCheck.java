class MyClass {

  // mit underscore
  private void _theName() {}
  protected void _theName() {} // Noncompliant
  void _theName() {} // Noncompliant
  public void _theName() {} // Noncompliant

  // ohne underscore
  private void theName() {} // Noncompliant
  protected void theName() {}
  void theName() {}
  public void theName() {}

}