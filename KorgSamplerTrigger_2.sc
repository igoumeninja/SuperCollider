/*
KorgSamplerTrigger_2.loadBuffers
KorgSamplerTrigger_2.createSynth
KorgSamplerTrigger_2.mapControllers_1
*/

KorgSamplerTrigger_2 {
	*loadBuffers {
		~loadBuffers = Task({
			~buffers = ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19"]
			collect: { | fn | Buffer.read(Server.default,"/home/aris/Music/03.Samples/200214/" ++ fn ++ ".aiff");};
			0.4.wait;
		}).start;
	}
	*createSynth{
		~synth1 = SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0, rate = 1, trigger = 0, startPos = 0, pitchShiftRatio = 1 |
			Out.ar(out,
				Pan2.ar(
					PitchShift.ar(
						PlayBuf.ar(1, bufnum, rate, trigger, startPos, loop:1),
						0.1,             // grain size
						pitchShiftRatio,    // mouse x controls pitch shift ratio
						0,                 // pitch dispersion
						0.004            // time dispersion
					), 0, 1
				)
			)
		}).play(Server.default, [\out, 0, \bufnum, 0]);
	}
	*mapControllers_1 {
		~trigger = 1;
		~midi21 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;

			~trigger.postln;
			~synth1.set(\bufnum, 9, \trigger, ~trigger);
			~trigger = ~trigger.neg;
		}, nil, nil, 21, nil);
		~midi31 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~loadBuffers = Task({
				~synth1.set(\trigger, ~trigger);
				~trigger = ~trigger.neg;
				0.02.wait;
				~synth1.set(\bufnum, 9, \trigger, ~trigger);
				~trigger = ~trigger.neg;
			}).start;
		}, nil, nil, 31, nil);
		~midi1 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\rate, LinLin.kr( value, 0, 126, 0.8, 2));
		}, nil, nil, 1, nil);
		~midi11 = CCResponder({ |src,chan,num,value|
			[src,chan,num,value].postln;
			~synth1.set(\pitchShiftRatio, LinLin.kr( value, 0, 126, 0.8, 2));
		}, nil, nil,11, nil);
	}
}

