package Lab_45;

import java.awt.geom.Rectangle2D;

//Класс фрактала множества мандельброта, наследуемый от генератора фракталов
public class BurningShip extends FractalGenerator{
    private static final String NAME = "Burning Ship";
    //Константа с максимальным количеством итераций
    public static final int MAX_ITERATIONS = 2000;
    //Переопределение метода для получения исходного диапазона на определённое комп.число
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    //Переопределение метода для получения кол-ва итераций для текущей координаты
    @Override
    public int numIterations(double x, double y) {
        int iteration = 0;
        double zreal = 0;
        double zimaginary = 0;
        double zreal2 = 0;
        double zimaginary2 = 0;
        while(iteration < MAX_ITERATIONS && (zreal2 + zreal2) < 4)
        {
            zimaginary = Math.abs((2 * zreal * zimaginary)) + y;
            zreal = (zreal2 - zimaginary2) + x;

            zreal2 = zreal*zreal;
            zimaginary2 = zimaginary*zimaginary;
            iteration++;
        }
        if (iteration == MAX_ITERATIONS) {
            return -1;
        }
        return iteration;
    }

    public String toString() {
        return "Burning Ship";
    }
}
