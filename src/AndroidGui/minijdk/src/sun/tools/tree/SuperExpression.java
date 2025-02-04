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

package sun.tools.tree;

import sun.tools.java.*;
import sun.tools.asm.Assembler;
import java.io.PrintStream;
import java.util.Hashtable;

/**
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
public
class SuperExpression extends ThisExpression {

    /**
     * Constructor
     */
    public SuperExpression(long where) {
        super(SUPER, where);
    }

    /**
     * Constructor for "outer.super()"
     */
    public SuperExpression(long where, Expression outerArg) {
        super(where, outerArg);
        op = SUPER;
    }

    public SuperExpression(long where, Context ctx) {
        super(where, ctx);
        op = SUPER;
    }

    /**
     * Check expression
     */
    public Vset checkValue(Environment env, Context ctx, Vset vset, Hashtable exp) {
        vset = checkCommon(env, ctx, vset, exp);
        if (type != Type.tError) {
            // "super" is not allowed in this context:
            env.error(where, "undef.var.super", idSuper);
        }
        return vset;
    }

    /**
     * Check if the present name is part of a scoping prefix.
     */
    public Vset checkAmbigName(Environment env, Context ctx,
                               Vset vset, Hashtable exp,
                               UnaryExpression loc) {
        return checkCommon(env, ctx, vset, exp);
    }

    /** Common code for checkValue and checkAmbigName */
    private Vset checkCommon(Environment env, Context ctx, Vset vset, Hashtable exp) {
        ClassDeclaration superClass = ctx.field.getClassDefinition().getSuperClass();
        if (superClass == null) {
            env.error(where, "undef.var", idSuper);
            type = Type.tError;
            return vset;
        }
        vset = super.checkValue(env, ctx, vset, exp);
        type = superClass.getType();
        return vset;
    }

}
