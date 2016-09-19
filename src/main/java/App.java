import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("artists/:id/records/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Artist artist = Artist.find(Integer.parseInt(request.params(":id")));
      model.put("artist", artist);
      model.put("template", "templates/artist-records-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/records", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("records", Record.all());
      model.put("template", "templates/records.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/records", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Artist artist = Artist.find(Integer.parseInt(request.queryParams("artistId")));
      String name = request.queryParams("name");
      Record newRecord = new Record(name, artist.getId());
      newRecord.save();
      model.put("artist", artist);
      model.put("template", "templates/artist-record-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/records/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Record record = Record.find(Integer.parseInt(request.params(":id")));
      model.put("record", record);
      model.put("template", "templates/record.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/artists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/artist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/artists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Artist newArtist = new Artist(name);
      newArtist.save();
      model.put("template", "templates/artist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/artists", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("artists", Artist.all());
     model.put("template", "templates/artists.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/artists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Artist artist = Artist.find(Integer.parseInt(request.params(":id")));
      model.put("artist", artist);
      model.put("template", "templates/artist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
