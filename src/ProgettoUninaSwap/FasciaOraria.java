package ProgettoUninaSwap;

public enum FasciaOraria {
	ORE_8_10("8:00 - 10:00"),
	ORE_10_12("10:00 - 12:00"),
	ORE_12_14("12:00 - 14:00"),
	ORE_14_16("14:00 - 16:00"),
	ORE_16_18("16:00 - 18:00"),
	ORE_18_20("18:00 - 20:00"),
	ORE_20_22("20:00 - 22:00");
	
	private final String Fascia;
	
	FasciaOraria(String Fascia) {
		this.Fascia= Fascia;
	}
	
	public String getFascia() {
		return Fascia;
	}
	
	@Override
	public String toString() {
		return Fascia; 
	}
	
	public static FasciaOraria fromLabel(String label) {
		for (FasciaOraria f: values()) {
				if (f.toString().equals(label)) {
					return f;
				}
			}
			throw new IllegalArgumentException("Fascia oraria non valida: " + label);
			
	}
}


