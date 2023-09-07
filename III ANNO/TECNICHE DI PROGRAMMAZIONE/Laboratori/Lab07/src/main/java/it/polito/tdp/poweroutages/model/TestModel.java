package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Nerc> nerc = model.getNercList();
		List<PowerOutage> po = model.getPowerOutagebyNerc(9);
		System.out.println(model.cercaSottoinsieme(new Nerc(3,"MAAC"), 4, 200));
		
		// System.out.println(po.get(0).getDate_event_began().getYear()+1900);
		/* List<PowerOutage> po = model.getPowerOutagebyNerc(9);
		for(PowerOutage p : po) {
			System.out.println(p.getPO_id()+" "+p.getNerc_id()+" "+p.getDate_event_began()+" "+p.getDate_event_finished());
			System.out.println((p.getDate_event_finished().getTime()-p.getDate_event_began().getTime())*10e-4/3600);
		} */
	}

}
