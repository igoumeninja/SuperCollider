/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Fbo_GUI.new
Escenas.new
Other.new
Deseos
Final

SendMidi
*/


Other	{
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
		this.other;
	}
	
	other	{
		window = SCWindow("Finale");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(660,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		Button(window, Rect(20,20,300,20))
				.states_([
					["Burbujas", Color.black, Color.white],
					["Burbujas", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						soloChan.stop;									addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						
						addr.sendMsg("/a8", 10);
						burbujas = Task({
						inf do:	{
						addr.sendMsg("/burbujas");
						0.3.rand.wait;
						};
						});
						burbujas.start;									3.wait;
						addr.sendMsg("/a8", 0);
						3.wait;
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", -0.3);						addr.sendMsg("/a8", 10);
						5.wait;
						addr.sendMsg("/a8", 0);
					}.fork;
				)											}
		);
		//	sKeTch at casas 9, 10, 11, 12
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena sKeTch Casas", Color.black, Color.white],
					["Escena sKeTch Casas", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						burbujas.stop;
						addr.sendMsg("/fbo9", 1);
						addr.sendMsg("/fbo10", 1);
						addr.sendMsg("/fbo11", 1);
						addr.sendMsg("/fbo12", 1);
						addr.sendMsg("/viewCasaGrandeFALSE");						addr.sendMsg("/viewCampanarioFALSE");
						addr.sendMsg("/viewCasaAquaFALSE");
						addr.sendMsg("/viewCasaDerechaFALSE");						addr.sendMsg("/viewSketchCasasTRUE");						}.fork;
				)											}
		);
		
		//Deseo
		Button(window, Rect(20,20,300,20))
				.states_([
					["El Deseo", Color.black, Color.white],
				])
				.action_({ 
				(
					{
						addr.sendMsg("/a8", 0);
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", -0.4);
						0.1.wait;
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						0.1.wait;
						addr.sendMsg("/background", 0,0,0);
						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", 0);						deseo = Task({
						120 do:	{
							addr.sendMsg("/deseo");
							0.5.wait;	
							};
						
						});
						deseo.start;				
						}.fork;
					)	
				}
		);
		
		
		// TENGO QUE PARAR LAS BURBUJAS
		
		//	Golondrinas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena Golondrinas", Color.black, Color.white],
					["Escena Golondrinas", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						
						var 	l,t;
						var	theta = 90;
						var	length = 130;
						golondrinasTask = Task({
							inf do:	{
								l= RedLSystem("+++F--F--F", ($F: "F--F--2F--GG", $G: "GG"));
								t = RedLTurtle(l, length, theta, 1);
								2 do:	{
									l.next;
								};
							
						addr.sendMsg("/golondrinas", l.asString, t.length, t.theta, t.scale, t.noise, l.generation);
						theta = theta + 1.4;
						0.7.wait;	
						
						};
						});	
						golondrinasTask.start;					
						}.fork;
																)											}
		);
		//	Para las golondrinas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 9 Para las colondrinas", Color.black, Color.white],
					["Escena 9 Para las colondrinas", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						golondrinasTask.stop;
						}.fork;
																)											}
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

		// haleo en la escena
		Button(window, Rect(20,20,300,20))
				.states_([
					["Haleo", Color.black, Color.white],
					["Para el Haleo", Color.white, Color.black],
				])
				.action_({  
					arg butt;
					if ( butt == 0, 
						{
						addr.sendMsg("/fbo");
						}
						);
				}
		);		

		//La Oso Construllen la casa
		Button(window, Rect(20,20,300,20))
				.states_([
					["El Oso construllen la casa Derecha", Color.black, Color.white],
					["El Oso construllen la casa Derecha", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var afbo21_23=0;	
						// initial values
						// 21, 23
						addr.sendMsg("/afbo21_23", 0);
						addr.sendMsg("/fbo21", 1);
						addr.sendMsg("/fbo23", 1);
						
						addr.sendMsg("/viewVentanaRotaTRUE");
						addr.sendMsg("/viewtejadosCasaDerechaTRUE");						
						
						//addr.sendMsg("/fbo16", 1);
						255 do:	{	
							afbo21_23 = (afbo21_23 + 0.36);
							addr.sendMsg("/afbo21_23", afbo21_23);
							0.1.wait;				
						};

						}.fork;
																)											}
		);
		// Que se pelea
		Button(window, Rect(20,20,300,20))
				.states_([
					["Que se Pelean", Color.black, Color.white],
					["Que se Pelean", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/fbo13",1);
						addr.sendMsg("/fbo14",1);
						floresCambian = Task({
						inf do:	{
						addr.sendMsg("/viewFlores6TRUE");
						addr.sendMsg("/viewFlores21TRUE");
						addr.sendMsg("/viewFlores6TRUE");
						addr.sendMsg("/viewFlores2TRUE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores6FALSE");
						addr.sendMsg("/viewFlores21FALSE");
						addr.sendMsg("/viewFlores6FALSE");
						addr.sendMsg("/viewFlores2FALSE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores6TRUE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores6FALSE");
						0.1.rand.wait;									addr.sendMsg("/viewFlores21TRUE");
						0.5.rand.wait;			
						addr.sendMsg("/viewFlores21FALSE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores6TRUE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores6FALSE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores2TRUE");
						0.5.rand.wait;
						addr.sendMsg("/viewFlores2FALSE");
						};
						});
						floresCambian.start;
						}.fork;
																)											}
		);
		//	Aparece el horno
		Button(window, Rect(20,20,300,20))
				.states_([
					["Aparece el horno", Color.black, Color.white],
					["Aparece el horno", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						arbols.stop;	
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewChimaneaTRUE");
						
						addr.sendMsg("/fbo21", 1);
						}.fork;
																)											}
		);
		//	Almanecer
		Button(window, Rect(20,20,300,20))
				.states_([
					["Almanecer", Color.black, Color.white],
					["Almanecer", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/viewGotasFALSE");
						addr.sendMsg("/fbo24", 0);						
						0.3.wait;
						addr.sendMsg("/a8", 0);
						2.1.wait;
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						5.wait;
						addr.sendMsg("/background", 0,0,0);

						
						}.fork;
																)											}
		);
		
		
	}
}
