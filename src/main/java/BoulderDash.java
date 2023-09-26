import controller.Controller;
import model.Game;
import model.Model;
import view.console.TextView;

public class BoulderDash {

    public static void main(String[] args) {
        Model game = new Game(1);
        Controller controller = new Controller(game, new TextView(game));
        controller.play();
    }
}
