/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Fbo_GUI.new
Escenas.new
Deseos

SendMidi
*/


Final	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var 	balsOso, soloChan, arbols, golondrinasTask, burbujas, sketchCasas, floresCambian, deseo, floresPelean, feedback, casas;
	var arbols1, arbols2, arbols3, luciernagas;
	
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
		this.final;
	}
	
	final	{
		window = SCWindow("Finale");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(660,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		//Luciernagas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Luciernagas", Color.black, Color.white],
					["NO Luciernagas", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				luciernagas.stop;
				},{
				(
					{
						var 	l,t;
						var	theta = 13;
						var	length = 130;
						addr.sendMsg("/a8", 2);						
						luciernagas = Task({
						inf do:	{
							l= RedLSystem("+++F--F--F", ($F: "F--F--2F--GG", $G: "GG"));
							t = RedLTurtle(l, length, theta, 1);
							2 do:	{
								l.next;
							};
							addr.sendMsg("/lsystem", l.asString, t.length, t.theta, t.scale, t.noise, l.generation);
							theta = theta + 1.4;
							0.7.wait;	
							};
							});
							luciernagas.start;
						}.fork;
					 )
					 
					 });
					 
				}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["SkeTch", Color.black, Color.white],
					["No sKeTch", Color.white, Color.black],
				])
				.action_({ arg butt;
					if (butt.value == 0, 
					{
					balsOso.stop;
					addr.sendMsg("/soloChanTiChanFALSE");					
					}, { 					
					(
						{ 	
						addr.sendMsg("/a7", 75);							addr.sendMsg("/r7", 255);
						addr.sendMsg("/g7", 255);
						addr.sendMsg("/b7", 255);							balsOso = Task({
						inf do:	{
						addr.sendMsg("/soloChanTiChanTRUE",2400.rand, 800.rand);
						7.wait;
						};
						});
						balsOso.start;
					}.fork;
					)	
					});										}
		);
		
		
	}
}
