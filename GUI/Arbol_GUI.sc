/*
100612 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for arbol interaction.
*/


Arbol_GUI	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	arbolWindow;
	var	arbolHeight, arbolLeaves, arbolWind;
	
	*default {
		if (default.isNil) { default = this.new }; 
		^default;
	}
	
	*start { this.default.start; }	
	
	*stop { this.default.stop }	
	
	*new { | server, addr, chan = 0 |
		^super.new.init(server, addr);	
	}	
	
	init { | argServer, argAddr, argChan = 0 |
		server = argServer ?? { Server.default };  //define server
		addr =  argAddr ?? { NetAddr("127.0.0.1", 12345); }; //localhost, oF port
		this.arbolGUI;	// call arbolGUI		
	}
	
	arbolGUI	{
		arbolWindow = SCWindow("ARBOL GUI");
		arbolWindow.front;
		arbolWindow.view.background_(Color.new255(22,75,27));
		arbolWindow.bounds_(Rect(660,0,330,580));
		q = arbolWindow.addFlowLayout( 10@10, 20@5 );
		Button(arbolWindow, Rect(20,20,300,20))
				.states_([
					["view arbol", Color.black, Color.white],
					["stop arbol", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/arbolView", butt.value)}
		);
		//heigth
		arbolHeight=EZSlider(arbolWindow, 300@20, "height ",  ControlSpec(10, 200, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolHeight.setColors(Color.grey,Color.white);
		arbolHeight.action_({addr.sendMsg("/arbolHeigth", arbolHeight.value)});
		//leaves
		arbolLeaves=EZSlider(arbolWindow, 300@20, "leavesLenght ",  ControlSpec(10, 200, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolLeaves.setColors(Color.grey,Color.white);
		arbolLeaves.action_({addr.sendMsg("/arbolLeaves", arbolLeaves.value)});
		//wind
		arbolWind=EZSlider(arbolWindow, 300@20, "wind ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolWind.setColors(Color.grey,Color.white);
		arbolWind.action_({addr.sendMsg("/arbolWind", arbolWind.value)});
		
	}
}