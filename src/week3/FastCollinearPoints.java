package week3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        Point[] aux = Arrays.copyOf(points, points.length);

        for (Point origin : points) {

            Arrays.sort(aux, origin.slopeOrder());
            int count = 1;
            int coursor = 0;
            List<Point> line = new ArrayList<Point>();
            line.add(origin);
            line.add(aux[coursor]);

            for (int i = 1; i < aux.length; i++) {

                if (origin.slopeTo(aux[coursor]) == origin.slopeTo(aux[i])) {
                    count++;
                    line.add(aux[i]);
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
                    line.removeAll(line);
                    line.add(origin);
                    line.add(aux[coursor]);
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
