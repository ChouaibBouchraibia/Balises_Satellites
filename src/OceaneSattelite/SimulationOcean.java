package OceaneSattelite;

import OceaneSattelite.Componenet.Balise;
import OceaneSattelite.Componenet.Satellite;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationOcean {
    private NiSpace space;
    private NiRectangle container;
    private NiRectangle sky;
    private NiRectangle ocean;
    private List<Satellite> satellites;
    private List<Balise> balises;

    public SimulationOcean() {
        initializeSpace();
        satellites = new ArrayList<>();
        balises = new ArrayList<>();
    }

    private void initializeSpace() {
        space = new NiSpace("Simulation Océanique", new Dimension(1000, 700));

        container = new NiRectangle();
        container.setBackground(Color.white);
        container.setSize(new Dimension(900, 600));
        container.setLocation(50, 50);

        sky = new NiRectangle();
        sky.setBackground(Color.white);
        sky.setLocation(new Point(0, 0));
        sky.setSize(new Dimension(900, 300));
        container.add(sky);

        ocean = new NiRectangle();
        ocean.setBackground(Color.blue);
        ocean.setLocation(new Point(0, 300));
        ocean.setSize(new Dimension(900, 300));
        container.add(ocean);

        space.add(container);
        space.openInWindow();
    }
    
    public List<Balise> lesBalises() {
    	return balises;
    }
    
    public void ajouterBalise(Balise balise) {
        balises.add(balise);
        ocean.add(balise);
    }
    
    public boolean retirerBalise(Balise balise) {
    	if (balises.remove(balise)) {
        	satellites.forEach(s -> s.supprimerObservateur(balise));
    		ocean.remove(balise);
    		return true;
    	}
    	return false;
    }
    
    public Balise retirerBalise(int index) {
    	if (index < 0 || index >= balises.size()) {
    		return null;
    	}
    	Balise balise = balises.remove(index);
    	satellites.forEach(s -> s.supprimerObservateur(balise));
    	ocean.remove(balise);
    	return balise;
    }
    
    public List<Satellite> lesSatellites() {
    	return satellites;
    }
    
    public void ajouterSatellite(Satellite satellite) {
        satellites.add(satellite);
        sky.add(satellite);
    }
    
    public boolean retirerSatellite(Satellite satellite) {
    	if (satellites.remove(satellite)) {
    		sky.remove(satellite);
    		return true;
    	}
    	return false;
    }
    
    public Satellite retirerSatellite(int index) {
    	if (index < 0 || index >= satellites.size()) {
    		return null;
    	}
    	Satellite satellite = satellites.remove(index);
    	sky.remove(satellite);
    	return satellite;
    }

    public NiRectangle getOcean() {
        return ocean;
    }

    public NiRectangle getSky() {
        return sky;
    }
}