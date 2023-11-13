package graphe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrapheMAdj implements IGraphe{
	private int[][] matrice;
	private Map<String, Integer> indices;
	
	public GrapheMAdj() {
		indices = new HashMap<>();
		matrice = new int[1][1];
		for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
            	matrice[i][j] = -1;
            }
        }
	}
	
	public GrapheMAdj (String graph) {
		this();
		peupler(graph);
	}

	@Override
	public List<String> getSommets() {
		List<String> s = new LinkedList<>();
		Set<String> cles = indices.keySet();
		for (String cle : cles) {
		    s.add(cle);
		}		
		return s;
	}


	@Override
	public List<String> getSucc(String sommet) {
		List<String> s = new LinkedList<>();
		int indice = this.indices.get(sommet);
		for (Map.Entry<String, Integer> entry : indices.entrySet()) {
			if(this.matrice[indice][entry.getValue()] >=0) {
				s.add(entry.getKey());			
			}		
		}
		return s;
	}

	@Override
	public int getValuation(String src, String dest) {
		int indice = this.indices.get(src);
		int indice2= this.indices.get(dest);
		return this.matrice[indice][indice2];
	}

	@Override
	public boolean contientSommet(String sommet) {	
		return indices.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		int indice = this.indices.get(src);
		int indice2 = this.indices.get(dest);
		return this.matrice[indice][indice2] >= 0;
	}

	@Override
    public void ajouterSommet(String noeud) {
        if (indices.containsKey(noeud) == false) {
	        indices.put(noeud, indices.size());
	        int n = matrice.length + 1;
	        int[][] newMatrice = new int[n][n];
	
	        for (int i = 0; i < matrice.length; i++) {
	            for (int j = 0; j < matrice[i].length; j++) {
	                newMatrice[i][j] = matrice[i][j];
	            }
	        }
	        for (int i = matrice.length; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                newMatrice[i][j] = -1;
	                newMatrice[j][i] = -1;
	            }
	        }
	        matrice = newMatrice;
        }
    }

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
        if(indices.get(source) == null) {
            ajouterSommet(source);
        }
        int a = indices.get(source);
        if(indices.get(destination) == null) {
            ajouterSommet(destination);
        }
        int b = indices.get(destination);
        if (contientArc(source, destination) == true) {
        	throw new IllegalArgumentException("Arc déja existant");
        }
        if (valeur < 0) {
        	throw new IllegalArgumentException("Valeur inférieure a 0");
        }
        if (contientArc(source, destination) == false) {
        	matrice[a][b] = valeur;
        }
    }

	@Override
	public void oterSommet(String noeud) {
		if (contientSommet(noeud)) {
			indices.remove(noeud);
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		if (contientSommet(source) == false) {
			throw new IllegalArgumentException("Arc non existant");
		}	
		int indice = this.indices.get(source);
		int indice2= this.indices.get(destination);
		if (contientArc(source, destination) == false) {
			throw new IllegalArgumentException("Arc non exitant");
		}
		for (int i = 0; i < matrice.length; ++i) {
			for (int j = 0; j < matrice[i].length; j++) {
				if (matrice[i][j] == matrice[indice][indice2]) {
					matrice[i][j] = -1;
				}
			}
		}
	}
	
    public String toString() {
    	return toAString();
    }
        
}