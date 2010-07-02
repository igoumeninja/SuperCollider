/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
*/


Fbo_GUI	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	
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
		this.fboGUI;
	}
	
	fboGUI	{
		window = SCWindow("Frame Buffer Objects");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(990,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO enable", Color.black, Color.white],
					["FBO disable", Color.white, Color.black],
				])
				.action_({ arg butt; 
					addr.sendMsg("/fbo");
					window.close;
					Fbo_GUI.new}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 0 enable", Color.black, Color.white],
					["FBO 0 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo0")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 1 enable", Color.black, Color.white],
					["FBO 1 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo1")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 2 enable", Color.black, Color.white],
					["FBO 2 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo2")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 3 enable", Color.black, Color.white],
					["FBO 3 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo3")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 4 enable", Color.black, Color.white],
					["FBO 4 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo4")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 5 enable", Color.black, Color.white],
					["FBO 5 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo5")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 6 enable", Color.black, Color.white],
					["FBO 6 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo6")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 7 enable", Color.black, Color.white],
					["FBO 7 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo7")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 8 enable", Color.black, Color.white],
					["FBO 8 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo8")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 9 enable", Color.black, Color.white],
					["FBO 9 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo9")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 10 enable", Color.black, Color.white],
					["FBO 10 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo10")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 11 enable", Color.black, Color.white],
					["FBO 11 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo11")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 12 enable", Color.black, Color.white],
					["FBO 12 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo12")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 13 enable", Color.black, Color.white],
					["FBO 13 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo13")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 14 enable", Color.black, Color.white],
					["FBO 14 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo14")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 15 enable", Color.black, Color.white],
					["FBO 15 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo15")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 16 enable", Color.black, Color.white],
					["FBO 16 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo16")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 17 enable", Color.black, Color.white],
					["FBO 17 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo17")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 18 enable", Color.black, Color.white],
					["FBO 18 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo18")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 19 enable", Color.black, Color.white],
					["FBO 19 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo19")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 20 enable", Color.black, Color.white],
					["FBO 20 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo20")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 21 enable", Color.black, Color.white],
					["FBO 21 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo21")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 22 enable", Color.black, Color.white],
					["FBO 22 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo22")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 23 enable", Color.black, Color.white],
					["FBO 23 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo23")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 24 enable", Color.black, Color.white],
					["FBO 24 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo24")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 25 enable", Color.black, Color.white],
					["FBO 25 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo25")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 26 enable", Color.black, Color.white],
					["FBO 26 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo26")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 27 enable", Color.black, Color.white],
					["FBO 27 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo27")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 28 enable", Color.black, Color.white],
					["FBO 28 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo28")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 29 enable", Color.black, Color.white],
					["FBO 29 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo29")}
		);
	
	}
}