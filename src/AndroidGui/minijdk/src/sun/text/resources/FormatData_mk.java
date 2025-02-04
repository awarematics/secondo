/*
 * Portions Copyright 1997-2005 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/*
 * (C) Copyright Taligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The original version of this source code and documentation
 * is copyrighted and owned by Taligent, Inc., a wholly-owned
 * subsidiary of IBM. These materials are provided under terms
 * of a License Agreement between Taligent and Sun. This technology
 * is protected by multiple US and International patents.
 *
 * This notice and attribution to Taligent may not be removed.
 * Taligent is a registered trademark of Taligent, Inc.
 *
 */

package sun.text.resources;

import java.util.ListResourceBundle;

public class FormatData_mk extends ListResourceBundle {
    /**
     * Overrides ListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u0458\u0430\u043d\u0443\u0430\u0440\u0438", // january
                    "\u0444\u0435\u0432\u0440\u0443\u0430\u0440\u0438", // february
                    "\u043c\u0430\u0440\u0442", // march
                    "\u0430\u043f\u0440\u0438\u043b", // april
                    "\u043c\u0430\u0458", // may
                    "\u0458\u0443\u043d\u0438", // june
                    "\u0458\u0443\u043b\u0438", // july
                    "\u0430\u0432\u0433\u0443\u0441\u0442", // august
                    "\u0441\u0435\u043f\u0442\u0435\u043c\u0432\u0440\u0438", // september
                    "\u043e\u043a\u0442\u043e\u043c\u0432\u0440\u0438", // october
                    "\u043d\u043e\u0435\u043c\u0432\u0440\u0438", // november
                    "\u0434\u0435\u043a\u0435\u043c\u0432\u0440\u0438", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "\u0458\u0430\u043d.", // abb january
                    "\u0444\u0435\u0432.", // abb february
                    "\u043c\u0430\u0440.", // abb march
                    "\u0430\u043f\u0440.", // abb april
                    "\u043c\u0430\u0458.", // abb may
                    "\u0458\u0443\u043d.", // abb june
                    "\u0458\u0443\u043b.", // abb july
                    "\u0430\u0432\u0433.", // abb august
                    "\u0441\u0435\u043f\u0442.", // abb september
                    "\u043e\u043a\u0442.", // abb october
                    "\u043d\u043e\u0435\u043c.", // abb november
                    "\u0434\u0435\u043a\u0435\u043c.", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "DayNames",
                new String[] {
                    "\u043d\u0435\u0434\u0435\u043b\u0430", // Sunday
                    "\u043f\u043e\u043d\u0435\u0434\u0435\u043b\u043d\u0438\u043a", // Monday
                    "\u0432\u0442\u043e\u0440\u043d\u0438\u043a", // Tuesday
                    "\u0441\u0440\u0435\u0434\u0430", // Wednesday
                    "\u0447\u0435\u0442\u0432\u0440\u0442\u043e\u043a", // Thursday
                    "\u043f\u0435\u0442\u043e\u043a", // Friday
                    "\u0441\u0430\u0431\u043e\u0442\u0430" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u043d\u0435\u0434.", // abb Sunday
                    "\u043f\u043e\u043d.", // abb Monday
                    "\u0432\u0442.", // abb Tuesday
                    "\u0441\u0440\u0435.", // abb Wednesday
                    "\u0447\u0435\u0442.", // abb Thursday
                    "\u043f\u0435\u0442.", // abb Friday
                    "\u0441\u0430\u0431." // abb Saturday
                }
            },
            { "Eras",
                new String[] { // era strings
                    "\u043f\u0440.\u043d.\u0435.",
                    "\u0430\u0435."
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    ".", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "HH:mm:ss z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:", // medium time pattern
                    "HH:mm", // short time pattern
                    "EEEE, d, MMMM yyyy", // full date pattern
                    "d, MMMM yyyy", // long date pattern
                    "d.M.yyyy", // medium date pattern
                    "d.M.yy", // short date pattern
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GuMtkHmsSEDFwWahKzZ" },
        };
    }
}
