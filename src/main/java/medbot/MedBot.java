package medbot;

import medbot.command.Command;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser() {
        PatientList patientList = new PatientList();
        boolean isInteracting = true;
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        while (isInteracting) {
            String userInput = ui.readInput();
            try {
                Command command = Parser.parseCommand(userInput);
                isInteracting = !command.isExit();
                command.execute(patientList, ui);
            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage());
            }
        }
    }
}