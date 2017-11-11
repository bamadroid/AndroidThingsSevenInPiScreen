package com.example.rollt.androidthings7inpiscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Activity used to verify the Raspberry Pi 7in Touch Screen
 * <p>
 * Launches the main activity on startup and displays a button that is clickable.
 * Included a Power button to shut down the Raspberry Pi ("reboot -p")
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

                // Destroys the Activity
                finish();
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // Shutdown Pi
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
