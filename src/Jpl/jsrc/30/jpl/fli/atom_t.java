//tabstop=4
//*****************************************************************************/
// Project: jpl
//
// File:    $Id: atom_t.java,v 1.1 2006/09/14 14:20:36 behr Exp $
// Date:    $Date: 2006/09/14 14:20:36 $
// Author:  Fred Dushin <fadushin@syr.edu>
//          
//
// Description:
//    
//
// -------------------------------------------------------------------------
// Copyright (c) 1998 Fred Dushin
//                    All rights reserved.
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Library Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Library Public License for more details.
//*****************************************************************************/
package jpl.fli;



//----------------------------------------------------------------------/
// atom_t
/**
 * An atom_t is a specialised LongHolder which decrements its atom's
 * reference count when garbage-collected (finalized).
 * 
 * <hr><i>
 * Copyright (C) 1998  Fred Dushin<p>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.<p>
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Library Public License for more details.<p>
 * </i><hr>
 * @author  Fred Dushin <fadushin@syr.edu>
 * @version $Revision: 1.1 $
 */
// Implementation notes:  
// 
//----------------------------------------------------------------------/
public class atom_t 
extends LongHolder
{
	//------------------------------------------------------------------/
	// toString
	/**
	 * The String representation of an atom_t is just the atom's name.
	 * 
	 * @return  atom's name
	 */
	// Implementation notes:  
	// 
	//------------------------------------------------------------------/
	public String
	toString()
	{
		return Prolog.atom_chars( this );
	}

	protected void finalize() throws Throwable {

		super.finalize();
		Prolog.unregister_atom( this);
	}
}

//345678901234567890123456789012346578901234567890123456789012345678901234567890
