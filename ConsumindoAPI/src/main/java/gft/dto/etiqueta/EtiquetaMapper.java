package gft.dto.etiqueta;

import gft.entities.Etiqueta;

public class EtiquetaMapper {

	public static Etiqueta fromDTO(RegistroEtiquetaDTO dto) {
		return new Etiqueta(null, dto.getNome());
	}

	public static ConsultaEtiquetaDTO fromEntity(Etiqueta etiqueta) {
		return new ConsultaEtiquetaDTO(etiqueta.getId(), etiqueta.getNome(), etiqueta.getUsuarios());
	}
}
