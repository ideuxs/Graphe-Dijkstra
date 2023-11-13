package graphe;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class GrapheLA implements IGraphe {
	private static List<Arc> arcs;
	
	/*public GrapheLA(String source, String dest, int val) {
		arcs = new ArrayList<>();
		arcs.add(new Arc(source,dest,val));
	}*/
	
	public GrapheLA(String str) {
		arcs = new ArrayList<>();
		peupler(str);
	}
	
	public GrapheLA() {
		arcs = new ArrayList<>();
	}
	
	@Override
	public List<String> getSommets() {
		List<String> s = new LinkedList<>();
		s.add(arcs.get(0).getSource());
		for(int i = 1; i < arcs.size(); ++i) {
			if(!arcs.get(i).getSource().equals(arcs.get(i-1).getSource())) {
				s.add(arcs.get(i).getSource());
			}
			else {
				continue;
			}
		}
		return s;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> s = new LinkedList<>();
		for(Arc a : arcs) {
			if(a.getSource().equals(sommet)) {
				if(a.getDest()!= ":")	
					s.add(a.getDest());
			}
		}
		return s;
	}

	@Override
	public int getValuation(String src, String dest) {
		for(Arc a  : arcs) {
			if(a.getSource().equals(src) && a.getDest().equals(dest)) {
				return a.getVal();
			}
		}
		return 0;
	}

	@Override
	public boolean contientSommet(String sommet) {
		for(Arc a : arcs) {
			if(a.getSource().equals(sommet)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public boolean contientArc(String src, String dest) {
		// TODO Auto-generated method stub
		for (Arc a: arcs) {
			if(a.getSource().equals(src)) {
				if(a.getDest().equals(dest))
					return true;
			}
		}
		return false;
	}

	private boolean sansSuccesseur(Arc a) {
		int cpt = 0;
		for(Arc arc : arcs) {
			if(a.getSource().equals(arc.getSource())) {
				cpt += 1;
			}
		}
		if(cpt >= 1) {
			return true;
		}
		return false;
	}
	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud))
			arcs.add(new Arc(noeud));
	}
	

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
		if(valeur < 0) {
			throw new IllegalArgumentException("Valuation negative.");
		}if(contientArc(source,destination)) {
			throw new IllegalArgumentException("Arc existant");
		}else {
			if(contientArc(source,destination)) {
				oterArc(source,destination);
			}
			arcs.add(new Arc(source,destination , valeur));
		}
		
		for(int i = 0; i < arcs.size();++i) {
			System.out.print(arcs.get(i).getSource() +"-->"+arcs.get(i).getDest()+"("+arcs.get(i).getVal()+")" + "\t");
		}
		System.out.println();
	}

	@Override
	public void oterSommet(String noeud) {
		// TODO Auto-generated method stub
		if(contientSommet(noeud)) {	
			for(Arc a : arcs) {
				if(a.getSource().equals(noeud)) {
					arcs.remove(a);
				}
			}
		}
	}

	private boolean naPasSucc(String str) {
		if(getSucc(str).size() >0) {
			return false;
		}
		return true;
	}
		
	@Override
	public void oterArc(String source, String destination) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		if(!contientArc(source,destination)) {
			throw new IllegalArgumentException("Arc inexistant");
		}else {
			for(Arc a : arcs) {
				if(a.getSource().equals(source)) {
					if(a.getDest().equals(destination)) {
						arcs.remove(a); 
					}
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		int size = arcs.size();
		Triage();
		for (Arc a : arcs) {
			boolean cpt = sansSuccesseur(a);
			if(arcs.lastIndexOf(a) != size-1) {
				if(cpt) {
					if(a.getDest()!= ":")
						s.append(a.getSource()+"-"+a.getDest()+"("+a.getVal()+"), ");
					else if(a.getDest() == ":" && naPasSucc(a.getSource())) {
						s.append(a.getSource()+a.getDest()+", ");
					}
				}
				else {
					if(a.getDest() == ":") {
						s.append(a.getSource()+a.getDest());
					}		
				}
			}else {
				System.out.println();
				if(cpt) {
					if(a.getDest()!= ":")
						s.append(a.getSource()+"-"+a.getDest()+"("+a.getVal()+")");
					if(a.getDest() == ":") {
						s.append(a.getSource()+a.getDest());
					}
				}else {
					s.append(a.getSource()+a.getDest());
				}
			}
		}
			
		return s.toString();
	}

	private void Triage() {
		// TODO Auto-generated method stub
		Arc.sortArcList(arcs);
	}
	
	
}