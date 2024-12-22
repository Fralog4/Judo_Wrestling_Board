package com.judo_wrestling_board.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum JudoAmmonitions {
    @Enumerated(EnumType.STRING)
    SHIDO,
    @Enumerated(EnumType.STRING)
    HANSOKU_MAKE
}
