/*
FFT study Aris Bezas 130112 Igoumeninja
~mySendFFT = SendFFT.new
~mySendFFT.start
*/

SendFFT {
	classvar default;
	var <synthFFTDef, <fftSynth;			// the listening Onsets process
	var <chan = 0;		// the channel that we detect Jack!!!
	var <rate = 0.04;
	var <intensity = 1;
	var <fftbuf;
	var loopa;

	*default {
		if (default.isNil) { default = this.new };  // create default
		^default;
	}
	*new { |chan|
		^super.new.init(chan);
	}
	init { |argChan|
		"-SendFFT".postln;
		chan = argChan;
		fftbuf = Buffer.alloc(Server.default, 1024);
	}

	start	{
		if (not(Server.default.serverRunning)) { Server.default.boot };
		Server.default.doWhenBooted {
			synthFFTDef = SynthDef(\fftData, { |buf = 0|
				FFT(fftbuf,in:SoundIn.ar(chan));
			}).add(Server.default);
			fftSynth = Synth(\fftData, [\buf, ~fftbuf.bufnum],Server.default);
			loopa = {
				var magnitudes, imaginary, real;
				loop {
					fftbuf.getn(0, fftbuf.numFrames,
						{ | buf |
							#real, imaginary = buf.clump(2).flop;
							magnitudes = Complex(Signal.newFrom(real), Signal.newFrom(imaginary)).magnitude;
							magnitudes = (1 + magnitudes).log10.clip(0, 1) * intensity;
							NetAddr("127.0.0.1", 12345).sendMsg("/fftData",*magnitudes);
					});
					rate.wait;
				};
			}.fork

		};

	}

	changeIntensity { |varIntensity|
		intensity = varIntensity;

	}
	changeRate { |varRate|
		rate = varRate;

	}

	stop	{
		fftSynth.free;
		loopa.stop;
	}
}

