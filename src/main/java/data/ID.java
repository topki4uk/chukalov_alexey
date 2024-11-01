package data;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ID {
  protected final AtomicLong id;

  public ID(long id) {
    this.id = new AtomicLong(id);
  }

  public long getID() {
    return id.get();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ID id1 = (ID) o;
    return id.get() == id1.id.get();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
