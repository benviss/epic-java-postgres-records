import java.util.ArrayList;
import java.util.List;

public class Record {
  private String mName;
  private static List<Record> instances = new ArrayList<Record>();
  private int mId;

  public Record(String _name) {
    mName = _name;
    instances.add(this);
    mId = instances.size();
  }

  public static List<Record> all() {
    return instances;
  }

  public static Record find(int _id) {
    return instances.get(_id - 1);
  }

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public static void clear() {
    instances.clear();
  }

}
