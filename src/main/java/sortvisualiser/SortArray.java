package sortvisualiser;

import sortvisualiser.algorithm.SortAlgorithmSelector;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortArray extends JPanel {

    private final SortAlgorithmSelector algorithmSelector = new SortAlgorithmSelector();
    private final int[] array = new int[253];
    private final int[] barColors = new int[253];
    private final int y = 681, width = 4;
    private int x = 0;
    private int totalArrayChanges;
    private int delay = 0;
    private boolean isShuffling = false;

    public SortArray() {
        setPreferredSize(new Dimension(1280, 720));
        for (int i = 0; i < array.length; i++) {
            array[i] = (i + 10) * 2;
            barColors[i] = 0;
        }
    }

    public void startSorting(String algorithm) {
        shuffle();
        algorithmSelector.selectAlgorithm(algorithm, this);
        highlightArray();
    }

    public int getArrayLength() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    public int getMaxValue() {
        return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void swap(int firstIndex, int secondIndex, long delay, boolean isStep) {
        repaint();
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        barColors[firstIndex] = 1;
        barColors[secondIndex] = 1;
        if (isStep) totalArrayChanges++;
        threadSleep(delay);
    }

    public void updateSingle(int index, int value, long delay, boolean isStep) {
        array[index] = value;
        barColors[index] = 1;
        if (isStep) totalArrayChanges++;
        threadSleep(delay);
        repaint();
    }

    public void highlightArray() {
        for (int i = 0; i < getArrayLength(); i++) {
            updateSingle(i, getValue(i), 10, false);
        }
    }

    private void shuffle() {
        isShuffling = true;
        totalArrayChanges = 0;
        delay = 10;
        Random random = new Random();
        for (int i = 0; i < getArrayLength(); i++) {
            int swapWithIndex = random.nextInt(getArrayLength() - 1);
            swap(i, swapWithIndex, delay, false);
        }
        isShuffling = false;
    }

    private void threadSleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void draw(Graphics g) {
        for (int i = 0; i < getArrayLength(); i++) {
            if (barColors[i] == 1) {
                g.setColor(Color.RED);
                barColors[i] = 0;
            } else {
                g.setColor(Color.DARK_GRAY);
            }
            g.fillRect(x, y - getValue(i), width, getValue(i));
            x += width + 1;
        }
        x = 0;

        g.drawString("Array Changes : " + totalArrayChanges, 10, 5 + g.getFontMetrics().getHeight());
        g.drawString("Delay : " + delay + " ms", 10, 20 + g.getFontMetrics().getHeight());
        if (isShuffling) g.drawString("Shuffling...", 10, 45 + g.getFontMetrics().getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

}