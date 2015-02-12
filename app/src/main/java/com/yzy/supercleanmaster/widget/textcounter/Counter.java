package com.yzy.supercleanmaster.widget.textcounter;

/**
 * Created by prem on 10/28/14.
 *
 * Class that handles the counting up/down of the text value
 */
class Counter implements Runnable {

    final CounterView view;
    final float increment, startValue, endValue;
    final long interval;
    float currentValue, newValue;

    Counter(CounterView view, float startValue, float endValue, long interval, float increment) {
        this.view = view;
        this.startValue = startValue;
        this.endValue = endValue;
        this.interval = interval;
        this.increment = increment;
        this.newValue = this.startValue;
        this.currentValue = this.startValue - increment;
    }

    @Override
    public void run() {
        if (valuesAreCorrect()) {
            float valueToSet;
            if (newValue <= endValue) {
                valueToSet = newValue;
            } else {
                valueToSet = endValue;
            }
            view.setCurrentTextValue(valueToSet);
            currentValue = newValue;
            newValue += increment;
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, interval);
        }
    }

    private boolean valuesAreCorrect() {
        if(increment >= 0) {
            return newValue >= currentValue;
        } else {
            return newValue <= currentValue;
        }
    }
}
