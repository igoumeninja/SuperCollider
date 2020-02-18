(
~loadBuffers = Task({
	~buffers = ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"]
	collect: { | fn | Buffer.read(Server.default,"/home/aris/Music/03.Samples/200214/" ++ fn ++ ".aif");};
	0.4.wait;
}).start;
)

(
~loadBuffers1 = Task({
	~buffers1 = ["ice", "birdGranular", "clarinetTapes", "XylophonoGranular", "tablaGranular",
		"Ross", "sculptor2", "fluxon", "PictorAlpha", "gongGranular", "metalGranular", "Gong", "purity", "stakato", "clarinet2"]
	collect: { | fn | Buffer.read(Server.default,"/home/aris/Music/03.Samples/" ++ fn ++ ".aiff");};
	0.4.wait;
}).start;
)

(
~synth = SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0, rate = 1, pitchShiftRatio = 1 |
	Out.ar(out,
		Pan2.ar(
			PitchShift.ar(
				PlayBuf.ar(1, bufnum, rate, loop:1.0),
				0.1,             // grain size
				pitchShiftRatio,    // mouse x controls pitch shift ratio
				0,                 // pitch dispersion
				0.004            // time dispersion
			), 0, 1
		)
	)
}).play(Server.default, [\out, 0, \bufnum, 0]);
)

~synth.set(\bufnum, 4)
~synth.set(\rate, 3)
~synth.set(\pitchShiftRatio, 0.1)


{[SinOsc.ar(440), SinOsc.ar(440)]}.play