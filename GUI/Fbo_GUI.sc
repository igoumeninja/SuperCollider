/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Escenas_GUI
Fbo_GUI.new
Open_GUI.new
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
		window.bounds_(Rect(330,0,330,880));
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
					["All FBO TRUE", Color.black, Color.white],
				])
				.action_({ arg butt; addr.sendMsg("/allFboTrue")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO default TRUE", Color.black, Color.white],
				])
				.action_({ arg butt; addr.sendMsg("/defaultFBOTRUE")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO default FALSE", Color.black, Color.white],
				])
				.action_({ arg butt; addr.sendMsg("/defaultFBOFALSE")}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 0 primero", Color.black, Color.white],
					["FBO 0 primero", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo0", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 1 tejado casa izquierda", Color.black, Color.white],
					["FBO 1 tejado casa izquierda", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo1", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 2 muro casa izquierda", Color.black, Color.white],
					["FBO 2 muro casa izquierda", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo2", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 3 ventana casa izquierda", Color.black, Color.white],
					["FBO 3 ventana casa izquierda", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo3", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 4 objeto casa izquierda", Color.black, Color.white],
					["FBO 4 objeto casa izquierda", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo4", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 5 empty", Color.black, Color.white],
					["FBO 5 empty", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo5", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 6 torre izquierda fonto", Color.black, Color.white],
					["FBO 6 torre izquierda fonto", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo6", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 7 montanas", Color.black, Color.white],
					["FBO 7 montanas", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo7", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 8 fonto izquierda", Color.black, Color.white],
					["FBO 8 fonto izquierda", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo8", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 9 casa grande", Color.black, Color.white],
					["FBO 9 casa grande", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo9", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 10 campanario", Color.black, Color.white],
					["FBO 10 campanario", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo10", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 11 casa aqua", Color.black, Color.white],
					["FBO 11 casa aqua", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo11", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 12 casa derecha", Color.black, Color.white],
					["FBO 12 casa derecha", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo12", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 13 perfil derecha", Color.black, Color.white],
					["FBO 13 perfil derecha", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo13", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 14 cohete", Color.black, Color.white],
					["FBO 14 cohete", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo14", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 15 muro de la casa alta", Color.black, Color.white],
					["FBO 15 muro de la casa alta", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo15", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 16 ventana de la casa alta", Color.black, Color.white],
					["FBO 16 ventana de la casa alta", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo16", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 17 tejado de la casa alta", Color.black, Color.white],
					["FBO 17 tejado de la casa alta", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo17", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 18 muro frontal del bar", Color.black, Color.white],
					["FBO 18 muro frontal del bar", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo18", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 19 muro lateral del bar", Color.black, Color.white],
					["FBO 19 muro lateral del bar", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo19", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 20 objeto derecha", Color.black, Color.white],
					["FBO 20 objeto derecha", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo20", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 21 muro del casita del pilar", Color.black, Color.white],
					["FBO 21 muro del casita del pilar", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo21", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 22 cajita de la casa de pilar", Color.black, Color.white],
					["FBO 22 cajita de la casa de pilar", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo22", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 23 tejados de la casa de pillar", Color.black, Color.white],
					["FBO 23 tejados de la casa de pillar", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo23", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 24 cielo", Color.black, Color.white],
					["FBO 24 cielo", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo24", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 25 mini ciudad", Color.black, Color.white],
					["FBO 25 mini ciudad", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo25", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 26 enable", Color.black, Color.white],
					["FBO 26 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo26", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 27 enable", Color.black, Color.white],
					["FBO 27 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo27", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 28 enable", Color.black, Color.white],
					["FBO 28 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo28", butt.value)}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["FBO 29 enable", Color.black, Color.white],
					["FBO 29 disable", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/fbo29", butt.value)}
		);
	
	}
}