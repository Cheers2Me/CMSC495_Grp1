/*
 * CMSC Capstone 495 - Group 1
 * Charlie Chiochankitmun 
 * 
 * Task Manager v.1 10/22/2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerUI extends JFrame {

    public DefaultListModel<String> taskListModel;
    public JList<String> taskList;
    public JTextField dataInput;
    public JTextArea taskTextArea;
    public List<Task> tasks;  // Store tasks with details

    public TaskManagerUI() {
        // Set up the frame
        setTitle("CMSC 495 - Group 1 Project");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        taskListModel = new DefaultListModel<>();
        dataInput = new JTextField(25);
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton printTasks = new JButton("Print Task(s)");
        taskTextArea = new JTextArea(60, 30);
        tasks = new ArrayList<>();

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame

        JPanel inputPanel = new JPanel();
        inputPanel.add(dataInput);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(printTasks);
        add(inputPanel, BorderLayout.SOUTH);

        add(new JScrollPane(taskTextArea));

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        printTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTasks();
            }
        });
    }

    private void addTask() {
        JTextField priorityField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dueDateField = new JTextField();

        Object[] message = {
                "Priority:", priorityField,
                "Task Description:", descriptionField,
                "Due Date:", dueDateField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Task", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String priority = priorityField.getText();
            String description = descriptionField.getText();
            String dueDate = dueDateField.getText();

            // Validate and add the task
            if (!priority.isEmpty() && !description.isEmpty() && !dueDate.isEmpty()) {
                Task task = new Task(priority, description, dueDate);
                tasks.add(task);
                updateTaskList();
                JOptionPane.showMessageDialog(this, "Task added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void printTasks() {
        StringBuilder tasksText = new StringBuilder("Current List of Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksText.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        taskTextArea.setText(tasksText.toString());
    }

    private void removeTask() {
        // Implement task removal as needed
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task.toString());
        }
    }

    private static class Task {
        private String priority;
        private String description;
        private String dueDate;

        public Task(String priority, String description, String dueDate) {
            this.priority = priority;
            this.description = description;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return "Priority: " + priority + ", Description: " + description + ", Due Date: " + dueDate;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerUI().setVisible(true);
            }
        });
    }
}



