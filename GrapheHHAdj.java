package graphe;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GrapheHHAdj implements IGraphe {
	private Map<String, Map<String, Integer>> hhadj;
	
	public GrapheHHAdj() {
		hhadj = new HashMap<>();
	}
	
	public GrapheHHAdj(String graph) {
		this();
		peupler(graph);
	}

	@Override
	public List<String> getSommets() {
		List<String> s = new LinkedList<>();
		Set <String> cles = hhadj.keySet();
		for (String cle : cles) {
			s.add(cle);
		}
		return s;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> s = new LinkedList<>();
		Map<String, Integer> innerMap = hhadj.get(sommet);
		Set <String> cles = innerMap.keySet();
		for (String cle : cles) {
			s.add(cle);
		}
		return s;
	}

	@Override
	public int getValuation(String src, String dest) {
		Map<String, Integer> innerMap = hhadj.get(src);
		Integer value = innerMap.get(dest); 
		return value;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return hhadj.containsKey(sommet);
		
	}

	@Override
	public boolean contientArc(String src, String dest) {
		return hhadj.get(src).containsKey(dest);
	}

	@Override
	public void ajouterSommet(String noeud) {
		if (hhadj.containsKey(noeud) == false) {
			hhadj.put(noeud, new HashMap<>());
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		ajouterSommet(source);
		ajouterSommet(destination);
		if (hhadj.get(source).containsKey(destination) == true) {
			throw new IllegalArgumentException("Arc déja existant");
		}
		if (valeur < 0) {
			throw new IllegalArgumentException("Valeur Inférieure à 0");
		}
		hhadj.get(source).put(destination, valeur);
	}
	

	@Override
	public void oterSommet(String noeud) {
		if (contientSommet(noeud)) {
			hhadj.remove(noeud);
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		if (hhadj.containsKey(source) == false) {
			throw new IllegalArgumentException("Arc non existant");
		}
		if (contientArc(source, destination) == false) {
			throw new IllegalArgumentException("Arc non existant");
		}
		if (contientArc(source, destination)) {
			hhadj.get(source).remove(destination);
		}
	}
	
	public String toString() {
		return toAString();
	}

}