/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yzy.supercleanmaster.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

/**
 * This class contains the static methods that return the appropriate UI
 * elements/colors based on the selected theme (light or dark).
 *
 * @author Saravan Pantham
 */
public class UIElementsHelper {

	private static final String NOW_PLAYING_COLOR = "NOW_PLAYING_COLOR";
	private static final String BLUE = "BLUE";
	private static final String RED = "RED";
	private static final String GREEN = "GREEN";
	private static final String ORANGE = "ORANGE";
	private static final String PURPLE = "PURPLE";
	private static final String MAGENTA = "MAGENTA";
	private static final String GRAY = "GRAY";
	private static final String WHITE = "WHITE";
	private static final String BLACK = "BLACK";

	/**
	 * Returns the ActionBar color based on the selected color theme (not used
	 * for the player).
	 */
	public static Drawable getGeneralActionBarBackground(Context context) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);

		Drawable drawable = new ColorDrawable(0xFF2c5aa9);
//		if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(BLUE)) {
//			drawable = new ColorDrawable(0xFF0099CC);
//
//		} else if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(RED)) {
//			drawable = new ColorDrawable(0xFFB0120A);
//
//		} else if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(GREEN)) {
//			drawable = new ColorDrawable(0xFF0A7E07);
//
//		} else if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(ORANGE)) {
//			drawable = new ColorDrawable(0xFFEF6C00);
//
//		} else if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(PURPLE)) {
//			drawable = new ColorDrawable(0xFF6A1B9A);
//
//		} else if (settings.getString(NOW_PLAYING_COLOR, GREEN).equals(MAGENTA)) {
//			drawable = new ColorDrawable(0xFFC2185B);
//
//		}

		return drawable;

	}

}
