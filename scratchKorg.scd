(
b = ["ice", "birdGranular", "clarinetTapes", "XylophonoGranular", "tablaGranular",
	 "Ross", "sculptor2", "fluxon", "PictorAlpha", "gongGranular", "metalGranular", "Gong", "purity", "stakato", "clarinet2"]
	 	collect: { | fn | Buffer.read(s,"/home/aris/Music/03.Samples/" ++ fn ++ ".aiff");};
)
(
~midi21 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 0]);}, nil, nil, 21, nil);
~midi31 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 1 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 1]);}, nil, nil, 31, nil);
~midi22 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 2 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 2]);}, nil, nil, 22, nil);
~midi32 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 3]);}, nil, nil, 32, nil);
~midi23 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 4]);}, nil, nil, 23, nil);
~midi33 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 5]);}, nil, nil, 33, nil);
~midi24 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 6]);}, nil, nil, 24, nil);
~midi34 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 7]);}, nil, nil, 34, nil);
~midi25 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 8]);}, nil, nil, 25, nil);
~midi35 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 9]);}, nil, nil, 35, nil);
)

MIDIClient.init;
(
d = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	y.set(\freq, 10*value);
},
nil, // any source
nil, // any channel
nil, // any CC number
nil // any value
)
)
d.remove;
SynthDef(\SimpleSine, {|freq = 440, out| Out.ar(out, SinOsc.ar(freq, 0, 0.2)) }).add;
y  = Synth(\SimpleSine, [\freq, 660]);

(
~midi21 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf))
}).play(s, [\out, 0, \bufnum, 0]);}, nil, nil, 21, nil)
)

(
~midi31 = CCResponder({ |src,chan,num,value|
	[src,chan,num,value].postln;
	SynthDef(\help_PlayBuf, {| out = 0, bufnum = 1 |
    Out.ar(out,
        PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf)
    )
}).play(s, [\out, 0, \bufnum, 1]);

},
nil, // any source
nil, // any channel
31, // any CC number
nil // any value
)
)

(
b = ["ice", "birdGranular", "clarinetTapes", "XylophonoGranular", "tablaGranular",
	 "Ross", "sculptor2", "fluxon", "PictorAlpha", "gongGranular", "metalGranular", "Gong", "purity", "stakato", "clarinet2"]
	 	collect: { | fn | Buffer.read(s,"/home/aris/.local/share/SuperCollider/Extensions/KorgSynth/sc/samples/" ++ fn ++ ".aiff");};
)

SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0 |
    Out.ar(out,
        PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), doneAction: Done.freeSelf)
    )
}).play(s, [\out, 0, \bufnum, b]);




(
SynthDef("PlayBuf",
	{ arg out=0, t_trig=0, amp=1, bufnum=1, delayTime, freqShift, pitchRatio, rate;
	Out.ar(out,Pan2.ar(
		PitchShift.ar(FreqShift.ar(PlayBuf.ar(1, bufnum, MouseX.kr(0.25, 0.75), trigger: t_trig, loop: 1.0),freqShift), 0.02, pitchRatio, 0, 0.004), 	0));
}).play;
)


x = Synth("PlayBuf");
x.set()






// OSC router
p = NetAddr("127.0.0.1", 12000);  // send to Processing


OSCresponder(nil, "ice", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 0);}).add;
OSCresponder(nil, "birdGranular", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 1);}).add;
OSCresponder(nil, "clarinetTapes", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 2);}).add;
OSCresponder(nil, "XylophonoGranular", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 3);}).add;
OSCresponder(nil, "tablaGranular", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 4);}).add;
OSCresponder(nil, "Ross", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 5);}).add;
OSCresponder(nil, "sculptor2", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 6);}).add;
OSCresponder(nil, "fluxon", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 7);}).add;
OSCresponder(nil, "PictorAlpha", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 8);}).add;
OSCresponder(nil, "gongGranular", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 9);}).add;
OSCresponder(nil, "metalGranular", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 10);}).add;
OSCresponder(nil, "Sythesis090519", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 11);}).add;
OSCresponder(nil, "Gong", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 12);}).add;
OSCresponder(nil, "purity", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 13);}).add;
OSCresponder(nil, "stakato", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 14);}).add;
OSCresponder(nil, "clarinet2", { s.sendMsg("/n_set", 1001, "t_trig", 1, "bufnum", 15);}).add;

OSCresponder(nil, "analogs", { | time, resp, message |
	//message.postln;
	p.sendMsg("/analog0", message[1]);
	p.sendMsg("/analog1", message[2]);
	p.sendMsg("/analog2", message[3]);
	p.sendMsg("/analog3", message[4]);
	p.sendMsg("/analog4", message[5]);
	p.sendMsg("/analog5", message[6]);
}).add;

// Recieve fController OSC messages collecting the digitals
OSCresponder(nil, "dig", { | time, resp, message |
	//message.postln;
	p.sendMsg("/dig2", message[3]);
	p.sendMsg("/dig3", message[4]);
	p.sendMsg("/dig4", message[5]);
	p.sendMsg("/dig5", message[6]);
	p.sendMsg("/dig6", message[7]);
    	p.sendMsg("/dig7", message[8]);
	p.sendMsg("/dig8", message[9]);
	p.sendMsg("/dig9", message[10]);
	p.sendMsg("/dig10", message[11]);
	p.sendMsg("/dig11", message[12]);
	p.sendMsg("/dig12", message[13]);
}).add;

// Start
OSCresponder(nil, "start", {
	z = Synth("Xaraktiki");
	x = Synth("PlayBuf");
}).add;

OSCresponder(nil, "amp", { | time, resp, message |
		z.set("amp", message[1]);
}).add;

OSCresponder(nil, "freqShift", { | time, resp, message |
		x.set("freqShift", message[1]);
}).add;


OSCresponder(nil, "pitchRatio", { | time, resp, message |
		x.set("pitchRatio", message[1]);
}).add;