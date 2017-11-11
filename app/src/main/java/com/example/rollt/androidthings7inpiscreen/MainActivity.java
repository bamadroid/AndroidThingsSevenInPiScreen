package com.example.rollt.androidthings7inpiscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity
{
    private Button mButton;
    private TextView mTextView;
    private Button mPowerButton;
    private ProgressBar mSpinner;
    private static int sCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.testText);

        mSpinner = findViewById(R.id.progressBar1);

        mButton = findViewById(R.id.testButton);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Update text and display click count
                mTextView.setText(getString(R.string.its_working_text, ++sCount));
            }
        });

        mPowerButton = findViewById(R.id.powerButton);
        mPowerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Show progress spinner
                mSpinner.setVisibility(View.VISIBLE);

                finish();
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // Start Power Off
        powerOff();
    }

    /**
     * Safely Power Down Raspberry Pi
     */
    private void powerOff()
    {
        try
        {
            Runtime.getRuntime().exec("reboot -p");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
