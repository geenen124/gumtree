package fr.labri.gumtree.test;

import fr.labri.gumtree.matchers.MappingStore;
import fr.labri.gumtree.matchers.Matcher;
import fr.labri.gumtree.matchers.optimal.zs.ZsMatcher;
import fr.labri.gumtree.tree.ITree;
import fr.labri.gumtree.tree.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestZsMatcher {
	
	@Test
	public void testWithCustomExample() {
		Pair<ITree, ITree> trees = TreeLoader.getZsCustomPair();
		ITree src = trees.getFirst();
		ITree dst = trees.getSecond();
		Matcher matcher = new ZsMatcher(src, dst, new MappingStore());
		matcher.match();
		assertEquals(5, matcher.getMappingSet().size());
		assertTrue(matcher.getMappings().has(src, dst.getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(0), dst.getChild(0).getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(1), dst.getChild(0).getChild(1)));
		assertTrue(matcher.getMappings().has(src.getChild(1).getChild(0), dst.getChild(0).getChild(1).getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(1).getChild(2), dst.getChild(0).getChild(1).getChild(2)));
	}

	@Test
	public void testWithSlideExample() {
		Pair<ITree, ITree> trees = TreeLoader.getZsSlidePair();
		ITree src = trees.getFirst();
		ITree dst = trees.getSecond();
		Matcher matcher = new ZsMatcher(src, dst, new MappingStore());
		matcher.match();
		assertEquals(5, matcher.getMappingSet().size());
		assertTrue(matcher.getMappings().has(src, dst));
		assertTrue(matcher.getMappings().has(src.getChild(0).getChild(0), dst.getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(0).getChild(0).getChild(0), dst.getChild(0).getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(0).getChild(1), dst.getChild(1).getChild(0)));
		assertTrue(matcher.getMappings().has(src.getChild(0).getChild(2), dst.getChild(2)));
	}
	
}
