/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Fbo_GUI.new
Escenas.new

SendMidi
*/


Escenas	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var 	balsOso, soloChan, arbols, golondrinasTask, burbujas, sketchCasas, floresCambian, deseo, floresPelean, feedback;
	var arbols1, arbols2, arbols3;
	
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
						5.wait;
//						addr.sendMsg("/viewVentanaRotaFALSE");						
						}.fork;
																)											}
		);
		//	Movimiento en las flores
		Button(window, Rect(20,20,300,20))
				.states_([
					["Movimiento con flores(baile del oso)", Color.black, Color.white],
					["Movimiento con flores (baile del oso)", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
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
						}.fork;									)											}
		);
		//	Movimiento en las flores
		Button(window, Rect(20,20,300,20))
				.states_([
					["Parar el movimiento con flores", Color.black, Color.white],
					["Parar el movimiento con flores", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/r8", 0);
						addr.sendMsg("/g8", 0);
						addr.sendMsg("/b8", 0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/feedbackSpeedX", 0);
						
						floresPelean.stop;	
						feedback.stop;					
						addr.sendMsg("/viewJasminFALSE");
						}.fork;									)											}
		);
		//	Bar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Bar", Color.black, Color.white],
					["Bar", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/fbo18", 1);						
						addr.sendMsg("/viewBarTRUE")						
						}.fork;									)											}
		);
		//	No Bar
		Button(window, Rect(20,20,300,20))
				.states_([
					["No Bar", Color.black, Color.white],
					["No Bar", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/viewBarFALSE");						addr.sendMsg("/a8", 10);							addr.sendMsg("/fbo18", 0);							}.fork;									)											}
		);
		//	Ojo
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ojo", Color.black, Color.white],
					["Ojo", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/viewOjoTRUE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo5", 1);							}.fork;									)											}
		);
		//	Ojo
		Button(window, Rect(20,20,300,20))
				.states_([
					[" No Ojo", Color.black, Color.white],
					[" No Ojo", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/viewOjoFALSE");						addr.sendMsg("/a8", 10);	
						addr.sendMsg("/fbo5", 0);							}.fork;									)											}
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
					["Pero", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						//addr.sendMsg("/fbo");	
						addr.sendMsg("/viewPeroTRUE");						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo20", 1);							}.fork;									)											}
		);
		//	No Pero
		Button(window, Rect(20,20,300,20))
				.states_([
					["No Pero", Color.black, Color.white],
					["No Pero", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						addr.sendMsg("/viewPeroFALSE");						addr.sendMsg("/a8", 10);	
						addr.sendMsg("/fbo20", 0);
						2.wait;
	addr.sendMsg("/a8", 10);							
													}.fork;									)											}
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
		
		
		//	Casas
		//Casa Construction
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Construcion", Color.black, Color.white],
					["Casa Construcion", Color.white, Color.black],
				])
				.action_({  					
					(
						{
						addr.sendMsg("/viewBarFALSE");						addr.sendMsg("/a8", 10);							addr.sendMsg("/fbo18", 0);							addr.sendMsg("/fbo4", 1);	
						addr.sendMsg("/viewBarNuevoTRUE");						}.fork;
																)											}
		);
		
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Grande", Color.black, Color.white],
					["Casa Grande", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/fbo9", 1);
						addr.sendMsg("/viewCasaGrandeTRUE");						}.fork;
																)											}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Campanario", Color.black, Color.white],
					["Campanario", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/fbo10", 1);							addr.sendMsg("/viewCampanarioTRUE");
						}.fork;
																)											}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Aqua", Color.black, Color.white],
					["Casa Aqua", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/fbo11", 1);							addr.sendMsg("/viewCasaAquaTRUE");

						}.fork;
																)											}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Derecha", Color.black, Color.white],
					["Casa Derecha", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 
						// initial values
						addr.sendMsg("/fbo12", 1);							addr.sendMsg("/viewCasaDerechaTRUE");
						}.fork;
																)											}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Pillar", Color.black, Color.white],
					["Casa Pillar", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var afbo21_23=0;	
						// initial values
						// 21, 23
						addr.sendMsg("/afbo21_23", 255);
						addr.sendMsg("/fbo21", 1);
						addr.sendMsg("/fbo23", 1);
						
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewtejadosCasaDerechaFALSE");
						addr.sendMsg("/viewCasaDerechaNuevaTRUE");												

						}.fork;
																)											}
		);
		
		//Casa Izquierda
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Izquierda", Color.black, Color.white],
					["Casa Iquierda", Color.white, Color.black],
				])
				.action_({  					
					(
						{	var afbo123=0, afbo15_17 = 0;	
						// initial values
						// 1,2,3											addr.sendMsg("/afbo123", 255);
						addr.sendMsg("/fbo1", 1);	
						addr.sendMsg("/fbo2", 1);	
						addr.sendMsg("/viewMuroCasaIzquierdaTRUE");
						addr.sendMsg("/viewTejadoCasaIzquierdaTRUE");						}.fork;
																)											}
		);
		//Casa Xbar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Xbar", Color.black, Color.white],
					["Casa Xbar", Color.white, Color.black],
				])
				.action_({  					
					(
						{
						// initial values
						// 1,2,3	
																addr.sendMsg("/fbo19", 1);	
						addr.sendMsg("/viewCasaXBarTRUE");						}.fork;
																)											}
		);
		//Casas Bailan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casa Bailan", Color.black, Color.white],
					["Casa Bailan", Color.white, Color.black],
				])
				.action_({  					
					(
						{
						// initial values
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/fbo9", 0);	
						addr.sendMsg("/fbo10", 0);	
						addr.sendMsg("/fbo11", 0);	
						addr.sendMsg("/fbo12", 0);							addr.sendMsg("/fbo13", 0);	
						addr.sendMsg("/fbo21", 0);
						addr.sendMsg("/fbo23", 0);
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);
						feedback.start;									}.fork;
				)											}
		);
		//Deseo 
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo", Color.black, Color.white],
					["Deseo", Color.white, Color.black],
				])
				.action_({  					
					(
						{
							addr.sendMsg("/deseoGrande");
							deseo = Task({
							120 do:	{
								addr.sendMsg("/deseo");
								0.5.wait;	
								};
							
							});
							deseo.start;									}.fork;
				)											}
		);

//--------		------------------------------------------
		// El bals del oso
		Button(window, Rect(20,20,300,20))
				.states_([
					["El Bals del Oso", Color.black, Color.white],
					["El Bals del Oso", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 	
						// initial values
						feedback.stop;									floresPelean.stop;		
						addr.sendMsg("/viewJasminFALSE");						addr.sendMsg("/fbo");
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						addr.sendMsg("/a7", 125);							addr.sendMsg("/r7", 255);
						addr.sendMsg("/g7", 255);
						addr.sendMsg("/b7", 255);							balsOso = Task({
						inf do:	{
						addr.sendMsg("/soloChanTiChanTRUE",2400.rand, 800.rand);
						1.wait;
						};
						});
						balsOso.start;
						3.wait;
						addr.sendMsg("/a8", 0);							}.fork;
					)											}
		);
		// Cambiar coloir y effecto
		Button(window, Rect(20,20,300,20))
				.states_([
					["Rojo con effecto", Color.black, Color.white],
					["Rojo con effecto", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 	
						// initial values
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 0.2);
						addr.sendMsg("/g7", 0);								addr.sendMsg("/b7", 0);								}.fork;
					)											}
		);
		// Cambiar coloir y effecto
		Button(window, Rect(20,20,300,20))
				.states_([
					["Verde con effecto", Color.black, Color.white],
					["Verde con effecto", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 	
						// initial values
						addr.sendMsg("/r7", 0);
						addr.sendMsg("/g7", 255);
						addr.sendMsg("/b7", 0);							}.fork;
					)											}
		);
		// Cambiar color y effecto
		Button(window, Rect(20,20,300,20))
				.states_([
					["Amarillo con effecto", Color.black, Color.white],
					["Amarillo con effecto", Color.white, Color.black],
				])
				.action_({  					
					(
						{ 	
						// initial values
						addr.sendMsg("/r7", 255);
						addr.sendMsg("/g7", 255);
						addr.sendMsg("/b7", 0);							}.fork;
					)											}
		);
		//	Solo de Chang Ti Chan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Solo de Chang Ti Chan", Color.black, Color.white],
					["Solo de Chang Ti Chan", Color.white, Color.black],
				])
				.action_({  					
					(
						{	
						// initial values
						balsOso.stop;
						addr.sendMsg("/background", 0,0,0);						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);
						soloChan = Task({
						inf do:	{
						addr.sendMsg("/mariposa");
						0.5.rand.wait;
						};
						});
						soloChan.start;
						}.fork;										)											}
		);
		//	Entra la limpiadora
		Button(window, Rect(20,20,300,20))
				.states_([
					["Entra la limpiadora", Color.black, Color.white],
					["Entra la limpiadora", Color.white, Color.black],
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

		
//------------------------------------------------------------------------
//------------------------------------------------------------------------
//------------------------------------------------------------------------		
		
		
		
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
