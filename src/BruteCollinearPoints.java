import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private int numberOfSegments = 0;
    private final List<LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) { // finds all line segments

        if (points == null || points.length < 1)
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

    private void checkAndAddCollinear(Point p, Point q, Point r, Point s) {

        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 1 && Double.compare(p.slopeTo(q), p.slopeTo(s)) == 1) {
            if (p.slopeTo(q) == Double.NEGATIVE_INFINITY)
                return;
            Point[] points = new Point[4];
            points[0] = p;
            points[1] = q;
            points[2] = r;
            points[3] = s;

            // System.out.println("Trying to make a line from " + p.toString() +
            // ", " + q.toString() + ", "+ r.toString() + ", "+ s.toString());
            LineSegment line = createLine(points);
            addLine(line);
        }

    }

    private void addLine(LineSegment line) {

        if (!segments.isEmpty()) {
            for (LineSegment segment : segments) {
                if (segment.toString().equals(line.toString()))
                    return;
            }
        }
        segments.add(line);
        numberOfSegments++;

    }

    private LineSegment createLine(Point[] points) {
        // TODO Auto-generated method stub
        Point max = points[0];
        Point min = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(max) > 0)
                max = points[i];
            if (points[i].compareTo(min) < 0)
                min = points[i];
        }
        // System.out.println("Made a line " + min.toString() + ", " +
        // max.toString());
        return new LineSegment(min, max);
    }
}
