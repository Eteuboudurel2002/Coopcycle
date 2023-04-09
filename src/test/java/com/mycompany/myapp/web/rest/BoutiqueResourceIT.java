package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Boutique;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.repository.BoutiqueRepository;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.service.mapper.BoutiqueMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BoutiqueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BoutiqueResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/boutiques";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @Autowired
    private BoutiqueMapper boutiqueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBoutiqueMockMvc;

    private Boutique boutique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boutique createEntity(EntityManager em) {
        Boutique boutique = new Boutique()
            .name(DEFAULT_NAME)
            .activity(DEFAULT_ACTIVITY)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS);
        // Add required entity
        Utilisateur utilisateur;
        if (TestUtil.findAll(em, Utilisateur.class).isEmpty()) {
            utilisateur = UtilisateurResourceIT.createEntity(em);
            em.persist(utilisateur);
            em.flush();
        } else {
            utilisateur = TestUtil.findAll(em, Utilisateur.class).get(0);
        }
        boutique.setProprio(utilisateur);
        return boutique;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boutique createUpdatedEntity(EntityManager em) {
        Boutique boutique = new Boutique()
            .name(UPDATED_NAME)
            .activity(UPDATED_ACTIVITY)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS);
        // Add required entity
        Utilisateur utilisateur;
        if (TestUtil.findAll(em, Utilisateur.class).isEmpty()) {
            utilisateur = UtilisateurResourceIT.createUpdatedEntity(em);
            em.persist(utilisateur);
            em.flush();
        } else {
            utilisateur = TestUtil.findAll(em, Utilisateur.class).get(0);
        }
        boutique.setProprio(utilisateur);
        return boutique;
    }

    @BeforeEach
    public void initTest() {
        boutique = createEntity(em);
    }

    @Test
    @Transactional
    void createBoutique() throws Exception {
        int databaseSizeBeforeCreate = boutiqueRepository.findAll().size();
        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);
        restBoutiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isCreated());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeCreate + 1);
        Boutique testBoutique = boutiqueList.get(boutiqueList.size() - 1);
        assertThat(testBoutique.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBoutique.getActivity()).isEqualTo(DEFAULT_ACTIVITY);
        assertThat(testBoutique.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBoutique.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    void createBoutiqueWithExistingId() throws Exception {
        // Create the Boutique with an existing ID
        boutique.setId(1L);
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        int databaseSizeBeforeCreate = boutiqueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoutiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = boutiqueRepository.findAll().size();
        // set the field null
        boutique.setName(null);

        // Create the Boutique, which fails.
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        restBoutiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isBadRequest());

        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = boutiqueRepository.findAll().size();
        // set the field null
        boutique.setDescription(null);

        // Create the Boutique, which fails.
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        restBoutiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isBadRequest());

        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = boutiqueRepository.findAll().size();
        // set the field null
        boutique.setAddress(null);

        // Create the Boutique, which fails.
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        restBoutiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isBadRequest());

        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBoutiques() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        // Get all the boutiqueList
        restBoutiqueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boutique.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].activity").value(hasItem(DEFAULT_ACTIVITY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }

    @Test
    @Transactional
    void getBoutique() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        // Get the boutique
        restBoutiqueMockMvc
            .perform(get(ENTITY_API_URL_ID, boutique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(boutique.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.activity").value(DEFAULT_ACTIVITY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    void getNonExistingBoutique() throws Exception {
        // Get the boutique
        restBoutiqueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBoutique() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();

        // Update the boutique
        Boutique updatedBoutique = boutiqueRepository.findById(boutique.getId()).get();
        // Disconnect from session so that the updates on updatedBoutique are not directly saved in db
        em.detach(updatedBoutique);
        updatedBoutique.name(UPDATED_NAME).activity(UPDATED_ACTIVITY).description(UPDATED_DESCRIPTION).address(UPDATED_ADDRESS);
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(updatedBoutique);

        restBoutiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, boutiqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isOk());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
        Boutique testBoutique = boutiqueList.get(boutiqueList.size() - 1);
        assertThat(testBoutique.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBoutique.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testBoutique.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBoutique.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void putNonExistingBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, boutiqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boutiqueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBoutiqueWithPatch() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();

        // Update the boutique using partial update
        Boutique partialUpdatedBoutique = new Boutique();
        partialUpdatedBoutique.setId(boutique.getId());

        restBoutiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoutique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBoutique))
            )
            .andExpect(status().isOk());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
        Boutique testBoutique = boutiqueList.get(boutiqueList.size() - 1);
        assertThat(testBoutique.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBoutique.getActivity()).isEqualTo(DEFAULT_ACTIVITY);
        assertThat(testBoutique.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBoutique.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    void fullUpdateBoutiqueWithPatch() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();

        // Update the boutique using partial update
        Boutique partialUpdatedBoutique = new Boutique();
        partialUpdatedBoutique.setId(boutique.getId());

        partialUpdatedBoutique.name(UPDATED_NAME).activity(UPDATED_ACTIVITY).description(UPDATED_DESCRIPTION).address(UPDATED_ADDRESS);

        restBoutiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoutique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBoutique))
            )
            .andExpect(status().isOk());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
        Boutique testBoutique = boutiqueList.get(boutiqueList.size() - 1);
        assertThat(testBoutique.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBoutique.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testBoutique.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBoutique.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void patchNonExistingBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, boutiqueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBoutique() throws Exception {
        int databaseSizeBeforeUpdate = boutiqueRepository.findAll().size();
        boutique.setId(count.incrementAndGet());

        // Create the Boutique
        BoutiqueDTO boutiqueDTO = boutiqueMapper.toDto(boutique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoutiqueMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(boutiqueDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Boutique in the database
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBoutique() throws Exception {
        // Initialize the database
        boutiqueRepository.saveAndFlush(boutique);

        int databaseSizeBeforeDelete = boutiqueRepository.findAll().size();

        // Delete the boutique
        restBoutiqueMockMvc
            .perform(delete(ENTITY_API_URL_ID, boutique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Boutique> boutiqueList = boutiqueRepository.findAll();
        assertThat(boutiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
