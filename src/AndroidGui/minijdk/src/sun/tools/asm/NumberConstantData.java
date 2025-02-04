/*
 * Copyright 1994-2003 Sun Microsystems, Inc.  All Rights Reserved.
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

package sun.tools.asm;

import sun.tools.java.*;
import java.io.IOException;
import java.io.DataOutputStream;

/**
 * A numeric constant pool item. Can either be integer, float, long or double.
 *
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
final
class NumberConstantData extends ConstantPoolData {
    Number num;

    /**
     * Constructor
     */
    NumberConstantData(ConstantPool tab, Number num) {
        this.num = num;
    }

    /**
     * Write the constant to the output stream
     */
    void write(Environment env, DataOutputStream out, ConstantPool tab) throws IOException {
        if (num instanceof Integer) {
            out.writeByte(CONSTANT_INTEGER);
            out.writeInt(num.intValue());
        } else if (num instanceof Long) {
            out.writeByte(CONSTANT_LONG);
            out.writeLong(num.longValue());
        } else if (num instanceof Float) {
            out.writeByte(CONSTANT_FLOAT);
            out.writeFloat(num.floatValue());
        } else if (num instanceof Double) {
            out.writeByte(CONSTANT_DOUBLE);
            out.writeDouble(num.doubleValue());
        }
    }
    /**
     * Return the order of the constant
     */
    int order() {
        return (width() == 1) ? 0 : 3;
    }

    /**
     * Return the number of entries that it takes up in the constant pool
     */
    int width() {
        return ((num instanceof Double) || (num instanceof Long)) ? 2 : 1;
    }
}
