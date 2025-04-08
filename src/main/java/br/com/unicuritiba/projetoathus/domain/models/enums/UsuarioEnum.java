package br.com.unicuritiba.projetoathus.domain.models.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum UsuarioEnum   {
    ADMIN(1, "ROLE_ADMIN"),
    TECHNICIAN(2, "ROLE_TECHNICIAN"),
    USER(3, "ROLE_USER");

    private Integer codigo;
    private String descricao;

    public static UsuarioEnum toEnum(Integer codigo) {
        if(Objects.isNull(codigo)){
            return null;
        }

        for (UsuarioEnum x : UsuarioEnum.values()) {
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new RuntimeException("Código inválido /s" + codigo);
    }
}
