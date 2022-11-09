package gft.dto.filial;

import gft.dto.endereco.EnderecoMapper;
import gft.entities.Filial;

public class FilialMapper {
	
	public static Filial fromDTO(RegistroFilialDTO dto) {
		return new Filial(null, dto.getNome(), EnderecoMapper.fromDTO(dto.getEndereco()));
	}
	
	public static ConsultaFilialDTO fromEntity(Filial filial) {
		return new ConsultaFilialDTO(filial.getId(), filial.getNome(), EnderecoMapper.fromEntity(filial.getEndereco()));
	}
}
