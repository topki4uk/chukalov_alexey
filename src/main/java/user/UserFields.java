package user;

public enum UserFields {
  FIRST_NAME("firstName"),
  LAST_NAME("lastName");

  private final String fieldName;

  UserFields(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return fieldName;
  }
}
