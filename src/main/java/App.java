import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object>  model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl" );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/amount", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Float userFloat = Float.parseFloat(request.queryParams("changeForm"));
      ChangeMachine newMachine = new ChangeMachine();
      String userChange = newMachine.makeChange(userFloat);
      String sorry = "Sorry the machine is out of change";
      model.put("changeForm", request.queryParams("changeForm"));
      model.put("template", "templates/amount.vtl" );
      if (userFloat < 8.20) {
        model.put("finalOutput", userChange);
      } else { model.put("finalOutput", sorry);

      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }
}
