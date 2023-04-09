package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoutiqueMapperTest {

    private BoutiqueMapper boutiqueMapper;

    @BeforeEach
    public void setUp() {
        boutiqueMapper = new BoutiqueMapperImpl();
    }
}
