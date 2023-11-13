package graphe;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

	   
		public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
		        Set<String> nonVisites = new HashSet<>(graphe.getSommets());
		        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
		        HashMap<String, Map.Entry<String, Integer>> pqEntries = new HashMap<>();
		        dist.put(source, 0);
		        Map.Entry<String, Integer> sourceEntry = new AbstractMap.SimpleEntry<>(source, 0);
		        pq.add(sourceEntry);
		        pqEntries.put(source, sourceEntry);

		        while (!nonVisites.isEmpty() && !pq.isEmpty()) {
		            Map.Entry<String, Integer> uEntry = pq.poll();
		            String u = uEntry.getKey();
		            nonVisites.remove(u);

		            for (String v : graphe.getSucc(u)) {
		                if (nonVisites.contains(v)) {
		                    int poids = graphe.getValuation(u, v);
		                    if (poids == -1) {
		                        continue;
		                    }
		                    int alt = dist.get(u) + poids;
		                    if (alt < dist.getOrDefault(v, Integer.MAX_VALUE)) {
		                        dist.put(v, alt);
		                        pred.put(v, u);

		                        Map.Entry<String, Integer> vEntry = pqEntries.get(v);
		                        if (vEntry != null) {
		                            pq.remove(vEntry);
		                        }

		                        vEntry = new AbstractMap.SimpleEntry<>(v, alt);
		                        pq.add(vEntry);
		                        pqEntries.put(v, vEntry);
		                    }
		                }
		            }
		        }

		        // Pour tous les sommets non visités, indiquer qu'aucun chemin n'existe entre la source et ces sommets
		        for (String sommet : nonVisites) {
		            dist.put(sommet, -1);
		        }
		   
	}

	

}
