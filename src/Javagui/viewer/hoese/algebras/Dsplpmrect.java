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

package viewer.hoese.algebras;

import java.awt.geom.*;
import java.awt.*;
import viewer.*;
import viewer.hoese.*;
import sj.lang.ListExpr;
import java.util.*;
import viewer.hoese.algebras.periodic.*;
import javax.swing.JPanel;
import tools.Reporter;

public class Dsplpmrect extends DisplayTimeGraph{

Rectangle2D.Double rect;
Rectangle2D.Double bounds;
Class linearClass = (new PMRectLinear()).getClass();
Time T = new Time();
TotalMove Move=null;

public int numberOfShapes(){
   return 1;
}

public Shape getRenderObject(int num,AffineTransform at){
  if(num!=0){
     return null;
  }
  if(Move==null){
     return null;
  }
  double t = RefLayer.getActualTime();
  T.readFrom(t);
  Rectangle2D.Double Rect =  (Rectangle2D.Double) Move.getObjectAt(T);
  return Rect;
}

public void init(String name, int nameWidth, int indent, ListExpr type,ListExpr value,QueryResult qr){
  AttrName = extendString(name, nameWidth, indent);
  Move = new TotalMove();
  if(!Move.readFrom(value,linearClass)){
     qr.addEntry("("+AttrName +"WrongListFormat )");
     return;
  }
  qr.addEntry(this);
  if(Move.getBoundingBox()==null){
     Reporter.writeError("Bounding Box can't be created");
  }
  bounds = Move.getBoundingBox().toRectangle2D();
  double StartTime = Move.getStartTime().getDouble();
  RelInterval D = Move.getInterval();
  double EndTime = StartTime;
  EndTime += D.getLength().getDouble();
  TimeBounds = new Interval(StartTime,EndTime,D.isLeftClosed(),D.isRightClosed());
}

public JPanel getTimeRenderer(double PixelTime){
   return new JPanel();
}

/* returns the minimum bounding box of this moving point */
public Rectangle2D.Double getBounds(){
   return bounds;
}


}
