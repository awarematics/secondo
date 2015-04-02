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

//2012, July Simone Jandt

package viewer.hoese.algebras;

import viewer.hoese.algebras.jnet.*;
import java.awt.geom.*;
import java.awt.*;
import sj.lang.ListExpr;
import java.util.*;
import viewer.*;
import viewer.hoese.*;
import tools.Reporter;
import gui.SecondoObject;


/**
 * A Displayclass for graphical representation of sets of positions in jnet
 * defined by an an jnetwork id and a set of route locations.
 */
public class Dspljpoints extends DisplayGraph {
	/** The internal datatype representation */
	String label;
	JPoints jpts;
  Rectangle2D.Double bounds=null;

  public Dspljpoints(){
    super();
  }
/**
 * Init jpoints instance.
 * @param value
 *            the nestedlist representation of the jpoint
 * @param qr
 *            queryresult to display output.
 */

public void init(String name, int nameWidth, int indent,ListExpr type,
                 ListExpr value, QueryResult qr){
  AttrName = extendString(name,nameWidth, indent);
  try{
    jpts = new JPoints(value);
    label = jpts.toString();
    qr.addEntry(this);
    return;
  } catch (JNetworkNotAvailableException ex){
    err = true;
    Reporter.writeError(ex.getMessage());
    qr.addEntry(AttrName + ex.getMessage());
    return;
  } catch (Exception ex) {
    ex.printStackTrace();
    err = true;
    Reporter.writeError("Error in ListExpr :parsing aborted");
    qr.addEntry(AttrName + "error");
    return;
  }

}

public String toString(){
  return jpts.toString();
}

 public int numberOfShapes(){
    return jpts.numOfShapes();
 }

 public boolean isPointType(int no){
     return jpts.isPointType(no);
 }

 public boolean isLineType(int no){
    return jpts.isLineType(no);
 }


/**
 * Returns the bounding rectangle of the jpoint
 * @return The bound rectangle of the jpoint
 */

public Rectangle2D.Double getBounds(){
  if (bounds == null)
    bounds = jpts.getBounds();
  return bounds;
}

public Shape getRenderObject(int no, AffineTransform af){
  double pointSize = Cat.getPointSize(renderAttribute,CurrentState.ActualTime);
  boolean asRect = Cat.getPointasRect();
  return jpts.getRenderObject(no, af, pointSize, asRect);
}

}
