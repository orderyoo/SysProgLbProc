import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessManager {
    public static void main(String[] args) throws IOException {
        ProcessManager processManager = new ProcessManager();
        List<String> listOfPID = new ArrayList<String>();
        processManager.runProcesses(10, 1000, listOfPID);
        System.out.println(listOfPID);

    }
    public void runProcesses(int count, long interval, List listOfPID) {
        for (int i = 0; i < count; i++) {
            try {
                ProcessBuilder procBuilder = new ProcessBuilder("notepad.exe");
                procBuilder.start();
                Process process = Runtime.getRuntime().exec("tasklist /fi \"IMAGENAME eq notepad.exe\"");
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(String.valueOf(process));
                matcher.find();
                listOfPID.add(matcher.group());
                Thread.sleep(interval);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
