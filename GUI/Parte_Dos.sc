/*
100702 igoumeninja
Aris Bezas
*/


Parte_Dos	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var deseo1, deseo2, deseo3, deseo4, deseo5, balsOso, luciernagas, ciudades, lineaAmarilla, casas;
	
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
		this.parte_Dos;
	}
	
	parte_Dos	{
		window = SCWindow("PART DOS");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(330,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
		//	Pero Mear Casa Construcion
		Button(window, Rect(20,20,300,20))
				.states_([
					["Pero Mear Casa Construcion", Color.black, Color.white],
					["Pero Mear Casa Construcion", Color.black, Color.white],
				])
				.action_({ arg butt;  				
					addr.sendMsg("/a8", 0);
					addr.sendMsg("/feedbackView",1);
					addr.sendMsg("/timeLine",1);
					addr.sendMsg("/feedbackSpeedY", 1.6);					addr.sendMsg("/feedbackSpeedX", 0);					addr.sendMsg("/fbo4", 0);	
					addr.sendMsg("/viewBarNuevoFALSE");			}
		);
		//	Pero Mear Casa Pillar
		Button(window, Rect(20,20,300,20))
				.states_([
					["Pero Mear Casa Pillar", Color.black, Color.white],
					["Pero Mear Casa Pillar", Color.black, Color.white],
				])
				.action_({ arg butt;  				
					addr.sendMsg("/a8", 0);
					addr.sendMsg("/feedbackView",1);
					addr.sendMsg("/timeLine",1);
					addr.sendMsg("/feedbackSpeedY", 1.2);					addr.sendMsg("/feedbackSpeedX", 0);						addr.sendMsg("/fbo21", 0);
						addr.sendMsg("/fbo23", 0);
						addr.sendMsg("/viewVentanaRotaFALSE");
						addr.sendMsg("/viewtejadosCasaDerechaFALSE");
						addr.sendMsg("/viewCasaDerechaNuevaFALSE");			}
		);
		//	Pero Mear Casa Campanario
		Button(window, Rect(20,20,300,20))
				.states_([
					["Pero Mear Casa Campanario", Color.black, Color.white],
					["Pero Mear Casa Campanario", Color.black, Color.white],
				])
				.action_({ arg butt;  				
					addr.sendMsg("/a8", 0);
					addr.sendMsg("/feedbackView",1);
					addr.sendMsg("/timeLine",1);
					addr.sendMsg("/feedbackSpeedY", 1.2);					addr.sendMsg("/feedbackSpeedX", 0);						addr.sendMsg("/fbo10", 0);							addr.sendMsg("/viewCampanarioFALSE");
			}
		);
		//	Casas Meadas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Casas mear", Color.black, Color.white],
					["No Casas mear", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/a8", 10);	
						addr.sendMsg("/feedbackView",0);
						addr.sendMsg("/timeLine",0);
						addr.sendMsg("/feedbackSpeedY", 0);						addr.sendMsg("/feedbackSpeedX", 0);						addr.sendMsg("/fbo4", 1);	
						addr.sendMsg("/fbo9", 1);	
						addr.sendMsg("/fbo10", 1);	
						addr.sendMsg("/fbo11", 1);	
						addr.sendMsg("/fbo12", 1);	
						addr.sendMsg("/fbo21", 1);	
						addr.sendMsg("/fbo19", 1);							addr.sendMsg("/fbo23", 1);	
						addr.sendMsg("/fbo1", 1);	
						addr.sendMsg("/fbo2", 1);						},{
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", 0.6);						addr.sendMsg("/feedbackSpeedX", 0);						addr.sendMsg("/fbo4", 0);	
						addr.sendMsg("/fbo9", 0);	
						addr.sendMsg("/fbo10", 0);	
						addr.sendMsg("/fbo11", 0);	
						addr.sendMsg("/fbo12", 0);	
						addr.sendMsg("/fbo21", 0);	
						addr.sendMsg("/fbo19", 0);							addr.sendMsg("/fbo23", 0);	
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);						});
							
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
						addr.sendMsg("/a8", 0);
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedY", 1.3);						addr.sendMsg("/feedbackSpeedX", 0);

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
								0.02.wait; 
								}
								)
							}
							);
						)
						
					});
					casas.start; 
							
			}
		);
		//	Casas NO Bailan
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
						addr.sendMsg("/fbo19", 0);							addr.sendMsg("/fbo23", 0);	
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);							addr.sendMsg("/timeLine",0);
						casas.stop;
			}
		);
		//	Todas las Casas
		Button(window, Rect(20,20,300,20))
				.states_([
					["Todas Las Casas", Color.black, Color.white],
					["Desaparecen Las Casas", Color.white, Color.black],
				])
				.action_({ arg butt;  			
					if(butt.value == 0, 
					{
						addr.sendMsg("/a8", 10);					
						addr.sendMsg("/fbo4", 0);	
						addr.sendMsg("/fbo9", 0);	
						addr.sendMsg("/fbo10", 0);	
						addr.sendMsg("/fbo11", 0);	
						addr.sendMsg("/fbo12", 0);	
						addr.sendMsg("/fbo21", 0);	
						addr.sendMsg("/fbo19", 0);							addr.sendMsg("/fbo23", 0);	
						addr.sendMsg("/fbo1", 0);	
						addr.sendMsg("/fbo2", 0);							addr.sendMsg("/timeLine",0);
					
					},
					{
					
						addr.sendMsg("/a8", 10);
						addr.sendMsg("/fbo4", 1);	
						addr.sendMsg("/fbo9", 1);	
						addr.sendMsg("/fbo10", 1);	
						addr.sendMsg("/fbo11", 1);	
						addr.sendMsg("/fbo12", 1);	
						addr.sendMsg("/fbo21", 1);	
						addr.sendMsg("/fbo19", 1);							addr.sendMsg("/fbo23", 1);	
						addr.sendMsg("/fbo1", 1);	
						addr.sendMsg("/fbo2", 1);							addr.sendMsg("/timeLine",0);
						});
			}
		);
		//	Tribunal
		Button(window, Rect(20,20,300,20))
				.states_([
					["Tribunal", Color.black, Color.white],
					["NO Tribunal", Color.white, Color.black],
				])
				.action_({ arg butt;  			
					if(butt.value == 0, 
					{
						addr.sendMsg("/a8", 10);							addr.sendMsg("/fbo7", 0);	
						addr.sendMsg("/viewTribunalFALSE");					},
					{
						addr.sendMsg("/a8", 10);							addr.sendMsg("/fbo7", 1);	
						addr.sendMsg("/viewTribunalTRUE");						});
			}
		);
		
		//view Indio
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Indio", Color.black, Color.white],
					["NO view Indio", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);
				addr.sendMsg("/a8", 0);								addr.sendMsg("/viewIndioTRUE");						 });
					 
				}
		);
		//view Azucar
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Azucar", Color.black, Color.white],
					["NO view Azucar", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);
				addr.sendMsg("/a8", 0);								addr.sendMsg("/viewAzucarTRUE");						 });
					 
				}
		);
		//view Columna
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Columna", Color.black, Color.white],
					["NO view Columna", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);
				addr.sendMsg("/a8", 0);								addr.sendMsg("/viewColumnaTRUE");						 });
					 
				}
		);
		//view Paris
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Paris", Color.black, Color.white],
					["NO view Paris", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);					addr.sendMsg("/a8", 0);								addr.sendMsg("/viewParisTRUE");						 });
					 
				}
		);
		//view Trullo
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Trullo", Color.black, Color.white],
					["NO view Trullo", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);					addr.sendMsg("/a8", 0);								addr.sendMsg("/viewTrulloTRUE");						 });
					 
				}
		);
		//view Valencia
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Valencia", Color.black, Color.white],
					["NO view Valencia", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);					addr.sendMsg("/a8", 0);								addr.sendMsg("/viewValenciaTRUE");						 });
					 
				}
		);
		//view Tokyo
		Button(window, Rect(20,20,300,20))
				.states_([
					["view Tokyo", Color.black, Color.white],
					["NO view Tokyo", Color.white, Color.black],					
				])
				.action_({arg butt;
				if( butt.value == 0, 
				{
				addr.sendMsg("/a8", 10);		
				},{
				addr.sendMsg("/background", 0,0,0);					addr.sendMsg("/a8", 0);								addr.sendMsg("/viewTokyoTRUE");							 });
				}
		);
		//	Cuidades Bailan
		Button(window, Rect(20,20,300,20))
				.states_([
					["Cuidades Bailen", Color.black, Color.white],
					["Cuidades Bailen", Color.black, Color.white],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						ciudades.stop;
						
					},{
					addr.sendMsg("/a8", 0);		
						(
						ciudades = Task(
							{
								arg f , r = 10, x = 0, y = r, z, o;
								z = [1,2,3,4,5,6,7];		
								inf.do(
								{
								switch (z.choose,
									1,   { addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewColumnaTRUE") },
									2, 	{ addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewParisTRUE") },
									3, 	{ addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewTokyoTRUE") },
									4, 	{ addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewAzucarTRUE") },
									5, 	{ addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewValenciaTRUE") },
									6, 	{ addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewTrulloTRUE") },
									7, {addr.sendMsg("/background", 0,0,0);
									addr.sendMsg("/viewCasaGrandeTRUE")});			
								0.1.wait; 
								}
								)
							}
							);
						)
						
					});
					ciudades.start; 
							
			}
		);
		//	Cuidades Bailan
		Button(window, Rect(20,20,300,20))
				.states_([
					["STOP Cuidades Bailen", Color.black, Color.white],
					["STOP Cuidades Bailen", Color.black, Color.white],
				])
				.action_({ arg butt; 
					addr.sendMsg("/a8", 5);						 		ciudades.stop; 
				}
		);
		
		//	Deseo1
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo 1", Color.black, Color.white],
					["XXX Deseo 1", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 1.5);
						addr.sendMsg("/feedbackSpeedY", 1.5);
					},{
						addr.sendMsg("/timeLine",0);						addr.sendMsg("/a8", 10);	
						addr.sendMsg("/deseo1");	
					
					});
			}
		);
		//	Deseo2
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo 2", Color.black, Color.white],
					["XXX Deseo 2", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 1.5);
						addr.sendMsg("/feedbackSpeedY", 1.5);
					},{
						addr.sendMsg("/timeLine",0);						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/deseo2");	
					
					});
			}
		);
		//	Deseo3
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo 3", Color.black, Color.white],
					["XXX Deseo 3", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 1.5);
						addr.sendMsg("/feedbackSpeedY", 1.5);
					},{
						addr.sendMsg("/timeLine",0);						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/deseo3");	
					
					});
			}
		);
		//	Deseo4
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo 4", Color.black, Color.white],
					["XXX Deseo 4", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 1.5);
						addr.sendMsg("/feedbackSpeedY", 1.5);
					},{
						addr.sendMsg("/timeLine",0);						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/deseo4");	
					
					});
			}
		);
		//	Deseo5
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo 5", Color.black, Color.white],
					["XXX Deseo 5", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
						addr.sendMsg("/feedbackView",1);
						addr.sendMsg("/timeLine",1);
						addr.sendMsg("/feedbackSpeedX", 1.5);
						addr.sendMsg("/feedbackSpeedY", 1.5);
					},{
						addr.sendMsg("/timeLine",0);						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/deseo5");	
					
					});
			}
		);
		//	Deseo5
		Button(window, Rect(20,20,300,20))
				.states_([
					["Deseo Jaleo", Color.black, Color.white],
					["XXX Deseo JALEO", Color.white, Color.black],
				])
				.action_({ arg butt;  								 	if( butt.value == 0,{
							addr.sendMsg("/a8", 10);
							deseo1.stop;			
							deseo2.stop;			
							deseo3.stop;			
							deseo4.stop;			
							deseo5.stop;			
					},{
					(
						{
						addr.sendMsg("/timeLine",0);						
							deseo1 = Task({
							inf do:	{
								addr.sendMsg("/deseo1mucho");
								0.5.wait;	
								};
							});
							deseo2 = Task({
							inf do:	{
								addr.sendMsg("/deseo2mucho");
								0.5.wait;	
								};
							});
							deseo3 = Task({
							inf do:	{
								addr.sendMsg("/deseo3mucho");
								0.5.wait;	
								};
							});
							deseo4 = Task({
							inf do:	{
								addr.sendMsg("/deseo4mucho");
								0.5.wait;	
								};
							});
							deseo5 = Task({
							inf do:	{
								addr.sendMsg("/deseo5mucho");
								0.5.wait;	
								};
							});
							deseo1.start;			
							deseo2.start;			
							deseo3.start;			
							deseo4.start;			
							deseo5.start;								}.fork;

				)													
					});
			}
		);
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
						addr.sendMsg("/a8", 0);						
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
						addr.sendMsg("/a8", 0);	
						addr.sendMsg("/a7", 75);							addr.sendMsg("/r7", 55);
						addr.sendMsg("/g7", 25);
						addr.sendMsg("/b7", 225);							balsOso = Task({
						inf do:	{
						addr.sendMsg("/soloChanTiChanTRUE",2400.rand, 300.rand);
						1.wait;
						};
						});
						balsOso.start;
					}.fork;
					)	
					});										}
		);
		Button(window, Rect(20,20,300,20))
				.states_([
					["lineaAmarillaTRUE", Color.black, Color.white],
					["No lineaAmarillaTRUE", Color.white, Color.black],
				])
				.action_({ arg butt;
					if (butt.value == 0, 
					{
					lineaAmarilla.stop;
					addr.sendMsg("/lineaAmarillaFALSE");					
					}, { 					
					(
						{ 	
						lineaAmarilla = Task({
						inf do:	{
						addr.sendMsg("/lineaAmarillaTRUE",2400.rand, 200+200.rand);
						2.wait;
						};
						});
						lineaAmarilla.start;
					}.fork;
					)	
					});										}
		);
		
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ultima Ciudad", Color.black, Color.white],
					["Ultima Ciudad", Color.white, Color.black],
				])
				.action_({ arg butt;
					if (butt.value == 0, 
					{
					addr.sendMsg("/viewUltimaFALSE");						}, { 					
					addr.sendMsg("/a8", 0);
					//addr.sendMsg("/background", 0,0,0);
					addr.sendMsg("/viewUltimaTRUE");
					});										}
		);
		
		Button(window, Rect(20,20,300,20))
				.states_([
					["Final", Color.black, Color.white],
					["Final", Color.black, Color.white],
				])
				.action_({ arg butt;
					if (butt.value == 0, 
					{
					addr.sendMsg("/background", 0,0,0);
					}, { 					
					addr.sendMsg("/background", 0,0,0);
					});										}
		);
		
		
		
	}
}
