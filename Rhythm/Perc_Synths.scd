//Synth from Understanding Streams, Patterns and Events - Part 3
(
SynthDef( \help_SPE3_Mridangam, {
	| t_amp = 1, midiNote = 60|
    var out;

    out = Resonz.ar(
        WhiteNoise.ar(70) * Decay2.kr( t_amp, 0.002, 0.1 ),
        midiNote.midicps,
        0.02,
        4
    ).distort * 0.4;

    Out.ar( 0, out );
    DetectSilence.ar( out, doneAction: 2 );
}).add;

)
(
{
	inf.do{|i|
		Synth(\help_SPE3_Mridangam,[\midiNote, rrand(60,60).asInt]);
		0.4.wait;
	};
}.fork
)

s = Synth(\help_SPE3_Mridangam)
//Vaggelis Synths
(
SynthDef(\kick, { | dur = 0.15, atk = 0.005, amp = 1, fb = 0 |

	Out.ar([0, 1], SinOscFB.ar(
		EnvGen.ar( Env(
			\freq_l.kr([2960, 70, 90]),
			\freq_d.kr([0.07, 0.2]),
			\freq_c.kr([-13, -1]),
		) ), fb,
		amp * EnvGen.ar(
			Env.perc( atk, dur - atk/3, curve: \amp_c.kr([-1, 6])),
			doneAction: 2
		);
	))
}).add;

SynthDef(\kick2, { | rel = 0.15, atk = 0.005, amp = 1, fb = 0 |

	Out.ar([0, 1], SinOscFB.ar(
		EnvGen.ar( Env(
			\freq_l.kr([2960, 70, 90]),
			\freq_d.kr([0.07, 0.2]),
			\freq_c.kr([-13, -1]),
		) ), fb,
		amp * EnvGen.ar(
			Env.perc( atk, rel - atk/3, curve: \amp_c.kr([-1, 6])),
			doneAction: 2
		);
	))
}).add;

)
//:Pattern
(
Pdef(\pattern87,

	Ppar([

		Pbind(*[
			\instrument, \kick,
			\dur, Pseq([Pseq([1/2, 1/4, 1/4, 1/2, Pseq([1/8], 3), 1/8], 10)], inf),
			\amp, Pseq([
				Pseq([1], 16),
				Pseq([0.8, 1], 8),
				Pseq([1], 16),
				Pseq([0.8, 0.8, 0.9, 1], 8)
			], inf) * 0.8,
			\freq_l, Pseq([
				`[1260,60,110],
				0, 0,
				`[8360,60,120], `[4360,60,120], 0, `[4360,60,120], 0,
				`[1260,60,110], 0, `[8360,60,120], 0
			],inf),
			\freq_d, Pseq([`[0.07, 0.2]], inf),
			\fb, Pseq([0, 0.3], inf),

		]),


		Pbind(*[
			\instrument, \kick2,
			\dur, Pseq([1/2, 1/4, 1/4, 1/2, Pseq([1/8], 3), 1/8]/2, inf),
			\amp, Pseq([
				Pseq([1], 16),
				Pseq([0.8, 1], 8),
				Pseq([1], 16),
				Pseq([0.8, 0.8, 0.9, 1], 8)
			], inf) * 0.5,
			\freq_l, Pseq([
				`[1260,60,110],
				0, 0,
				`[4360,60,120], `[8360,60,120], 0, `[4360,60,120], 0,
				`[1260,60,110], 0, `[8360,60,120], 0
			],inf),
			\freq_d, Pseq([`[0.01, 0.8]], inf),
			\fb, Pseq([0, 0.3], inf),
			\rel, 0.3,
			\atk, 0.001

		])

])).play(TempoClock(70/60), quant:[1]);
)
//:



