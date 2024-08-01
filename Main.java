import javax.swing.*;

public class Main {
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	ScheduleConflictFinder gui = new ScheduleConflictFinder();
            gui.setVisible(true);
        }
    });
}
}
