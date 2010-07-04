/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Escenas_GUI.new
SendMidi
*/


Escenas_GUI	{
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
		this.escenasGUI;
	}
	
	escenasGUI	{
		window = SCWindow("Escenas");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(990,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 0 Catastrofe", Color.black, Color.white],
					["Escena 0 Catastrofe", Color.white, Color.black],
				])
				.action_({  
					addr.sendMsg("/midi8", 55);	
					addr.sendMsg("/midi88", 0);
					addr.sendMsg("/midi96", 0);
					addr.sendMsg("/midi104", 0);
					addr.sendMsg("/fbo0");
				}
		);
		//back ecrem poco poco fbo7 4sec -(3sec)> fbo6 3sec -(sec)> fbo8 2sec
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 1 Entrada del Oso", Color.black, Color.white],
					["Escena 1 Entrada del Oso", Color.white, Color.black],
				])
				.action_({  					
					(
						{var a8 = 0, r8 = 0, g8 = 0, b8 = 0, afbo7 = 0;
						// initial values
							addr.sendMsg("/background", 0, 0, 0);
							addr.sendMsg("/a8", 10);
						// hacer ecrem el background
						50 do:	{	
							r8 = (r8 + 4.8);									g8 = (g8 + 4.36);									b8 = (b8 + 2.66);		
							addr.sendMsg("/r8", r8);
							addr.sendMsg("/g8", g8);
							addr.sendMsg("/b8", b8);
							0.2.wait;	
							};
						addr.sendMsg("/a8", 0);	
						3.wait;					
						addr.sendMsg("/fbo7");
						3.wait;
						addr.sendMsg("/fbo6");								3.wait;				
						addr.sendMsg("/fbo8");		
						}.fork;										)											}
		);
	}
}