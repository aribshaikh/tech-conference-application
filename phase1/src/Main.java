import Controllers.MasterSystem;
import Gateways.ProgramGenerator;

public class Main {

    /**
     * main method to run the program
     * @param args: string arguments for main method
     */
    public static void main(String[] args) {
        ProgramGenerator programGenerator = new ProgramGenerator();
        MasterSystem masterSystem = programGenerator.readFromFile("conference_system");
        masterSystem.run();
    }
}