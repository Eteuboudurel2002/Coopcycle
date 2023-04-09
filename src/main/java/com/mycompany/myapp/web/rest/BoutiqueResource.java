package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.BoutiqueRepository;
import com.mycompany.myapp.service.BoutiqueService;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Boutique}.
 */
@RestController
@RequestMapping("/api")
public class BoutiqueResource {

    private final Logger log = LoggerFactory.getLogger(BoutiqueResource.class);

    private static final String ENTITY_NAME = "boutique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoutiqueService boutiqueService;

    private final BoutiqueRepository boutiqueRepository;

    public BoutiqueResource(BoutiqueService boutiqueService, BoutiqueRepository boutiqueRepository) {
        this.boutiqueService = boutiqueService;
        this.boutiqueRepository = boutiqueRepository;
    }

    /**
     * {@code POST  /boutiques} : Create a new boutique.
     *
     * @param boutiqueDTO the boutiqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boutiqueDTO, or with status {@code 400 (Bad Request)} if the boutique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/boutiques")
    public ResponseEntity<BoutiqueDTO> createBoutique(@Valid @RequestBody BoutiqueDTO boutiqueDTO) throws URISyntaxException {
        log.debug("REST request to save Boutique : {}", boutiqueDTO);
        if (boutiqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new boutique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoutiqueDTO result = boutiqueService.save(boutiqueDTO);
        return ResponseEntity
            .created(new URI("/api/boutiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /boutiques/:id} : Updates an existing boutique.
     *
     * @param id the id of the boutiqueDTO to save.
     * @param boutiqueDTO the boutiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boutiqueDTO,
     * or with status {@code 400 (Bad Request)} if the boutiqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boutiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/boutiques/{id}")
    public ResponseEntity<BoutiqueDTO> updateBoutique(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BoutiqueDTO boutiqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Boutique : {}, {}", id, boutiqueDTO);
        if (boutiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boutiqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boutiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BoutiqueDTO result = boutiqueService.update(boutiqueDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boutiqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /boutiques/:id} : Partial updates given fields of an existing boutique, field will ignore if it is null
     *
     * @param id the id of the boutiqueDTO to save.
     * @param boutiqueDTO the boutiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boutiqueDTO,
     * or with status {@code 400 (Bad Request)} if the boutiqueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the boutiqueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the boutiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/boutiques/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BoutiqueDTO> partialUpdateBoutique(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BoutiqueDTO boutiqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Boutique partially : {}, {}", id, boutiqueDTO);
        if (boutiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boutiqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boutiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BoutiqueDTO> result = boutiqueService.partialUpdate(boutiqueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boutiqueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /boutiques} : get all the boutiques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boutiques in body.
     */
    @GetMapping("/boutiques")
    public ResponseEntity<List<BoutiqueDTO>> getAllBoutiques(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Boutiques");
        Page<BoutiqueDTO> page = boutiqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /boutiques/:id} : get the "id" boutique.
     *
     * @param id the id of the boutiqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boutiqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/boutiques/{id}")
    public ResponseEntity<BoutiqueDTO> getBoutique(@PathVariable Long id) {
        log.debug("REST request to get Boutique : {}", id);
        Optional<BoutiqueDTO> boutiqueDTO = boutiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boutiqueDTO);
    }

    /**
     * {@code DELETE  /boutiques/:id} : delete the "id" boutique.
     *
     * @param id the id of the boutiqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/boutiques/{id}")
    public ResponseEntity<Void> deleteBoutique(@PathVariable Long id) {
        log.debug("REST request to delete Boutique : {}", id);
        boutiqueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
