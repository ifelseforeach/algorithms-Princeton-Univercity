import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

public class BruteCollinearPoints {

    private int numberOfSegments = 0;
    private final List<LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) { // finds all line segments

        if (points == null || containsRepeatedPoint(points))
            throw new NullPointerException();

        // containing 4 points
        if (points.length >= 4) {
            for (int i = 0; i < points.length; i++) {
                for (int j = 1; j < points.length; j++) {
                    if (points[i].compareTo(points[j]) == 0)
                        continue;
                    for (int k = 2; k < points.length; k++) {
                        if (points[k].compareTo(points[i]) == 0 || points[k].compareTo(points[j]) == 0)
                            continue;
                        for (int m = 3; m < points.length; m++) {
                            if (points[m].compareTo(points[i]) == 0 || points[m].compareTo(points[j]) == 0 || points[m].compareTo(points[k]) == 0)
                                continue;
                            checkAndAddCollinear(points[i], points[j], points[k], points[m]);
                        }
                    }
                }
            }
        }
    }

    private boolean containsRepeatedPoint(Point[] points) {
        Merge.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].toString().equals(points[i + 1].toString()))
                return true;
        }
        return false;

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

    private void checkAndAddCollinear(Point p, Point q, Point r, Point s) {

        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0 && Double.compare(p.slopeTo(q), p.slopeTo(s)) == 0) {

            List<Point> aux = new ArrayList<Point>();
            aux.add(p);
            aux.add(q);
            aux.add(r);
            aux.add(s);
            addLine(aux);
        }

    }

}
