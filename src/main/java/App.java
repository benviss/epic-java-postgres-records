import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/" (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/whereToSubmitInfo", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String variable1 = request.queryParams("variable1");
      String variable2 = request.queryParams("variable2");
      model.put("variable1", variable1);
      model.put("variable2", variable2);
      model.put("template", "templates/pageToLoad.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
