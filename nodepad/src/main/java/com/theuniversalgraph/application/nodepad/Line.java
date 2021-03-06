// Decompiled by Jad v1.5.7d. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Line.java

package com.theuniversalgraph.application.nodepad;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Hashtable;


public class Line extends Component
{
	public void setChild(NodeComponent str) {
		child = str;
	}

	public NodeComponent getChild() {
		return child;
	}


    public void removed()
    {
        getParent().remove(this);
        parent_node.getChildren().remove(child.getNodeInterface().getId());
        parent_node.getChildren_line().remove(child.getNodeInterface().getId());
        child.getParents().remove(getParent_node().getNodeInterface().getId());
        if(parent_node.getObserver().getMode().isHardDelete()){
        	RemoveAllLine removeAllLine = new RemoveAllLine();
        }
    }

    public void disselected()
    {
        setForeground(new Color(122,124,115));
        if(parent_node.getObserver().getMode().getSelected_line() != null)
            parent_node.getObserver().getMode().getSelected_line().remove(parent_node);
        repaint();
    }

    public Line(NodeComponent a, NodeComponent b)
    {
        parent_node = null;
        child = null;
        setLocationAndSize(a, b);
//        setForeground(new Color(122,124,115));
        enableEvents(16L);
        repaint();
    }

    public boolean isSelected()
    {
        return getForeground().equals(Color.red);
    }

    public void paint(Graphics g)
    {
    	Graphics2D graphics2D = (Graphics2D)g;
    	graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.setColor(this.getParent_node().getObserver().getNodeFieldApplet().getSkin().getLineColor());
        if(!reverse){
        	if(this.getSize().getWidth() <=5 ){
            	graphics2D.drawLine(0, 0, 0, getSize().height);
        	}else{
            	graphics2D.drawLine(0, 0, getSize().width, getSize().height);
        	}
        }else{
        	if(this.getSize().getWidth() <= 5){
            	graphics2D.drawLine(0, 0, 0, getSize().height);
        	}else{
            	graphics2D.drawLine(0, getSize().height, getSize().width, 0);
        	}
        }
        graphics2D.fillRoundRect(getSize().width / 2 - 5, getSize().height / 2 - 5, 10, 10,4,4);
        super.paint(g);
    }

    public void processMouseEvent(MouseEvent me)
    {
        switch(me.getID())
        {
        default:
            break;

        case 500: 
            if(!isSelected())
                selected();
            else
                disselected();
            break;
        }
    }

    public boolean contains(int x, int y)
    {
        return x > getSize().width / 2 - 5 && y > getSize().height / 2 - 5 && x < getSize().width / 2 + 5 && y < getSize().height / 2 + 5;
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void selected()
    {
        setForeground(Color.red);
        if(parent_node.getObserver().getMode().getSelected_line() == null)
            parent_node.getObserver().getMode().setSelected_line(new Hashtable());
        parent_node.getObserver().getMode().getSelected_line().put(parent_node.getNodeInterface().getId(), this);
        repaint();
    }

    public Dimension getMinimumSize()
    {
        return new Dimension(10, 10);
    }

    public void setLocationAndSize(NodeComponent a, NodeComponent b)
    {
        parent_node = a;
        child = b;
        if(a.getLocation().x <= b.getLocation().x)
        {
            if(a.getLocation().y+a.getOutlet().getLocation().y <= b.getLocation().y)
            {
                setLocation(a.getLocation().x, a.getLocation().y+a.getOutlet().getLocation().y);
                setSize(b.getLocation().x - a.getLocation().x, b.getLocation().y - a.getLocation().y -a.getOutlet().getLocation().y);
                reverse = false;
            } else
            {
                setLocation(a.getLocation().x, b.getLocation().y);
                setSize(b.getLocation().x - a.getLocation().x, (a.getLocation().y+a.getOutlet().getLocation().y) - b.getLocation().y);
                reverse = true;
            }
        } else
        if(a.getLocation().y+a.getOutlet().getLocation().y< b.getLocation().y)
        {
            setLocation(b.getLocation().x, a.getLocation().y+a.getOutlet().getLocation().y);
            setSize(a.getLocation().x - b.getLocation().x, b.getLocation().y - a.getLocation().y - a.getOutlet().getLocation().y);
            reverse = true;
        } else
        {
            setLocation(b.getLocation());
            setSize(a.getLocation().x - b.getLocation().x, (a.getLocation().y+a.getOutlet().getLocation().y) - b.getLocation().y);
            reverse = false;
        }
        if(this.getSize().getWidth() <=3){
        	this.setSize(new Dimension(5,(int)this.getSize().getHeight()));
        }
        repaint();
    }

    public Dimension getPreferredSize()
    {
        return getSize();
    }

    public void addedToRecycleBox(RecycleBox rb)
    {
        getParent().remove(this);
    }

	public void setParent_node(NodeComponent str) {
		parent_node = str;
	}

	public NodeComponent getParent_node() {
		return parent_node;
	}


    private boolean reverse;

	private NodeComponent parent_node;

	private NodeComponent child;

}
