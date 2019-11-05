import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

public class FastCollinearPoints {

    private int numberOfSegments = 0;
    private final List<LineSegment> segments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) { // finds all line segments
                                                 // containing 4 or more points
        if (points == null)
            throw new IllegalArgumentException();
        if (points.length < 4)
            return;

        Double[] slopes;

        for (Point origin : points) {

            slopes = new Double[points.length];
            for (int i = 0; i < points.length; i++) {
                slopes[i] = origin.slopeTo(points[i]);
            }
            Merge.sort(slopes);

            int count = 1;
            int coursor = 0;
            List<Point> aux = new ArrayList<Point>();
            aux.add(points[coursor]);
            for (int i = 1; i < slopes.length; i++) {

                if (Double.compare(slopes[coursor], slopes[i]) == 0) {
                    count++;                    
                    aux.add(points[i]);
                    // Corner conditions
                    if (i == slopes.length - 1 && count >= 3) {
                        addLine(aux, origin);
                    }
                } else {
                    // Get a line
                    if (count >= 3) {
                        addLine(aux, origin);

                    }

                    count = 1;
                    coursor = i;
                    aux = new ArrayList<Point>();
                    aux.add(points[coursor]);
                }

            }
        }
        
    }

    private void addLine(List<Point> aux, Point origin) {
        aux.add(origin);
        Point[] auxArr = new Point[aux.size()];
        auxArr = aux.toArray(auxArr);
        Quick.sort(auxArr);
        this.numberOfSegments++;
        LineSegment segment = new LineSegment(auxArr[0], auxArr[auxArr.length - 1]);
        segments.add(segment);

        System.out.println("LineSegment=" + segment.toString());
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
}
