/*
100612 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for sound interaction.
*/


Sound_GUI	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	soundWindow;
	var 	soundLines,  soundMinElast, soundMaxElast, soundMinDamp, soundMaxDamp, numSoundSketches;	var	minAmp, maxAmp, minFreq, maxFreq;
	
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
		this.soundGUI;	// call soundGUI		
	}
	
	soundGUI	{
		soundWindow = SCWindow("sound GUI");
		soundWindow.front;
		soundWindow.view.background_(Color.new255(185,157,157));
		soundWindow.bounds_(Rect(0,0,330,580));
		q = soundWindow.addFlowLayout( 10@10, 20@5 );
		//a = StaticText(soundWindow, Rect(10, 10, 200, 20));
		Button(soundWindow, Rect(20,20,300,50))
				.states_([
					["view sound", Color.black, Color.white],
					["stop sound", Color.white, Color.black],
				])
				.action_({ arg butt; 
					addr.sendMsg("/viewSoundChanels", butt.value);
					}
		);
		//numSoundSketches
		numSoundSketches=EZSlider(soundWindow, 300@20, "numSoundSketches ", ControlSpec(1, 499, \lin, 1, 5), numberWidth:50,layout:\horz, initVal:99, labelWidth: 120);
		numSoundSketches.setColors(Color.grey,Color.white);
		numSoundSketches.action_({addr.sendMsg("/numSoundSketches", numSoundSketches.value)});
		//soundMinElast
		soundMinElast=EZSlider(soundWindow, 300@20, "soundMinElast ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		soundMinElast.setColors(Color.grey,Color.white);
		soundMinElast.action_({addr.sendMsg("/minSoundElasticity", soundMinElast.value)});
		//soundMaxElast
		soundMaxElast=EZSlider(soundWindow, 300@20, "soundMaxElast ", numberWidth:50,layout:\horz, initVal:1.0, labelWidth: 100);
		soundMaxElast.setColors(Color.grey,Color.white);
		soundMaxElast.action_({addr.sendMsg("/maxSoundElasticity", soundMaxElast.value)});
		//soundMinDamp
		soundMinDamp=EZSlider(soundWindow, 300@20, "soundMinDamp ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		soundMinDamp.setColors(Color.grey,Color.white);
		soundMinDamp.action_({addr.sendMsg("/minSoundDamping", soundMinDamp.value)});
		//soundMaxDamp
		soundMaxDamp=EZSlider(soundWindow, 300@20, "soundMaxDamp ", numberWidth:50,layout:\horz, initVal:1.0, labelWidth: 100);
		soundMaxDamp.setColors(Color.grey,Color.white);
		soundMaxDamp.action_({addr.sendMsg("/soundMaxDamp", soundMaxDamp.value)});
		Button(soundWindow, Rect(20,20,300,20))
				.states_([
					["sound Lines", Color.black, Color.white],
					["sound Points", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/soundLines", butt.value)}
		);
		
		
		//minAmp
		minAmp=EZSlider(soundWindow, 300@20, "minAmp ",numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		minAmp.setColors(Color.grey,Color.white);
		minAmp.action_({addr.sendMsg("/minAmp", minAmp.value)});
		//maxAmp
		maxAmp=EZSlider(soundWindow, 300@20, "maxAmp ", ControlSpec(0.001, 1, \lin, 0.001, 0.15), numberWidth:50,layout:\horz, initVal:0.15, labelWidth: 100);
		maxAmp.setColors(Color.grey,Color.white);
		maxAmp.action_({addr.sendMsg("/maxAmp", maxAmp.value)});
		//minFreq
		minFreq=EZSlider(soundWindow, 300@20, "minFreq ", ControlSpec(20, 10000, \lin, 1, 1), numberWidth:50,layout:\horz, initVal:20, labelWidth: 100);
		minFreq.setColors(Color.grey,Color.white);
		minFreq.action_({addr.sendMsg("/minFreq", minFreq.value)});
		//maxFreq
		maxFreq=EZSlider(soundWindow, 300@20, "maxFreq ", ControlSpec(40, 10000, \lin, 1, 1), numberWidth:50,layout:\horz, initVal:4000, labelWidth: 100);
		maxFreq.setColors(Color.grey,Color.white);
		maxFreq.action_({addr.sendMsg("/maxFreq", maxFreq.value)});				
	}
}