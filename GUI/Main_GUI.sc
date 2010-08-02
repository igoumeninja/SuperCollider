/*
100612 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for Main interaction.
*/


Main_GUI	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	mainWindow;
	var 	mouseLines, mouseMinElast, mouseMaxElast, mouseMinDamp, mouseMaxDamp, numMouseSketches;
	var	numSoundSketches, feedbackSpeedOrient, feedbackSpeedX, feedbackSpeedY;		
	*default {
		if (default.isNil) { default = this.new }; 
		^default;
	}
	
	*start { this.default.start; }	
	
	*stop { this.default.stop }	
	
	*new { | server, addr, chan = 0 |
		^super.new.init(server, addr);	
	}	
	
	// start initialize	
	init { | argServer, argAddr, argChan = 0 |
		server = argServer ?? { Server.default };  //define server
		addr =  argAddr ?? { NetAddr("127.0.0.1", 12345); }; //localhost, oF port
		this.mainGUI;	// call mainGUI		
	}
	
	//####################   Main GUI  #################
	mainGUI	{
		mainWindow = SCWindow("MAIN GUI");
		mainWindow.front;
		mainWindow.view.background_(Color.new255(75,96,130));
		mainWindow.bounds_(Rect(330,0,330,580));
		q = mainWindow.addFlowLayout( 10@10, 20@5 );
		
		//#### MOUSE Elasticity and Damping  #######################
		//numMouseSketches
		numMouseSketches=EZSlider(mainWindow, 300@20, "numMouseSketches ", ControlSpec(1, 499, \lin, 1, 5), numberWidth:50,layout:\horz, initVal:99, labelWidth: 120);
		numMouseSketches.setColors(Color.grey,Color.white);
		numMouseSketches.action_({addr.sendMsg("/numMouseSketches", numMouseSketches.value)});
		//mouseMinElast
		mouseMinElast=EZSlider(mainWindow, 300@20, "mouseMinElast ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		mouseMinElast.setColors(Color.grey,Color.white);
		mouseMinElast.action_({addr.sendMsg("/minMouseElasticity", mouseMinElast.value)});
		//mouseMaxElast
		mouseMaxElast=EZSlider(mainWindow, 300@20, "mouseMaxElast ", numberWidth:50,layout:\horz, initVal:1.0, labelWidth: 100);
		mouseMaxElast.setColors(Color.grey,Color.white);
		mouseMaxElast.action_({addr.sendMsg("/maxMouseElasticity", mouseMaxElast.value)});
		//mouseMinDamp
		mouseMinDamp=EZSlider(mainWindow, 300@20, "mouseMinDamp ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		mouseMinDamp.setColors(Color.grey,Color.white);
		mouseMinDamp.action_({addr.sendMsg("/minMouseDamping", mouseMinDamp.value)});
		//mouseMaxDamp
		mouseMaxDamp=EZSlider(mainWindow, 300@20, "mouseMaxDamp ", numberWidth:50,layout:\horz, initVal:1.0, labelWidth: 100);
		mouseMaxDamp.setColors(Color.grey,Color.white);
		mouseMaxDamp.action_({addr.sendMsg("/maxMouseDamping", mouseMaxDamp.value)});
		Button(mainWindow, Rect(20,20,300,20))
			.states_([
				["mouse Lines", Color.black, Color.white],
				["mouse Points", Color.white, Color.black],
			])
			.action_({ arg butt; addr.sendMsg("/mouseLines", butt.value);}
		);
		//feedback
		a = StaticText(mainWindow, Rect(10, 10, 300, 20));
		a.string = "feedback";
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view feedback", Color.black, Color.white],
					["stop feedback", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/feedbackView", butt.value)}
		);
		//timeLine
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view timeLine", Color.black, Color.white],
					["stop timeLine", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/timeLine", butt.value)}
		);
		//feedback speed and orientation
		feedbackSpeedOrient = Slider2D(mainWindow, Rect(20, 20,80, 80))
						.action_({|sl|
							//[\sliderX, sl.x, \sliderY, sl.y].postln;
							addr.sendMsg("/feedbackSpeedX", linlin(sl.x, 0, 1, -10, 10));
							addr.sendMsg("/feedbackSpeedY", linlin(sl.y, 0, 1, -10, 10));
						});
		
		//viewRotate
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view viewRotate", Color.black, Color.white],
					["stop viewRotate", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/viewRotate", butt.value)}
		);
		//feedbackSpeedX
		feedbackSpeedX=EZSlider(mainWindow, 300@20, "feedbackSpeedX ", ControlSpec(-10, 10, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0, labelWidth: 100);
		feedbackSpeedX.setColors(Color.grey,Color.white);
		feedbackSpeedX.action_({addr.sendMsg("/feedbackSpeedX", feedbackSpeedX.value)});
		//feedbackSpeedY
		feedbackSpeedY=EZSlider(mainWindow, 300@20, "feedbackSpeedY ", ControlSpec(-10, 10, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0, labelWidth: 100);
		feedbackSpeedY.setColors(Color.grey,Color.white);
		feedbackSpeedY.action_({addr.sendMsg("/feedbackSpeedY", feedbackSpeedY.value)});
		
		//mask
		a = StaticText(mainWindow, Rect(10, 10, 140, 20));
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view scope", Color.black, Color.white],
				])
				.action_({ arg butt; addr.sendMsg("/scopeView")}
		);				
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view beats", Color.black, Color.white],
					["stop beats", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/beatsView", butt.value)}
		);
		
		//circleView
		Button(mainWindow, Rect(20,20,300,20))
				.states_([
					["view circle", Color.black, Color.white],
					["stop circle", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/circleView", butt.value)}
		);
		
		}
}