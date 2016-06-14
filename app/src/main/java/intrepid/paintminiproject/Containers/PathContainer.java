package intrepid.paintminiproject.Containers;

import android.graphics.Path;

public class PathContainer {
    private Path path;
    private Integer paintColor;
    private float strokeWidth;

    public PathContainer(Path path, Integer paintColor, float strokeWidth) {
        this.path = path;
        this.paintColor = paintColor;
        this.strokeWidth = strokeWidth;
    }

    public Path getPath() {
        return path;
    }

    public Integer getPaintColor() {
        return paintColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }
}
