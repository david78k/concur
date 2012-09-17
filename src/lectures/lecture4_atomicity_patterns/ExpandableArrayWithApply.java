package lectures.lecture4_atomicity_patterns;

public class ExpandableArrayWithApply extends ExpandableArray {

	public static void main(String[] args) {
		ExpandableArrayWithApply v = new ExpandableArrayWithApply(0);
		
		v.applyToAll(new Procedure() {
			
			@Override
			public void apply(Object command) {
				System.out.println(command);
			}
		});
	}
	
	public ExpandableArrayWithApply(int cap) {
		super(cap);
	}

	synchronized void applyToAll(Procedure p) {
		for (int i = 0; i < data.length; i++) {
			p.apply(data[i]);
		}
	}
}

interface Procedure {
	void apply(Object command);
}