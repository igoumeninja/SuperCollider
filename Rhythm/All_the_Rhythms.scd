
(
//Ρυθμός τριών χρόνων
~bpm = 80;
~ratio = ~bpm/60;
~ratio = ~ratio*2; //this is because we count 8a (half quarter)

Pbind(
    \instrument,     \dum,
	\bufnum,         Pseq([
		~dum, ~te, ~te
	], inf),
	\amp,            Pseq([1,0.5,0.5], inf),
	\dur,            Pseq([1,1,1]/~ratio, inf),
).play;
)

(
//Tsamiko
~bpm = 120;
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