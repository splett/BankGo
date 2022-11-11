package com.main.authorizer;

public class Singleton {

  private static Authorizer authorizer;

  /**
   * Get a instance of {@link Authorizer}. If the reset is true, create a new instance. 
   * @param reset {@link Boolean} true for reset; false for don't reset
   * @return {@link Authorizer} Authorizer instance
   */
  public static Authorizer getInstance(boolean reset) {
    if (authorizer == null || reset)
      authorizer = new Authorizer();
    return authorizer;
  }

  /**
   * Get instance of {@link Authorizer}
   * @return {@link Authorizer} Authorizer instance
   */
  public static Authorizer getInstance() {
    return getInstance(false);
  }
}
