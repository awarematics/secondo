/*
 * Portions Copyright 2005 Sun Microsystems, Inc.  All Rights Reserved.
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

public class CollationData_hr extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for hr, default sorting except for the following: */

                /* add dz "ligature" between d and d<stroke>. */
                /* add d<stroke> between d and e. */
                /* add lj "ligature" between l and l<stroke>. */
                /* add l<stroke> between l and m. */
                /* add nj "ligature" between n and o. */
                /* add z<abovedot> after z.       */
                "& \u200f = \u030c "
                + "& \u0306 = \u030d "
                + "& C < c\u030c , C\u030c "  // C < c-caron
                + "< c\u0301 , C\u0301 "      // c-acute
                + "& D < \u01f3 , \u01f2 , \u01f1 "  // dz
                + "< dz , dZ , Dz , DZ "      // dz ligature
                + "< \u01c6 , \u01c5 , \u01c4 "  // dz-caron
                + "< \u0111 , \u0110 "           // d-stroke
                + "& L < lj , lJ , Lj , LJ " // l < lj ligature
                + "& N < nj , nJ , Nj , NJ " // n < nj ligature
                + "& S < s\u030c , S\u030c "  // s < s-caron
                + "& Z < z\u030c , Z\u030c "  // z < z-caron
            }
        };
    }
}
