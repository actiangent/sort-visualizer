package sortvisualiser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App implements ActionListener {

    private final SortArray sortArray = new SortArray();
    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    private final JFrame frame = new JFrame("Sort Visualizer");
    private final String[] algorithms = {"Bubble Sort", "Insertion Sort", "Pancake Sort", "Merge Sort", "Counting Sort", "Quick Sort", "Radix Sort", "Selection Sort"};
    private final JComboBox<String> algorithmsList = new JComboBox<>(algorithms);
    private final JButton runSortingButton = new JButton("Begin");

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                executor.shutdownNow();
            }
        });

        sortArray.add(algorithmsList);
        sortArray.add(runSortingButton);

        frame.setVisible(true);
        frame.add(sortArray);

        runSortingButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //run on another thread

        executor.submit(() -> {
            runSortingButton.setEnabled(false);
            String selectedAlgorithm = (String) algorithmsList.getSelectedItem();
            sortArray.startSorting(selectedAlgorithm);
            runSortingButton.setEnabled(true);
        });
    }

}

