package graphe;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IGrapheTest {
	// graphe de l'exercice 3.1 du poly de maths
	// avec en plus un noeud isole : J
	private String g31 = 
			  "A-C(2), A-D(1), "
			+ "B-G(3), "
			+ "C-H(2), "
			+ "D-B(3), D-C(5), D-E(3), "
			+ "E-C(1), E-G(3), E-H(7), "
			+ "F:, "
			+ "G-B(2), G-F(1), "
			+ "H-F(4), H-G(2), "
			+ "I-H(10), "
			+ "J:";
	
	@Test
	public void exo3_1Maths() {
		GrapheLA gla = new GrapheLA(g31);
		System.out.println(gla.toString());
		tester3_1(gla);
	}
	
	@Test
	public void importer() throws NumberFormatException, FileNotFoundException {
		System.out.println("SAE graphes");
		IGraphe g = new GrapheLA();
		Arc a = GraphImporter.importer("graphes/ac/g-10-1.txt", g);
		
		assertEquals(g.toString(), "1-3(5), "
				+ "10-3(3), 2-1(5), 2-3(5), 2-5(4), "
				+ "3-4(4), 3-5(4), 4-10(1), 4-2(1), 4-7(3), "
				+ "5-9(4), 6-2(3), 6-3(4), 7-3(2),"
				+ " 8-2(4), 8-6(1), 9-2(4)");
		assertEquals("5", a.getSource());
		assertEquals("7", a.getDest());
		
	}
	
	
	void tester3_1(IGraphe g) {
		List<String> sommets = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
		List<Arc> a = new ArrayList<>();
		assertThrows(IllegalArgumentException.class,() -> g.ajouterArc("A", "B", -1)); // valuation negative		assertEquals(sommets, g.getSommets());
		assertTrue(g.contientSommet("C"));
		assertFalse(g.contientSommet("c"));
		assertTrue(g.contientArc("C","H"));
		assertFalse(g.contientArc("H","C"));
		assertEquals(7,g.getValuation("E", "H"));
		assertEquals(Arrays.asList("B","C", "E"), g.getSucc("D"));
		assertEquals(g31, g.toString());
	}

}
