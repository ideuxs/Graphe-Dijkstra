package graphe;

import java.util.Comparator;
import java.util.List;

public class Arc {
	
	private String source;  //noeud source
	private String dest;	//noeud dest
	private int val;
	
	public Arc(String source, String dest, int val) {
		assert(val >=0);
		this.source = source;
		this.dest = dest;
		this.val = val;
	}
	
	public Arc(String sour) {	//Constructeur pour arc sans successeur
		this(sour, ":",0);
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getDest() {
		return this.dest;
	}
	public int getVal() {
		return this.val;
	}
	public static void sortBySource(List<Arc> list) {
        Comparator<Arc> compareBySource = Comparator.comparing(Arc::getSource);
        list.sort(compareBySource);
    }
	
	public static void sortArcList(List<Arc> arcList) {
        arcList.sort(Comparator.comparing(Arc::getSource)
                .thenComparing(Arc::getDest)
                .thenComparingInt(Arc::getVal));
    }

	
}
