/*
100702 igoumeninja
Aris Bezas

This class create the Graphical User Interface (GUI) for FBO
Open_GUI.new
Fbo_GUI.new
Escenas.new
Deseos.new

SendMidi
*/


Deseos	{
	classvar	default;
	var	<server;			
	var	<addr;
	var	<a;
	var	<q;		
	var 	window;
	var deseo1, deseo2, deseo3, deseo4, deseo5, balsOso, luciernagas, ciudades;
	
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
		this.deseos;
	}
	
	deseos	{
		window = SCWindow("PART DOS");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(330,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
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
						addr.sendMsg("/a8", 10);						
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
							4.wait;
							addr.sendMsg("/a8", 2);
							
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
						addr.sendMsg("/a7", 75);							addr.sendMsg("/r7", 55);
						addr.sendMsg("/g7", 25);
						addr.sendMsg("/b7", 225);							balsOso = Task({
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
		Button(window, Rect(20,20,300,20))
				.states_([
					["Ultima Ciudad", Color.black, Color.white],
					["Ultima Ciudad", Color.white, Color.black],
				])
				.action_({ arg butt;
					if (butt.value == 0, 
					{
					addr.sendMsg("/viewUltimaFALSE");						}, { 					
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
					}, { 					
					addr.sendMsg("/background", 0,0,0);
					});										}
		);
		
		
		
	}
}
