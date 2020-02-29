/*
Rhythm
OOP with tradicional rhythms
Aris Bezas 121115

Rhythm.play(501,85);  //Rhythm.play(rhythmID, bpm);
~durPPatt.source.asStream.next
~dum = ~cM
Rhythm.mute
~rhythm.
~bufnumPPatt.source.asStream.next.next()
Rhythm.changeBpm(200)
Rhythm.mute
~instrumentPPatt.source = Pseq(\simplePlayBuf, inf);
~rhythm.fin
Rhythm_IDE.create
*/

Rhythm {
	*initClass {
		StartUp add: {
			if (not(Server.default.serverRunning)) { Server.default.boot };
			Server.default.doWhenBooted {
				//ratio= [   0,    1,    2,     3,     4,       5,     6,      7,     8,     9,     10,    11 ,          12,  13]
				//ratio= [ 2/4,  4/4,  8/8, 16/16,  33/32,    3/4,   6/8,    7/8,   9/8,   10/8   12/8,   18/8(9/4),  18/16, 7/4]
				~ratio = [1/60, 1/60, 1/30,  1/15,   2/15, 0.4/18,  1/30,   1/15, 0.1/3,   1/30,   1/30, 1/60,      0.1/1.5,   1];
				~bpm = 100;
				~rhythmID = 0;
				this.loadTheBuffers;
				this.defineNotesFreqs;
				this.sendTheSynths;
				this.defineProxyPattern;
				~rhythmNum = 0;
				"\n|=========================|".postln;
				"| Rhythm Class is running |".postln;
				"|=========================|\n".postln;
			};
		}
	}
	*play {|rhythmID=1, bpm=100|
		if (~rhythmNum == 0,
			{
				"Rhythm is not Playing, so first let's start the Pattern and after define the rhythm".postln;
				this.start;
				this.changeRhythm(rhythmID, bpm);
				~rhythmNum = 1;
			},{
				"Rhythm is Playing, so just change the rhythm".postln;
				this.changeRhythm(rhythmID, bpm);
			}
		)
	}
	*start{
		~rhythm.play;
		~meterCounter = 0;
		//~countPPatt.next(());
	}
	*stop {~rhythm.stop;~rhythm = 0}//Doesn't work
	*mute {~ampPPatt.source = Pseq([0, 0], inf);}

	*loadTheBuffers	{

		~bass_drum = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/bass_Drum_Single_Kick.aiff");
		~gonga = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/gonga_single_hit.aiff");
		~bell = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/agogo_bell.aiff");
		~tambourine = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/tambourine.aiff");
		~darbukaDum = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/36607__schalkalwis__darbuka-1-003.aiff");
		~darbukaTe = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/36611__schalkalwis__darbuka-2-002.aiff");

		~cM = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/guitar/CM.aiff");
		~dM = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/guitar/DM.aiff");

		~ntefiDum = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/ntefiDum.aiff");
		~ntefiTe = Buffer.read(Server.default, Platform.userExtensionDir +/+ "Rhythm/samples/ntefiTe.aiff");
		//default
		//
		~dum = ~bass_drum;
		~te  = ~gonga;
		// ~dum = ~ntefiDum;
		// ~te  = ~ntefiTe;

	}
	*sendTheSynths	{
		//Synth from Understanding Streams, Patterns and Events - Part 3
		SynthDef( \mridangam, {
			| t_amp = 2, midiNote = 60, bufnum|
			var out;
			// if (bufnum == ~dum, {midiNote = 60;"dum".postln}, {"no dum".postln});
			// if (bufnum == ~te, {midiNote = 600;"dum".postln}, {"no dum".postln});
			out = Resonz.ar(
				WhiteNoise.ar(70) * Decay2.kr( t_amp, 0.002, 0.1 ),
				midiNote.midicps,
				0.02,
				4
			).distort * 0.4;

			Out.ar( 0, out );
			DetectSilence.ar( out, doneAction: 2 );
		}).store;

		//PlayBuf
		SynthDef(\simplePlayBuf, { | out, freq = 440, amp = 2, bufnum = 10, pan = 0, gate = 1, loop = 0 |
			Out.ar(0, Pan2.ar(PlayBuf.ar(1,bufnum:bufnum,doneAction:
				2, loop: loop), 0, amp));
		}).store;

		//PercSynth
		SynthDef(\percSynth, { | out, freq = 440, amp = 1, bufnum = 10, pan = 0, gate = 1, loop = 0 |
			Out.ar(0, Pan2.ar(SinOsc.ar(freq:freq), 0, amp));
		}).store;

		//\phasorPlayBuf
		SynthDef(\phasorPlayBuf, { | out, freq = 440, amp = 2, bufnum = 10, pan = 0, gate = 1,
			loop = 0, rate = 1|
			var playBuf, phasor;
			//phasor = Phasor.ar(0, BufRateScale.kr(bufnum)*rate, 0, BufFrames.kr(bufnum));
			//playBuf = BufRd.ar(1, bufnum, phase:phasor,loop:0,interpolation:1);
			playBuf = PlayBuf.ar(1,bufnum,BufRateScale.kr(bufnum)*rate,loop,doneAction:2);
			Out.ar(0, Pan2.ar(playBuf, 0, amp));
		}).store;

		//doesn't work
		SynthDef(\playBufGVerb, { | out, freq = 440, amp = 1, bufnum = 10, pan = 0, gate = 1,
			roomsize = 5, revtime = 1.6, damping = 0.62, mulVerb = 1 |
			var playBuffer = PlayBuf.ar(1,bufnum:bufnum, doneAction:2);
			Out.ar(0,
				Pan2.ar(
					GVerb.ar(
						playBuffer,
						roomsize,
						revtime,
						damping,
						mul: mulVerb
					)
					+
					playBuffer,
					0, amp)
			);
		}).store;
	}

	*defineNotesFreqs{
	~c0 = 16.35;~d0b = 17.32;~d0 = 18.35;~e0b = 19.45;~e0 = 20.60;~f0 = 21.83;~g0b = 23.12;~g0 = 24.50;~a0b = 25.96;~a0 = 27.50;~b0b = 29.14;~b0 = 30.87;
~c11 = 32.70;~d1b = 34.65;~d1 = 36.71;~e1b = 38.89;~e1 = 41.20;~f1 = 43.65;~g1b = 46.25;~g1 = 49.00;~a1b = 51.91;~a1 = 55.00;~b1b = 58.27;~b1 =61.74;
~c2 = 65.41;~d2b = 69.30 ;~d2 = 73.42 ;~e2b = 77.78;~e2 = 82.41;~f2 = 87.31;~g2b = 92.50;~g2 = 98.00;~a2b = 103.83;~a2 = 110.00;~b2b = 116.54;~b2 = 123.47;
~c3 = 130.81;~d3b = 138.59;~d3 = 146.83;~e3b = 155.56;~e3 = 164.81;~f3 = 174.61;~g3b = 185.00;~g3 = 196.00;~a3b = 207.65;~a3 = 220.00;~b3b = 233.08;~b3 = 246.94;~c4 = 261.63;~d4b = 277.18;~d4 = 293.66;~e4b = 311.13;~e4 = 329.63;~f4 = 349.23;~g4b = 369.99;~g4 = 392.00;~a4b = 415.30;~a4 = 440.00;~b4b = 466.16;~b4 = 493.88;~c5 = 523.25;~d5b = 554.37;~d5 = 587.33;~e5b = 622.25;~e5 = 659.26;~f5 = 698.46;~g5b = 739.99;~g5 = 783.99;~a5b = 830.61;~a5 = 880.00;~b5b = 932.33;~b5 = 987.77;~c6 = 1046.50;~d6b = 1108.73;~d6 = 1174.66;~e6b = 1244.51;~e6 = 1318.51;~f6 = 1396.91;~g6b = 1479.98;~g6 = 1567.98;~a6b = 1661.22;~a6 = 1760.00;~b6b = 1864.66;~b6 = 1975.53;~c7 = 2093.00;~d7b = 2217.46;~d7 = 2349.32;~e7b = 2489.02;~e7 = 2637.02;~f7 = 2793.83;~g7b = 2959.96;~g7 = 3135.96;~a7b = 3322.44;~a7 = 3520.00;~b7b = 3729.31;~b7 = 3951.07;~c8 = 4186.01;~db = 4434.92;~d8 = 4698.64;~eb = 4978.03;
	}
	*defineProxyPattern {
		~instrumentPPatt = PatternProxy(Pseq([\simplePlayBuf], inf));
		~ratePPatt = PatternProxy(Pseq([1], inf));
		~bufnumPPatt = PatternProxy(Pseq([~dum, ~te], inf));
		~ampPPatt = PatternProxy(Pseq([0, 0], inf));
		~durPPatt = PatternProxy(Pseq([1, 1], inf));
		// ~countPPatt = PatternProxy(Pseq([1, 1], inf));
		// ~countPPatt.asStream;

		~rhythm = Pbind(
			\instrument,     ~instrumentPPatt,
			\rate,           ~ratePPatt,
			\bufnum,         ~bufnumPPatt,
			\amp,            ~ampPPatt,
			\dur,            ~durPPatt,
			// \count,          ~countPPatt,
		);
	}
	*changeBpm { |bpm=100|
		~bpm = bpm;
	}
	//Rhythms
	*changeRhythm { |rhythmNum=1, bpm=100|
		case
		{rhythmNum == 1} {
			~bpm = ~ratio[0]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,  ~dum, ~te], inf);
			~ampPPatt.source =    Pseq([   1,   1,     1,   1], inf);
		}
		{rhythmNum == 2} {
			~bpm = ~ratio[5]*bpm;
			~durPPatt.source = Pseq([1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,  ~te], inf);
			~ampPPatt.source =    Pseq([   1,   1,    1], inf);
		}
		{rhythmNum == 3} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,  ~te, ~dum,  0,  ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,    1,    1,  0,   1,   0], inf);
		}
		{rhythmNum == 4} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,  ~te, ~dum,  ~te,  ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,    1,    1,    1,   1,   0], inf);
		}
		{rhythmNum == 5} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,  ~te, ~dum,   0,  ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,    1,    1,   0,   1,   0], inf);
		}
		{rhythmNum == 6} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,  ~te, ~dum,   0,  ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,    1,    1,   0,   1,    1], inf);
		}
		{rhythmNum == 7} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,  ~te, ~te,   0,  ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,    1,   1,   0,   1,  0], inf);
		}
		{rhythmNum == 8.1} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, 0,  ~te, ~te,   0,  ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0, 0,    1,   1,   0,   1,  0], inf);
		}
		{rhythmNum == 8.2} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, 0,  ~te, ~te,  ~te,  ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0, 0,    1,   1,    1,   1,    1], inf);
		}
		{rhythmNum == 9.1} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, 0,  ~te, ~te,   0,  ~te, 0,~dum, 0,~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0, 0,    1,   1,   0,   1,  0,  1, 0,  1, 0], inf);
		}
		{rhythmNum == 9.2} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, 0,~te, ~te,   0,  ~te, 0,~te, ~te,~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0, 0,  1,   1,   0,   1,  0,  1,   1,  1,   1], inf);
		}
		{rhythmNum == 10.1} {
			~bpm = ~ratio[6]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~dum, ~te,   0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,    0], inf);
		}
		{rhythmNum == 10.2} {
			~bpm = ~ratio[6]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~dum, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,    1], inf);
		}
		{rhythmNum == 11.1} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~dum, ~te, 0, ~dum, ~dum, ~te, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,  0,    1,    1,   1,  0,  1,   1], inf);
		}
		{rhythmNum == 11.2} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~dum, ~te, 0, ~dum, ~dum, ~te, 0, 0,0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,  0,    1,    1,   1, 0, 0,0], inf);
		}
		{rhythmNum == 12.1} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum,  0, ~te,  0, 0,0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,    1,  0,   1,  0, 0,0], inf);
		}
		{rhythmNum == 12.2} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum,  ~dum, ~te,  0, ~te,  ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,    1,     1,   1,  0,   1,    1], inf);
		}
		{rhythmNum == 12.3} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum,  0, ~te,  0, ~te,  ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,    1,  0,   1,  0,   1,    1], inf);
		}
		{rhythmNum == 13} {
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum,  0, ~te,  0, 0, 0,~dum, 0, ~te, ~dum, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,    1,  0,   1,  0, 0, 0,   1, 0,  1,    1,   1, 0], inf);
		}
		{rhythmNum == 14} { //TODO
			~bpm = ~ratio[9]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum,  0, ~te,  0, 0, 0,~dum, 0, ~te, ~dum, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,    1,  0,   1,  0, 0, 0,   1, 0,  1,    1,   1, 0], inf);
		}
		{rhythmNum == 15.1} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, 0, ~te, 0,  0, ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0, 0,   1, 0,  0,   1,  0], inf);
		}
		{rhythmNum == 15.2} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, 0,  ~te, ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1, 0,    1,   1,  0], inf);
		}
		{rhythmNum == 15.3} {
			~bpm = ~ratio[3]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, 0,  ~te, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1, 0,    1,   1,   1], inf);
		}
		{rhythmNum == 16} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~dum, 0,  ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,  0,    1,  0], inf);
		}
		{rhythmNum == 17} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~dum, 0,  ~te,  0], inf);
			~ampPPatt.source =    Pseq([1,      1,   1,   1,  0,    1,  0], inf);
		}
		{rhythmNum == 18} {
			~bpm = ~ratio[7]*bpm;
			//~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, 0, ~dum, 0, ~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,  0,    1, 0,  1,   1,    1, 0,   1, 0], inf);
		}
		{rhythmNum == 19} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(7,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~dum, ~te, ~dum, ~te], inf);
			~ampPPatt.source = Pseq([     1,   1,    1,  1,    1,    1,   1], inf);
		}
		{rhythmNum == 20} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, 0,~dum, 0,~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,  0,  1,  0,  1,   1,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 21} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, 0,~dum, ~te,~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,  0,  1,    1,  1,   1,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 22} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~te, ~te, ~te, 0,~dum, 0,~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,    1, 1,    1,  1,  0,  1,  0,  1,   1,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 23} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~te, ~te, ~te, 0,~dum, ~te,~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,    1, 1,    1,  1,  0,  1,    1,  1,   1,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 24} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, 0,~dum, 0,~te, ~te, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,  0,  1,  0,  1,   1,   1,   1,   1,   1], inf);
		}
		{rhythmNum == 25} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~te, ~te, ~te, 0,~dum, 0,~te, ~te, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,    1, 1,    1,  1,  0,  1,  0,  1,   1,   1,   1,   1,   1], inf);
		}
		{rhythmNum == 26} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1);, inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, ~te,~dum, 0,~te, ~te, ~te, 0, ~te, 0], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,    1,  1,  0,  1,   1,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 27} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(14,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, ~te,~dum, 0,~te, ~te, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,    1,  1,  0,  1,   1,   1,   1,   1,   1], inf);
		}

		{rhythmNum == 28.1} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(16,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, 0,~te, 0,~te, 0,~dum,0,~te, 0,~te, 0, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,  0,  1, 0, 1,  0, 1, 0, 1,  0,  1,  0,  1,   1], inf);
		}
		{rhythmNum == 28.2} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(8,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~te,~te, ~dum, 0,~te, 0], inf);
			~ampPPatt.source = Pseq([     1,    1,  1, 1,    1,  0,  1,  0], inf);
		}
		//  FILL THE GAP

		{rhythmNum == 31} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(9,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~dum, 0,~te, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,   1,  0,  1,  1,   1], inf);
		}
		{rhythmNum == 32.1} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(7,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,    1,  1,  1,   1], inf);
		}
		{rhythmNum == 32.2} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(7,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, 0, ~dum, 0, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,  0,   1,  0,   1], inf);
		}
		{rhythmNum == 32.3} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(7,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, 0, ~dum, 0, ~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,  0,   1,  0,   1], inf);
		}
		//  FILL THE GAP
		{rhythmNum == 35} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq(Array.fill(6,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te,~te,~te,~te], inf);
			~ampPPatt.source = Pseq([     1,  0, 1,  1,  1,  1], inf);
		}
		//  FILL THE GAP

		{rhythmNum == 39.1} {
			~bpm = ~ratio[6]*bpm;
			~durPPatt.source = Pseq(Array.fill(12,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te, 0, ~dum, 0, ~te, 0,~te, 0 ] , inf);
			~ampPPatt.source = Pseq([      1, 0,  1,  1,    1,  0,  1,  0,   1, 0, 1,  1], inf);
		}

		{rhythmNum == 40} {
			~bpm = ~ratio[7]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~dum, 0, ~te, 0, ~te, 0 ] , inf);
			~ampPPatt.source = Pseq([      1, 0,  1,  1,    1,  0,  1,  0,   1, 0], inf);
		}
		{rhythmNum == 41.1} {
			~durPPatt.source = Pseq(Array.fill(5,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0,~te, ~te, ~te ] , inf);
			~ampPPatt.source = Pseq([      1, 0,  1,  1,    1], inf);
		}
		{rhythmNum == 41.2} {
			~durPPatt.source = Pseq(Array.fill(5,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~te, ~te, ~te ] , inf);
			~ampPPatt.source = Pseq([      1,  1,  1,  1,    1], inf);
		}

		{rhythmNum == 44} {
			~bpm = ~ratio[11]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum, 0,~te,  ~te, ~dum,  0, ~te, ~te, ~dum, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,  0,  1,   1,   1,   0,    1,  1,   1,  0,   1, 0,   1, 0], inf);
		}

		{rhythmNum == 45} {
			~bpm = ~ratio[11]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~dum, 0,~te,  0, ~dum,  0, ~te, ~te, ~dum, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,  0,  1,  0,   1,   0,    1,  1,   1,  0,   1, 0,   1, 0], inf);
		}
		{rhythmNum == 46} {
			~bpm = ~ratio[11]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, 0, ~te, ~dum,  0, ~te, 0, ~dum, ~te, 0, ~te, ~dum, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    1,   0,   1,   1,   0,   1, 0,   1,    1, 0,   1, 1,    0, 1,   0,   1, 0], inf);
		}

		{rhythmNum == 47} {
			~bpm = ~ratio[11]*bpm;
			~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, 0, ~te, 0,~dum,  0, ~te,  ~te, ~dum, 0, ~te, 0, ~dum, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,   1, 0,   1,  0,   1,    1,    1, 0,   1, 0,    1, 0,   1,   1], inf);
		}
		{rhythmNum == 47.1} { //Zeybek_Dance
			~bpm = ~ratio[11]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			//~durPPatt.source = Pseq([1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, 0, ~dum, 0,~te,  0, ~dum,  0, 0, 0, ~te, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,   1, 0,   1,  0,   1,   0, 0, 0,   1, 0,    1, 0,   1,   0 ], inf);
		}

		//TODO
		{rhythmNum == 52} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0,~te,  0, ~dum,  0, ~te, ~te, ~te, 0, ~te, 0 ,~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,  0,   1,   0,   1,   1,   1, 0,   1, 0,   1,  0], inf);
		}
		{rhythmNum == 53} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0,~te,  0, ~dum, ~te, ~te, ~te, ~te, 0, ~te, 0 ,~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,  0,   1,    1,   1,   1,   1, 0,   1, 0,   1,  0], inf);
		}
		{rhythmNum == 54} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~te, ~te, 0,~te,  0, ~dum,  0, ~te, ~te, ~te, 0, ~te, 0 ,~te, 0], inf);
			~ampPPatt.source =    Pseq([1,      1,   1,   1,   1, 0,  1,  0,   1,   0,   1,   1,   1, 0,   1, 0,   1,  0], inf);
		}
		{rhythmNum == 55} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~te, ~te, 0,~te,  0, ~dum,  ~te, ~te, ~te, ~te, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,      1,   1,   1,   1, 0,  1,  0,   1,     1,   1,   1,   1, 0,   1, 0,   1,  0], inf);
		}
		{rhythmNum == 56} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0,~te,  0, ~dum,  0, ~te, ~te, ~te, 0, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,  0,   1,   0,   1,   1,   1, 0,  1,    1,  1,    1], inf);
		}
		{rhythmNum == 57} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~te, ~te, 0,~te,  0, ~dum,  ~te, ~te, ~te, ~te, 0, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,      1,   1,   1,   1, 0,  1,  0,   1,     1,   1,   1,   1, 0,   1,   1,  1,  1], inf);
		}

		{rhythmNum == 58.1} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, 0, ~dum, 0,~te,  0, ~te,  0, ~dum, 0, ~te, ~te,  ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,    1, 0,  1,  0,   1,  0,    1, 0,  1,   1,     1, 0,  1,  0], inf);
		}
		{rhythmNum == 58.2} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~bass_tambourine, 0, ~te, ~te, ~te, 0,~te,  0, ~te,  0, ~dum, 0, ~te, ~te,  ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,  0,   1,  0,    1, 0,  1,   1,     1, 0,  1,  0], inf);
		}
		{rhythmNum == 58.3} { // 4 matia 2 kardies NikoKonstas
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~bass_tambourine, 0, ~te, 0, ~dum, 0,~te,  0, ~te,  0, ~dum, 0, ~te, 0,  ~dum, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,    1, 0,  1,  0,   1,  0,    1, 0,  1,  0,    1, 0,  1,  0], inf);
		}
		{rhythmNum == 58.4} { // 4 matia 2 kardies NikoKonstas
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~tambourine, 0, ~te, 0, ~dum, 0,~te,  0, ~te,  0, ~dum, 0, ~te, 0,  ~dum, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,    1, 0,  1,  0,   1,  0,    1, 0,  1,  0,    1, 0,  1,  0], inf);
		}


		{rhythmNum == 59.1} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0,~te, ~te, ~dum,  0, ~te, ~te, ~dum, 0, ~te, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,   1,    1,  0,  1,   1,     1, 0,  1,  0,   1, 0], inf);
		}
		{rhythmNum == 59.2} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(18,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0,~te, ~te, ~dum,  0, ~te, ~te, ~dum, 0, ~te, ~te, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1, 0,  1,   1,    1,  0,  1,   1,     1, 0,  1,    1,   1,   1], inf);
		}

		// TODO
		// DONE
		{rhythmNum == 63} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, 0, ~te, ~dum, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([1,    1,   0, 1,   1,    0, 1,   0], inf);
		}
		{rhythmNum == 64} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, 0, ~te, ~te, ~te, 0, ~dum, 0, ~te, ~te, ~te, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0, 1,   0, 1,   1,   1,   0,    1, 0,   1,   1,   1, 0,   1,   1], inf);
		}
		{rhythmNum == 65} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, 0, ~te, ~te, ~te, 0, ~dum, ~te, ~te, ~te, ~te, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1, 0,   1,   1,   1, 0,    1,   1,   1,   1,   1, 0,   1,   1], inf);
		}
		{rhythmNum == 66} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, ~te, ~te, 0, ~dum,   0, ~te, ~te,   ~te, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,   1,   1,   1,   1, 0,    1,   0,   1,   1,     1, 0,   1,   1], inf);
		}
		{rhythmNum == 70} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~dum, 0, ~te, ~te, ~te, 0, ~dum,   0, ~te, ~te,   ~te, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,  0,   1,   1,   1, 0,    1,   0,   1,   1,     1, 0,   1,   1], inf);
		}
		{rhythmNum == 73} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(16,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te,~te, ~te, 0, ~te,   0, ~dum, 0, ~te, 0, ~dum, 0, ~te, 0], inf);
			~ampPPatt.source =    Pseq([ 1,   0,   1,  1,   1, 0,   1,   0,   1,  0,  1,  0,   1,  0,   1,  0], inf);
		}

		// Gankino Horo Rhythm.play(500,100);
		{rhythmNum == 500} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq(Array.fill(13,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~dum, 0, ~dum, 0, ~dum, ~te, ~te,  ~dum,   0, ~dum, 0], inf);
			~ampPPatt.source =    Pseq([1,    0,   1,  0,   1,  0,   1,   1,    1,     1,   0,    1, 0], inf);
		}
		// Curcuna
		{rhythmNum == 501} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq(Array.fill(10,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te, ~te, ~dum, ~te, ~dum, ~te, ~dum, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([1,     1,   1,    1,    1,   1,   1,  1,     1,   1], inf);
		}

		//Τριανταφυλλιας
		{rhythmNum == 173} {
			~bpm = ~ratio[12]*bpm;
			~durPPatt.source = Pseq(Array.fill(9,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, ~te,~dum, ~te, ~dum, ~te,  ~dum, ~te, ~te ], inf);
			~ampPPatt.source =    Pseq([ 1,    1,  1,     1,    1,   1,   1,    1,   1  ], inf);
		}
		// Aksak Semai
		{rhythmNum == 100} {
			~bpm = ~ratio[2]*bpm;
			~durPPatt.source = Pseq(Array.fill(20,1), inf)/~bpm;
			~bufnumPPatt.source = Pseq([~dum, 0, ~te, ~te, ~te, 0, ~te, 0, ~te, ~te, ~dum, ~te, ~dum, 0, ~te, 0, 0, 0, ~te, ~te], inf);
			~ampPPatt.source =    Pseq([   1, 0,   1,   1,   1, 0,   1, 0,   1,   1,    1,   1,    1, 0,   1, 0, 0, 0,   1,   1], inf);
		}


	}
}


/*
(
SynthDef(\percSynth, { | out, freq = 440, amp = 1, bufnum = 10, pan = 0, gate = 1, loop = 0 |
	Out.ar(0, Pan2.ar(SinOsc.ar(freq:freq), 0, amp));
}).store;
)

*/