package medbot.ui;

import medbot.list.PersonList;

public class PatientUi {
    private static final String END_LINE = System.lineSeparator();
    private static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;


    public static String getListPatientHelpMessage() {
        return "View information of all current patients." + END_LINE
                + "Format: list" + END_LINE
                + "Expected Output for 2 patients: " + END_LINE
                + "Patient ID: [PATIENT_ID_1] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE
                + "Patient ID: [PATIENT_ID_2] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE;
    }

    public static String getViewPatientHelpMessage() {
        return "View a patient’s personal information." + END_LINE
                + "Format: view PATIENT_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "id: PATIENT_ID " + "name: NAME " + "phone number: PHONE_NUMBER "
                + "email: EMAIL " + "address: ADDRESS" + END_LINE;
    }

    public static String getAddPatientHelpMessage() {
        return "Add a patient to the patient’s list." + END_LINE
                + "Format:" + END_LINE
                + "add i/PATIENT_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output:" + END_LINE
                + "Added patient with patient ID: PATIENT_ID" + END_LINE;
    }

    public static String getEditPatientHelpMessage() {
        return "Edit the personal and medical information of a patient in the list." + END_LINE
                + "Format:" + END_LINE
                + "edit PATIENT ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output: " + END_LINE
                + "The information of patient with ID PATIENT_ID has been edited to:" + END_LINE
                + "Patient ID: [PATIENT_ID] IC: [PATIENT_IC] Name: [NAME] H/P: [PHONE_NUMBER] "
                + "Email: [EMAIL] Address: [ADDRESS] " + END_LINE;
    }

    public static String getFindPatientHelpMessage() {
        return "Find patients from the list based on given attributes." + END_LINE
                + "Format:" + END_LINE
                + "find [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "    * The attributes do not have to be in full." + END_LINE
                + "    * At least one attribute must be present." + END_LINE
                + "Expected Output:" + END_LINE
                + "Patient ID: PATIENT_ID IC: PATIENT_IC Name: NAME "
                + "H/P: PHONE_NUMBER Email: EMAIL Address: ADDRESS" + END_LINE;
    }

    public static String getDeletePatientHelpMessage() {
        return "Delete a patient from the list." + END_LINE
                + "Format:" + END_LINE
                + "delete PATIENT_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "Patient with id PATIENT_ID deleted from system." + END_LINE;
    }


    /**
     * Prints a message when viewing the profile of a patient.
     *
     * @param patientInfo the Info of the patient to be printed.
     * @return the Patient information
     */
    public static String getPatientInfo(String patientInfo) {
        return "Here's the requested patient:" + END_LINE + END_LINE
                + patientInfo + END_LINE;
    }

    /**
     * Prints all patients in a list.
     *
     * @param patientList the list containing patients to be printed.
     * @return all Patients' information.
     */
    public static String getAllPatientsString(PersonList patientList) {
        String output = getPatientTableHeader();
        output += patientList.listPersons();
        output += TABLE_ROW_SEPARATOR;
        return output;
    }

    public static String getPatientTableHeader() {
        String output = "Here is a list of all patients:" + END_LINE;
        output += "For full details of each patient, please use the command \"view PATIENT_ID\"" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        return output;
    }

}