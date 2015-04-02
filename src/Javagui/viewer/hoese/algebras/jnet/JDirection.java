//This file is part of SECONDO.

//Copyright (C) 2004, University in Hagen, Department of Computer Science,
//Database Systems for New Applications.

//SECONDO is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2 of the License, or
//(at your option) any later version.

//SECONDO is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with SECONDO; if not, write to the Free Software
//Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

//2012, June Simone Jandt

package  viewer.hoese.algebras.jnet;

import  sj.lang.ListExpr;
import  viewer.*;
import viewer.hoese.*;


/**
 * JDirection
 * Tells if the direction of driving respectively the side of
 * a route location or route interval or is up, down or both.
 */
public class JDirection{

  String dir;

  public JDirection(ListExpr value){
    if (value.listLength() == 1)
      dir = value.first().stringValue();
    else
      dir = "undefined";
  }

  public JDirection(String in){
    dir = in;
  }

  public String toString()  {
    return dir;
  }

  public int compareTo(String indir, boolean strict){
    if (dir.toString().compareTo(indir) != 0) {
      if (strict){
        return -1;
      } else {
        if (dir.toString().compareTo("Both") == 0 ||
            indir.compareTo("Both") == 0)
          return 0;
        else
          return -1;
      }
    } else {
      return 0;
    }
  }
}



