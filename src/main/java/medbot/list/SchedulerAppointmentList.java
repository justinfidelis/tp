package medbot.list;

import java.util.HashMap;

import medbot.Appointment;
import medbot.exceptions.MedBotException;
import medbot.person.Person;

import static medbot.ui.Ui.END_LINE;

public class SchedulerAppointmentList extends MedBotList {
    private static final String END_LINE = System.lineSeparator();

    protected HashMap<Integer, Appointment> appointments = new HashMap<>();
    private int lastId = 1;

    public SchedulerAppointmentList() {

    }

    private int generateAppointmentId() {
        while (appointments.containsKey(lastId)) {
            lastId++;
        }
        return lastId;
    }

    /**
     * Returns a copy of the appointment with the specified appointmentId.
     *
     * @param appointmentId the appointmentId to search for
     * @return copy of appointment with the specified appointmentId
     * @throws MedBotException if no appointment with the specified appointmentId is found
     */
    public Appointment getAppointment(int appointmentId) throws MedBotException {
        if (!appointments.containsKey(appointmentId)) {
            throw new MedBotException(getAppointmentNotFoundErrorMessage(appointmentId));
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentId);
        appointment.mergeAppointmentData(appointments.get(appointmentId));
        return appointment;
    }

    /**
     * Adds the given appointment into the appointment list, returns the appointmentId that was allocated to it.
     *
     * <p>If the appointment has an appointmentId, the appointment will be added if there is no existing appointment
     * with that id, and throw a MedBotException if there is a clash. If not, i.e, appointmentId == 0, an id will be
     * generated and the appointment's id will be set to that value.
     *
     * @param appointment Appointment to be added into the appointment list
     * @return id of the appointment that was added
     * @throws MedBotException if another appointment with that id already exists
     */
    public int addAppointment(Appointment appointment) throws MedBotException {
        assert appointment.isComplete();
        int appointmentId = appointment.getAppointmentId();
        if (appointments.containsKey(appointmentId)) {
            throw new MedBotException("Appointment with id " + appointmentId + " already exits!");
        }
        if (appointmentId == 0) {
            appointmentId = generateAppointmentId();
            appointment.setAppointmentId(appointmentId);
        }
        appointments.put(appointmentId, appointment);
        return appointmentId;
    }


    /**
     * Returns if an appointment with the specified appointmentId exists.
     *
     * @param appointmentId the appointmentId to check for
     * @return Boolean of whether an appointment with that appointmentId exists.
     */
    public boolean doesAppointmentExist(int appointmentId) {
        return appointments.containsKey(appointmentId);
    }

    /**
     * Removes the appointment with the specified appointmentId.
     *
     * @param appointmentId the appointmentId of the appointment to be removed
     * @return the Appointment that was removed
     * @throws MedBotException if there is no appointment with that appointmentId
     */
    public Appointment deleteAppointment(int appointmentId) throws MedBotException {
        if (!appointments.containsKey(appointmentId)) {
            throw new MedBotException(getAppointmentNotFoundErrorMessage(appointmentId));
        }
        return appointments.remove(appointmentId);
    }

    private String getAppointmentNotFoundErrorMessage(int appointmentId) {
        return "No appointment with ID " + appointmentId + " found.";
    }

    public String listAppointments() {
        String output = "";
        for (int appointmentId : appointments.keySet()) {
            output += appointments.get(appointmentId) + END_LINE;
        }
        return output;
    }

    /**
     * Get storageString for all appointments.
     *
     * @return storageString of all appointments
     */
    @Override
    public String getStorageString() {
        String output = "";
        for (int key : appointments.keySet()) {
            Appointment appointment = appointments.get(key);
            String appointmentStorageString = appointment.getStorageString();
            output += appointmentStorageString + END_LINE;
        }
        return output;
    }

    @Override
    public void addListItemFromStorage(ListItem appointmentItem) {
        Appointment appointment = (Appointment) appointmentItem;
        int appointmentId = appointment.getId();
        appointments.put(appointmentId, appointment);
    }

    @Override
    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @Override
    public int getLastId() {
        return lastId;
    }
}
