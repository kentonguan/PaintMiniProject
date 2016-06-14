package intrepid.paintminiproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import intrepid.paintminiproject.Views.BlankCanvasView;

public class PaintActivity extends AppCompatActivity {
    private static final int SEEKBAR_PROGRESS_OFFSET = 1;
    public static final int SEEKBAR_X_OFFSET = 40;
    public static final int SEEKBAR_Y_OFFSET = 170;
    public static final int SEEKBAR_PROGRESS_MAX = 99;

    Boolean isEditMenuOpened;
    Boolean isColorPaletteOpened;
    Boolean isSeekBarOpened;
    Boolean isInEraseMode;

    @BindView(R.id.blank_canvas_view)
    BlankCanvasView blankCanvasView;
    @BindView(R.id.color_palette_layout)
    LinearLayout colorPaletteLayout;
    @BindView(R.id.edit_tools_layout)
    LinearLayout editToolsLayout;
    @BindView(R.id.stroke_width_seekbar)
    SeekBar strokeWidthSeekbar;
    @BindView(R.id.seekbar_textview)
    TextView seekbarTextView;

    ArrayList<View> colorPaletteList;
    ArrayList<View> editMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ButterKnife.bind(this);
        initFields();
        initSeekBar();
        closeAllMenus();
    }

    @OnClick(R.id.delete_fab)
    public void clearView() {
        blankCanvasView.clear();
        closeAllMenus();
    }

    @OnClick(R.id.edit_fab)
    public void closeEditMenu() {
        if (isEditMenuOpened) {
            for (View touchable : editMenuList) {
                ((FloatingActionButton) touchable).hide();
            }
            isEditMenuOpened = false;
            closeAllMenus();
        }
    }

    @OnClick(R.id.edit_fab)
    public void openEditMenu() {
        if (!isEditMenuOpened) {
            for (View touchable : editMenuList) {
                ((FloatingActionButton) touchable).show();
            }
            isEditMenuOpened = true;
        }
    }

    @OnClick(R.id.erase_fab)
    public void setBrushToErase() {
        closeAllMenus();
        blankCanvasView.setToEraseMode();
    }

    @OnClick({R.id.red_fab, R.id.orange_fab, R.id.yellow_fab, R.id.green_fab, R.id.blue_fab, R.id.purple_fab, R.id.black_fab})
    public void changePaintColor(FloatingActionButton floatingActionButton) {
        switch (floatingActionButton.getId()) {
            case R.id.red_fab:
                blankCanvasView.setStrokeColor(Color.RED);
                break;
            case R.id.orange_fab:
                blankCanvasView.setStrokeColor(Color.rgb(255, 165, 0));
                break;
            case R.id.yellow_fab:
                blankCanvasView.setStrokeColor(Color.YELLOW);
                break;
            case R.id.green_fab:
                blankCanvasView.setStrokeColor(Color.GREEN);
                break;
            case R.id.blue_fab:
                blankCanvasView.setStrokeColor(Color.BLUE);
                break;
            case R.id.purple_fab:
                blankCanvasView.setStrokeColor(Color.rgb(160, 32, 240));
                break;
            case R.id.black_fab:
                blankCanvasView.setStrokeColor(Color.BLACK);
                break;
        }

        closeAllMenus();
    }

    @OnClick(R.id.color_change_fab)
    public void closeColorMenu() {
        if (isColorPaletteOpened) {
            for (View color : colorPaletteList) {
                ((FloatingActionButton) color).hide();
            }
            isColorPaletteOpened = false;
        }
    }

    @OnClick(R.id.color_change_fab)
    public void showColorMenu() {
        if (!isColorPaletteOpened) {
            for (View color : colorPaletteList) {
                ((FloatingActionButton) color).show();
            }
            isColorPaletteOpened = true;
        }
    }

    @OnClick(R.id.blank_canvas_view)
    public void closeAllMenus() {
        closeEditMenu();
        closeColorMenu();
        closeSeekBar();
    }

    public void initSeekBar() {
        strokeWidthSeekbar.setMax(SEEKBAR_PROGRESS_MAX);
        seekbarTextView.setTextColor(Color.RED);
        seekbarTextView.setX(blankCanvasView.getWidth() - SEEKBAR_X_OFFSET);

        strokeWidthSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarTextView.setText(String.valueOf(strokeWidthSeekbar.getProgress() + SEEKBAR_PROGRESS_OFFSET));
                seekbarTextView.setY(blankCanvasView.getHeight() - strokeWidthSeekbar.getThumb().getBounds().centerX() - SEEKBAR_Y_OFFSET);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                blankCanvasView.setStrokeWidth(seekBar.getProgress() + SEEKBAR_PROGRESS_OFFSET);
                closeAllMenus();
            }
        });
    }

    public void closeSeekBar() {
        if (isSeekBarOpened) {
            isSeekBarOpened = false;
            strokeWidthSeekbar.setVisibility(View.GONE);
            seekbarTextView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.stroke_width_change_fab)
    public void showSeekBar() {
        if (!isSeekBarOpened) {
            isSeekBarOpened = true;
            strokeWidthSeekbar.setVisibility(View.VISIBLE);
            seekbarTextView.setVisibility(View.VISIBLE);
        } else {
            isSeekBarOpened = false;
            strokeWidthSeekbar.setVisibility(View.GONE);
            seekbarTextView.setVisibility(View.GONE);
        }
    }

    public void initFields() {
        isSeekBarOpened = true;
        isEditMenuOpened = true;
        isColorPaletteOpened = true;
        isInEraseMode = false;
        colorPaletteList = colorPaletteLayout.getTouchables();
        editMenuList = editToolsLayout.getTouchables();
    }


}
