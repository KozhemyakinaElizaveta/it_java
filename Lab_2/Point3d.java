package Lab_2;

public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;
    
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public double getX() {
        return xCoord;
    }

    public double getY() {
        return yCoord;
    }

    public double getZ() {
        return zCoord;
    }

    public void setX(double xInput) {
        xCoord = xInput;
    }

    public void setY(double yInput) {
        yCoord = yInput;
    }

    public void setZ(double zInput) {
        zCoord = zInput;
    }

    public static boolean equalsPoint3d(Point3d dot1, Point3d dot2){
        if (dot1.getX() != dot2.getX() || dot1.getY() != dot2.getY() || dot1.getZ() != dot2.getZ())
            return false;
        
        return true;
    }

    public double distanceTo(Point3d anotherDot)
    {
            double value = Math.round(Math.sqrt(
                Math.pow((anotherDot.getX() - this.getX()), 2) +
                        Math.pow((anotherDot.getY() - this.getY()), 2) +
                        Math.pow((anotherDot.getZ() - this.getZ()), 2)));

            double scale = Math.pow(10, 2);
            double result = Math.round(value * scale) / scale;            

        return result; 
    }
}
