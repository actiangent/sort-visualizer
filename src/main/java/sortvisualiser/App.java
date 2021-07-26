package sortvisualiser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {

    private final SortArray sortArray = new SortArray();
    private final JFrame frame = new JFrame("Sort Visualizer");
    private final String[] algorithms = {"Bubble Sort", "Insertion Sort", "Pancake Sort", "Merge Sort", "Counting Sort", "Quick Sort"};
    private final JComboBox<String> algorithmsList = new JComboBox<>(algorithms);
    private final JButton runSortingButton = new JButton("Begin");

    public static void main(String[] args) {
        new sortvisualiser.App().run();
    }

    private void run() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);

        sortArray.add(algorithmsList);
        sortArray.add(runSortingButton);

        frame.setVisible(true);
        frame.add(sortArray);

        runSortingButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //run on a another thread

        new Thread(() -> {
            runSortingButton.setEnabled(false);
            String selectedAlgorithm = (String) algorithmsList.getSelectedItem();
            sortArray.startSorting(selectedAlgorithm);
            runSortingButton.setEnabled(true);
        }).start();

    }

}

