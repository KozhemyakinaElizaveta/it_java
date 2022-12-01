package Lab_45;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.io.*;
//Класс для отображения фрактала
public class FractalExplorer {
    private int displaySize;
    //Константы, хардкоженные строки
    private static final String TITLE = "Навигатор фракталов";
    private static final String RESET = "Сброс";
    private static final String SAVE = "Сохранить";
    private static final String CHOOSE = "Выбрать фрактал :";
    private static final String COMBOBOX_CHANGE = "comboBoxChanged";
    private static final String SAVE_ERROR = "Ошибка при сохранении изображения";
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    private JComboBox comboBox;

    //Имплементируем интерфейс ActionListener для обработки событий
    class ActionsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals(RESET)){
                fractal.getInitialRange(range);
                drawFractal();
            } else if (command.equals(COMBOBOX_CHANGE)) {
                JComboBox source = (JComboBox) e.getSource();
                fractal = (FractalGenerator) source.getSelectedItem();
                fractal.getInitialRange(range);
                display.clearImage();
                drawFractal();
            } else if (command.equals(SAVE)) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                if(fileChooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String path = file.toString();
                    if(path.length() == 0) return;
                    if(!path.contains(".png")){
                        file = new File(path + ".png");
                    }
                    try {
                        javax.imageio.ImageIO.write(display.getImage(), "png", file);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, exception.getMessage(), SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    //Наследуем MouseAdapter для обработки событий мыши
    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            display.clearImage();
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    //Точка входа в программу
    public static void main(String[] args){
        FractalExplorer fractalExplorer = new FractalExplorer(600);
        fractalExplorer.createAndShowGUI();
    }

    //Конструктор класса
    public FractalExplorer(int displaySize){
        this.displaySize = displaySize;
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
    }

    //Метод для инициализации графического интерфейса Swing
    public void createAndShowGUI(){
        ActionsHandler actionsHandler = new ActionsHandler();
        //Frame
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display
        display = new JImageDisplay(displaySize, displaySize);
        frame.add(display, BorderLayout.CENTER);

        //Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //label
        JLabel label = new JLabel(CHOOSE);
        topPanel.add(label);

        //ComboBox
        comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(actionsHandler);
        topPanel.add(comboBox, BorderLayout.NORTH);


        //Save Button
        JButton saveButton = new JButton(SAVE);
        saveButton.addActionListener(actionsHandler);
        bottomPanel.add(saveButton, BorderLayout.WEST);

        //Reset Button
        JButton resetButton = new JButton(RESET);
        resetButton.addActionListener(actionsHandler);
        bottomPanel.add(resetButton, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        //Mouse Handler
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        //Misc
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        drawFractal();
    }

    //Метод для отрисовки фрактала
    private void drawFractal(){
        for(int i = 0; i < displaySize; i++){
            for(int j = 0; j < displaySize; j++){
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, i);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, j);
                int iteration = fractal.numIterations(xCoord, yCoord);
                if (iteration == -1) {
                    display.drawPixel(i, j, 0);
                }
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(i, j, rgbColor);
                }
            }
            display.repaint();
        }
    }
}
