// ~startAppTask.start
StartUpClass {
	*initClass {
		StartUp.add {
			if (not(Server.default.serverRunning)) { Server.default.boot };
			~screenWidth = 1900;
			~screenHeight = 1080;
			//- StarAppptask
         		  ~startAppTask = Task({
			     1.do({
                               "cd ~/Code/oF_apps/recover/drawingApp && make run".unixCmd;
				2.wait;
				"cd ~/Code/oF_apps/recover/controlApp && make run".unixCmd;
			    })
			  });
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
			~letterTask = Task({
				inf.do({
					NetAddr("127.0.0.1", 12345).sendMsg(
						"/writeString",
						["ς","ε","ρ","τ","υ","θ","ι","ο","π","α","σ","δ","φ","γ","η","ξ","κ","λ","ζ","χ","ψ","ω","β","ν","μ"].at(rrand(0,24).asInteger).asString,
						16*(rrand(0,200)).asInteger,
						18*(rrand(0,100)).asInteger,
						255.asInteger,255.asInteger,255.asInteger,255.asInteger);
					0.01.wait;
				})
			});
			////////////////////////////////////////////////////////////////////////////////////////////////
			~letterTask2 = Task({
				var xPos, yPos = 20, count = 0;
				inf.do({
					|i|
					xPos = 10*count;
					NetAddr("127.0.0.1", 12345).sendMsg(
						"/writeString",
						["ς","ε","ρ","τ","υ","θ","ι","ο","π","α","σ","δ","φ","γ","η","ξ","κ","λ","ζ","χ","ψ","ω","β","ν","μ", " ", " ", "ά","ί","ό","έ","ή"].at(rrand(0,24).asInteger).asString,
						xPos, yPos,
						255.asInteger,255.asInteger,255.asInteger,255.asInteger);
					if (xPos > ~screenWidth, {xPos = 0; count = 0; yPos = yPos +15} );
					count = count + 1;
					0.01.wait;
				})
			});
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			~imageTask = Task({
				inf.do({
					NetAddr("127.0.0.1", 9005).sendMsg(
						"/drawImage", rrand(0,50).asInteger,
						100*(rrand(0,20)).asInteger,
						100*(rrand(0,10)).asInteger,
						100.asInteger,
						100.asInteger);
					0.1.wait;
				})
			});
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			~startupTask = Task({
				~sendAmpFreq = SendAmpFreq.new;              0.4.wait;
				~sendOnsets = SendOnsets.new;                0.4.wait;
				~makeResponders = MakeResponders.new;        0.4.wait;
				~makeResponders.all;                         0.4.wait;
			}).start;
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			thisProcess.openUDPPort(46100);
			~scNetAddr = NetAddr.new("127.0.0.1", 46100);
			~respondersTask = Task({
				~fftTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~fftTask.stop; }, {~fftTask.start; });
					[msg, time, addr, recvPort].postln; }, '/startFFT', recvPort: 46100);
				0.04.wait;
				~ampFreqTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~sendAmpFreq.stop; }, {~sendAmpFreq.start; });
					[msg, time, addr, recvPort].postln; }, '/sendAmpFreq', recvPort: 46100);
				0.04.wait;
				~onsetTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~sendOnsets.stop; }, {~sendOnsets.start; });
					[msg, time, addr, recvPort].postln; }, '/sendOnsets', recvPort: 46100);
				~letterTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~letterTask.stop; }, {~letterTask.start; });
					[msg, time, addr, recvPort].postln; }, '/letterTask', recvPort: 46100);
				~letterTask2Resp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~letterTask2.stop; }, {~letterTask2.start; });
					[msg, time, addr, recvPort].postln; }, '/letterTask2', recvPort: 46100);
				~imageTaskResp = OSCFunc({
					arg msg, time, addr, recvPort;
					if (msg[1] == 0, { ~imageTask.stop; }, {~imageTask.start; });
					[msg, time, addr, recvPort].postln; }, '/imageTask', recvPort: 46100);
			}).start;
			~removeRespondersTask = Task({
				~fftTaskResp.free;
				~ampFreqTaskResp.free;
				~onsetTaskResp.free;
				~letterTaskResp.free;
				~letterTask2Resp.free;
				~imageTaskResp.free;
			});
		}
	}
}

