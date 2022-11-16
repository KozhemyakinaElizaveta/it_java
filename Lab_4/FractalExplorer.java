package Lab_4;
import java.awt.*;
import javax.swing.*;

import java.awt.geom.Rectangle2D;
import java.awt.event.*;
//Класс для отображения фрактала
public class FractalExplorer {
    private int displaySize;
    //Константы, хардкоженные строки
    private static final String TITLE = "Навигатор фракталов";
    private static final String RESET = "Сброс";
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    //Имплементируем интерфейс ActionListener для кнопки сброса
    class ResetButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);
            drawFractal();
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
        FractalExplorer fractalExplorer = new FractalExplorer(800);
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
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JImageDisplay(displaySize, displaySize);
        frame.add(display, BorderLayout.CENTER);
        JButton resetButton = new JButton(RESET);
        ResetButtonHandler resetButtonHandler = new ResetButtonHandler();
        resetButton.addActionListener(resetButtonHandler);
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        frame.add(resetButton, BorderLayout.SOUTH);
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
