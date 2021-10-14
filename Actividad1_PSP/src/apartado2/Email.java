package apartado2;

public class Email{

		public String id;
		public String destinatario;
		public String remitente;
		public String asunto;
		public String cuerpoMensaje;

		
		//Genero los getter and setter
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}


		public String getDestinatario() {
			return destinatario;
		}

		public void setDestinatario(String destinatario) {
			this.destinatario = destinatario;
		}


		public String getRemitente() {
			return remitente;
		}

		public void setRemitente(String remitente) {
			this.remitente = remitente;
		}


		public String getAsunto() {
			return asunto;
		}

		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}


		public String getCuerpoMensaje() {
			return cuerpoMensaje;
		}

		public void setCuerpoMensaje(String cuerpoMensaje) {
			this.cuerpoMensaje = cuerpoMensaje;
		}
		
		
		@Override 
		public String toString() {
			return "Email [id=" + id + ", destinatario=" + destinatario + ", remitente=" + remitente + ", asunto="
					+ asunto + ", cuerpoMensaje=" + cuerpoMensaje + "]";
		}

		
}


