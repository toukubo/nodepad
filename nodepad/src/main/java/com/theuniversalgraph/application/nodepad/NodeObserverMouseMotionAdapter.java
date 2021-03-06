/*
 *
 */
package com.theuniversalgraph.application.nodepad;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @author Administrator
 *
 */
public class NodeObserverMouseMotionAdapter extends MouseMotionAdapter {

	public void mouseDragged(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON2){
			
		
		}else{
			if(this.observer.getMode().getSelectingZone() != null){
				SelectingZone selectingZone = this.observer.getMode().getSelectingZone();
				if(arg0.getX() < selectingZone.getStartx()){
					selectingZone.setLocation(arg0.getX(),selectingZone.getY());
				}
				if(arg0.getY()< selectingZone.getStarty()){
					selectingZone.setLocation(selectingZone.getX(),arg0.getY());
				}
				selectingZone.setSize(Math.abs(arg0.getX()-selectingZone.getStartx()), Math.abs(arg0.getY()-selectingZone.getStarty()));
				this.observer.getMode().getSelectingZone().repaint();
				this.observer.repaint();
			}
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		super.mouseMoved(arg0);
	}
	
	public NodeObserverMouseMotionAdapter(NodeObserver observer)
	{
		this.observer = observer;
	}

	public void setObsever(NodeObserver observer)
	{
		this.observer = observer;
	}

	/**
	 * 
	 * @uml.property name="observer"
	 * @uml.associationEnd 
	 * @uml.property name="observer" multiplicity="(1 1)"
	 */
	private NodeObserver observer;

	/**
	 * 
	 * @uml.property name="observer"
	 */
	public NodeObserver getObserver() {
		return observer;
	}

}
