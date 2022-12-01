package Lab_45;
import java.awt.geom.Rectangle2D;


/**
 * Этот класс предоставляет общий интерфейс и операции для fractal
* generators, которые можно просмотреть в Fractal Explorer.
 */
public abstract class FractalGenerator {

    /**
     * Эта статическая вспомогательная функция принимает целочисленную координату и преобразует ее
    * в значение двойной точности, соответствующее определенному диапазону. Он
    * используется для преобразования координат пикселей в значения двойной точности для
    * вычисление фракталов и т.д.
     *
     * @param rangeMin the minimum value of the floating-point range
     * @param rangeMax the maximum value of the floating-point range
     *
     * @param size the size of the dimension that the pixel coordinate is from.
     *        For example, this might be the image width, or the image height.
     *
     * @param coord the coordinate to compute the double-precision value for.
     *        The coordinate should fall in the range [0, size].
     */
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * Задает указанный прямоугольник, содержащий начальный диапазон, подходящий для
    * генерируемого фрактала.
     */
    public abstract void getInitialRange(Rectangle2D.Double range);


    /**
     * Обновляет текущий диапазон, чтобы он был центрирован в указанных координатах
    * и увеличивался или уменьшался на указанный коэффициент масштабирования.
     */
    public void recenterAndZoomRange(Rectangle2D.Double range,
        double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }


    /**
     * Учитывая координату <em>x</em> + <em>iy</em> в комплексной плоскости,
    * вычисляет и возвращает количество итераций до фрактала
    * функция выходит за пределы ограничивающей области для этой точки. Указывается точка, которая
    * не исчезает до достижения предела итерации
    * с результатом -1.
     */
    public abstract int numIterations(double x, double y);
}

