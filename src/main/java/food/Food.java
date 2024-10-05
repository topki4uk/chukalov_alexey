package food;

public enum Food {
  GRASS("grass"),
  FISH("fish"),
  BEEF("beef");

  private final String title;

  public String getTitle() {
    return title;
  }

  Food(String title) {
    this.title = title;
  }
}
