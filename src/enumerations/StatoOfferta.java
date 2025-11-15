package enumerations;

public enum StatoOfferta {
	In_Attesa("In Attesa"),
	Accettata("Accettata"),
	Rifiutata("Rifiutata");

	private final String Stato;
	
	StatoOfferta(String Stato) {
		this.Stato = Stato;
	}
	
	public String getStato() {
		return Stato;
	}
	
	@Override
	public String toString() {
		return Stato; 
	}
	
	public static StatoOfferta fromLabel(String label) {
		for (StatoOfferta so: values()) {
				if (so.toString().equals(label)) {
					return so;
				}
			}
			throw new IllegalArgumentException("Stato Offerta non valido " + label);
			
	}

}
