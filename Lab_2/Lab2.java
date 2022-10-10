package Lab_2;

public class Lab2 {
    public static void main(String[] args)
    {
        double[] values = new double[9];

        for (int i = 0; i < args.length; i++) {
            values[i] = Double.parseDouble(args[i]);
        }

        Point3d dot1 = new Point3d(values[0], values[1], values[2]);
        Point3d dot2 = new Point3d(values[3], values[4], values[5]);
        Point3d dot3 = new Point3d(values[6], values[7], values[8]);

        if (!isTriangle(dot1, dot2, dot3))
            System.out.println("Error, one dot equals to another");
        else
            System.out.println(computeArea(dot1, dot2, dot3));
    }

    public static boolean isTriangle(Point3d firsPoint, Point3d secondPoint, Point3d thirdPoint) {
        if (Point3d.equalsPoint3d(secondPoint, firsPoint) || Point3d.equalsPoint3d(thirdPoint, firsPoint) || Point3d.equalsPoint3d(thirdPoint, secondPoint))
            return false;

        return true;
    }

    public static double computeArea(Point3d firsPoint, Point3d secondPoint, Point3d thirdPoint) {
        double lineAB = firsPoint.distanceTo(secondPoint);
        double lineBC = thirdPoint.distanceTo(secondPoint);
        double lineAC = firsPoint.distanceTo(thirdPoint);

        double perimeter = (lineAB + lineAC + lineBC) / 2;
        double area = Math.round(
                Math.sqrt(perimeter * (perimeter - lineAB) *
                        (perimeter - lineAC) * (perimeter - lineBC)));

        double scale = Math.pow(10, 2);
        double result = Math.round(area * scale) / scale;
        
        return result;
    }

    
}
