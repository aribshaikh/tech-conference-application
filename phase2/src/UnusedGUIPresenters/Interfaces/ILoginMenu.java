package UnusedGUIPresenters.Interfaces;

import java.io.IOException;

public interface ILoginMenu {

    String getUsername();

    String getPassword();

    void invalidUser();

    void showAttendeeMenu(String username) throws IOException;

    void showOrganizerMenu(String username) throws IOException;

    void showSpeakerMenu(String username) throws IOException;

    void showAdminMenu(String username) throws IOException;
}
