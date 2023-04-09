package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Boutique;
import com.mycompany.myapp.repository.BoutiqueRepository;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.service.mapper.BoutiqueMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Boutique}.
 */
@Service
@Transactional
public class BoutiqueService {

    private final Logger log = LoggerFactory.getLogger(BoutiqueService.class);

    private final BoutiqueRepository boutiqueRepository;

    private final BoutiqueMapper boutiqueMapper;

    public BoutiqueService(BoutiqueRepository boutiqueRepository, BoutiqueMapper boutiqueMapper) {
        this.boutiqueRepository = boutiqueRepository;
        this.boutiqueMapper = boutiqueMapper;
    }

    /**
     * Save a boutique.
     *
     * @param boutiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public BoutiqueDTO save(BoutiqueDTO boutiqueDTO) {
        log.debug("Request to save Boutique : {}", boutiqueDTO);
        Boutique boutique = boutiqueMapper.toEntity(boutiqueDTO);
        boutique = boutiqueRepository.save(boutique);
        return boutiqueMapper.toDto(boutique);
    }

    /**
     * Update a boutique.
     *
     * @param boutiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public BoutiqueDTO update(BoutiqueDTO boutiqueDTO) {
        log.debug("Request to update Boutique : {}", boutiqueDTO);
        Boutique boutique = boutiqueMapper.toEntity(boutiqueDTO);
        boutique = boutiqueRepository.save(boutique);
        return boutiqueMapper.toDto(boutique);
    }

    /**
     * Partially update a boutique.
     *
     * @param boutiqueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BoutiqueDTO> partialUpdate(BoutiqueDTO boutiqueDTO) {
        log.debug("Request to partially update Boutique : {}", boutiqueDTO);

        return boutiqueRepository
            .findById(boutiqueDTO.getId())
            .map(existingBoutique -> {
                boutiqueMapper.partialUpdate(existingBoutique, boutiqueDTO);

                return existingBoutique;
            })
            .map(boutiqueRepository::save)
            .map(boutiqueMapper::toDto);
    }

    /**
     * Get all the boutiques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BoutiqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Boutiques");
        return boutiqueRepository.findAll(pageable).map(boutiqueMapper::toDto);
    }

    /**
     * Get one boutique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BoutiqueDTO> findOne(Long id) {
        log.debug("Request to get Boutique : {}", id);
        return boutiqueRepository.findById(id).map(boutiqueMapper::toDto);
    }

    /**
     * Delete the boutique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Boutique : {}", id);
        boutiqueRepository.deleteById(id);
    }
}
