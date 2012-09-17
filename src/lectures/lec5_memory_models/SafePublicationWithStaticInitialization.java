package lectures.lec5_memory_models;

public class SafePublicationWithStaticInitialization {
	public Holder holder;
	
	// safe publication with static initializer
	public static Holder staticHolder = new Holder(2007);
	
	// unsafe publication
	void initialize() {
		holder = new Holder(2007);
	}
}
