package Lab_6;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.io.*;

//Класс для отображения фрактала
public class FractalExplorer {
    private int displaySize;
    private int rowsRemaining;
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
    private JButton resetButton;
    private JButton saveButton;

   //Имплементируем интерфейс ActionListener для обработки событий
    class ActionsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals(RESET)){
                fractal.getInitialRange(range);
                drawFractal();
            } else if (command.equals(COMBOBOX_CHANGE)) {
                // если событие поступило от выпадающего списка, вы можете извлечь выбранный элемент из виджета и установить его в
                //качестве текущего генератора фракталов
                JComboBox source = (JComboBox) e.getSource();
                fractal = (FractalGenerator) source.getSelectedItem();
                fractal.getInitialRange(range);
                display.clearImage(); //сбрасываем начальный диапазон и перерисовываем фрактал
                drawFractal();
            } else if (command.equals(SAVE)) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                //возможность указания пользователем, в какой файл он будет сохранять изображение
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
                        //проинформировать пользователя об ошибке через диалоговое окно
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
            if(rowsRemaining != 0) return;//приложение будет реагировать на щелчки мышью, только в том случае, 
        //если больше нет строк, которые должны бытьнарисованы
            display.clearImage();
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }


    class FractalWorker extends javax.swing.SwingWorker<Object, Object> {
        //возвращает промежуточные значения во время выполнения
        //эти промежуточные значения будут доступны при использовании методов publish () и process ()
        private int y;
        private int[] rgb;
        public FractalWorker(int y){
            this.y = y;
        }
        @Override
        protected Object doInBackground(){
            //выполняет фоновые операции 
            //Swing вызывает этот метод в фоновом потоке, а не в потоке обработке событий
            rgb = new int[displaySize];
            //цикл сохраняет каждое значение RGB в соответствующем элементе целочисленного массива
            int color;
            for(int x = 0; x < displaySize; x++){
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                int iteration = fractal.numIterations(xCoord, yCoord);
                color = 0;
                if(iteration > 0){
                    float hue = 0.7f + (float) iteration / 200f;
                    color = Color.HSBtoRGB(hue, 1f, 1f);
                }
                rgb[x] = color;
                //сохраняем цвет каждого пикселя
            }
            return null;
        }
        @Override
        protected void done() {
            //вызывается, когда фоновая задача завершена
            //Он вызывается в потоке обработки событий, поэтому данному методу разрешено взаимодействовать с пользовательским интерфейсом
            for(int x = 0; x < displaySize; x++){
                display.drawPixel(x, y, rgb[x]);
            }
            display.repaint(0, 0, y, displaySize, 1);
            rowsRemaining--;
            if(rowsRemaining == 0) enableUI(true);// если после уменьшения значение «rows remaining» равно 0
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
        //компонент Java Swing, который представляет собой выпадающий список,  управляtn коллекцией объектов, но 
        //объекты должны предоставлять метод toString()
        comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(actionsHandler);
        topPanel.add(comboBox, BorderLayout.NORTH);


        //Save Button
        saveButton = new JButton(SAVE);
        saveButton.addActionListener(actionsHandler);
        bottomPanel.add(saveButton, BorderLayout.WEST);

        //Reset Button
        resetButton = new JButton(RESET);
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

    //включает или отключает кнопки с выпадающим списком в пользовательском
    //интерфейсе на основе указанного параметра
    private void enableUI(boolean val){
        comboBox.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

   //Метод для отрисовки фрактала
    private void drawFractal(){
        //отключить все элементы пользовательского интерфейса во время рисования
        enableUI(false);
        rowsRemaining = displaySize; //установить значение равным общему количеству строк,которые нужно нарисовать
        for(int y = 0; y < displaySize; y++){
            FractalWorker worker = new FractalWorker(y);
            worker.execute();
            //запустит фоновый поток и запустит задачу в фоновом режиме
        }
    }
}
