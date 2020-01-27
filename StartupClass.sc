StartUpClass {
	*initClass {
		StartUp.add {
			if (not(Server.default.serverRunning)) { Server.default.boot };
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
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
							magnitudes = (1 + magnitudes).log10.clip(0, 1) * ~intensity;
							NetAddr("127.0.0.1", 12345).sendMsg("/fftData", *magnitudes)
					});
					~rate.wait;
				});
			});
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			~startupTask = Task({
				~sendAmpFreq = SendAmpFreq.new;              0.4.wait;
				//~sendAmpFreq.start;                          0.4.wait;
				~sendOnsets = SendOnsets.new;                0.4.wait;
				//~sendOnsets.start;                           0.4.wait;
				//~fftTask.start;
				~makeResponders = MakeResponders.new;        0.4.wait;
				~makeResponders.all;                         0.4.wait;
			}).start;
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			~respondersTask = Task({
				~fftTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~fftTask.stop; }, {~fftTask.start; });
					[msg, time, addr, recvPort].postln; }, '/startFFT');
				0.04.wait;
				~ampFreqTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~sendAmpFreq.stop; }, {~sendAmpFreq.start; });
					[msg, time, addr, recvPort].postln; }, '/sendAmpFreq');
				0.04.wait;
				~onsetTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~sendOnsets.stop; }, {~sendOnsets.start; });
					[msg, time, addr, recvPort].postln; }, '/sendOnsets');
			}).start;
			~removeRespondersTask = Task({
				~fftTaskResp.free;
				~onsetTaskResp.free;
			});
			//~removeRespondersTask.start;
			OSCFunc.trace(true);
		}
	}
}

