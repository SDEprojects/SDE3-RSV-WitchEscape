package com.gamewindow;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VolumeSlider {
    JSlider jSlider;
    VolumeSlider (final FloatControl volumeControl){
        jSlider = new JSlider((int) volumeControl.getMaximum()*100,
                (int) volumeControl.getMinimum()*100,
                    (int) volumeControl.getValue()*100);
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float value = jSlider.getValue()/100f;
                volumeControl.setValue(value);

            }
        };
        jSlider.addChangeListener(listener);
    }
}
