import java.util.ArrayList;
import org.sql2o.*;
import java.util.List;

public class Record {
  private String name;
  private int id;
  private int artistId;

  public Record(String name, int artistId) {
    this.name = name;
    this.artistId = artistId;
  }

  public String getName() {
    return name;
  }

  public int getArtistId() {
    return artistId;
  }

  public int getId() {
    return id;
  }

  public static List<Record> all() {
    String sql = "SELECT id, artistId FROM records";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Record.class);
    }
  }

  @Override
  public boolean equals(Object otherRecord) {
    if (!(otherRecord instanceof Record)) {
      return false;
    } else {
      Record newRecord = (Record) otherRecord;
      return this.getName().equals(newRecord.getName()) && this.getId() == newRecord.getId() && this.getArtistId() == newRecord.getArtistId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO records(name, artistId) VALUES (:name, :artistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("artistId", this.artistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Record find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM records where id=:id";
      Record record = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Record.class);
      return record;
    }
  }
}
