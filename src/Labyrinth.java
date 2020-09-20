import fr.ubordeaux.ao.labyrinth.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Labyrinth extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		MainController controller;
		try {
			controller = MainController.makeInstance();
			controller.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
