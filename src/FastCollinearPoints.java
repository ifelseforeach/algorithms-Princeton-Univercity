import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.Quick;

public class FastCollinearPoints {

    private int numberOfSegments = 0;
    private final List<LineSegment> segments = new ArrayList<LineSegment>();

    private final static class PointWithSlope {
        double slope;
        Point p;

        PointWithSlope(Point origin, Point p) {
            this.slope = origin.slopeTo(p);
            this.p = p;
        }
    }

    public FastCollinearPoints(Point[] points) { // finds all line segments
                                                 // containing 4 or more points
        if (points == null)
            throw new IllegalArgumentException();
        if (points.length < 4)
            return;

        // Point[] aux = Arrays.copyOf(points, points.length);
        PointWithSlope[] aux = new PointWithSlope[points.length];
        for (Point origin : points) {

            for (int i = 0; i < points.length; i++) {
                aux[i] = new PointWithSlope(origin, points[i]);
            }
            Arrays.sort(aux, new Comparator<PointWithSlope>() {
                @Override
                public int compare(PointWithSlope emp1, PointWithSlope emp2) {
                    return Double.compare(emp1.slope, emp2.slope);
                }
            });

            int count = 1;
            int coursor = 0;
            List<Point> line = new ArrayList<Point>();
            line.add(origin);
            line.add(aux[coursor].p);
            for (int i = 1; i < aux.length; i++) {

                if (Double.compare(aux[coursor].slope, aux[i].slope) == 0) {
                    count++;
                    line.add(aux[i].p);
                    // Corner conditions
                    if (i == aux.length - 1 && count >= 3) {
                        addLine(line);
                    }
                } else {
                    // Get a line
                    if (count >= 3) {
                        addLine(line);

                    }

                    count = 1;
                    coursor = i;
                    line = new ArrayList<Point>();
                    line.add(origin);
                    line.add(aux[coursor].p);
                }

            }
        }

    }

    private void addLine(List<Point> aux) {
       
        Point[] auxArr = new Point[aux.size()];
        auxArr = aux.toArray(auxArr);
        Quick.sort(auxArr);
        LineSegment segment = new LineSegment(auxArr[0], auxArr[auxArr.length - 1]);
        if (isLineSegmentExist(segment))
            return;
        this.numberOfSegments++;
        segments.add(segment);
    }

    public int numberOfSegments() { // the number of line segments
        // TODO
        return this.numberOfSegments;

    }

    public LineSegment[] segments() { // the line segments

        if (segments.isEmpty())
            return new LineSegment[0];
        LineSegment[] arr = new LineSegment[segments.size()];
        return segments.toArray(arr);
    }

    private boolean isLineSegmentExist(LineSegment checkSegment) {
        for (LineSegment segment : this.segments) {
            if (segment.toString().equals(checkSegment.toString()))
                return true;
        }
        return false;
    }
}
