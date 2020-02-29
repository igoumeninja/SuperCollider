/*
KorgSamplerTrigger_200217.start
~synth1.set(\loop, 1);
~synth1.set(\panFreq, 2);
~synth1.set(\level, 1);
*/

KorgSamplerTrigger_200217 {
	*start {
		Task({
			this.loadBuffers;
			0.5.wait;
			this.createSynth;
			0.01.wait;
			this.mapControllers;
		}).start;
	}
	*loadBuffers {
		~loadBuffers = Task({
			~buffers = ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19"]
			collect: { | fn | Buffer.read(Server.default,"/home/aris/Music/03.Samples/Argyrakos/" ++ fn ++ ".wav");};
			0.4.wait;
		}).start;
	}
	*createSynth {
		~synth1 = SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0, rate = 1, trigger = 0, startPos = 0, loop = 0, pitchShiftRatio = 1, pan = 0, level = 1|
			Out.ar(out,
				Pan2.ar(
					PitchShift.ar(
						PlayBuf.ar(1, bufnum, rate, trigger, startPos, loop),
						0.1, pitchShiftRatio, 0, 0.004),
					pan, level)
			)
		}).play(Server.default, [\out, 0, \bufnum, 0]);
	}
	*mapControllers {
		~trigger = 1;
		~midi21 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 0, \trigger, 1);
			}).start;
		}, nil, nil, 21, nil);
		~midi31 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 1, \trigger, 1);
			}).start;
		}, nil, nil, 31, nil);
		~midi22 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 2, \trigger, 1);
			}).start;
		}, nil, nil, 22, nil);
		~midi32 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 3, \trigger, 1);
			}).start;
		}, nil, nil, 32, nil);
		~midi23 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 4, \trigger, 1);
			}).start;
		}, nil, nil, 23, nil);
		~midi33 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 5, \trigger, 1);
			}).start;
		}, nil, nil, 33, nil);
		~midi24 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 6, \trigger, 1);
			}).start;
		}, nil, nil, 24, nil);
		~midi34 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 7, \trigger, 1);
			}).start;
		}, nil, nil, 34, nil);
		~midi25 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 8, \trigger, 1);
			}).start;
		}, nil, nil, 25, nil);
		~midi35 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 9, \trigger, 1);
			}).start;
		}, nil, nil, 35, nil);
		~midi26 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 10, \trigger, 1);
			}).start;
		}, nil, nil, 26, nil);
		~midi36 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 11, \trigger, 1);
			}).start;
		}, nil, nil, 36, nil);
		~midi27 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 12, \trigger, 1);
			}).start;
		}, nil, nil, 27, nil);
		~midi37 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 13, \trigger, 1);
			}).start;
		}, nil, nil, 37, nil);
		~midi28 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 14, \trigger, 1);
			}).start;
		}, nil, nil, 28, nil);
		~midi38 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 15, \trigger, 1);
			}).start;
		}, nil, nil, 38, nil);
		~midi29 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 16, \trigger, 1);
			}).start;
		}, nil, nil, 29, nil);
		~midi39 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			Task({
				~synth1.set(\trigger,-1);
				0.018.wait;
				~synth1.set(\bufnum, 17, \trigger, 1);
			}).start;
		}, nil, nil, 39, nil);
		~midi11 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\rate, LinLin.kr( value, 0, 126, 0.3, 3));
		}, nil, nil, 11, nil);
		~midi12 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\pitchShiftRatio, LinLin.kr( value, 0, 126, 0.2, 3));
		}, nil, nil, 12, nil);
		~midi13 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\bufnum, LinLin.kr( value, 0, 126, 0, 18).asInteger);
		}, nil, nil, 13, nil);
		~midi19 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\level, LinLin.kr( value, 0, 126, 0, 1));
		}, nil, nil, 19, nil);
		~midi18 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\pan, LinLin.kr( value, 0, 126, -1, 1));
		}, nil, nil, 18, nil);
		//RESETFSinOsc.kr(panFreq)
		~midi44 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\pitchShiftRatio, 1, \rate, 1, \pan, 0, \level2, 0);
		}, nil, nil, 44, nil);
		//LOOP
		~midi47 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\loop, 1);
		}, nil, nil, 47, nil);
		~midi48 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\loop, 0);
		}, nil, nil, 48, nil);
	}
}

