import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Event {
    String name;
    int eventStartTime;
    int eventEndTime;

    public Event(String name, int startTime, int endTime) {
        this.name = name;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;
    }

    public boolean conflict_finder(Event other) {
        return !(this.eventEndTime <= other.eventStartTime || this.eventStartTime >= other.eventEndTime);
    }

    public String toString() {
        return name + " (" + eventStartTime + " - " + eventEndTime + ")";
    }
}

public class ScheduleConflictFinder extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Event> events;
    private JTextArea textArea;

    public ScheduleConflictFinder() {
        events = new ArrayList<>();

        setTitle("Schedule Conflict Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel startTimeLabel = new JLabel("Start Time:");
        JTextField startTimeField = new JTextField();
        inputPanel.add(startTimeLabel);
        inputPanel.add(startTimeField);

        JLabel endTimeLabel = new JLabel("End Time:");
        JTextField endTimeField = new JTextField();
        inputPanel.add(endTimeLabel);
        inputPanel.add(endTimeField);

        JButton addButton = new JButton("Add To Schedule");
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int startTime = Integer.parseInt(startTimeField.getText());
                int endTime = Integer.parseInt(endTimeField.getText());
                Event event = new Event(name, startTime, endTime);
                events.add(event);
                display_events();
                nameField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");
            }
        });

        JButton findConflictsButton = new JButton("Find Conflicting Events In Schedule");
        add(findConflictsButton, BorderLayout.SOUTH);
        findConflictsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	display_conflict();
            }
        });
    }

    private void display_events() {
        textArea.setText("Schedule:\n");
        for (Event event : events) {
            textArea.append(event.toString() + "\n");
        }
    }

    private void display_conflict() {
        textArea.append("\nConflicting Events:\n");
        boolean conflicting_events = false;
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).conflict_finder(events.get(j))) {
                	conflicting_events = true;
                    textArea.append(events.get(i) + " conflicts with " + events.get(j) + "\n");
                }
            }
        }
        if (!conflicting_events) {
            textArea.append("No Conflicting Events.\n");
        }
    }}
