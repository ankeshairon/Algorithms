package haqathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Quora engineering team went to an archery offsite recently.
 * <p/>
 * At the training corner, there was a target on the ground with a pile of arrows on it. Anna noticed that some of the arrows form the symbol 'Q' by intersecting the rings of the target.
 * <p/>
 * The target is composed of  concentric circles and there are  arrows lying on it, each represented as a line segment.
 * <p/>
 * The -th circle is centered at the origin  and has radius . The -th arrow is a line segment with endpoints  and .
 * <p/>
 * Now, Anna wonders if it is possible to write a program to quickly count the number of 'Q's formed. A 'Q' is defined as a pair of a circle and an arrow such that the arrow intersects the circumference of the circle exactly once.
 * <p/>
 * See the diagram for some examples of 'Q's: Alt text
 * <p/>
 * For simplicity, no endpoint of an arrow will lie on any circle, and no arrow will be tangent to a circle.
 * <p/>
 * Constraints
 * <p/>
 * For 100% of the test data,
 * For 50% of the test data,
 * All the  are less than  and greater than 0
 * <p/>
 * All the coordinates are less than  by absolute value
 * <p/>
 * Input Format
 * <p/>
 * Line 1: One integer
 * Line 2:  integers,  (the radii of the circles)
 * <p/>
 * Line 3: One integer
 * Line 4...M+3: Line  contains 4 integers: , , , , the coordinates of the endpoints of the -th arrow.
 * <p/>
 * Output Format
 * <p/>
 * Line 1: One integer, the number of 'Q's.
 * <p/>
 * Sample Input
 * <p/>
 * 4
 * 1 2 3 4
 * 3
 * 1 -1 4 -3
 * 2 1 1 2
 * 1 -2 3 -4
 * Sample Output
 * <p/>
 * 5
 */
public class Archery {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().replaceAll(" ", ""));

        List<Point> points = new ArrayList<>();

        String[] tokens = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            points.add(new Point(Double.parseDouble(tokens[i]), PointType.CIRCUMFERENCE));
        }

        n = Integer.parseInt(reader.readLine().replaceAll(" ", ""));
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            points.addAll(Point.createLeftRight(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
        }

        Collections.sort(points);

        int count = 0;
        int runningSegments = 0;

        for (Point point : points) {
            switch (point.pointType) {
                case LEFT:
                    ++runningSegments;
                    break;
                case RIGHT:
                    --runningSegments;
                    break;
                case CIRCUMFERENCE:
                    count += runningSegments;
                    break;
            }
        }
        System.out.println(count);
    }
}

class Point implements Comparable<Point> {
    Double distanceFromCenter;
    PointType pointType;

    Point(double distanceFromCenter, PointType pointType) {
        this.distanceFromCenter = distanceFromCenter;
        this.pointType = pointType;
    }

    @Override
    public int compareTo(Point that) {
        return distanceFromCenter.compareTo(that.distanceFromCenter);
    }

    public static List<Point> createLeftRight(Double x1, Double y1, Double x2, Double y2) {
        List<Point> points = new ArrayList<>(5);
        double d1 = Math.sqrt(x1 * x1 + y1 * y1);
        double d2 = Math.sqrt(x2 * x2 + y2 * y2);
        if (d1 < d2) {
            points.add(new Point(d1, PointType.LEFT));
            points.add(new Point(d2, PointType.RIGHT));
        } else {
            points.add(new Point(d1, PointType.RIGHT));
            points.add(new Point(d2, PointType.LEFT));
        }
        return points;
    }
}

enum PointType {
    LEFT,
    RIGHT,
    CIRCUMFERENCE
}