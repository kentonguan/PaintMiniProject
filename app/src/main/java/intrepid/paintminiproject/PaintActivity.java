package intrepid.paintminiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import intrepid.paintminiproject.Views.BlankCanvasView;

public class PaintActivity extends AppCompatActivity {

    @BindView(R.id.blank_canvas_view)
    BlankCanvasView blankCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ButterKnife.bind(this);
    }
}
