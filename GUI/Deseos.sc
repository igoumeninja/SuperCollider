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
	var deseo1, deseo2, deseo3, deseo4, deseo5;
	
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
		window = SCWindow("Deseos");
		window.front;
		window.view.background_(Color.new255(22,75,27));
		window.bounds_(Rect(330,0,330,880));
		q = window.addFlowLayout( 10@10, 20@5 );
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
		
		
	}
}
