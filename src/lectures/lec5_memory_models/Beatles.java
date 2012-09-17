package lectures.lec5_memory_models;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.Immutable;

@Immutable
public final class Beatles {
	private final Set<String> beatles = new HashSet<String>();
	
	public Beatles() {
		beatles.add("Paul McCartney");
		beatles.add("John Lennon");
		beatles.add("George Harrison");
		beatles.add("Ringo Starr");
	}
	
	public boolean isBeatles(String name) {
		return beatles.contains(name);
	}
}
