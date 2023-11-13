package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj extends Graphe implements IGraphe {
	
	private Map<String, List<Arc>> ladj;
	private List<Arc> listarc;
	
	public GrapheLAdj(String s) {
		ladj = new HashMap<>();
		listarc = new ArrayList<>();
		peupler(s);
	}
	public GrapheLAdj() {
		ladj = new HashMap<>();
		listarc = new ArrayList<>();
	}
	@Override
	public List<String> getSommets() {
		List<String> str = new ArrayList<>(ladj.keySet());
		return str;
	}

	@Override
	public List<String> getSucc(String sommet) {
		ArrayList<String> a = new ArrayList<>();
		List<Arc> arc = new ArrayList<>();
		arc = ladj.get(sommet);
		for(int i = 0; i < arc.size(); ++i) {
			a.add(arc.get(i).getDest());
		}
		return a;
		
	}

	@Override
	public int getValuation(String src, String dest) {
		int val = -1;
		List<Arc> liste = new ArrayList<>();
		liste = ladj.get(src);
		for(int i = 0; i < liste.size();++i) {
			if(liste.get(i).getDest().equals(dest)) {
				val = liste.get(i).getVal();
			}
		}
		return val;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return ladj.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(ladj.containsKey(src)) {
			List<Arc> list = new ArrayList<>(ladj.get(src));
			for(int i = 0; i < list.size(); ++i) {
				if(list.get(i).getDest().equals(dest)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud))
			ladj.put(noeud, new ArrayList<>());
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (valeur < 0) {
			throw new IllegalArgumentException("Valuation negative.");
		}else if(contientArc(source,destination)) {
			throw new IllegalArgumentException("Arc existant");
		}else {
			if(!contientSommet(source))
				ajouterSommet(source);
			ladj.get(source).add(new Arc(source,destination,valeur));
		}
			//listarc.add(new Arc(source,destination,valeur));
			
				
	}

	@Override
	public void oterSommet(String noeud) {
		// TODO Auto-generated method stub
		if(contientSommet(noeud))
			ladj.remove(noeud);
	}

	@Override
	public void oterArc(String source, String destination) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		if(!contientArc(source,destination)) {
			throw new IllegalArgumentException("Arc inexistant");
		}else {
			if(ladj.containsKey(source)) {
				List<Arc> arc = new ArrayList<>();
				arc = ladj.get(source);
				for(int i = 0; i < arc.size(); ++i) {
					if(arc.get(i).getDest().equals(destination)) {
						arc.remove(i);
					}
				}
			}
		}
	}
	
	
}