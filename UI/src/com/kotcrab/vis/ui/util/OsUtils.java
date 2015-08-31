/*
 * Copyright 2014-2015 See AUTHORS file.
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

package com.kotcrab.vis.ui.util;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;

/**
 * Operating system related utils
 * @author Kotcrab
 * @author Simon Gerst
 */
public class OsUtils {
	private static final String OS = System.getProperty("os.name").toLowerCase();
	private static final boolean WINDOWS = OS.contains("win");
	private static final boolean MAC = OS.contains("mac");
	private static final boolean UNIX = OS.contains("nix") || OS.contains("nux") || OS.contains("aix");

	/** @return {@code true} if the current OS is Windows */
	public static boolean isWindows () {
		return WINDOWS;
	}

	/** @return {@code true} if the current OS is Mac */
	public static boolean isMac () {
		return MAC;
	}

	/** @return {@code true} if the current OS is Unix */
	public static boolean isUnix () {
		return UNIX;
	}

	/** @return {@code true} if the current OS is iOS */
	public static boolean isIos () {
		return Gdx.app.getType() == ApplicationType.iOS;
	}

	/** @return {@code true} if the current OS is Android */
	public static boolean isAndroid () {
		return Gdx.app.getType() == ApplicationType.Android;
	}

	/**
	 * Returns the Android API level it's basically the same as android.os.Build.VERSION.SDK_INT
	 * @return the API level. Returns 0 if the current OS isn't Android
	 */
	public static int getAndroidApiLevel () {
		if (isAndroid()) {
			return Gdx.app.getVersion();
		} else {
			return 0;
		}
	}

	/** Returns a shortcut text that can be displayed in a menu item. Returns keycode+keycode+keycode (eg. Ctrl+Shift+F5 on Windows
	 * and Linux, on Mac ⌘⇧F5). CONTROL_LEFT and CONTROL_RIGHT are mapped to Ctrl. The same goes for Alt and Shift.
	 * @param keycodes keycodes from {@link Keys} that are used to determine the shortcut text
	 * @return the platform dependent shortcut text **/
	public static String getShortcutFor (int... keycodes) {
		StringBuilder builder = new StringBuilder();
		String separatorString = "+";
		String ctrlKey = "Ctrl";
		String altKey = "Alt";
		String shiftKey = "Shift";
		if (OsUtils.isMac()) {
			separatorString = "";
			ctrlKey = "\u2318";
			altKey = "\u2325";
			shiftKey = "\u21E7";
		}
		for (int i = 0; i < keycodes.length; i++) {
			if (keycodes[i] == Keys.CONTROL_LEFT || keycodes[i] == Keys.CONTROL_RIGHT) {
				builder.append(ctrlKey);
			} else if (keycodes[i] == Keys.SHIFT_LEFT || keycodes[i] == Keys.SHIFT_RIGHT) {
				builder.append(shiftKey);
			} else if (keycodes[i] == Keys.ALT_LEFT || keycodes[i] == Keys.ALT_RIGHT) {
				builder.append(altKey);
			} else {
				builder.append(Keys.toString(keycodes[i]));
			}

			if (i < keycodes.length - 1) { // Is this NOT the last key
				builder.append(separatorString);
			}
		}
		return builder.toString();
	}

}
