package com.teamunemployment.lolanalytics.Login;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */

public class ArrayAdapterFactory {

    public Context context;

    @Inject
    public ArrayAdapterFactory(Context context) {
        this.context = context;
    }
    /**
     * A proxy for the static createFromResource method on {@link ArrayAdapter}. Used for testing purposes.
     * @param textArrayResId the text array that we are building the array from.
     * @param textViewresId The text view that we are using for the arrayAdapter items.
     * @return The newly constructed arrayadapter.
     */
    public ArrayAdapter<CharSequence> getArrayAdapter(int textArrayResId, int textViewresId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                textArrayResId, textViewresId);
        return adapter;
    }
}
