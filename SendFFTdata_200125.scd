

~fftTask = Task({
	~rate = 0.04;
	0.04.wait;
	~intensity = 1;
	0.04.wait;
	~fftbuf = Buffer.alloc(Server.default, 1024);
	0.04.wait;
	~synthdef = SynthDef(\fft, { | in = 8, buf = 0 |	FFT(buf, SoundIn.ar(0,0.05));}).add;
	0.04.wait;
	~fftSyth = Synth(\fft, [\in, 8, \buf, ~fftbuf.bufnum],Server.default);
	0.04.wait;
	inf.do({ arg  magnitudes, imaginary, real;
		~fftbuf.getn(0, ~fftbuf.numFrames,
		{ | buf |
				#real, imaginary = buf.clump(2).flop;
				magnitudes = Complex(Signal.newFrom(real), Signal.newFrom(imaginary)).magnitude;
				//magnitudes.postln;
				magnitudes = (1 + magnitudes).log10.clip(0, 1) * ~intensity;
				NetAddr("127.0.0.1", 12345).sendMsg("/fftData", *magnitudes)
				//OF.mlab('fftData', *magnitudes);

				//dependants do: _.update(index, buf, magnitudes);
				//index = index + 1;
		});
		~rate.wait;
	});
})

~fftTask.start;
~fftTask.stop;
