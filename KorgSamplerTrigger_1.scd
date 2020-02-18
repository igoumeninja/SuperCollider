KorgSamplerTrigger_1 {
	*initClass {
		StartUp.add {
			~midi21 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 0]);}, nil, nil, 21, nil);
			~midi31 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 1 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 1]);}, nil, nil, 31, nil);
			~midi22 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 2 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 2]);}, nil, nil, 22, nil);
			~midi32 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 3]);}, nil, nil, 32, nil);
			~midi23 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 4]);}, nil, nil, 23, nil);
			~midi33 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 5]);}, nil, nil, 33, nil);
			~midi24 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 6]);}, nil, nil, 24, nil);
			~midi34 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 7]);}, nil, nil, 34, nil);
			~midi25 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 8]);}, nil, nil, 25, nil);
			~midi35 = CCResponder({ |src,chan,num,value|
				[src,chan,num,value].postln;
				SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
					Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
			}).play(Server.default, [\out, 0, \bufnum, 9]);}, nil, nil, 35, nil);
		}
	}
}

