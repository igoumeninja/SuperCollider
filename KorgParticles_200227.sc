/*
KorgParticles_200227.start


~startAppTask.start
// Particles

NetAddr("127.0.0.1", 9005).sendMsg("/particleView", 0.asInteger)
NetAddr("127.0.0.1", 9005).sendMsg("/particleView", 1.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", 100,100,500,900)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", 100,100,800,900)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", 4)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", 20)


NetAddr("127.0.0.1", 12345).sendMsg("/particle", "conColor", 255.asInteger,255.asInteger,255.asInteger,25.asInteger)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "dotColor", 255.asInteger,255.asInteger,255.asInteger,255.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "forceRadius", 35.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "forceScale", 100.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "pushParticles", 1.asInteger)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "pushParticles", 0.asInteger)


NetAddr("127.0.0.1", 12345).sendMsg("/particle", "push", 300.asInteger, 200.asInteger)

NetAddr("127.0.0.1", 12345).sendMsg("/particle", "lineOpacity", 2)
NetAddr("127.0.0.1", 12345).sendMsg("/particle", "push", 150,150)
*/

KorgParticles_200227 {
	*start {
		Task({
			this.mapControllers;
			0.5.wait;
		}).start;
	}
	*mapControllers {
		~xStart = 0;~yStart = 0;~xEnd = 1920; ~yEnd = 1020;
		~midi42 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~yStart = LinLin.kr( value, 0, 126, 1020, 0);
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", ~xStart, ~yStart, ~xEnd, ~yEnd);
		}, nil, nil, 42, nil);
		~midi57 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~xStart = LinLin.kr( value, 0, 126, 0, 1920);
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", ~xStart, ~yStart, ~xEnd, ~yEnd);
		}, nil, nil, 57, nil);
		~midi43 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~yEnd = LinLin.kr( value, 0, 126, 1020, 0);
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", ~xStart, ~yStart, ~xEnd, ~yEnd);
		}, nil, nil, 43, nil);
		~midi58 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~xEnd = LinLin.kr( value, 0, 126, 0, 1920);
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "bounce", ~xStart, ~yStart, ~xEnd, ~yEnd);
		}, nil, nil, 58, nil);
		//NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", 4)
		~midi66 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "particleNeighborhood", LinLin.kr( value, 0, 126, 0, 50));
		}, nil, nil, 66, nil);
		~midi65 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "dotColor", 255.asInteger,255.asInteger,255.asInteger,LinLin.kr( value, 0, 126, 0, 255).asInteger);
		}, nil, nil, 65, nil);
		~midi63 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			NetAddr("127.0.0.1", 12345).sendMsg("/particle", "conColor", 255.asInteger,255.asInteger,255.asInteger,LinLin.kr( value, 0, 126, 0, 255).asInteger);
		}, nil, nil, 63, nil);
	}
}

