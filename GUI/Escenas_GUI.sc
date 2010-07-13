/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Fbo_GUI.new
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
	var 	balsOso, soloChan, arbols, golondrinasTask, burbujas, sketchCasas;
	
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
		window.bounds_(Rect(0,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		//Chan Ti Chan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Chan Ti Chan se caen", Color.black, Color.white],
					["Chan Ti Chan se caen", Color.white, Color.black],
				])
				.action_({  
				addr.sendMsg("/fbo");				
				addr.sendMsg("/background", 0,0,0);
				addr.sendMsg("/fbo0", 1);
				addr.sendMsg("/viewChanTiChanTRUE");
				}
		);
		//Catastrofe
		Button(window, Rect(20,20,300,20))
				.states_([
					["Video de Catastrofe", Color.black, Color.white],
					["Video de Catastrofe", Color.white, Color.black],
				])
				.action_({  
				addr.sendMsg("/viewChanTiChanFALSE");
				addr.sendMsg("/viewCiudadDestruidaTRUE");
				}
		);
		//Entrada del Oso
		Button(window, Rect(20,20,300,20))
				.states_([
					["Entrada del Oso", Color.black, Color.white],
					["Entrada del Oso", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var a8=0,r8=0,g8=0,b8=0,afbo6=0,afbo7=0,afbo8=0,afbo123=0,afbo15_17=0,afbo21_23=0;	
						// initial values
						addr.sendMsg("/fbo6", 0);
						addr.sendMsg("/fbo7", 0);
						addr.sendMsg("/fbo8", 0);
						addr.sendMsg("/viewMontanasTRUE", 1);
						addr.sendMsg("/viewMontanasBlancasTRUE", 1);
						addr.sendMsg("/viewMontanasRojasTRUE", 1);						addr.sendMsg("/background", 0, 0, 0);
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/afbo6", 0.1);
						addr.sendMsg("/afbo7", 0.1);
						addr.sendMsg("/afbo8", 0.1);						addr.sendMsg("/a8", 0);	
						// afbo7
						addr.sendMsg("/fbo7", 1);							80 do:	{	
							afbo7 = (afbo7 + 0.36);
							addr.sendMsg("/afbo7", afbo7);
							0.1.wait;	
							};					
						3.wait;
						// afbo6
						addr.sendMsg("/fbo6", 1);	
						80 do:	{	
							afbo6 = (afbo6 + 0.36);
							addr.sendMsg("/afbo6", afbo6);
							0.1.wait;	
							};					
						3.wait;				
						// afbo8
						addr.sendMsg("/fbo8", 1);	
						80 do:	{	
							afbo8 = (afbo8 + 0.36);
							addr.sendMsg("/afbo8", afbo8);
							0.1.wait;	
						};

						}.fork;
																)											}
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
						80 do:	{	
							afbo21_23 = (afbo21_23 + 0.36);
							addr.sendMsg("/afbo21_23", afbo21_23);
							0.1.wait;				
						};

						}.fork;
																)											}
		);
		//La Oso Construllen la casa Izquierda
		Button(window, Rect(20,20,300,20))
				.states_([
					["El Oso construllen la casa Izquierda", Color.black, Color.white],
					["El Oso construllen la casa Iquierda", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var afbo123=0, afbo15_17 = 0;	
						// initial values
						// 1,2,3
						addr.sendMsg("/fbo1", 1);	
						addr.sendMsg("/fbo2", 1);	
						80 do:	{	
							afbo123 = (afbo123 + 0.36);
							addr.sendMsg("/afbo123", afbo123);
							0.1.wait;				
						};
						5.wait;
						// 15, 17
						addr.sendMsg("/afbo15_17", 0);
						addr.sendMsg("/fbo15", 1);
						addr.sendMsg("/fbo17", 1);
						80 do:	{	
							afbo15_17 = (afbo15_17 + 0.36);
							addr.sendMsg("/afbo15_17", afbo15_17);
							0.1.wait;				
						};
						5.wait;

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
						addr.sendMsg("/fbo6",1);
						addr.sendMsg("/fbo21",1);
						addr.sendMsg("/fbo13",1);
						addr.sendMsg("/fbo14",1);
						addr.sendMsg("/fbo2",1);
						20 do:	{
						addr.sendMsg("/viewFlores6TRUE");
						0.5.wait;
						addr.sendMsg("/viewFlores6FALSE");						0.1.wait;					
						addr.sendMsg("/viewFlores21TRUE");
						0.5.wait;					
						addr.sendMsg("/viewFlores21FALSE");
						0.1.wait;					
						addr.sendMsg("/viewFlores13TRUE");
						0.5.wait;					
						addr.sendMsg("/viewFlores13FALSE");
						0.1.wait;
						addr.sendMsg("/viewFlores6TRUE");
						0.5.wait;
						addr.sendMsg("/viewFlores6FALSE");
						0.1.wait;
						addr.sendMsg("/viewFlores14TRUE");
						0.5.wait;
						addr.sendMsg("/viewFlores14FALSE");
						0.1.wait;
						addr.sendMsg("/viewFlores2TRUE");
						0.5.wait;
						addr.sendMsg("/viewFlores2FALSE");
						};
						}.fork;
																)											}
		);
		// Los dos hablan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 3 Los dos hablan", Color.black, Color.white],
					["Escena 3 Los dos hablan", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values						
						addr.sendMsg("/fbo4",1);
						addr.sendMsg("/fbo20",1);
						addr.sendMsg("/fbo22",1);
						addr.sendMsg("/viewRaton4TRUE");
						1.wait;					
						addr.sendMsg("/viewRaton20TRUE");
						1.wait;
						addr.sendMsg("/viewRaton22TRUE");
						2.wait;	
						addr.sendMsg("/viewRaton4FALSE");
						addr.sendMsg("/viewRaton20FALSE");
						addr.sendMsg("/viewRaton22FALSE");
						addr.sendMsg("/viewLadrillo4TRUE");
						addr.sendMsg("/viewLadrillo20TRUE");
						addr.sendMsg("/viewLadrillo22TRUE");
						3.wait;
						addr.sendMsg("/viewCiudadDestruidaFALSE");
						addr.sendMsg("/viewCiudadDestruidaConLluviaTRUE");

						}.fork;
																)											}
		);
		// El bals del oso
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 4 El Bals del Oso", Color.black, Color.white],
					["Escena 4 El Bals del Oso", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 	
						// initial values
						addr.sendMsg("/fbo");
						addr.sendMsg("/a8", 0);
						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", 0.4);
						5.wait;
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						2.wait;
						addr.sendMsg("/a8", 0);
						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						
						20 do:	{
						addr.sendMsg("/mariposa");
						0.3.wait;
						};
						
						balsOso = Task({
						inf do:	{
						addr.sendMsg("/mariposa");
						0.1.wait;
						};
						});
						balsOso.start
						
						
						
						//addr.sendMsg("/background", 0, 0, 0);

						}.fork;
																)											}
		);
		//	Solo de Chang Ti Chan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 5 Solo de Chang Ti Chan", Color.black, Color.white],
					["Escena 5 Solo de Chang Ti Chan", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						balsOso.stop;
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", 0.4);
						2.wait;
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);		
						addr.sendMsg("/feedbackSpeedY", 0.8);
						2.wait;
						addr.sendMsg("/feedbackSpeedY", 0.2);
						addr.sendMsg("/a8", 2);
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
		//	Construllen el bar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 6 Construllen el bar", Color.black, Color.white],
					["Escena 6 Construllen el bar", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						soloChan.stop;
						2.wait;
						addr.sendMsg("/soloChanTiChanFALSE");
						
						arbols = Task({
						inf do:	{
						addr.sendMsg("/arbol");
						
						0.1.wait;
						};
						});
						arbols.start;


						}.fork;
																)											}
		);
		//	Aparece el horno
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 7 Aparece el horno", Color.black, Color.white],
					["Escena 7 Aparece el horno", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						//arbols.stop;	
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						
						//addr.sendMsg("/afbo21_23", 0);
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewChimaneaTRUE");
						
						addr.sendMsg("/fbo23", 1);
						//addr.sendMsg("/fbo16", 1);
//						80 do:	{	
//							afbo21_23 = (afbo21_23 + 0.36);
//							addr.sendMsg("/afbo21_23", afbo21_23);
//							0.1.wait;				
//						};
						}.fork;
																)											}
		);
		//	Entra la limpiadora
		Button(window, Rect(20,20,300,20))
				.states_([
					["Escena 8 Entra la limpiadora", Color.black, Color.white],
					["Escena 8 Entra la limpiadora", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						burbujas = Task({
						inf do:	{
						addr.sendMsg("/burbujas");
						0.1.wait;
						};
						});
						burbujas.start;
						}.fork;
																)											}
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
						addr.sendMsg("/fbo9", 1);
						addr.sendMsg("/fbo10", 1);
						addr.sendMsg("/fbo11", 1);
						addr.sendMsg("/fbo12", 1);
						addr.sendMsg("/viewSketchCasasTRUE", 1);						
//						sketchCasas = Task({
//						inf do:	{
//						addr.sendMsg("/soloChanTiChanTRUE",3000.rand, 1000.rand);
//						addr.sendMsg("/r7", 255.rand);
//						addr.sendMsg("/g7", 255.rand);
//						addr.sendMsg("/b7", 255.rand);
								

//						3.rand.wait;
//						};
//						});
//						sketchCasas.start;
			
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
	}
}