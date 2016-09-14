import java.util.List;
import java.util.ArrayList;

public class Artist {
  private String mName;
  private static ArrayList<Artist> instances = new ArrayList<Artist>();
  private int mId;
  private List<Record> mRecords;

  public Artist(String _name) {
    mName = _name;
    instances.add(this);
    mId = instances.size();
    mRecords = new ArrayList<Record>();
  }

  public static ArrayList<Artist> all() {
    return instances;
  }

  public static Artist find(int _id) {
    return instances.get(_id - 1);
  }

  public static void clear() {
    instances.clear();
  }

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public List<Record> getRecords() {
    return mRecords;
  }

  public void addRecord(Record _record) {
    mRecords.add(_record);
  }

}
