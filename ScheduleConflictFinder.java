package cp213;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Class to represent an event with start and end times
class Event {
    String name;
    int startTime;
    int endTime;

    public Event(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Method to check if this event conflicts with another event
    public boolean conflictsWith(Event other) {
        return !(this.endTime <= other.startTime || this.startTime >= other.endTime);
    }

    @Override
    public String toString() {
        return name + " (" + startTime + " - " + endTime + ")";
    }
}

public class ScheduleConflictFinder extends JFrame {
    /**
	 * 
	 */
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

        JButton addButton = new JButton("Add Event");
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int startTime = Integer.parseInt(startTimeField.getText());
                int endTime = Integer.parseInt(endTimeField.getText());
                Event event = new Event(name, startTime, endTime);
                events.add(event);
                displayEvents();
                nameField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");
            }
        });

        JButton findConflictsButton = new JButton("Find Conflicts");
        add(findConflictsButton, BorderLayout.SOUTH);
        findConflictsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayConflicts();
            }
        });
    }

    private void displayEvents() {
        textArea.setText("");
        for (Event event : events) {
            textArea.append(event.toString() + "\n");
        }
    }

    private void displayConflicts() {
        textArea.append("\nConflicting events:\n");
        boolean foundConflict = false;
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).conflictsWith(events.get(j))) {
                    foundConflict = true;
                    textArea.append(events.get(i) + " conflicts with " + events.get(j) + "\n");
                }
            }
        }
        if (!foundConflict) {
            textArea.append("No conflicting events found.\n");
        }
    }}
