/*
100616 igoumeninja
Aris Bezas

Class to control GUI
*/


Open_GUI	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		

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
	init {
		Sendmidi.start;
		Sound_GUI.new;
		Main_GUI.new;
		UnaCiudadSonada.new;	
		Fbo_GUI.new	
	}

}