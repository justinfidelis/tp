package medbot.parser;

import medbot.command.Command;
import medbot.command.CommandType;
import medbot.command.HelpInfoCommand;
import medbot.command.HelpSchedulerCommand;
import medbot.command.personcommand.patientcommand.AddPatientCommand;
import medbot.command.personcommand.patientcommand.DeletePatientCommand;
import medbot.command.personcommand.patientcommand.EditPatientCommand;
import medbot.command.personcommand.patientcommand.FindPatientCommand;
import medbot.command.personcommand.patientcommand.ListPatientCommand;
import medbot.command.personcommand.patientcommand.ViewPatientCommand;
import medbot.exceptions.MedBotParserException;
import medbot.person.Patient;
import medbot.utilities.ViewType;

public abstract class PatientCommandParser {
    private static final String END_LINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_SWITCH = "switch";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;
    private static final String EMPTY_STRING = "";


    /**
     * Parses the user input and returns the corresponding command when the view type is PATIENT_INFO.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if user input is not a recognised command or contains invalid information.
     */
    public static Command parsePatientCommand(String userInput) throws MedBotParserException {
        if (userInput.startsWith(COMMAND_ADD)) {
            return parseAddPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeletePatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_VIEW)) {
            return parseViewPatientCommand(userInput);
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListPatientCommand();
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_HELP)) {
            return parseHelpCommand(userInput);
        }
        throw new MedBotParserException(ERROR_WRONG_COMMAND);
    }

    /**
     * Parses user input and returns ViewPatientCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return ViewPatientCommand object.
     * @throws MedBotParserException when patient id is not specified or not a number.
     */
    private static ViewPatientCommand parseViewPatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(4));
        return new ViewPatientCommand(personId);
    }

    /**
     * Parses user input and returns DeletePatientCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return DeletePatientCommand object.
     * @throws MedBotParserException when patient id given is not specified or not a number.
     */
    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(6));
        return new DeletePatientCommand(personId);
    }

    /**
     * Parses user input and returns EditPatientCommand with the specified patient ID and parameters.
     *
     * @param userInput String containing the full user input.
     * @return EditPatientCommand object.
     * @throws MedBotParserException when patient id given is not specified or not a number, or when
     *                               the parameters given cannot be parsed.
     */
    private static EditPatientCommand parseEditPatientCommand(String userInput) throws MedBotParserException {
        int patientId = ParserUtils.parseId(userInput.substring(4));
        String[] parameters = ParserUtils.getParameters(userInput);
        Patient patient = new Patient();
        patient.setNull();
        ParserUtils.updateMultiplePersonalInformation(patient, parameters);
        return new EditPatientCommand(patientId, patient);
    }

    /**
     * Parses user input and returns AddPatientCommand with the specified parameters.
     *
     * @param userInput String containing the full user input.
     * @return AddPatientCommand object.
     * @throws MedBotParserException when no parameters are specified, or when the parameters given cannot be parsed.
     */
    private static AddPatientCommand parseAddPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        Patient patient = new Patient();
        ParserUtils.updateMultiplePersonalInformation(patient, parameters);
        return new AddPatientCommand(patient);
    }

    private static FindPatientCommand parseFindPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        return new FindPatientCommand(parameters);
    }

    /**
     * Parses user input to pass relevant parameters into the HelpCommand constructor.
     *
     * @param userInput String containing the full user input.
     * @return HelpCommand object.
     * @throws MedBotParserException if parameters.length < 1 && > 2
     */
    private static Command parseHelpCommand(String userInput) throws MedBotParserException {
        String commandTypeString = EMPTY_STRING;
        try {
            commandTypeString = userInput.substring(4).strip();
        } catch (IndexOutOfBoundsException ie) {
            return new HelpInfoCommand();
        }
        if (commandTypeString.equals(EMPTY_STRING)) {
            return new HelpInfoCommand();
        }

        CommandType commandType = parseHelpInfoViewCommandType(commandTypeString);
        return new HelpInfoCommand(commandType);
    }

    private static CommandType parseHelpInfoViewCommandType(String commandTypeString) throws MedBotParserException {
        switch (commandTypeString) {
        case COMMAND_ADD:
            return CommandType.ADD_PERSON;
        case COMMAND_DELETE:
            return CommandType.DELETE_PERSON;
        case COMMAND_EDIT:
            return CommandType.EDIT_PERSON;
        case COMMAND_EXIT:
            return CommandType.EXIT;
        case COMMAND_HELP:
            return CommandType.HELP;
        case COMMAND_LIST:
            return CommandType.LIST_PERSON;
        case COMMAND_SWITCH:
            return CommandType.SWITCH;
        case COMMAND_VIEW:
            return CommandType.VIEW_PERSON;
        case COMMAND_FIND:
            return CommandType.FIND_PERSON;
        default:
            throw new MedBotParserException(ERROR_WRONG_COMMAND);
        }
    }
}
