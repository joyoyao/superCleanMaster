package com.yzy.supercleanmaster.widget.textcounter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.widget.textcounter.formatters.CommaSeparatedDecimalFormatter;
import com.yzy.supercleanmaster.widget.textcounter.formatters.DecimalFormatter;
import com.yzy.supercleanmaster.widget.textcounter.formatters.IntegerFormatter;
import com.yzy.supercleanmaster.widget.textcounter.formatters.NoFormatter;

/**
 * Created by prem on 10/28/14.
 *
 * A TextView that counts values depending on the attributes set via xml or via java.
 * Depending on the value set in {@link #setAutoStart(boolean)}, the counter will start.
 * You may call {@link #start()} to start manually at any time.
 */
public class CounterView extends TextView {

    /**
     * 5 milliseconds
     */
    public static final long DEFAULT_INTERVAL = 5l;

    /**
     * increment by 10 units for each time interval
     */
    public static final float DEFAULT_INCREMENT = 10f;


    protected String text, prefix, suffix;

    /**
     * Using floats because android's attributes do not support longs
     */
    protected long timeInterval;
    protected float increment, startValue, endValue;

    protected CounterType counterType;

    protected boolean autoStart, autoFormat;

    protected Counter counter;

    protected Formatter formatter;

    public CounterView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs == null) {
            this.prefix = "";
            this.suffix = "";
            this.text = "";
            this.timeInterval = DEFAULT_INTERVAL;
            this.increment = DEFAULT_INCREMENT;
            this.startValue = 0f;
            this.endValue = 0f;
            this.autoStart = false;
            this.autoFormat = true;
            this.counterType = CounterType.NUMBER;
            return;
        }

        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CounterView,
                defStyleAttr, defStyleRes);

        try {
            final CharSequence prefix = typedArray.getText(R.styleable.CounterView_prefix);
            if (prefix != null) {
                this.prefix = prefix.toString();
            } else {
                this.prefix = "";
            }

            final CharSequence suffix = typedArray.getText(R.styleable.CounterView_suffix);
            if (suffix != null) {
                this.suffix = suffix.toString();
            } else {
                this.suffix = "";
            }

            final CharSequence text = typedArray.getText(R.styleable.CounterView_android_text);
            if (text != null) {
                this.text = text.toString();
            } else {
                this.text = "";
            }

            this.timeInterval = (long) typedArray.getFloat(R.styleable.CounterView_timeInterval, DEFAULT_INTERVAL);
            this.increment = typedArray.getFloat(R.styleable.CounterView_incrementValue, DEFAULT_INCREMENT);
            this.startValue = typedArray.getFloat(R.styleable.CounterView_startValue, 0f);
            this.endValue = typedArray.getFloat(R.styleable.CounterView_endValue, 0f);
            this.autoStart = typedArray.getBoolean(R.styleable.CounterView_autoStart, true);
            this.autoFormat = typedArray.getBoolean(R.styleable.CounterView_formatText, true);

            final int type = typedArray.getInteger(R.styleable.CounterView_type, 0);
            switch(type) {
                case 0:
                    counterType = CounterType.NUMBER;
                    break;
                case 1:
                    counterType = CounterType.DECIMAL;
                    break;
                case 2:
                    counterType = CounterType.BOTH;
                    break;
            }
        } finally {
            typedArray.recycle();
        }

        counter = new Counter(this, startValue, endValue, timeInterval, increment);

        updateCounterType();
    }

    protected void updateCounterType() {
        switch(counterType) {
            case NUMBER:
                formatter = new IntegerFormatter();
                break;
            case DECIMAL:
                formatter = new DecimalFormatter();
                break;
            case BOTH:
                formatter = new CommaSeparatedDecimalFormatter();
                break;
        }
    }

    void setCurrentTextValue(final float number) {
        text = formatter.format(prefix, suffix, number);
        setText(text);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (autoStart) {
            start();
        }
    }

    /**
     * Start the counter. This method will be called if autostart is set to true
     */
    public void start() {
        removeCallbacks(counter);
        post(counter);
    }

    /**
     * Set the prefix for the number
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Set the suffix for the number
     * @param suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Set the time interval between each increment. Default is {@link #DEFAULT_INTERVAL}
     * @param timeInterval
     */
    public void setTimeInterval(long timeInterval) {
        this.timeInterval = timeInterval;
        counter = new Counter(this, startValue, endValue, timeInterval, increment);
    }

    /**
     * Set the increment between the two values. Can be negative or positive. Default is
     * {@link #DEFAULT_INCREMENT}
     * @param increment
     */
    public void setIncrement(float increment) {
        this.increment = increment;
        counter = new Counter(this, startValue, endValue, timeInterval, increment);
    }

    /**
     * Set the value the counter should start at
     * @param startValue
     */
    public void setStartValue(float startValue) {
        this.startValue = startValue;
        counter = new Counter(this, startValue, endValue, timeInterval, increment);
    }

    /**
     * Set the value the counter should end at
     * @param endValue
     */
    public void setEndValue(float endValue) {
        this.endValue = endValue;
        counter = new Counter(this, startValue, endValue, timeInterval, increment);
    }


    /**
     * Set the counterType. See for further details
     * @param counterType
     */
    public void setCounterType(CounterType counterType) {
        this.counterType = counterType;
        updateCounterType();
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }


    /**
     * Allow the view to auto format the number depending on the
     * set using {@link #setCounterType(CounterType)}
     * @param formatText
     */
    public void setAutoFormat(boolean formatText) {
        if(autoFormat) {
            if (counterType == CounterType.NUMBER) {
                formatter = new IntegerFormatter();
            } else {
                formatter = new DecimalFormatter();
            }
        } else {
            formatter = new NoFormatter();
        }

        this.autoFormat = formatText;
    }

    /**
     * Set a custom  to format the text before
     * {@link #setText(CharSequence)} is called
     * @param formatter
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
}
