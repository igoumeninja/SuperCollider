/*
Rhythm_IDE.create
https://www.flickr.com/photos/55063999@N03/5110498719/in/pool-1575422@N22/

Aris Bezas 150114

Volume control
a = Volume(s, 0, s.options.numOutputBusChannels);
a.volume_(-12);
a.volume_(-20);
*/

Rhythm_IDE {
	classvar w,but00,but01,but02,v,b1,b2,b3,a1,a2,a3,ww;
	*create	{
		w = Window.new("Παραδοσιακοί Ρυθμοί - Το Μουσικό Μποστάνι", Rect(100,100,800,630));

		a1 = StaticText(w, Rect(480, 2, 130, 40));
		a1.string = "Ταχύτητα Ρυθμού:";
		b1 = NumberBox(w, Rect(700, 12, 45, 20));
		b1.value = 100;
		b1.step = 1;
		b1.action = {~bpm=b1.value;Rhythm.play(~rhythmID,~bpm); };

		a2 = StaticText(w, Rect(480, 30, 200, 40));
		a2.string = "Ταυτότητα Ρυθμού:";
		b2 = NumberBox(w, Rect(700, 40, 45, 20));
		b2.value = 1;
		b2.step = 1;
		b2.action = {~rhythmID = b2.value;Rhythm.play(~rhythmID,b1.value); };

		but01 = Button(w, Rect(480,80,270,48)).states_([
			["Παίξε",Color.white,Color.black],
			["Σίγαση",Color.black,Color.white]
		]);
		but01.action = { if ( but01.value.asBoolean,
			{ ~rhythmID = b2.value;Rhythm.play(~rhythmID,b1.value); },
			{ Rhythm.mute; }
		)};

		but02 = Button(w, Rect(480,130,270,48))
        .states_([
            ["Start again", Color.black, Color.red]
        ]);
		but02.action = {
			Rhythm.reset;
		};


/*		but02 = Button(w, Rect(490,61,160,48)).states_([
			["Ντέφι",Color.white,Color.black],
			["Κονγκας",Color.black,Color.white]
		]);
		but02.action = { if ( but01.value.asBoolean,
			{ ~dum = ~bass_drum;~te  = ~gonga;~rhythmID = b2.value;Rhythm.play(~rhythmID,b1.value);},
			{ ~dum = ~ntefiDum; ~te  = ~ntefiTe;~rhythmID = b2.value;Rhythm.play(~rhythmID,b1.value); */


		v = ListView(w,Rect(10,10,460,600))
		.items_([
			"1. Χασάπικο\t\t\t[id:1]",
			"2. Συρτό στα τρία\t\t\t[id: 2]",
			"3. Πωγωνίσιο - Μπαγιό\t\t\t[id: 3~4]",
			"4. Χασαποσέρβικο\t\t\t[id: 5~6]",
			"5. Σούστα\t\t\t\t[id: 7]",
			"6. Μπάλλος\t\t\t\t[id: 8.1~8.2]",
			"7. Τσάμικο\t\t\t\t[id: 9.1~9.2]",
			"8. Τσάμικο Ηπείρου\t\t\t[id: 10.1~10.2]",
			"9. Ρυθμός 6 χρόνων\t\t\t[id: 11.1~11.2]",
			"10. Ζαγορίσιο\t\t\t[id: 12.1~12.3]",
			"11. Μπεράτι\t\t\t[id: 13]",
			"12. Συρτός\t\t\t\t[id: 15.1~15.3]",
			"13. Μπεράτι Θασσαλίας 7/8\t\t[id: 16~18]",
			"14. Συρτό Καλαματιανό 7/8\t\t[id: 19~27]",
			"15. Συρτο Καγκέλι 4/4\t\t\t[id: 28.1]",
			"16. Συρτός Συγκαθιστός 9/8 (31)",
			"17. Μαντηλάτος 7/8 (32.1~32.3)",
			"18. Ζωναράδικο 6/8 (35)",
			"19. Μακεδονίτικη Μπαιντούσκα 6/8(39.1)",
			"20. Κιόρογλου 5/8 (40)",
			"21. Μπαϊντούσκα Θράκης 5/8 (41.1~41.2)",
			"22. Καμηλιέρικο Ζευμπέκικο 9/4 (44)",
			"23. Παλιό Ζευμπέκικο 9/4 (45)",
			"24. Καινούργιο Ζευμπέκικο 9/4 (46)",
			"25. Απτάλικο Μονό - Διπλό 9/4 (47)",
			"26. Ζευμπέκικο (zeybek dance) 9/4 (47.1)",
			"27. Καρσιλαμάς 9/8 (52~57)",
			"28. Ανάποδος καρσιλαμάς 9/8 (58.1~58.2)",
			"29. Πεταχτός καρσιλαμάς 9/8 (59.1~59.2)",
			"30. Τσιφτετέλι (63~66)",
			"31. Αράβικο Τσιφτετέλι (70)",
			"32. Μπολερό (73)",
			"100. Askak Semai [id: 100]"
		])
		.action_({arg v;
			v.value.switch(
				0, { ~rhythmID = 1;    Rhythm.play(~rhythmID,b1.value); b2.value = 1;    but01.value=1;},
				1, { ~rhythmID = 2;    Rhythm.play(~rhythmID,b1.value); b2.value = 2;    but01.value=1;},
				2, { ~rhythmID = 3;    Rhythm.play(~rhythmID,b1.value); b2.value = 3;    but01.value=1;},
				3, { ~rhythmID = 5;    Rhythm.play(~rhythmID,b1.value); b2.value = 5;    but01.value=1;},
				4, { ~rhythmID = 7;    Rhythm.play(~rhythmID,b1.value); b2.value = 7;    but01.value=1;},
				5, { ~rhythmID = 8.1;  Rhythm.play(~rhythmID,b1.value); b2.value = 8.1;  but01.value=1;},
				6, { ~rhythmID = 9.1;  Rhythm.play(~rhythmID,b1.value); b2.value = 9.1;  but01.value=1;},
				7, { ~rhythmID = 10.1; Rhythm.play(~rhythmID,b1.value); b2.value = 10.1; but01.value=1;},
				8, { ~rhythmID = 11.1; Rhythm.play(~rhythmID,b1.value); b2.value = 11.1; but01.value=1;},
				9, { ~rhythmID = 12.1; Rhythm.play(~rhythmID,b1.value); b2.value = 12.1; but01.value=1;},
				10, {~rhythmID = 13;   Rhythm.play(~rhythmID,b1.value); b2.value = 13;   but01.value=1;},
				11, {~rhythmID = 15.1; Rhythm.play(~rhythmID,b1.value); b2.value = 15.1; but01.value=1;},
				12, {~rhythmID = 16;   Rhythm.play(~rhythmID,b1.value); b2.value = 16;   but01.value=1;},
				13, {~rhythmID = 19;   Rhythm.play(~rhythmID,b1.value); b2.value = 19;   but01.value=1;},
				14, {~rhythmID = 28.1; Rhythm.play(~rhythmID,b1.value); b2.value = 28.1; but01.value=1;},
				15, {~rhythmID = 31;   Rhythm.play(~rhythmID,b1.value); b2.value = 31;   but01.value=1;},
				16, {~rhythmID = 32.2; Rhythm.play(~rhythmID,b1.value); b2.value = 32.2; but01.value=1;},
				17, {~rhythmID = 35;   Rhythm.play(~rhythmID,b1.value); b2.value = 35;   but01.value=1;},
				18, {~rhythmID = 39.1; Rhythm.play(~rhythmID,b1.value); b2.value = 39.1; but01.value=1;},
				19, {~rhythmID = 40;   Rhythm.play(~rhythmID,b1.value); b2.value = 40;   but01.value=1;},
				20, {~rhythmID = 41.1; Rhythm.play(~rhythmID,b1.value); b2.value = 41.1; but01.value=1;},
				21, {~rhythmID = 44;   Rhythm.play(~rhythmID,b1.value); b2.value = 44;   but01.value=1;},
				22, {~rhythmID = 45;   Rhythm.play(~rhythmID,b1.value); b2.value = 45;   but01.value=1;},
				23, {~rhythmID = 46;   Rhythm.play(~rhythmID,b1.value); b2.value = 46;   but01.value=1;},
				24, {~rhythmID = 47;   Rhythm.play(~rhythmID,b1.value); b2.value = 47;   but01.value=1;},
				25, {~rhythmID = 47.1; Rhythm.play(~rhythmID,b1.value); b2.value = 47.1; but01.value=1;},
				26, {~rhythmID = 52;   Rhythm.play(~rhythmID,b1.value); b2.value = 52;   but01.value=1;},
				27, {~rhythmID = 58.1; Rhythm.play(~rhythmID,b1.value); b2.value = 58.1; but01.value=1;},
				28, {~rhythmID = 59.1; Rhythm.play(~rhythmID,b1.value); b2.value = 59.1; but01.value=1;},
				29, {~rhythmID = 63;   Rhythm.play(~rhythmID,b1.value); b2.value = 63;   but01.value=1;},
				30, {~rhythmID = 70;   Rhythm.play(~rhythmID,b1.value); b2.value = 70;   but01.value=1;},
				31, {~rhythmID = 73;   Rhythm.play(~rhythmID,b1.value); b2.value = 73;  but01.value=1;},
				32, {~rhythmID = 100;  Rhythm.play(~rhythmID,b1.value); b2.value = 100;  but01.value=1;}
			)
		});
		w.front;
	}
}
