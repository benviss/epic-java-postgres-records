import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Artist {
  private String name;
  private int id;

  public Artist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static List<Artist> all() {
    String sql = "SELECT id, name FROM artists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Artist.class);
    }
  }

  public int getId() {
    return id;
  }

  public static Artist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM artists where id=:id";
      Artist artist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Artist.class);
      return artist;
    }
  }

  public List<Record> getRecords() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM records where artistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Record.class);
    }
  }


  @Override
  public boolean equals(Object otherArtist) {
    if (!(otherArtist instanceof Artist)) {
      return false;
    } else {
      Artist newArtist = (Artist) otherArtist;
      return this.getName().equals(newArtist.getName()) && this.getId() == newArtist.getId();
    }
  }



  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO artists(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

}
