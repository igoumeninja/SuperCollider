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


Escenas	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var 	balsOso, soloChan, arbols, golondrinasTask, burbujas, sketchCasas, floresCambian, deseo, floresPelean, feedback, casas;
	var arbols1, arbols2, arbols3;
	var mariposas, casas;
	
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
		this.escenas;
	}
	
	escenas	{
		window = SCWindow("SALVADORE OLE");
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
		//	Aparecer el bosque
		Button(window, Rect(20,20,300,20))
				.states_([
					["Acaba el viento", Color.black, Color.white],
					["Acaba el viento", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/fbo0", 0);	
						addr.sendMsg("/soloChanTiChanFALSE");
						arbols = Task({
						inf do:	{
						addr.sendMsg("/arbol");
						1.wait;
						};
						});	
						arbols.start;			
						addr.sendMsg("/a8", 2);	
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);								addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", -0.6);						addr.sendMsg("/feedbackSpeedX", 0);
						8.wait;
						addr.sendMsg("/feedbackSpeedY", 0);
						}.fork;									)											}
		);
		//	Movimiento en el bosque
		Button(window, Rect(20,20,300,20))
				.states_([
					["Movimiento en el bosque", Color.black, Color.white],
					["Movimiento en el bosque", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						arbols.stop;		
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/feedbackSpeedX", 0.4);
						arbols = Task({
						inf do:	{
						addr.sendMsg("/arbol");
						0.1.wait;
						};
						});
						arbols.start;				
						}.fork;									)											}
		);
		//	Movimiento rapido
		Button(window, Rect(20,20,300,20))
				.states_([
					["Movimiento mas rapido", Color.black, Color.white],
					["Movimiento mas rapido", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						arbols.stop;
						arbols = Task({
						inf do:	{
						addr.sendMsg("/arbol");
						0.05.wait;
						};
						});
						arbols.start;				
						
						addr.sendMsg("/a8", 2);	
						feedback = Task({
						inf do:	{
						//addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", -5 + 10.rand);
						0.5.wait;
						};
						});
						feedback.start;			
						}.fork;									)											}
		);
		// Estrellas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Esrellas", Color.black, Color.white],
					["Esrellas", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/feedbackSpeedY", 0.9);
						addr.sendMsg("/feedbackSpeedX", 2.8);
						feedback.stop;
						arbols.stop;				
						floresPelean = Task({
						inf do:	{
						addr.sendMsg("/flore");
						0.1.rand.wait;
						};
						});
						floresPelean.start;
						3.wait;
						addr.sendMsg("/a8", 0);								}.fork;
					)											}
		);
		// La ventana rota
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ventana Rota", Color.black, Color.white],
					["Ventana Rota", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var afbo21_23=0;	
						// initial values
						addr.sendMsg("/a8", 0);								addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", 0);
						
						// 21, 23
						addr.sendMsg("/afbo21_23", 255);
						addr.sendMsg("/fbo21", 1);
						addr.sendMsg("/fbo23", 1);
						addr.sendMsg("/viewVentanaRotaTRUE");
						}.fork;
																)											}
		);
		
		//	Movimiento en las flores
		Button(window, Rect(20,20,300,20))
				.states_([
					["Movimiento con flores", Color.black, Color.white],
					["Parar el movimiento con flores", Color.white, Color.black],
				])
				.action_({   arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", 0);
						
						floresPelean.stop;	
						feedback.stop;					
						addr.sendMsg("/viewJasminFALSE");
					},{					
					(
						{	
						// initial values
						floresPelean.stop;
						addr.sendMsg("/fbo21", 0);
						addr.sendMsg("/fbo23", 0);
						
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/a8", 2);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 255);
						
						floresPelean.stop;			
						floresPelean = Task({
						inf do:	{
						addr.sendMsg("/flore");
						0.02.wait;
						};
						});
						floresPelean.start;
						addr.sendMsg("/viewJasminTRUE");
						feedback = Task({
						inf do:	{
						addr.sendMsg("/feedbackSpeedY",  2.rand);
						addr.sendMsg("/feedbackSpeedX", -2 + 4.rand);
						0.6.wait;
						};
						});
						feedback.start;			
						}.fork;									)														});											}
		);
		//Mariposas L_System
		Button(window, Rect(20,20,300,20))
				.states_([
					["Mariposas L-System", Color.black, Color.white],
					["No Mariposas", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
					mariposas.stop;	
						addr.sendMsg("/a8", 10);									
					},{
					(
						{
						var 	l,t;
						var	theta = 0;
						var	length = 30;
						addr.sendMsg("/a8", 5);	
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", 0);
						
						floresPelean.stop;	
						feedback.stop;					
						addr.sendMsg("/viewJasminFALSE");
												
						mariposas = Task({
							inf do:	{
								l= RedLSystem("+++F--F--F", ($F: "F--F--2F--GG", $G: "GG"));
								t = RedLTurtle(l, length, theta, 1);
								2 do:	{
									l.next;
								};
							
						addr.sendMsg("/golondrinas", l.asString, t.length, t.theta, t.scale, t.noise, l.generation);
						theta = theta + 1;
						0.2.wait;	
						
						};
						});	
						mariposas.start;					
						}.fork;

				)													
					});
			}
		);
		
		//	Bar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Bar", Color.black, Color.white],
					["No Bar", Color.white, Color.black],
				])
				.action_({ arg butt;  								 		
						if( butt.value == 0,{
						addr.sendMsg("/fbo18", 0);						addr.sendMsg("/viewBarFALSE");
						addr.sendMsg("/a8", 10);							
						},{
						addr.sendMsg("/fbo18", 1);							addr.sendMsg("/viewBarTRUE");						});
							
			}
		);
		//	Ormigas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ormigas", Color.black, Color.white],
					["No Ormigas", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/viewHormigasFALSE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo3", 0);
					},{
						addr.sendMsg("/viewHormigasTRUE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo3", 1);						});
							
			}
		);
		
		//	Ojo
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ojo", Color.black, Color.white],
					["No Ojo", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/viewOjoFALSE");						addr.sendMsg("/a8", 10);	
						addr.sendMsg("/fbo5", 0);						},{
						addr.sendMsg("/viewOjoTRUE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo5", 1);						});
							
			}
		);
		//	Primer Amor
		Button(window, Rect(20,20,300,20))
				.states_([
					["Lluvia", Color.black, Color.white],
					["Lluvia", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/background", 0,0,0);						
						addr.sendMsg("/fbo24", 1);
						burbujas.stop;			
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedX", 0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/viewGotasTRUE");
						
						addr.sendMsg("/a8", 0);
						}.fork;
																)											}
		);
		//	Lluvia se cae
		Button(window, Rect(20,20,300,20))
				.states_([
					["Lluvia se cae", Color.black, Color.white],
					["Lluvia se cae", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/feedbackSpeedY", 0.8);
						}.fork;
																)											}
		);
		//	Pero
		Button(window, Rect(20,20,300,20))
				.states_([
					["Pero", Color.black, Color.white],
					["No Pero", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/viewPeroFALSE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo20", 0);
					},{
						addr.sendMsg("/viewPeroTRUE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo20", 1);						});
							
			}
		);
		//	Lluvia se acaba
		Button(window, Rect(20,20,300,20))
				.states_([
					["Lluvia se acaba", Color.black, Color.white],
					["Lluvia se acaba", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/fbo24", 0);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/feedbackSpeedY", 0.8);
						5.wait;											addr.sendMsg("/a8", 10);
						}.fork;
																)											}
		);
		//	Casa Construction
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Construction", Color.black, Color.white],
					["No Casa Construction", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo4", 0);	
						addr.sendMsg("/viewBarNuevoFALSE");					},{
						addr.sendMsg("/fbo4", 1);	
						addr.sendMsg("/viewBarNuevoTRUE");					});
							
			}
		);
		//	Casa Grande
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Grande", Color.black, Color.white],
					["No Casa Grande", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo9", 0);
						addr.sendMsg("/viewCasaGrandeFALSE");					},{
						addr.sendMsg("/fbo9", 1);
						addr.sendMsg("/viewCasaGrandeTRUE");					});
							
			}
		);
		//	Casa Campanario
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Campanario", Color.black, Color.white],
					["No Casa Campanario", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo10", 0);							addr.sendMsg("/viewCampanarioFALSE");
					},{
						addr.sendMsg("/fbo10", 1);							addr.sendMsg("/viewCampanarioTRUE");
					});
							
			}
		);
		//	Casa Aqua
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Aqua", Color.black, Color.white],
					["No Casa Aqua", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo11", 0);							addr.sendMsg("/viewCasaAquaFALSE");
					},{
						addr.sendMsg("/fbo11", 1);							addr.sendMsg("/viewCasaAquaTRUE");
					});
							
			}
		);
		//	Casa Derecha
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Derecha", Color.black, Color.white],
					["No Casa Derecha", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo12", 0);							addr.sendMsg("/viewCasaDerechaFALSE");
					},{
						addr.sendMsg("/fbo12", 1);							addr.sendMsg("/viewCasaDerechaTRUE");
					});
							
			}
		);
		//	Casa Pillar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Pillar", Color.black, Color.white],
					["No Casa Pillar", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/afbo21_23", 255);
						addr.sendMsg("/fbo21", 0);
						addr.sendMsg("/fbo23", 0);
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewtejadosCasaDerechaFALSE");
						addr.sendMsg("/viewCasaDerechaNuevaFALSE");					},{
						addr.sendMsg("/afbo21_23", 255);
						addr.sendMsg("/fbo21", 1);
						addr.sendMsg("/fbo23", 1);
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewtejadosCasaDerechaFALSE");
						addr.sendMsg("/viewCasaDerechaNuevaTRUE");					});
							
			}
		);
		//	Casa Izquierda
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Izquierda", Color.black, Color.white],
					["No Casa Izquierda", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);	
						addr.sendMsg("/viewMuroCasaIzquierdaFALSE");
						addr.sendMsg("/viewTejadoCasaIzquierdaFALSE");					},{
						addr.sendMsg("/fbo1", 1);	
						addr.sendMsg("/fbo2", 1);	
						addr.sendMsg("/viewMuroCasaIzquierdaTRUE");
						addr.sendMsg("/viewTejadoCasaIzquierdaTRUE");					});
							
			}
		);
		//	Casa Xbar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Xbar", Color.black, Color.white],
					["No Casa Xbar", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/fbo19", 0);	
						addr.sendMsg("/viewCasaXBarFALSE");					},{
						addr.sendMsg("/fbo19", 1);	
						addr.sendMsg("/viewCasaXBarTRUE");					});
							
			}
		);
		//	Casas Bailan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Bailen", Color.black, Color.white],
					["Casa Bailen", Color.black, Color.white],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/a8", 10);					
						addr.sendMsg("/fbo4", 0);	
						addr.sendMsg("/fbo9", 0);	
						addr.sendMsg("/fbo10", 0);	
						addr.sendMsg("/fbo11", 0);	
						addr.sendMsg("/fbo12", 0);	
						addr.sendMsg("/fbo21", 0);	
						addr.sendMsg("/fbo23", 0);	
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);							addr.sendMsg("/timeLine",0);
						casas.stop;
						
					},{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", -0.6);						addr.sendMsg("/feedbackSpeedX", 0);

						(
						casas = Task(
							{
								arg f , r = 10, x = 0, y = r, z, o;
								z = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];		
								inf.do(
								{
								switch (z.choose,
									1,   { addr.sendMsg("/fbo4", 1) },
									2, 	{ addr.sendMsg("/fbo9", 1) },
									3, 	{ addr.sendMsg("/fbo10", 1) },
									4, 	{ addr.sendMsg("/fbo11", 1) },
									5, 	{ addr.sendMsg("/fbo12", 1) },
									6, 	{ addr.sendMsg("/fbo21", 1) },
									7, 	{ addr.sendMsg("/fbo23", 1) },
									8, 	{ addr.sendMsg("/fbo1", 1) },																			9, 	{ addr.sendMsg("/fbo2", 1) },																			10, 	{ addr.sendMsg("/fbo4", 1) },
									11,   { addr.sendMsg("/fbo4", 0) },
									12, 	{ addr.sendMsg("/fbo9", 0) },
									13, 	{ addr.sendMsg("/fbo10", 0) },
									14, 	{ addr.sendMsg("/fbo11", 0) },
									15, 	{ addr.sendMsg("/fbo12", 0) },
									16, 	{ addr.sendMsg("/fbo21", 0) },
									17, 	{ addr.sendMsg("/fbo23", 0) },
									18, 	{ addr.sendMsg("/fbo1", 0) },																			19, 	{ addr.sendMsg("/fbo2", 0) },																			20, {addr.sendMsg("/viewCasaXBarFALSE") },
									21, {addr.sendMsg("/viewMuroCasaIzquierdaTRUE");
									addr.sendMsg("/viewTejadoCasaIzquierdaTRUE"); },
									22, {addr.sendMsg("/viewMuroCasaIzquierdaFALSE");
									addr.sendMsg("/viewTejadoCasaIzquierdaFALSE"); },
									23, {addr.sendMsg("/viewCasaDerechaTRUE")},
									24, {addr.sendMsg("/viewCasaDerechaFALSE")},								
									25, {addr.sendMsg("/viewCasaAquaFALSE")},
									26, {addr.sendMsg("/viewCasaAquaTRUE")},
									27, {addr.sendMsg("/viewCampanarioFALSE") },
									28, {addr.sendMsg("/viewCampanarioTRUE")},
									29, {addr.sendMsg("/viewCasaGrandeFALSE")},																	30, {addr.sendMsg("/viewCasaGrandeTRUE")}																		
								);			
								0.01.wait; 
								}
								)
							}
							);
						)
						
					});
					casas.start; 
							
			}
		);
		//	Casas Bailan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casas NO Bailen", Color.black, Color.white],
					["Casas NO Bailen", Color.black, Color.white],
				])
				.action_({ arg butt;  										addr.sendMsg("/a8", 10);					
						addr.sendMsg("/fbo4", 0);	
						addr.sendMsg("/fbo9", 0);	
						addr.sendMsg("/fbo10", 0);	
						addr.sendMsg("/fbo11", 0);	
						addr.sendMsg("/fbo12", 0);	
						addr.sendMsg("/fbo21", 0);	
						addr.sendMsg("/fbo23", 0);	
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);							addr.sendMsg("/timeLine",0);
						casas.stop;
			}
		);
	}
}
