package medbot.command.patientcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.ListPersonCommand;


public class ListPatientCommand extends ListPersonCommand {

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String allPatientsString = ui.getAllPatientsString(scheduler.getPatientList());
        ui.printOutput(allPatientsString);
    }
}
