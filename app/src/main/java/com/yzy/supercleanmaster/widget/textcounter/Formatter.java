package com.yzy.supercleanmaster.widget.textcounter;

/**
 * Created by prem on 10/28/14.
 *
 * Used to format the float value of the text to be set.
 */
public interface Formatter {

    /**
     * Format the value in anyway you want
     * Remember to prepend the prefix and append the suffix
     *
     * @param prefix prefix set in {@link com.github.premnirmal.textcounter.CounterView}
     * @param suffix suffix set in {@link com.github.premnirmal.textcounter.CounterView}
     * @param value the number value at the current time interval
     * @return formatted String
     */
    String format(String prefix, String suffix, float value);

}
