package Lab_6;
import java.awt.image.BufferedImage;
import java.awt.*;

//Класс дисплея, наследуемый от JComponent
public class JImageDisplay extends javax.swing.JComponent{
    private BufferedImage buffImage;

    //Конструктор класса
    public JImageDisplay(int width, int height) {
        buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }

    //Метод для отрисовки изображения
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage (buffImage, 0, 0, buffImage.getWidth(), buffImage.getHeight(), null);
    }

    //Метод для очистки изображения
    public void clearImage() {
        int[] rgbArray = new int[buffImage.getWidth() * buffImage.getHeight()];
        buffImage.setRGB(0, 0, buffImage.getWidth(), buffImage.getHeight(), rgbArray, 0, 1);
    }

    //Метод для установки пикселя в определенный цвет
    public void drawPixel(int x, int y, int color){
        buffImage.setRGB(x, y, color);
    }

    public BufferedImage getImage(){
        return buffImage;
    }
}
