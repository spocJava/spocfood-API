package com.algaworks.algafood.core.configurations;

import com.algaworks.algafood.api.DTO.EnderecoDTO;
import com.algaworks.algafood.api.input_model.ItensPedidoInput;
import com.algaworks.algafood.domain.entitys.Endereco;
import com.algaworks.algafood.domain.entitys.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        var modelMapper = new ModelMapper();

        var toEstatoName = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        //-- o model mapper não vai settar o id vindo do input no id do obejto ItemPedido
        modelMapper.createTypeMap(ItensPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        toEstatoName.<String>addMapping(
                //--pega o nome do estado da cidade do endereço
                enderecoDomain -> enderecoDomain.getCidade().getEstado().getNome(),
                //--mapeia ou setta o nome do estadoDomain no estadoDTO
                (enderecoDTO, enderecoDomainValue) -> enderecoDTO.getCidade().setEstado(enderecoDomainValue)
                );

        return modelMapper;
    }
    
}
