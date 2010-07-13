/*
100612 igoumeninja
Aris Bezas

Open_GUI.new
UnaCiudadSonada.new

This class create the Graphical User Interface (GUI) for arbol interaction.
*/


UnaCiudadSonada	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var  effectoLinea;
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
		this.unaCiudadSonadaGUI;	// call arbolGUI		
	}
	
	unaCiudadSonadaGUI	{
		var soloChan;
		window = SCWindow("Una Ciudad Sonada GUI");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(660,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
				Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 5 Solo de Chang Ti Chan", Color.black, Color.white],
					["Escena 5 Solo de Chang Ti Chan", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						2.wait;
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);		
						2.wait;
						addr.sendMsg("/a8", 0);
						soloChan = Task({
						inf do:	{
						addr.sendMsg("/soloChanTiChanTRUE",3000.rand, 1000.rand);
						addr.sendMsg("/r7", 255.rand);
						addr.sendMsg("/g7", 255.rand);
						addr.sendMsg("/b7", 255.rand);
								

						3.rand.wait;
						};
						});
						soloChan.start;

						}.fork;
																)											}
		);

		Button(window, Rect(20,20,300,20))
				.states_([
					["El Deseo", Color.black, Color.white],
				])
				.action_({ 
				(
					{
						120 do:	{
							addr.sendMsg("/deseo");
							0.5.wait;	
							};
						}.fork;
					)	
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Linea Horizontal", Color.black, Color.white],
				])
				.action_({ 
				(
					addr.sendMsg("/startEffect");
					{
						1200 do:	{
							addr.sendMsg("/drawLine");
							0.05.wait;	
							};
						}.fork;
					)	
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Linea Vertical", Color.black, Color.white],
				])
				.action_({ 
				(
					addr.sendMsg("/startEffect");
					{
						3600 do:	{
							addr.sendMsg("/drawLineVertical");
							0.05.wait;	
							};
						}.fork;
					)	
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["luciernagas", Color.black, Color.white],
				])
				.action_({
				(
					{
						var 	l,t;
						var	theta = 13;
						var	length = 130;
						
						500 do:	{
							l= RedLSystem("+++F--F--F", ($F: "F--F--2F--GG", $G: "GG"));
							t = RedLTurtle(l, length, theta, 1);
							2 do:	{
								l.next;
							};
							addr.sendMsg("/lsystem", l.asString, t.length, t.theta, t.scale, t.noise, l.generation);
							theta = theta + 1.4;
							0.7.wait;	
							};
						}.fork;
					 )
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["arbolLsystem", Color.black, Color.white],
				])
				.action_({
				(
					{
						var 	l,t;
						var	theta = 13;
						var	length = 130;
						
						500 do:	{
							l= RedLSystem("+++F--F--F", ($F: "F--F--2F--GG", $G: "GG"));
							t = RedLTurtle(l, length, theta, 1);
							2 do:	{
								l.next;
							};
							addr.sendMsg("/arbolLsystem", l.asString, t.length, t.theta, t.scale, t.noise, l.generation);
							theta = theta + 1.4;
							0.7.wait;	
							};
						}.fork;
					 )
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["view arbol", Color.black, Color.white],
					["stop arbol", Color.white, Color.black],
				])
				.action_({ arg butt; addr.sendMsg("/arbolView", butt.value)}
		);
		//heigth
		arbolHeight=EZSlider(window, 300@20, "height ",  ControlSpec(10, 200, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolHeight.setColors(Color.grey,Color.white);
		arbolHeight.action_({addr.sendMsg("/arbolHeigth", arbolHeight.value)});
		//leaves
		arbolLeaves=EZSlider(window, 300@20, "leavesLenght ",  ControlSpec(10, 200, \lin, 0.05, 0.01), numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolLeaves.setColors(Color.grey,Color.white);
		arbolLeaves.action_({addr.sendMsg("/arbolLeaves", arbolLeaves.value)});
		//wind
		arbolWind=EZSlider(window, 300@20, "wind ", numberWidth:50,layout:\horz, initVal:0.0, labelWidth: 100);
		arbolWind.setColors(Color.grey,Color.white);
		arbolWind.action_({addr.sendMsg("/arbolWind", arbolWind.value)});
		
	}
}