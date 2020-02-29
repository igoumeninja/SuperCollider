/*
Easy guide to play traditional rythms with SuperCollider
Aris Bezas 121111 Igoumeninja
*/
//:!boot the server
s.boot;
//============================//
//:Start with default Pbind
//============================//
(
Pbind(
	\amp,            Pseq([1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/10, inf),
).play;
)

//:Use simple Synth who generate BrownNoise with Envelope
(
SynthDef(\noise, { | out, freq = 440, amp = 1|
    Out.ar(
		out,
		Pan2.ar(
			BrownNoise.ar(mul:amp,add:0)*EnvGen.ar(Env.perc(0.01,1,0.1),
			doneAction:2
	)));
}).add;
)
//:or SinOsc synth
(
SynthDef(\drumSin, { | out, freq = 440, amp = 1, nharms = 10, pan = 0, gate = 1 |
	Out.ar(out, Pan2.ar(SinOsc.ar(mul:amp)*EnvGen.ar(Env.perc(0.01,1,0.1),doneAction:
	2), pan));
}).add;
)

//:Play the pattern with your own Synth
(
Pbind(
    \instrument,     \noise,
	\amp,            Pseq([1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/10, inf),
).play;
)

//==========================================//
//:PlayBuf Synth make it more interesting
//==========================================//

(
//:Load the buffers
~dum = Buffer.read(s, "/Users/ari/Media/sounds/percusion/bass_Drum_Single_Kick.aiff");
~te = Buffer.read(s, "/Users/ari/Media/sounds/percusion/gonga_single_hit.aiff");
~bell = Buffer.read(s, "/Users/ari/Media/sounds/percusion/agogo_bell.aiff");
)

(
SynthDef(\dum, { | out, freq = 440, amp = 1, bufnum = 10, pan = 0, gate = 1 |
	Out.ar(0, Pan2.ar(PlayBuf.ar(1,bufnum:bufnum,doneAction:
		2), 0, amp));
}).store;
)

(
//Aptaliko Zeimpekiko
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0,~te, 0, ~te, 0,
		~dum, 0, ~te, ~te,
		~dum, 0, ~te, 0,
		~dum, 0, ~te, ~te], inf),
	\amp,            Pseq([
		1, 0, 1, 0, 1, 0,
		1, 0, 1, 1,
		1, 0, 1, 0,
		1, 0, 1, 1], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/2, inf),
).play;
)

//==========================================//
//:Control BPM
//==========================================//
(
//Kalamatianos (7/8, 3-2-2)
~bpm = 120;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0, ~te, ~dum, 0, ~te, 0
	], inf),
	\amp,            Pseq([1, 0, 1, 1, 0, 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1]/~ratio, inf),
).play;
)

//==========================================//
//======== GVerb ===========//
//==========================================//
(
SynthDef(\dum, { | out, freq = 440, amp = 1, bufnum = 10, pan = 0, gate = 1, roomsize = 5, revtime = 1.6, damping = 0.62, mulVerb = 1 |
	Out.ar(0,
		Pan2.ar(
			GVerb.ar(
				PlayBuf.ar(1,bufnum:bufnum, doneAction:2),
				roomsize,
				revtime,
				damping,
				mul: mulVerb
			)
			+
			PlayBuf.ar(1,bufnum:bufnum, doneAction:2),
			0, amp)
	);
}).store;
)

(
//Tsamiko
~bpm = 160;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0, 0, ~te,
		~te,  0, ~te, 0,
		~dum, 0, ~te, 0
	], inf),
	\amp,            Pseq([1,0,0,1,1,0,1,0,1,0,1,0], inf),
	\dur,            Pseq([1,1,1,1,1,1,1,1,1,1,1,1]/~ratio, inf),
).play;
)

(
//Bayon - Pogonisio
~bpm = 160;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\roomsize,       Pseq([10, 20, 30, 4, 50], inf),
	\bufnum,         Pseq([
		~dum, ~te,
		~te,  ~te,
		~dum,  ~te,
		~te, 0,
	], inf),
	\amp,            Pseq([1, Prand([0, 1],1), 1, 1, 1, Prand([0, 1],1), 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1]/~ratio, inf),
).play;
)
//==========================================//
//==========================================//
(
//Aptaliko Zeimpekiko
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0,~te, 0, ~te, 0,
		~dum, 0, ~te, ~te,
		~dum, 0, ~te, 0,
		~dum, 0, ~te, ~te], inf),
	\amp,            Pseq([
		1, 0, 1, 0, 1, 0,
		1, 0, 1, 1,
		1, 0, 1, 0,
		1, 0, 1, 1], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/2, inf),
).play;
)

(
//Palio Zeimpekiko
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0, ~te, ~te,
		~dum, 0, ~te, 0,
		~dum, 0, ~te, ~te,
		~dum, 0, ~te, 0,
		~te, 0], 14),
	\amp,            Pseq([1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/10, inf),
).play;
)


(
//Mantilatos (2+2+3) from [[http://www.musicking.gr/%CE%A1%CF%85%CE%B8%CE%BC%CF%8C%CF%82/?lang=el]]
~bpm = 340;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0,
		~te,  0,
		~te,  0, 0,
		~dum, 0,
		~te, 0,
		~te,  0, 0
	], inf),
	\amp,            Pseq([1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/~ratio, inf),
).play;
)

(
//Xasapiko
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~te
	], inf),
	\amp,            Pseq([1, 1], inf),
	\dur,            Pseq([1, 1]/3, inf),
).play;
)

(
//Kalamatianos (7/8, 3-2-2)
~bpm = 120;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0, ~te, ~dum, 0, ~te, 0
	], inf),
	\amp,            Pseq([1, 0, 1, 1, 0, 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1]/~ratio, inf),
).play;
)

//Syrto (3+2+2)
(
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~te, ~te,
		~dum, 0,
		~dum, ~te], inf),
	\amp,            Pseq([1, 1, 1, 1, 0, 1, 1], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1]/10, inf),
).play;
)

//Tsifteteli
(
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~te, 0,
		~te, ~dum, 0,
		~te, 0], inf),
	\amp,            Pseq([4, 1, 0, 1, 1, 0, 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1]/7, inf),
).play;
)

//Tsifteteli 2
(
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~te, 0,
		~dum, ~dum, 0,
		~te, 0], inf),
	\amp,            Pseq([1, 1, 0, 1, 1, 0, 1, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1]/3, inf),
).play;
)
//Tsifteteli 3
(
~bpm = 160;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 16a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, 0, ~te,  ~te,
		~dum, 0, ~te,  0,
		~dum, 0, ~dum, 0,
		~te,  0, 0,    0], inf),
	\amp,            Pseq([
		1, 0, 1, 1,
		1, 0, 1, 0,
		1, 0, 1, 0,
		1, 0, 0, 0], inf),
	\dur,            Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]/~ratio, inf),
).play;
)
Kaffer


//:Extra technics for patterns

Prand([~te, ~bell],1)

//======= GLOBAL BPM ===========
//:Implement global bps
(
~bpm = 240;
~ratio = ~bpm/60;
Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~dum
	], inf),
	\amp,            Pseq([
		1, 1
	], inf),
	\dur,            Pseq([1, 1]/~ratio, inf),
).play;
)

//:====== SYNTH IMPROVeMENT ===========

//:pitch correction
BufRateScale
(
x = { arg rate=1;
    BufRd.ar(1, ~dum, Phasor.ar(0, BufRateScale.kr(~dum) * rate, 0, BufFrames.kr(~dum)))
}.play;
)
(
SynthDef(\krousta, {
	| out, freq = 440, amp = 1, bufnum, pan = 0, rate|
	Out.ar(0,
		Pan2.ar(
			PlayBuf.ar(1, bufnum:bufnum, rate: rate, doneAction:2),
			pan,
			amp
	));
}).store;
)
(
~bpm = 1440;
~ratio = ~bpm/60;
Pbind(
    \instrument,     \krousta,
	\rate,           Pseq([
		1, 1
	], inf),
	\pan,           Pseq([
		1, -1
	], inf),

	\bufnum,         Pseq([
		~dum, ~dum
	], inf),
	\amp,            Pseq([
		1, 1
	], inf),
	\dur,            Pseq([1, 1]/~ratio, inf),
).play;
)

//:====== PROXY PATTERNS ================
PatternProxy

(

~rateProxyPattern = PatternProxy(Pseq([0, 2, 9], inf));

x = Pseq([0, 0, ~rateProxyPattern], inf).asStream;

t = Task({ loop({ x.next.postln; 0.3.wait }) }).play;
~rateProxyPattern.source = Pseq([55, 66, 77], inf);
~rateProxyPattern.source = Pseq([55, 66, 77], 1);

t.stop;

)

Pbind(
    \instrument,     \krousta,
	\rate,           Pseq([
		1, 1
	], inf),
	\pan,           Pseq([
		1, -1
	], inf),

	\bufnum,         Pseq([
		~dum, ~dum
	], inf),
	\amp,            Pseq([
		1, 1
	], inf),
	\dur,            Pseq([1, 1]/~ratio, inf),
).play;
)
